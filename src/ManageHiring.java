import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ManageHiring implements Serializable {
    private String ID;
    Scanner scan = new Scanner(System.in);
    static ArrayList<Vehicle> vehs = new ArrayList<Vehicle>();
    static ArrayList<PremiumVehicle> pvehs = new ArrayList<PremiumVehicle>();
    static ArrayList<ICustomer> icust = new ArrayList<ICustomer>();
    static ArrayList<CCustomer> ccust = new ArrayList<CCustomer>();
    static ArrayList<Report> report = new ArrayList<Report>();

    public static void main(String[] args) throws IOException, StatusException, OdoMeterException, VehicleNotFound,
            CustomerNotFound, ParseException, ClassNotFoundException {
        Scanner scan = new Scanner(System.in);
        ManageHiring mh = new ManageHiring();
        char opt1;
        loadData(report);
        do {

            System.out.println("Enter your choice \n " + "1 : Add Vehicle \n " + "2 : Add Customer \n "
                    + "3 : Display details of available vehicles in the specified range \n " + "4 : Hire a vehicle \n "
                    + "5 : Complete hire of vehicle \n " + "6 : Service/service complete \n "
                    + "7 : Rental income in specified range \n " + "8 : To set date advance \n " + "9 : Exit \n"
                    + "Enter your choice : ");
            int value = scan.nextInt();

            switch (value) {
                case 1:
                    mh.addVehicle();
                    break;

                case 2:
                    mh.addCustomer();
                    break;

                case 3:
                    try {
                        mh.display();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        mh.hireAVehicle();
                    } catch (StatusException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        mh.hireCompleted();
                    } catch (StatusException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (OdoMeterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    try {
                        mh.service();
                    } catch (StatusException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (OdoMeterException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    mh.rentalIncome();
                    break;

                case 8:
                    mh.setAdvance();
                    break;

                case 9:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;
            }

            System.out.println("Do you want to continue? Y/N");
            opt1 = scan.next().toUpperCase().charAt(0);

        } while (opt1 != 'N');
        scan.close();
        saveData();
    }

    // method to save arraylist to files
    // save arrayLists to files
    private static void saveData() {
        try {
            // a text file is needed to save the data, open vehicle.txt to save the data
            PrintWriter pw = new PrintWriter(new File("vehicle.txt"));

            for (Vehicle vh1 : vehs) {
                pw.println(vh1.getClass().getSimpleName());

                pw.println(vh1.getID());
                pw.println(vh1.getDescription());
                pw.println(vh1.getDailyRate());
                pw.println(vh1.getOdometer());
                pw.println(vh1.getStatus());
                if (vh1.getStatus() == 'H') {
                    pw.println(vh1.getHirer());
                } else {
                    pw.println("");
                }
            }

            for (PremiumVehicle pvh1 : pvehs) {
                pw.println(pvh1.getClass().getSimpleName());

                pw.println(pvh1.getID());
                pw.println(pvh1.getDescription());
                pw.println(pvh1.getDailyRate());
                pw.println(pvh1.getOdometer());
                pw.println(pvh1.getStatus());
                if (pvh1.getStatus() == 'H') {
                    pw.println(pvh1.getHirer());
                } else {
                    pw.println("");
                }
                pw.println(pvh1.getDailyMileage());
                pw.println(pvh1.getServiceLength());
                pw.println(pvh1.getLastService());
            }
            pw.close();

            PrintWriter pw1 = new PrintWriter(new File("customer.txt"));

            for (ICustomer icust1 : icust) {
                pw1.println(icust1.getClass().getSimpleName());

                pw1.println(icust1.getCustomerID());
                pw1.println(icust1.getName());
                pw1.println(icust1.getPhoneNO());
                pw1.println(icust1.getPastMileage());
            }

            for (CCustomer ccust1 : ccust) {
                pw1.println(ccust1.getClass().getSimpleName());

                pw1.println(ccust1.getCustomerID());
                pw1.println(ccust1.getName());
                pw1.println(ccust1.getPhoneNO());
                pw1.println(ccust1.getDiscountRate());
            }
            pw1.close();

        }

        catch (FileNotFoundException e) {
            System.out.println("Error - the file cannot be opened for writing!");
        }
    }

    // method to load arraylist from files to arraylist
    // load object from files to arrayLists
    @SuppressWarnings("unchecked")
    private static void loadData(ArrayList<Report> r) throws IOException, ClassNotFoundException {

        String ID, description, vehicleType, custId, custName, phoneNo, customerType, hirer;
        double odometer, dailyR, discountR;
        int dailyM, serviceL, lastS, pastMileage;
        char status;

        ObjectInputStream oin = null;

        try {

            // loading arraylist(report) from file to arraylist
            FileInputStream fin = new FileInputStream("report.txt");
            oin = new ObjectInputStream(fin);
            r.addAll((ArrayList<Report>) oin.readObject());
            fin.close();

            // scanner for reading the file vehicle.txt
            Scanner sc = new Scanner(new File("vehicle.txt"));

            // while loop is for reading each and every line sequentially
            while (sc.hasNextLine()) {

                // deciding which constructor is to be used
                vehicleType = sc.nextLine();

                // read file into variable ready to construct the objects
                ID = sc.nextLine();
                description = sc.nextLine();
                dailyR = sc.nextDouble();
                odometer = sc.nextDouble();
                int odo = (int) odometer;
                status = sc.next().charAt(0);
                if (status == 'H') {
                    hirer = sc.next();
                } else {
                    hirer = "";
                    sc.nextLine();
                }

                if (vehicleType.equals("Vehicle")) {
                    vehs.add(new Vehicle(ID, description, dailyR, odo));
                    for (Vehicle vh : vehs) {
                        if (vh.getID().compareTo(ID) == 0) {
                            vh.setStatus(status);
                            vh.setHirer(hirer);
                            for (Report r1 : report) {
                                if (vh.getID().compareTo(r1.getVehicleID()) == 0) {
                                    vh.setHireDateTime(r1.getHireDt());
                                }
                            }
                        }
                    }
                }

                else {
                    dailyM = sc.nextInt();
                    serviceL = sc.nextInt();
                    lastS = sc.nextInt();
                    pvehs.add(new PremiumVehicle(ID, description, dailyR, odo, dailyM, serviceL, lastS));
                    for (PremiumVehicle pvh : pvehs) {
                        if (pvh.getID().compareTo(ID) == 0) {
                            pvh.setStatus(status);
                            pvh.setHirer(hirer);
                            for (Report r1 : report) {
                                if (pvh.getID().compareTo(r1.getVehicleID()) == 0) {
                                    System.out.println("in r1");
                                    pvh.setHireDateTime(r1.getHireDt());
                                }
                            }
                        }
                    }
                }

                if (sc.hasNextLine())
                    sc.nextLine();
            }
            sc.close();

            Scanner sc1 = new Scanner(new File("customer.txt"));

            // while loop is for reading each and every line sequentially
            while (sc1.hasNextLine()) {

                // deciding which constructor is to be used
                customerType = sc1.nextLine();

                // read file into variable ready to construct the objects
                custId = sc1.nextLine();
                custName = sc1.nextLine();
                phoneNo = sc1.nextLine();

                if (customerType.equals("ICustomer")) {
                    pastMileage = sc1.nextInt();
                    icust.add(new ICustomer(custId, custName, phoneNo, pastMileage));
                }

                else {
                    discountR = sc1.nextDouble();
                    ccust.add(new CCustomer(custId, custName, phoneNo, discountR));
                }

                if (sc1.hasNextLine())
                    sc1.nextLine();
            }
            sc1.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("Cannot open file for reading!");
        }
    }

    // method to add vehicle
    // adding new vehicle
    public void addVehicle() throws IOException {

        char opt;
        do {

            System.out.println("Enter 6 character's Vehicle ID");
            ID = scan.next();
            int counter = 0;

            // checking if vehicle id length is 6 and is not repeated
            if (ID.length() == 6) {
                for (Vehicle vh : vehs) {
                    if (vh.getID().compareTo(ID) == 0) {
                        counter++;
                    }
                }
                for (PremiumVehicle pvh : pvehs) {
                    if (pvh.getID().compareTo(ID) == 0) {
                        counter++;
                    }
                }
            } else {
                System.out.println("Entered Vehicle ID is not of 6 characters");
            }

            if (counter > 0) {
                System.out.println("Entered Vehicle ID is repeated ");
            } else if (ID.length() == 6) {
                System.out.println("Enter description of a vehicle");
                String description = scan.next();
                description += scan.nextLine();
                System.out.println("Enter daily rate of a vehicle");
                Double dRate = scan.nextDouble();
                System.out.println("Enter Odometer reading of a vehicle");
                int odo = scan.nextInt();
                System.out.println("Enter 1 : Premium Vehicle \nEnter 2 : Standard Vehicle");
                int choice = scan.nextInt();

                if (choice == 1) {
                    System.out.println("Enter dailyMileage of a vehicle");
                    int dailyMileage = scan.nextInt();
                    System.out.println("Enter serviceLength of a vehicle");
                    int serviceLength = scan.nextInt();
                    System.out.println("Enter lastService of a vehicle");
                    int lastService = scan.nextInt();
                    pvehs.add(
                            new PremiumVehicle(ID, description, dRate, odo, dailyMileage, serviceLength, lastService));

                }

                else {
                    vehs.add(new Vehicle(ID, description, dRate, odo));
                }
            }

            System.out.println("Do you want to add more vehicles? Y/N");
            opt = scan.next().toUpperCase().charAt(0);

        } while (opt != 'N');
    }

    // method to add customer
    // adding new customer
    public void addCustomer() throws IOException {

        char opt;
        do {

            System.out.println("Enter 6 character's Customer ID starting with 'C'");
            ID = scan.next();
            int counter = 0;

            // checking if customer id length is 6 and is not repeated
            if (ID.length() == 6) {
                for (ICustomer indC : icust) {
                    if (indC.getCustomerID().compareTo(ID) == 0) {
                        counter++;
                    }
                }
                for (CCustomer corpC : ccust) {
                    if (corpC.getCustomerID().compareTo(ID) == 0) {
                        counter++;
                    }
                }
            } else {
                System.out.println("Entered Customer ID is not of 6 characters");
            }

            if (counter > 0) {
                System.out.println("Entered Customer ID is repeated ");
            }
            // customer id should start with 'C'
            else if (ID.charAt(0) != 'C') {
                System.out.println("Entered Customer ID is not starting with 'C' ");
            } else if (ID.length() == 6) {
                System.out.println("Enter Customer Name");
                String name = scan.next();
                name += scan.nextLine();
                System.out.println("Enter Customer Phone Number");
                String phoneNo = scan.next();
                System.out.println("Enter 1 : Individual Customer \nEnter 2 : Corporate Customer");
                int choice = scan.nextInt();

                if (choice == 1) {
                    System.out.println("Enter pastMileage of a Customer");
                    int pastMileage = scan.nextInt();
                    icust.add(new ICustomer(ID, name, phoneNo, pastMileage));
                }

                else {
                    System.out.println("Enter Discount Rate for a Customer");
                    double discountRate = scan.nextDouble();
                    ccust.add(new CCustomer(ID, name, phoneNo, discountRate));
                }
            }

            System.out.println("Do you want to add more customers? Y/N");
            opt = scan.next().toUpperCase().charAt(0);

        } while (opt != 'N');

    }

    // method to display vehicles in specified range
    // display vehicles in specified range
    public void display() throws Exception {
        System.out.println("Enter Lower Limit for daily rate");
        double lower = scan.nextDouble();
        System.out.println("Enter Upper Limit for daily rate");
        double upper = scan.nextDouble();
        if (upper < lower)
            throw new Exception("Upper limit is less than Lower limit");

        int counter = 0;
        for (Vehicle vehs1 : vehs) {
            if (vehs1.getDailyRate() >= lower && vehs1.getDailyRate() <= upper && vehs1.getStatus() == 'A') {
                counter++;
                vehs1.print();
            }
        }

        for (PremiumVehicle pvehs1 : pvehs) {
            if (pvehs1.getDailyRate() >= lower && pvehs1.getDailyRate() <= upper && pvehs1.getStatus() == 'A') {
                counter++;
                pvehs1.print();
            }

        }

        if (counter == 0) {
            System.out.println("No vehicles in the specified range");
        }
    }

    // method to hire a vehicle
    // method to hire a vehicle
    public void hireAVehicle() throws StatusException, VehicleNotFound, CustomerNotFound, IOException {
        boolean found = false;
        boolean foundCust = false;
        boolean canHire = true;
        char status = 0;
        ObjectOutputStream oos = null;
        System.out.println("Enter Vehicle ID to be hired.");
        String ID = scan.next();

        //// checking if vehicle id is present
        for (Vehicle vh : vehs) {
            if (vh.getID().compareTo(ID) == 0) {
                status = vh.getStatus();
                found = true;
                break;
            }
        }

        for (PremiumVehicle pvh : pvehs) {
            if (pvh.getID().compareTo(ID) == 0) {
                status = pvh.getStatus();
                found = true;
                break;
            }
        }

        // checking if vehicle is available
        if (found && status == 'A') {
            System.out.println("Enter Customer ID");
            String custID = scan.next();
            for (ICustomer indC : icust) {
                if (indC.getCustomerID().compareTo(custID) == 0) {
                    foundCust = true;
                    for (Vehicle vh : vehs) {
                        if (vh.getHirer() != null && indC.getCustomerID().compareTo(vh.getHirer()) == 0) {
                            canHire = false;
                            System.out.println(" Customer " + custID + " has already hired a vehicle.");
                            break;
                        }
                    }

                    for (Vehicle vh : vehs) {
                        if (vh.getID().compareTo(ID) == 0 && canHire) {

                            if (vh.hire(custID)) {
                                report.add(new Report(ID, vh.getHirer(), vh.getHireDateTime(),
                                        vh.getHireCompleteDateTime(), 0.0));
                                System.out.println(vh.getID() + " is now hired by " + indC.getCustomerID());
                            }
                            break;
                        }
                    }

                    for (PremiumVehicle pvh : pvehs) {
                        if (pvh.getHirer() != null && indC.getCustomerID().compareTo(pvh.getHirer()) == 0) {
                            canHire = false;
                            System.out.println("Indivisual Customer " + custID + " has already hired a vehicle.");
                            break;
                        }
                    }

                    for (PremiumVehicle pvh : pvehs) {
                        if (pvh.getID().compareTo(ID) == 0 && canHire) {
                            if (pvh.hire(custID)) {
                                report.add(new Report(ID, pvh.getHirer(), pvh.getHireDateTime(),
                                        pvh.getHireCompleteDateTime(), 0.0));
                                System.out.println(pvh.getID() + " is now hired by " + indC.getCustomerID());

                            }
                            break;
                        }
                    }
                }

            }

            for (CCustomer corpC : ccust) {
                if (corpC.getCustomerID().compareTo(custID) == 0) {
                    foundCust = true;
                    for (Vehicle vh : vehs) {
                        if (vh.getID().compareTo(ID) == 0) {
                            vh.hire(custID);
                            report.add(new Report(ID, vh.getHirer(), vh.getHireDateTime(), vh.getHireCompleteDateTime(),
                                    0.0));
                            System.out.println(vh.getID() + " is now hired by " + corpC.getCustomerID());
                            break;
                        }
                    }

                    for (PremiumVehicle pvh : pvehs) {
                        if (pvh.getID().compareTo(ID) == 0) {
                            pvh.hire(custID);
                            report.add(new Report(ID, pvh.getHirer(), pvh.getHireDateTime(),
                                    pvh.getHireCompleteDateTime(), 0.0));
                            System.out.println(pvh.getID() + " is now hired by " + corpC.getCustomerID());
                            break;
                        }
                    }
                    break;
                }
            }

            if (!foundCust) {
                throw new CustomerNotFound("Customer ID " + custID + " not found.");
            }

        } else {
            if (status == 'S' || status == 'H')
                System.out.println("Vehicle ID " + ID + " is not available for hiring.");
            else
                throw new VehicleNotFound("Vehicle ID " + ID + " not found.");
        }

        //writing arraylist to file
        FileOutputStream fos = new FileOutputStream("Report.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(report);
        fos.close();
    }

    // method to complete a hired vehicle
    // method to complete hired vehicle
    public void hireCompleted() throws StatusException, OdoMeterException, VehicleNotFound, IOException {
        boolean found = false;
        double charges = 0.0;
        double discount = 0.0;
        int odo = 0;
        ObjectOutputStream oos = null;
        System.out.println("Enter Vehicle ID to complete the hire.");
        String ID = scan.next();
        for (Vehicle vh : vehs) {
            // proceed if vehicle found
            if (vh.getID().compareTo(ID) == 0) {
                found = true;
                System.out.println("Enter current Odometer reading.");
                odo = scan.nextInt();
                charges = vh.hireComplete(odo);
                for (ICustomer i : icust) {
                    if ((i.getCustomerID().compareTo(vh.getHirer()) == 0)) {
                        discount = i.getDiscount(charges);
                        charges -= discount;
                    }
                }
                for (CCustomer c : ccust) {
                    if ((c.getCustomerID().compareTo(vh.getHirer()) == 0)) {
                        discount = c.getDiscount(charges);
                        charges -= discount;
                    }
                }
                for (Report r : report) {
                    if ((r.getVehicleID().compareTo(vh.getID()) == 0)) {
                        r.setHireC(vh.hireCompleteDateTime);
                        r.setCharges(charges);
                    }
                }

                System.out.println("Hire completed for " + vh.getID() + " with charges " + charges);
                break;
            }
        }

        for (PremiumVehicle pvh : pvehs) {
            // proceed if vehicle found
            if (pvh.getID().compareTo(ID) == 0) {
                found = true;
                System.out.println("Enter current Odometer reading.");
                odo = scan.nextInt();
                charges = pvh.hireComplete(odo);
                for (ICustomer i : icust) {
                    if ((i.getCustomerID().compareTo(pvh.getHirer()) == 0)) {
                        discount = i.getDiscount(charges);
                        charges -= discount;
                    }
                }
                for (CCustomer c : ccust) {
                    if ((c.getCustomerID().compareTo(pvh.getHirer()) == 0)) {
                        discount = c.getDiscount(charges);
                        charges -= discount;
                    }
                }
                for (Report r : report) {
                    if ((r.getVehicleID().compareTo(pvh.getID()) == 0)) {
                        r.setHireC(pvh.hireCompleteDateTime);
                        r.setCharges(charges);
                    }
                }

                System.out.println("Hire completed for " + pvh.getID() + " with charges " + charges);
                break;
            }
        }

        if (!found) {
            throw new VehicleNotFound("Vehicle ID " + ID + " not found.");
        }
        FileOutputStream fos = new FileOutputStream("Report.txt");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(report);
        fos.close();

    }

    // method to service/complete service of a vehicle
    // method for servicing of a vehicle
    public void service() throws StatusException, OdoMeterException, VehicleNotFound {
        boolean found = false;
        int odo;
        System.out.println("Enter 1 to give Vehicle for servicing and 2 for service complete");
        int n = scan.nextInt();
        System.out.println("Enter Vehicle ID.");
        String ID = scan.next();
        if (n == 1) {
            for (Vehicle vh : vehs) {
                //proceed if vehicle found
                if (vh.getID().compareTo(ID) == 0) {
                    found = true;
                    vh.service();
                    System.out.println("Vehicle " + vh.getID() + " is given for service");
                    break;
                }
            }

            for (PremiumVehicle pvh : pvehs) {
                //proceed if vehicle found
                if (pvh.getID().compareTo(ID) == 0) {
                    found = true;
                    pvh.service();
                    System.out.println("Vehicle " + pvh.getID() + " is given for service");
                    break;
                }
            }
        }

        else if (n == 2) {
            System.out.println("Enter current Odometer reading.");
            odo = scan.nextInt();
            for (Vehicle vh : vehs) {
                if (vh.getID().compareTo(ID) == 0) {
                    found = true;
                    vh.serviceComplete(odo);
                    System.out.println("Vehicle " + vh.getID() + " service completed");
                    break;
                }
            }

            for (PremiumVehicle pvh : pvehs) {
                if (pvh.getID().compareTo(ID) == 0) {
                    found = true;
                    pvh.serviceComplete(odo);
                    System.out.println("Vehicle " + pvh.getID() + " service completed");
                    break;
                }
            }
        }

        if (!found) {
            throw new VehicleNotFound("Vehicle " + ID + " not found");
        }
    }

    // advances date/time by specified days, hours and mins for testing purpose
    // advances date/time by specified days, hours and mins for testing purpose
    public void setAdvance() throws VehicleNotFound {
        System.out.println("Enter no. of days");
        int days = scan.nextInt();
        System.out.println("Enter no. of hours");
        int hours = scan.nextInt();
        System.out.println("Enter no. of minutes");
        int mins = scan.nextInt();
        DateTime.setAdvance(days, hours, mins);
    }

    // method to display rental income in specified period
    // calculate rental income from vehicles
    private void rentalIncome() throws ParseException {

        System.out.println("******** Rental Report ********");

        System.out.println("Enter the start Date in (dd-mm-yyyy) format");

        String startDate = scan.next();

        System.out.println("Enter the End Date in (dd-mm-yyyy) format");

        String endDate = scan.next();

        Date d1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);

        Date d2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);

        int count = 0;

        // convert hired date which was DateTime string and then to date to match user
        // input date format
        for (int i = 0; i < report.size(); i++) {
            Date hireddate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
                    .parse(report.get(i).getHireDt().toString());

            for (Vehicle vh : vehs) {
                if ((report.get(i).getVehicleID().compareTo(vh.getID()) == 0)) {
                    if (vh.getStatus() == 'H') {
                        int numberOfDays = DateTime.diffDays(vh.getHireDateTime(), new DateTime());
                        report.get(i).setCharges(numberOfDays * vh.getDailyRate());
                    }
                }
            }

            for (PremiumVehicle pvh : pvehs) {
                if ((report.get(i).getVehicleID().compareTo(pvh.getID()) == 0)) {
                    if (pvh.getStatus() == 'H') {
                        int numberOfDays = DateTime.diffDays(pvh.getHireDateTime(), new DateTime());
                        report.get(i).setCharges(numberOfDays * pvh.getDailyRate());
                    }
                }
            }

            if (d1.compareTo(hireddate) < 0 && d2.compareTo(hireddate) > 0) {
                report.get(i).print();
                count++;
            }

        }

        if (count == 0) {
            System.out.println("There is no Vehicle Rental Income from " + d1 + " to " + d2);
        }

    }
}
