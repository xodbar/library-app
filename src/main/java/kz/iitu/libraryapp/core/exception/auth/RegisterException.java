package kz.iitu.libraryapp.core.exception.auth;

public class RegisterException extends AuthenticationException {
    public RegisterException(String errorMessage) {
        super(errorMessage);
    }
}
