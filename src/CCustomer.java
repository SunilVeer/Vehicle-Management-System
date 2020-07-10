
public class CCustomer extends Customer {
    private double discountRate;

    // getter
    public double getDiscountRate() {
        return this.discountRate;
    }

    // setter
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    // constructor
    public CCustomer(String customerID, String name, String phoneNO, double discountRate) {
        super(customerID, name, phoneNO);
        this.discountRate = discountRate;
    }

    // method for discount
    public double getDiscount(double amount) {
        amount *=(discountRate/100); // calculate discount and return
        System.out.println("amount " +amount );
        return amount;
    }

}
