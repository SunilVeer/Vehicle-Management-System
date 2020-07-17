@SuppressWarnings("serial")

//handled Vehicle not found exception
public class VehicleNotFound extends Exception {
    public VehicleNotFound(String errMessage) {
        super(errMessage);
    }

    public VehicleNotFound() {
        super();
    }
}
