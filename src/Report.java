import java.io.Serializable;

@SuppressWarnings("serial")
public class Report implements Serializable {

    String vehicleID, customerID;
    DateTime hireDt, hireC;
    Double charges;

    // constructor
    public Report(String vehicleID, String customerID, DateTime hireDt, DateTime hireC, Double charges) {
        this.vehicleID = vehicleID;
        this.customerID = customerID;
        this.hireDt = hireDt;
        this.hireC = hireC;
        this.charges = charges;
    }

    // getter methods
    public String getVehicleID() {
        return vehicleID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public DateTime getHireDt() {
        return hireDt;
    }

    public DateTime getHireC() {
        return hireC;
    }

    public Double getCharges() {
        return this.charges;
    }

    // setter methods
    public void setHireC(DateTime hireC) {
        this.hireC = hireC;
    }

    public void setHireDt(DateTime hireDt) {
        this.hireDt = hireDt;
    }

    public void setCharges(Double charges) {
        this.charges = charges;
    }

    // method to print rental income from vehicles
    public void print() {
        System.out.println("***********Rental Income from Vehicles in specified range***********");
        System.out.println(
                "Vehicle Id: " + getVehicleID() + "\tHired date: " + getHireDt() + "\tCharges: " + getCharges());
    }

}
