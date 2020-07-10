
public abstract class Customer {
    protected String customerID;
    private String name;
    private String phoneNo;

    // constructor
    public Customer(String customerID, String name, String phoneNO) {
        this.customerID = customerID;
        this.name = name;
        this.phoneNo = phoneNO;
    }

    // getter methods
    public String getCustomerID() {
        return this.customerID;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNO() {
        return this.phoneNo;
    }

    // abstract method for discount so that subclass/s should implement it
    public abstract double getDiscount(double amount);
}
