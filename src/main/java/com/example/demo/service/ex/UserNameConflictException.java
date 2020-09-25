package com.example.demo.service.ex;

public class UserNameConflictException extends ServiceException {
    private static final long serialVersionUID = 1029696475724309770L;

    public UserNameConflictException() {
    }

    public UserNameConflictException(String message) {
        super(message);
    }

    public UserNameConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameConflictException(Throwable cause) {
        super(cause);
    }

    public UserNameConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
