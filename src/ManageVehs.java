import java.util.Scanner;

public class ManageVehs {
    public static void main(String args[]) throws StatusException, OdoMeterException {
        Scanner scan = new Scanner(System.in);
        Vehicle vehs[] = new Vehicle[6];

        vehs[0] = new Vehicle("QJT123", "Starlet 99", 35.0, 190000);
        vehs[1] = new PremiumVehicle("TUX132", "BMW 05", 90.0, 12000, 100, 10000, 5000);
        vehs[2] = new Vehicle("PTU121", "Holden 03", 60.0, 165000);
        vehs[3] = new Vehicle("OCD856", "Camry 04", 65.0, 230000);
        vehs[4] = new PremiumVehicle("TEY749", "Jaguar 06", 125.0, 27000, 120, 12000, 20000);
        vehs[5] = new Vehicle("TYR852", "Subaru 02", 60.0, 270000);

        char option;
        do {
            System.out.println("\tVehicle Menu");
            System.out.println("Display Available Cars \t1");
            System.out.println("Hire Vehicle \t\t2");
            System.out.println("Complete Hire \t\t3");
            System.out.println("Service Vehicle \t4");
            System.out.println("Complete Service \t5");
            System.out.println("Exit \t\t\t6");
            System.out.print("Your Choice : ");

            int choice = scan.nextInt();

            if (choice == 1) {
                for (int i = 0; i < vehs.length; i++) {

                    vehs[i].print();
                }
            }

            else if (choice == 2) {
                System.out.println("Enter Vehicle ID to be hired");
                String ID = scan.next();
                int i;
                for (i = 0; i < vehs.length; i++) {
                    if (vehs[i].getID().compareTo(ID) == 0 && vehs[i].getStatus() != 'H') {
                        System.out.println("Enter Hirer ID");
                        String hID = scan.next();
                        vehs[i].hire(hID);
                        System.out.println("\nCar " + vehs[i].getID() + " is now hired to " + vehs[i].getHirer());
                        break;
                    }
                }
                if (i == vehs.length) {
                    System.out.println("The vehicle " + ID + " does not exists or is not available for hire");
                }
            }

            else if (choice == 3) {
                System.out.println("Enter Vehicle ID to complete hire");
                String ID = scan.next();
                int i;
                for (i = 0; i < vehs.length; i++) {
                    if (vehs[i].getID().compareTo(ID) == 0 && vehs[i].getStatus() != 'H') {
                        System.out.println("Enter current Odometer Reading");
                        int odo = scan.nextInt();
                        double charges = vehs[i].hireComplete(odo);
                        System.out.println("Hire Completed. \nCharges=" + charges);
                        break;
                    }
                }
                if (i == vehs.length) {
                    System.out.println("The vehicle " + ID + " does not exists or is not available for hire");
                }
            }

            else if (choice == 4) {
                System.out.println("Enter Vehicle ID to be given for service");
                String ID = scan.next();
                int i;
                for (i = 0; i < vehs.length; i++) {
                    if (vehs[i].getID().compareTo(ID) == 0 && vehs[i].getStatus() == 'A') {
                        vehs[i].service();
                        System.out.println("Car " + vehs[i].getID() + " is now given for servicing.");
                        break;
                    }
                }
                if (i == vehs.length) {
                    System.out.println("The vehicle " + ID + " does not exists or is not available for service");
                }
            }

            else if (choice == 5) {
                System.out.println("Enter Vehicle ID to complete the service");
                String ID = scan.next();
                int i;
                for (i = 0; i < vehs.length; i++) {
                    if (vehs[i].getID().compareTo(ID) == 0 && vehs[i].getStatus() == 'S') {
                        System.out.println("Enter current Odometer Reading");
                        int odo = scan.nextInt();
                        vehs[i].serviceComplete(odo);
                        System.out.println("Service is completed for Car " + vehs[i].getID());
                        break;
                    }
                }

                if (i == vehs.length) {
                    System.out.println(
                            "The vehicle " + ID + " does not exists or is not available for completing service");
                }
            }

            else if (choice == 6) {
                break;
            }

            System.out.println("Do you want to continue? Y/N");
            option = scan.next().charAt(0);
        } while (option != 'N');
        scan.close();
    }
}
