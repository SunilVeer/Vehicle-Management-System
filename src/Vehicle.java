import java.io.Serializable;

@SuppressWarnings("serial")
public class Vehicle implements Serializable {
    private String vehicleID;
    private String hirerID = "";
    private String description;
    private char status = 'A';
    private double dailyRate;
    private int odometer;
    DateTime hireDateTime;
    DateTime hireCompleteDateTime;
    protected int numberOfDays;
    protected int initialOdo;

    //constructor
    public Vehicle(String vehicleID, String description, double dailyRate, int odometer) {
        this.vehicleID = vehicleID;
        this.description = description;
        this.dailyRate = dailyRate;
        this.odometer = odometer;
        this.initialOdo = odometer;
    }

    public Vehicle() {
        // TODO Auto-generated constructor stub
    }

    //getters
    public String getID() {
        return this.vehicleID;
    }

    public String getHirer() {
        return this.hirerID;
    }

    public String getDescription() {
        return this.description;
    }

    public char getStatus() {
        return this.status;
    }

    public double getDailyRate() {
        return this.dailyRate;
    }

    public double getOdometer() {
        return this.odometer;
    }

    public void getHireCompleteDateTime(DateTime hireCompleteDateTime) {
        this.hireCompleteDateTime = hireCompleteDateTime;
    }

    //setters
    public void setOdometer(int odo) {
        this.odometer = odo;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setHirer(String hirerID) {
        this.hirerID = hirerID;
    }

    public DateTime getHireDateTime() {
        return this.hireDateTime;
    }

    public DateTime getHireCompleteDateTime() {
        return this.hireCompleteDateTime;
    }

    public void setHireDateTime(DateTime hireDateTime) {
        this.hireDateTime = hireDateTime;
    }

    //method for hiring a vehicle
    public boolean hire(String hirerID) throws StatusException {
        if (status == 'H')
            throw new StatusException("Vehicle is hired. So can not be hired now");
        else if (status == 'S')
            throw new StatusException("Vehicle is at service. So can not be hired now");
        else { // if vehicle is Available then only hire
            this.hirerID = hirerID;
            this.hireDateTime = new DateTime();
            this.status = 'H';
            return true;
        }
    }

    //method for servicing a vehicle
    public boolean service() throws StatusException {
        if (status == 'H')
            throw new StatusException("Vehicle is hired. So can not be given for servicing");
        else if (status == 'S')
            throw new StatusException("Vehicle is already at service.");
        else { // if vehicle is Available then only give it to service
            status='S';
            return true;
        }
    }

    //method for completing service of a vehicle
    public boolean serviceComplete(int odo) throws StatusException,OdoMeterException {
        if(status == 'A')
            throw new StatusException("Vehicle is available and not at service. So can not proceed to service completed");

        else if (status == 'H')
            throw new StatusException("Vehicle is hired and not at service. So can not proceed to service completed");

        else if (odo < odometer)
            throw new OdoMeterException("Odometer reading is less than reading at service time. So can not proceed to service completed");

        else { // service complete only if vehicle is given for service
            status = 'A';
            this.odometer = odo;
            return true;
        }
    }

    //method for completing hire of a vehicle
    public double hireComplete(int odo) throws StatusException, OdoMeterException {
        if(status == 'A')
            throw new StatusException("Vehicle is available and not hired. So can not proceed to hire completed");

        else if (status == 'S')
            throw new StatusException("Vehicle is at service and not hired. So can not proceed to hire completed");

        else if (odo < odometer)
            throw new OdoMeterException("Odometer reading is less than reading at hire time. So can not proceed to hire completed");

        else { // complete hiring only if vehicle is hired
            numberOfDays = DateTime.diffDays(new DateTime(), hireDateTime);
            this.status='A';
            this.odometer = odo;
            this.hirerID = "";
            return numberOfDays*dailyRate;
        }
    }

    // method to print details of vehicle
    public void print() {
        System.out.println("******************Vehicle Details******************");
        System.out.println(DateTime.getCurrentTime());
        System.out.println("Vehicle ID="+vehicleID+"\tDescription="+description+"\tStatus="+status+"\tDaily Rate="+dailyRate+"\tOdometer Reading="+odometer);

        if(status == 'H') {
            System.out.println("Hirer="+this.hirerID+"\tDate/time of hire="+this.hireDateTime);
        }
    }

    // converting output of print() to String
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("******************Vehicle Details******************\n"+DateTime.getCurrentTime()+"\n"+"Vehicle ID="+vehicleID+"\tDescription="+description+"\tStatus="+status+"\tDaily Rate="+dailyRate+"\tOdometer Reading="+odometer);

        if(status == 'H') {
            sb.append("Hirer="+this.hirerID+"\tDate/time of hire="+this.hireDateTime);
        }
        return sb.toString();
    }
}
