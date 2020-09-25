package com.example.demo.service.ex;

public class UpdateDataException extends ServiceException {
    private static final long serialVersionUID = 744472338346798475L;

    public UpdateDataException() {
    }

    public UpdateDataException(String message) {
        super(message);
    }

    public UpdateDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDataException(Throwable cause) {
        super(cause);
    }

    public UpdateDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
