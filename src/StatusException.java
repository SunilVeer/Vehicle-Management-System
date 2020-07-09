
@SuppressWarnings("serial")

//handled status exception
public class StatusException extends Exception {
    public StatusException(String errMessage) {
        super(errMessage);
    }

    public StatusException() {
        super();
    }
}
