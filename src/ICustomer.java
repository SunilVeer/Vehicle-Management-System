
public class ICustomer extends Customer {
    private int pastMileage;

    // constructor
    public ICustomer(String ID, String name, String phoneNO, int pastMileage) {
        super(ID, name, phoneNO);
        this.pastMileage = pastMileage;
    }

    // getter
    public int getPastMileage() {
        return this.pastMileage;
    }

    // method calculating discount
    public double getDiscount(double amount) {
        double discount = 0.0;

        // 10% discount is given for customers with past mileage between 100000 and
        // 200000
        if (this.pastMileage > 100000 && this.pastMileage <= 200000) {
            discount = amount * 0.1;
        }

        // 20% discount is given for customers with past mileage greater than 200000
        else if (this.pastMileage > 200000) {
            discount = amount * 0.2;
        }
        return discount;
    }
}
