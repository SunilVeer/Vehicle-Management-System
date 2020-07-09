
@SuppressWarnings("serial")
public class PremiumVehicle extends Vehicle {
    private int dailyMileage;
    private int serviceLength;
    private int lastService;
    protected double additionalCharges=0;

    // constructor
    public PremiumVehicle(String vehicleID, String description, double dailyRate, int odometer, int dailyMileage,
                          int serviceLength, int lastService) {
        super(vehicleID, description, dailyRate, odometer);
        this.dailyMileage = dailyMileage;
        this.serviceLength = serviceLength;
        this.lastService = lastService;
    }

    public PremiumVehicle() {
        // TODO Auto-generated constructor stub
    }

    // getter methods
    public int getDailyMileage() {
        return this.dailyMileage;
    }

    public int getServiceLength() {
        return this.serviceLength;
    }

    public int getLastService() {
        return this.lastService;
    }

    public DateTime getHireDateTime() {
        return super.hireDateTime;
    }

    // setter method
    public void setHireDateTime(DateTime hireDateTime) {
        super.hireDateTime = hireDateTime;
    }

    // method for completing service of a vehicle
    public boolean serviceComplete(int odo) throws StatusException, OdoMeterException {
        this.lastService = odo;
        return super.serviceComplete(odo);
    }

    // method to complete hiring of a vehicle
    public double hireComplete(int odo) throws StatusException, OdoMeterException {

        double charges = super.hireComplete(odo);
        // actual OdoMeter reading is previous reading minus current reading
        double totalOdometer = odo - initialOdo;
        // calculate total daily mileage
        double totalDailyMileage = numberOfDays * dailyMileage;

        double mileage = totalOdometer-totalDailyMileage;
        if(totalOdometer>totalDailyMileage)
        {
            additionalCharges = charges +( mileage*0.10);
        }
        else
        {
            additionalCharges=charges+mileage;
        }
        super.setOdometer(odo);
        return additionalCharges;
    }

    // method to hire a vehicle
    public boolean hire(String hirerID) throws StatusException {
        if (lastService + serviceLength > this.getOdometer()) {
            return super.hire(hirerID);
        } else {
            System.out.println("Vehicle could not be hired as service is due");
            return false;
        }
    }

    // method to print details of vehicle
    public void print() {
        super.print();
        System.out.println("Mileage Allowance=" + dailyMileage + "\tService Length=" + serviceLength + "\tLast Service="
                + lastService);
    }

    // converting output of print() to String
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Mileage Allowance=" + dailyMileage + "\tService Length=" + serviceLength + "\tLast Service="
                + lastService);
        return sb.toString();
    }
}
