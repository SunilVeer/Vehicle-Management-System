// Class to test the PremiumVehicle class (provided)
class TestPremiumVehicle {
    public static void main(String args[]) throws StatusException, OdoMeterException {
// passing ID, description, daily-rate, odometer reading, daily-mileage,
// service-length, last-service
        PremiumVehicle v = new PremiumVehicle("SAM134", "Lexus 05", 95.00, 18000, 200, 10000, 17500);
        v.print();
        if (v.hire("ST36784") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nCar " + v.getID() + " could not be hired ");
        v.print();
        DateTime.setAdvance(4, 2, 0);
        int odoReading = 28000;
        double val = v.hireComplete(odoReading);
        if (val > 0.0) {
            System.out.print("\nCar " + v.getID() + " is returned by " + v.getHirer());
            System.out.println(" Charges = " + val + " odo Reading = " + odoReading);
        } else
            System.out.println("\nWARNING: Car " + v.getID() + " could not be returned from hire ");
        v.print();
        DateTime.setAdvance(6, 0, 0);
//attemption to hire a car which is due for service
        if (v.hire("ST7656") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nCar " + v.getID() + " could not be hired ");
        DateTime.setAdvance(7, 0, 0);
//sending car for service
        if (v.service() == true)
            System.out.println("\nCar " + v.getID() + " is now sent to service");
        else
            System.out.println("\nCar " + v.getID() + " could not be serviced ");
        v.print();
        DateTime.setAdvance(8, 0, 0);
        if (v.serviceComplete(28100) == true)
            System.out.println("\nCar " + v.getID() + " is now returned from service");
        else
            System.out.println("\nCar " + v.getID() + " could not be returned from serviced ");
        v.print();
        DateTime.setAdvance(9, 0, 0);
        if (v.hire("ST7656") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nCar " + v.getID() + " could not be hired ");
        v.print();
    }
}