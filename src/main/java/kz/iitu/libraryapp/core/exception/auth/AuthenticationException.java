package kz.iitu.libraryapp.core.exception.auth;

public class AuthenticationException extends Exception {
    private final String errorMessage;

    public AuthenticationException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
