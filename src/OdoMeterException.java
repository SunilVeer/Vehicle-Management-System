
@SuppressWarnings("serial")

//handled OdoMeter exception
public class OdoMeterException extends Exception {
    public OdoMeterException(String errMessage) {
        super(errMessage);
    }

    public OdoMeterException() {
        super();
    }
}
