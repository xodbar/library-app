package kz.iitu.libraryapp.core.exception.leasing;

public class LeasingException extends Exception {
    private final String errorMessage;

    public LeasingException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
