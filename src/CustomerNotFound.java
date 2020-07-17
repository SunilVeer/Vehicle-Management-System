@SuppressWarnings("serial")

//handled Customer not found exception
public class CustomerNotFound extends Exception {
    public CustomerNotFound(String errMessage) {
        super(errMessage);
    }

    public CustomerNotFound() {
        super();
    }
}