package by.feedblog.user.service.exception;

public class UserIsAlreadyExistException extends RuntimeException {
    public UserIsAlreadyExistException() {
        super();
    }

    public UserIsAlreadyExistException(String message) {
        super(message);
    }

    public UserIsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected UserIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
