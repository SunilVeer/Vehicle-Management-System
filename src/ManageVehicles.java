import java.util.Scanner;

public class ManageVehicles {
    public static void main(String args[]) throws StatusException, OdoMeterException {
        Scanner scan = new Scanner(System.in);

        // 1
        Vehicle vehs[] = new Vehicle[5];
        vehs[0] = new Vehicle("SAM134", "Toyota Camry 02", 45.00, 140000);
        vehs[1] = new Vehicle("QKO156", "Honda Accord 04", 65.0, 125000);
        vehs[2] = new Vehicle("TUV178", "Toyota Starlet 02", 35.00, 180000);
        vehs[3] = new Vehicle("SAG132", "Toyota Avalon 05", 52.0, 190000);
        vehs[4] = new Vehicle("PRE342", "Honda Civic 97", 35.00, 145000);

        // 2
        for (int i = 0; i < vehs.length; i++) {
            System.out.println("ID=" + vehs[i].getID() + "\tDescription=" + vehs[i].getDescription());
        }

        // 3
        System.out.println("Enter Lower Limit for daily rate");
        double lower = scan.nextDouble();
        System.out.println("Enter Upper Limit for daily rate");
        double upper = scan.nextDouble();

        int counter = 0;
        for (int i = 0; i < vehs.length; i++) {
            if (vehs[i].getDailyRate() >= lower && vehs[i].getDailyRate() <= upper) {
                counter++;
                System.out.println("ID=" + vehs[i].getID() + "\tDescription=" + vehs[i].getDescription()
                        + "\tDaily Rate=" + vehs[i].getDailyRate());
            }
        }
        if (counter == 0) {
            System.out.println("No vehicles in the specified range");
        }

        // 4&5
        char trans;
        do {
            int i = 0;
            System.out.println("Enter the vehicle ID");
            String ID = scan.nextLine();
            for (i = 0; i < vehs.length; i++) {
                if (vehs[i].getID().compareTo(ID) == 0) {
                    System.out.println("What operation do you want to perform?");
                    System.out.println("Enter 1 for hire");
                    System.out.println("Enter 2 for service");
                    System.out.println("Enter 3 for service-complete");
                    System.out.println("Enter 4 for hire-complete");
                    int input = scan.nextInt();
                    if (input == 1) {
                        System.out.println("Enter hirer ID");
                        String HirerID = scan.next();
                        vehs[i].hire(HirerID);
                        break;
                    } else if (input == 2) {
                        vehs[i].service();
                        break;
                    } else if (input == 3) {
                        System.out.println("Enter Odometer Reading");
                        int OdoM = scan.nextInt();
                        vehs[i].serviceComplete(OdoM);
                        break;
                    } else if (input == 4) {
                        System.out.println("Enter Odometer Reading");
                        int OdoM = scan.nextInt();
                        vehs[i].hireComplete(OdoM);
                        break;
                    } else {
                        System.out.println("Selected option is incorrect");
                    }

                }
            }

            if (i == vehs.length) {
                System.out.println("Specified ID not matched");
            }

            System.out.println("Any more transactions? Y/N");
            trans = scan.nextLine().charAt(0);
        } while (trans != 'N');

        // 6
        for (int i = 0; i < vehs.length; i++) {
            vehs[i].print();
        }

        scan.close();
    }
}
