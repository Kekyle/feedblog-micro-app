package by.feedblog.post.service.exception;

public class PostTitleException extends RuntimeException {
    public PostTitleException() {
        super();
    }

    public PostTitleException(String message) {
        super(message);
    }

    public PostTitleException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostTitleException(Throwable cause) {
        super(cause);
    }

    protected PostTitleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
