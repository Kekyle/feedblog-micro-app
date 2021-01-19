package by.feedblog.post.service.exception;

public class TagIsAlreadyExistException extends RuntimeException {
    public TagIsAlreadyExistException() {
        super();
    }

    public TagIsAlreadyExistException(String message) {
        super(message);
    }

    public TagIsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagIsAlreadyExistException(Throwable cause) {
        super(cause);
    }

    protected TagIsAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
