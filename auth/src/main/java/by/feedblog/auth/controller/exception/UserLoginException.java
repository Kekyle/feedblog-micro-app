package by.feedblog.auth.controller.exception;

public class UserLoginException extends RuntimeException {
    public UserLoginException() {
        super();
    }

    public UserLoginException(String message) {
        super(message);
    }

    public UserLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginException(Throwable cause) {
        super(cause);
    }

    protected UserLoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
