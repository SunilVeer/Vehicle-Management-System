// Class to test the Vehicle class (provided)
class TestVehicle {
    public static void main(String args[]) throws StatusException, OdoMeterException {
// Constructing a car currently available for hire
        Vehicle v = new Vehicle("SAT123", "Toyota Camry", 12.50, 100000);
        v.print();
// Sending the car for service
        if (v.service() == true)
            System.out.println("\nCar " + v.getID() + " is now sent for service ");
        else
            System.out.println("\nWARNING: Car " + v.getID() + " cannot be sent for service ");
        v.print();
//Forward the time by 2 days (0 hours and 0 minutes)
        DateTime.setAdvance(2, 0, 0);
//Attempt to hire a car on service
        if (v.hire("User36784") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");
        v.print();
//Car returning from service
        if (v.serviceComplete(100100) == true)
            System.out.println("\nCar " + v.getID() + " is now returned from service ");
        else
            System.out.println("\nWARNING: Car " + v.getID() + " cannot be sent returnned from service ");
        v.print();
        DateTime.setAdvance(4, 0, 0);
//Attempt to hire the car which is now available for hire
        if (v.hire("User36784") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");
        v.print();
        DateTime.setAdvance(6, 0, 0);
//Attempt to hire a car already on hire
        if (v.hire("User9999") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");
        v.print();
//Attempt to return car from hire
        double val = v.hireComplete(121000);
        if (val > 0.0) {
            System.out.print("\nCar " + v.getID() + " is returned by " + v.getHirer());
            System.out.println(" Charges = " + val);
        } else
            System.out.println("\nWARNING:Car " + v.getID() + " could not be returned from hire ");
        v.print();
        DateTime.setAdvance(8, 0, 0);
//Attempt to hire the car now available
        if (v.hire("User9999") == true)
            System.out.println("\nCar " + v.getID() + " is now hired to " + v.getHirer());
        else
            System.out.println("\nWARNING: Car " + v.getID() + " could not be hired ");
        v.print();
    }
}
