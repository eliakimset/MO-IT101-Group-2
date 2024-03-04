package motorphemployee;

import java.util.Scanner;

public class MotorPHEmployee {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        Employee employee = new Employee();
        
        Header();
        
        char choice;
        do { 
              // Continuously prompt for valid employee number
        int x = -1; // Initialize with an invalid value
        while (x < 1 || x > 34) {
            System.out.print("Enter Employee Number (1-34): ");
            x = scan.nextInt();
            if (x < 1 || x > 34) {
                System.out.println("Invalid input. Please enter a number between 1 and 34.");
            }
        }

        int index = x - 1;

        if (x >= 1 && x <= 34) {
            // Display employee information
            System.out.println("==========================================");
            System.out.println("Employee Number: " + x);
            System.out.println("Name: " + employee.employeeName[index]);
            System.out.println("Employee Birthday: " + employee.Birthday[index]);
            System.out.println("Phone number: " + employee.PhoneNumber[index]);
            System.out.println("Address: " + employee.address[index]);
            System.out.println("SSS #: " + employee.SSS[index]);
            System.out.println("Philhealth #: " + employee.Philhealth[index]);
            System.out.println("TIN #: " + employee.TIN[index]);
            System.out.println("Pagibig #: " + employee.Pagibig[index]);
            System.out.println("Status: " + employee.Status[index]);
            System.out.println("Position: " + employee.Position[index]);
            System.out.println("Immediate Supervisor: " + employee.Supervisor[index]);
            System.out.println("Basic Salary: " + employee.Salary[index]);
            System.out.println("Rice Subsidy: " + employee.RiceSubsidy[index]);
            System.out.println("Phone Allowance: " + employee.PhoneAllowance[index]);
            System.out.println("Clothing Allowance: " + employee.ClothingAllowance[index]);
            System.out.println("Gross Semi-monthly Rate: " + employee.Gross_SemiMonthlyRate[index]);
            System.out.println("Hourly Rate: " + employee.HourlyRate[index]);
            System.out.println("==========================================");

            // Capture number of days the employee will work
            System.out.print("Enter number of days employee will work: ");
            int daysWorked = scan.nextInt();

            int presentCounter = 0;
            int absentCounter = 0;
            int totalLate = 0;
            int overtimeCounter = 0;
            double totalWorkedHours = 0;
            double totalSalary = 0;

            // Capture time in and out for each day
            for (int i = 0; i < daysWorked; i++) {
                System.out.print("Enter time in for day " + (i + 1) + " (HH:MM): ");
                String timeIn = scan.next();
                System.out.print("Enter time out for day " + (i + 1) + " (HH:MM): ");
                String timeOut = scan.next();

                // Parse time in and out to calculate worked hours
                int timeInHours = Integer.parseInt(timeIn.split(":")[0]);
                int timeOutHours = Integer.parseInt(timeOut.split(":")[0]);
                int timeInMinutes = Integer.parseInt(timeIn.split(":")[1]);
                int timeOutMinutes = Integer.parseInt(timeOut.split(":")[1]);
                int timeInConvMinutes = (timeInHours * 60) + timeInMinutes;
                int timeOutConvMinutes = (timeOutHours * 60) + timeOutMinutes;

                // Calculate total worked hours
                double workedHours = (timeOutHours - timeInHours);

                // Grace Period
                if (timeInMinutes > 10 && timeInHours == 8) {
                    workedHours--;
                }

                // 9AM beyond
                if (timeInConvMinutes > 540) {
                    workedHours--;
                }

                // Late Counter
                if (timeInConvMinutes > 490) {
                    totalLate++;
                    System.out.println("Status: Late");
                }

                // Absent Counter
                if (timeOutConvMinutes == 0 && timeInConvMinutes == 0) {
                    absentCounter++;
                    System.out.println("Status: Absent");
                } else {
                    presentCounter++;
                    System.out.println("Status: Present");
                }

                // Overtime Counter
                if (workedHours > 9){
                    overtimeCounter++;
                }

                // Break Time
                if (workedHours > 4) { // Worked for more than 2 hours
                    workedHours--;
                }

                // Calculate the salary for the day based on the worked hours
                double dailySalary = workedHours * employee.HourlyRate[index];

                // Adding daily salary/hours
                totalSalary += dailySalary;
                totalWorkedHours += workedHours;

                System.out.println("Total Worked Hours (Day " + (i + 1) + "): " + workedHours);
                System.out.println("Daily Salary (Day " + (i + 1) + "): " + dailySalary);
            }
                
            
            System.out.println("Do you want to create Payslip? (Y/N)");

                String response = scan.next();

                if (response.equalsIgnoreCase("Y")){
                    
                } else if (response.equalsIgnoreCase("N")) {
                    System.out.println("Exiting creating payslip.");
                    return;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }

                // SSS Contribution Declaration
                double sssContribution = calculateSSSContribution(totalSalary);

                // PhilHealth Contribution Delcaration
                double philHealthContribution = calculatePhilHealthContribution(totalSalary);

                // PagIbig Contribution Declaration
                double pagibigContribution = calculatePagibigContribution(totalSalary);

                //Payslip
                System.out.println("-------------------------------------------");
                System.out.println("MOTORPH EMPLOYEE PAYSLIP");
                System.out.println("-------------------------------------------");
                System.out.println("EMPLOYEE INFORMATION");
                System.out.println();
                System.out.println("Employee Number: " + x);
                System.out.println("Name: " + employee.employeeName[index]);
                System.out.println("Hourly Rate: " + employee.HourlyRate[index]);
                System.out.println("-------------------------------------------");
                System.out.println("ATTENDANCE");
                System.out.println();
                System.out.println("Total Working Days: " + daysWorked);
                System.out.println("Total Present: " + presentCounter);
                System.out.println("Total Absent: " + absentCounter);
                System.out.println("Total Late: " + totalLate);
                System.out.println("Total Overtime: " + overtimeCounter);
                System.out.println("Total Worked Hours: " + totalWorkedHours);
                System.out.println();
                System.out.println("Gross Pay: " + totalSalary);
                System.out.println("-------------------------------------------");
                System.out.println("BENEFITS");
                System.out.println();
                System.out.println("Rice Subsidy: " + employee.RiceSubsidy[index]);
                System.out.println("Phone Allowance: " + employee.PhoneAllowance[index]  );
                System.out.println("Clothing Allowance: " + employee.ClothingAllowance[index]);
                System.out.println("-------------------------------------------");
                System.out.println("GOVERNMENT DEDUCTION");
                System.out.println();
                System.out.println("SSS Contribution :" + sssContribution);
                System.out.println("PhilHealth Contribution :" + philHealthContribution);
                System.out.println("PagIbig Contribution :" + pagibigContribution);
                double governmentTax = (sssContribution + philHealthContribution + pagibigContribution);
                System.out.println();
                System.out.println("Total Contribution: " + governmentTax);
                System.out.println("-------------------------------------------");
                double grossPay = totalSalary - governmentTax;
                
                // Witholding Tax Declaration
                double withholdingTax = calculateWithholdingTax(grossPay);
                System.out.println("WITHHOLDING TAX");
                System.out.println();
                System.out.println("Wthholding Tax: " + withholdingTax);
                printCalculation(grossPay);

                System.out.println("-------------------------------------------");
                double netPay = grossPay - withholdingTax;
                System.out.println("Net Pay: " + netPay);
                System.out.println("-------------------------------------------");
    }
                System.out.println("Do you want to search again? Y/N");
                choice = scan.next().charAt(0);
                clearOutputWindow();
}               while(choice == 'Y'|choice == 'y');
    }
    
    public static class Employee {
    // Personal Information
    String[] employeeName = {"Garcia, Manuel III",
            "Lim, Antonio",
            "Aquino, Bianca Sofia",
            "Reyes, Isabella",
            "Hernandez, Eduard",
            "Villanueva, Andrea Mae",
            "San Jose, Brad",
            "Romualdez, Alice",
            "Atienza, Rosie",
            "Alvaro, Roderick",
            "Salcedo, Anthony",
            "Lopez, Josie",
            "Farala, Martha",
            "Martinez, Leila",
            "Romualdez, Fredrick",
            "Mata, Christian",
            "De Leon, Selena",
            "San Jose, Allison",
            "Rosario, Cydney",
            "Bautista, Mark",
            "Lazaro, Darlene",
            "Delos Santos, Kolby",
            "Santos, Vella",
            "Del Rosario, Tomas",
            "Tolentino, Jacklyn",
            "Gutierrez, Percival",
            "Manalaysay, Garfield",
            "Villegas, Lizeth",
            "Ramos, Carol",
            "Marceda, Emelia",
            "Aguilar, Delia",
            "Castro, John Rafael",
            "Martinez, Carlos Ian",
            "Santos, Beatriz"};

    String[] Birthday = {"10/11/1983",
            "06/19/1988",
            "08/04/1989",
            "06/16/1994",
            "09/23/1989",
            "02/14/1988",
            "03/15/1996",
            "05/14/1992",
            "09/24/1948",
            "03/30/1988",
            "09/14/1993",
            "01/14/1987",
            "01/11/1942",
            "07/11/1970",
            "03/10/1985",
            "10/21/1987",
            "02/20/1975",
            "06/24/1986",
            "10/06/1996",
            "02/12/1991",
            "11/25/1985",
            "02/26/1980",
            "12/31/1983",
            "12/18/1978",
            "05/19/1984",
            "12/18/1970",
            "08/28/1986",
            "12/12/1981",
            "08/20/1978",
            "04/14/1973",
            "01/27/1989",
            "02/09/1992",
            "11/16/1990",
            "08/07/1990"};

    String[] address = {"Valero Carpark Building Valero Street 1227, Makati City",
            "San Antonio De Padua 2, Block 1 Lot 8 and 2, Dasmarinas, Cavite",
            "Rm. 402 4/F Jiao Building Timog Avenue Cor. Quezon Avenue 1100, Quezon City",
            "460 Solanda Street Intramuros 1000, Manila",
            "National Highway, Gingoog,  Misamis Occidental",
            "17/85 Stracke Via Suite 042, Poblacion, Las Piñas 4783 Dinagat Islands",
            "99 Strosin Hills, Poblacion, Bislig 5340 Tawi-Tawi",
            "12A/33 Upton Isle Apt. 420, Roxas City 1814 Surigao del Norte",
            "90A Dibbert Terrace Apt. 190, San Lorenzo 6056 Davao del Norte",
            "#284 T. Morato corner, Scout Rallos Street, Quezon City",
            "93/54 Shanahan Alley Apt. 183, Santo Tomas 1572 Masbate",
            "49 Springs Apt. 266, Poblacion, Taguig 3200 Occidental Mindoro",
            "42/25 Sawayn Stream, Ubay 1208 Zamboanga del Norte",
            "37/46 Kulas Roads, Maragondon 0962 Quirino",
            "22A/52 Lubowitz Meadows, Pililla 4895 Zambales",
            "90 O'Keefe Spur Apt. 379, Catigbian 2772 Sulu",
            "89A Armstrong Trace, Compostela 7874 Maguindanao",
            "08 Grant Drive Suite 406, Poblacion, Iloilo City 9186 La Union",
            "93A/21 Berge Points, Tapaz 2180 Quezon",
            "65 Murphy Center Suite 094, Poblacion, Palayan 5636 Quirino",
            "47A/94 Larkin Plaza Apt. 179, Poblacion, Caloocan 2751 Quirino",
            "06A Gulgowski Extensions, Bongabon 6085 Zamboanga del Sur",
            "99A Padberg Spring, Poblacion, Mabalacat 3959 Lanao del Sur",
            "80A/48 Ledner Ridges, Poblacion, Kabankalan 8870 Marinduque",
            "96/48 Watsica Flats Suite 734, Poblacion, Malolos 1844 Ifugao",
            "58A Wilderman Walks, Poblacion, Digos 5822 Davao del Sur",
            "60 Goyette Valley Suite 219, Poblacion, Tabuk 3159 Lanao del Sur",
            "66/77 Mann Views, Luisiana 1263 Dinagat Islands",
            "72/70 Stamm Spurs, Bustos 4550 Iloilo",
            "50A/83 Bahringer Oval Suite 145, Kiamba 7688 Nueva Ecija",
            "95 Cremin Junction, Surallah 2809 Cotabato",
            "Hi-way, Yati, Liloan Cebu",
            "Bulala, Camalaniugan",
            "Agapita Building, Metro Manila"};

    String[] PhoneNumber = {"966-860-270",
            "171-867-411",
            "966-889-370",
            "786-868-477",
            "088-861-012",
            "918-621-603",
            "797-009-261",
            "983-606-799",
            "266-036-427",
            "053-381-386",
            "070-766-300",
            "478-355-427",
            "329-034-366",
            "877-110-749",
            "023-079-009",
            "783-776-744",
            "975-432-139",
            "179-075-129",
            "868-819-912",
            "683-725-348",
            "740-721-558",
            "739-443-033",
            "955-879-269",
            "882-550-989",
            "675-757-366",
            "512-899-876",
            "948-628-136",
            "332-372-215",
            "250-700-389",
            "973-358-041",
            "529-705-439",
            "332-424-955",
            "078-854-208",
            "526-639-511"};

    // Government IDs
    String[] SSS = {"44-4506057-3",
            "52-2061274-9",
            "30-8870406-2",
            "40-2511815-0",
            "50-5577638-1",
            "49-1632020-8",
            "40-2400714-1",
            "55-4476527-2",
            "41-0644692-3",
            "64-7605054-4",
            "26-9647608-3",
            "44-8563448-3",
            "45-5656375-0",
            "27-2090996-4",
            "26-8768374-1",
            "49-2959312-6",
            "27-2090208-8",
            "45-3251383-0",
            "49-1629900-2",
            "49-1647342-5",
            "45-5617168-2",
            "52-0109570-6",
            "52-9883524-3",
            "45-5866331-6",
            "47-1692793-0",
            "40-9504657-8",
            "45-3298166-4",
            "40-2400719-4",
            "60-1152206-4",
            "54-1331005-0",
            "52-1859253-1",
            "26-7145133-4",
            "11-5062972-7",
            "20-2987501-5"};

    String[] Philhealth = {"820126853951",
            "331735646338",
            "177451189665",
            "341911411254",
            "957436191812",
            "382189453145",
            "239192926939",
            "545652640232",
            "708988234853",
            "578114853194",
            "126445315651",
            "431709011012",
            "233693897247",
            "515741057496",
            "308366860059",
            "824187961962",
            "587272469938",
            "745148459521",
            "579253435499",
            "399665157135",
            "606386917510",
            "357451271274",
            "548670482885",
            "953901539995",
            "753800654114",
            "797639382265",
            "810909286264",
            "934389652994",
            "351830469744",
            "465087894112",
            "136451303068",
            "601644902402",
            "380685387212",
            "918460050077"};

    String[] TIN = {"442-605-657-000",
            "683-102-776-000",
            "971-711-280-000",
            "876-809-437-000",
            "031-702-374-000",
            "317-674-022-000",
            "672-474-690-000",
            "888-572-294-000",
            "604-997-793-000",
            "525-420-419-000",
            "210-805-911-000",
            "218-489-737-000",
            "210-835-851-000",
            "275-792-513-000",
            "598-065-761-000",
            "103-100-522-000",
            "482-259-498-000",
            "121-203-336-000",
            "122-244-511-000",
            "273-970-941-000",
            "354-650-951-000",
            "187-500-345-000",
            "101-558-994-000",
            "560-735-732-000",
            "841-177-857-000",
            "502-995-671-000",
            "336-676-445-000",
            "210-395-397-000",
            "395-032-717-000",
            "215-973-013-000",
            "599-312-588-000",
            "404-768-309-000",
            "256-436-296-000",
            "911-529-713-000"};

    String[] Pagibig = {"691295330870",
            "663904995411",
            "171519773969",
            "416946776041",
            "952347222457",
            "441093369646",
            "210850209964",
            "211385556888",
            "260107732354",
            "799254095212",
            "218002473454",
            "113071293354",
            "631130283546",
            "101205445886",
            "223057707853",
            "631052853464",
            "719007608464",
            "114901859343",
            "265104358643",
            "260054585575",
            "104907708845",
            "113017988667",
            "360028104576",
            "913108649964",
            "210546661243",
            "210897095686",
            "211274476563",
            "122238077997",
            "212141893454",
            "515012579765",
            "110018813465",
            "697764069311",
            "993372963726",
            "874042259378"};

    // Employee Details

    String[] Status = {"Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Regular",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Probationary",
            "Regular",
            "Regular",
            "Regular"};

    String[] Position = {"Chief Executive Officer",
            "Chief Operating Officer",
            "Chief Finance Officer",
            "Chief Marketing Officer",
            "IT Operations and Systems",
            "HR Manager",
            "HR Team Leader",
            "HR Rank and File",
            "HR Rank and File",
            "Accounting Head",
            "Payroll Manager",
            "Payroll Team Leader",
            "Payroll Rank and File",
            "Payroll Rank and File",
            "Account Manager",
            "Account Team Leader",
            "Account Team Leader",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Account Rank and File",
            "Sales & Marketing",
            "Supply Chain and Logistics",
            "Customer Service and Relations"};

    String[] Supervisor = {"N/A",
            "Garcia, Manuel III",
            "Garcia, Manuel III",
            "Garcia, Manuel III",
            "Lim, Antonio",
            "Lim, Antonio",
            "Villanueva, Andrea Mae",
            "San, Jose Brad",
            "San, Jose Brad",
            "Aquino, Bianca Sofia",
            "Alvaro, Roderick",
            "Salcedo, Anthony",
            "Salcedo, Anthony",
            "Salcedo, Anthony",
            "Lim, Antonio",
            "Romualdez, Fredrick",
            "Romualdez, Fredrick",
            "Mata, Christian",
            "Mata, Christian",
            "Mata, Christian",
            "Mata, Christian",
            "Mata, Christian",
            "Mata, Christian",
            "Mata, Christian",
            "De Leon, Selena",
            "De Leon, Selena",
            "De Leon, Selena",
            "De Leon, Selena",
            "De Leon, Selena",
            "De Leon, Selena",
            "De Leon, Selena",
            "Reyes, Isabella",
            "Reyes, Isabella",
            "Reyes, Isabella"};

    //Salary Information
    int[] Salary = {90000,
            60000,
            60000,
            60000,
            52670,
            52670,
            42975,
            22500,
            22500,
            52670,
            50825,
            38475,
            24000,
            24000,
            53500,
            42975,
            41850,
            22500,
            22500,
            23250,
            23250,
            24000,
            22500,
            22500,
            24000,
            24750,
            24750,
            24000,
            22500,
            22500,
            22500,
            52670,
            52670,
            52670};

    int[] RiceSubsidy = {1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500,
            1500};

    int[] PhoneAllowance = {2000,
            2000,
            2000,
            2000,
            1000,
            1000,
            800,
            500,
            500,
            1000,
            1000,
            800,
            500,
            500,
            1000,
            800,
            800,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            1000,
            1000,
            1000,
            1000};

    int[] ClothingAllowance = {1000,
            1000,
            1000,
            1000,
            1000,
            1000,
            800,
            500,
            500,
            1000,
            1000,
            800,
            500,
            500,
            1000,
            800,
            800,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            500,
            1000,
            1000,
            1000};

    int[] Gross_SemiMonthlyRate = {45000,
            30000,
            30000,
            30000,
            26335,
            26335,
            21488,
            11250,
            11250,
            26335,
            25413,
            19238,
            12000,
            12000,
            26750,
            21488,
            20925,
            11250,
            11250,
            11625,
            11625,
            12000,
            11250,
            11250,
            12000,
            12375,
            12375,
            12000,
            11250,
            11250,
            11250,
            26335,
            26335,
            26335};

    double[] HourlyRate = {535.71,
            357.14,
            357.14,
            357.14,
            313.51,
            313.51,
            255.80,
            133.93,
            133.93,
            313.51,
            302.53,
            229.02,
            142.86,
            142.86,
            318.45,
            255.80,
            249.11,
            133.93,
            133.93,
            138.39,
            138.39,
            142.86,
            133.93,
            133.93,
            142.86,
            147.32,
            147.32,
            142.86,
            133.93,
            133.93,
            133.93,
            313.51,
            313.51,
            313.51};
    }


            public static double calculateSSSContribution(double totalSalary) {
                double sssContribution = 0;
                
                if (totalSalary <= 3250) {
                    sssContribution = 135;
                } else if (totalSalary <= 3750) {
                    sssContribution = 157.50;
                } else if (totalSalary <= 4250) {
                    sssContribution = 180;
                } else if (totalSalary <= 4750) {
                    sssContribution = 202.50;
                } else if (totalSalary <= 5250) {
                    sssContribution = 225;
                } else if (totalSalary <= 5750) {
                    sssContribution = 247.50;
                } else if (totalSalary <= 6250) {
                    sssContribution = 270;
                } else if (totalSalary <= 6750) {
                    sssContribution = 292.50;
                } else if (totalSalary <= 7250) {
                    sssContribution = 315;
                } else if (totalSalary <= 7750) {
                    sssContribution = 337.50;
                } else if (totalSalary <= 8250) {
                    sssContribution = 360;
                } else if (totalSalary <= 8750) {
                    sssContribution = 382.50;
                } else if (totalSalary <= 9250) {
                    sssContribution = 405;
                } else if (totalSalary <= 9750) {
                    sssContribution = 427.50;
                } else if (totalSalary <= 10250) {
                    sssContribution = 450;
                } else if (totalSalary <= 10750) {
                    sssContribution = 472.50;
                } else if (totalSalary <= 11250) {
                    sssContribution = 495;
                } else if (totalSalary <= 11750) {
                    sssContribution = 517.50;
                } else if (totalSalary <= 12250) {
                    sssContribution = 540;
                } else if (totalSalary <= 12750) {
                    sssContribution = 562.50;
                } else if (totalSalary <= 13250) {
                    sssContribution = 585;
                } else if (totalSalary <= 13750) {
                    sssContribution = 607.50;
                } else if (totalSalary <= 14250) {
                    sssContribution = 630;
                } else if (totalSalary <= 14750) {
                    sssContribution = 652.50;
                } else if (totalSalary <= 15250) {
                    sssContribution = 675;
                } else if (totalSalary <= 15750) {
                    sssContribution = 697.50;
                } else if (totalSalary <= 16250) {
                    sssContribution = 720;
                } else if (totalSalary <= 16750) {
                    sssContribution = 742.50;
                } else if (totalSalary <= 17250) {
                    sssContribution = 765;
                } else if (totalSalary <= 17750) {
                    sssContribution = 787.50;
                } else if (totalSalary <= 18250) {
                    sssContribution = 810;
                } else if (totalSalary <= 18750) {
                    sssContribution = 832.50;
                } else if (totalSalary <= 19250) {
                    sssContribution = 855;
                } else if (totalSalary <= 19750) {
                    sssContribution = 877.50;
                } else if (totalSalary <= 20250) {
                    sssContribution = 900;
                } else if (totalSalary <= 20750) {
                    sssContribution = 922.50;
                } else if (totalSalary <= 21250) {
                    sssContribution = 945;
                } else if (totalSalary <= 21750) {
                    sssContribution = 967.50;
                } else if (totalSalary <= 22250) {
                    sssContribution = 990;
                } else if (totalSalary <= 22750) {
                    sssContribution = 1012.50;
                } else if (totalSalary <= 23250) {
                    sssContribution = 1035;
                } else if (totalSalary <= 23750) {
                    sssContribution = 1057.50;
                } else if (totalSalary <= 24250) {
                    sssContribution = 1080;
                } else if (totalSalary <= 24750) {
                    sssContribution = 1102.50;
                } else {
                    sssContribution = 1125;
                }
            
                return sssContribution;
            }

            public static double calculatePhilHealthContribution(double totalSalary) {
                double premiumRate = 0.03; // 3% Premium Rate
        
                // Calculate monthly premium based on salary range
                double monthlyPremium;
                if (totalSalary <= 10000) {
                    monthlyPremium = 300;
                } else if (totalSalary <= 59999.99) {
                    monthlyPremium = Math.min(300 + (totalSalary - 10000) * premiumRate, 1800);
                } else {
                    monthlyPremium = 1800;
                }
        
                // Employee and employer share equally
                return monthlyPremium;
            }

            public static double calculatePagibigContribution(double totalSalary) {
                double employeeContributionRate = 0;
                double employerContributionRate = 0;
                double totalContributionRate = 0;
        
                if (totalSalary >= 1000 && totalSalary <= 1500) {
                    employeeContributionRate = 0.01;
                    employerContributionRate = 0.02;
                    totalContributionRate = employeeContributionRate + employerContributionRate;
                } else if (totalSalary > 1500) {
                    employeeContributionRate = 0.02;
                    employerContributionRate = 0.02;
                    totalContributionRate = employeeContributionRate + employerContributionRate;
                }
        
                // Calculate the contribution amount
                double totalContribution = Math.min(totalSalary * totalContributionRate, 100); // Maximum contribution is 100
        
                return totalContribution;
            }

            public static double calculateWithholdingTax(double grossPay) {
                double withholdingTax = 0;
        
                if (grossPay <= 20832) {
                    withholdingTax = 0;
                } else if (grossPay <= 33333) {
                    withholdingTax = (grossPay - 20832) * 0.20;
                } else if (grossPay <= 66667) {
                    withholdingTax = 2500 + (grossPay - 33333) * 0.25;
                } else if (grossPay <= 166667) {
                    withholdingTax = 10833 + (grossPay - 66667) * 0.30;
                } else if (grossPay <= 666667) {
                    withholdingTax = 40833.33 + (grossPay - 166667) * 0.32;
                } else {
                    withholdingTax = 200833.33 + (grossPay - 666667) * 0.35;
                }
        
                return withholdingTax;
            }

            public static void printCalculation(double grossPay) {
                if (grossPay > 20832 && grossPay <= 33333) {
                    double excessAmount = grossPay - 20832;
                    double calculation = excessAmount * 0.20;
                    System.out.println("Calculation (20% in excess of 20,833): " + excessAmount + " * 20% = " + calculation);
                } else if (grossPay > 33333 && grossPay <= 66667) {
                    double excessAmount = grossPay - 33333;
                    double calculation = excessAmount * 0.25;
                    System.out.println("Calculation (25% in excess of 33,333): " + excessAmount + " * 25% = " + calculation);
                } else if (grossPay > 66667 && grossPay <= 166667) {
                    double excessAmount = grossPay - 66667;
                    double calculation = excessAmount * 0.30;
                    System.out.println("Calculation (30% in excess of 66,667): " + excessAmount + " * 30% = " + calculation);
                } else if (grossPay > 166667 && grossPay <= 666667) {
                    double excessAmount = grossPay - 166667;
                    double calculation = excessAmount * 0.32;
                    System.out.println("Calculation (32% in excess of 166,667): " + excessAmount + " * 32% = " + calculation);
                } else if (grossPay > 666667) {
                    double excessAmount = grossPay - 666667;
                    double calculation = excessAmount * 0.35;
                    System.out.println("Calculation (35% in excess of 666,667): " + excessAmount + " * 35% = " + calculation);
                }
            }
            public static void clearOutputWindow() {
             for (int i = 0; i < 50; i++) {
            System.out.println();
    }
            }
            public static void Header() {
                            System.out.println("\t\t/========================================\\");
                System.out.println("\t\t||   __  ___     __           ___  __ __||");
                System.out.println("\t\t||  /  |/  /__  / /____  ____/ _ \\/ // /||");
                System.out.println("\t\t|| / /|_/ / _ \\/ __/ _ \\/ __/ ___/ _  / ||");
                System.out.println("\t\t||/_/  /_/\\___/\\__/\\___/_/ /_/  /_//_/  ||");
                System.out.println("\t\t\\========================================/");
                                        
                System.out.println("\t\t*******************************************");
                System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
                System.out.println("\t\t*******************************************");
                System.out.println("\t\t\t    --------------------");
                System.out.println("\t\t\t           Group 2");
                System.out.println("\t\t\t    --------------------");
            }     
}