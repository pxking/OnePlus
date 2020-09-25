package com.example.demo.service.ex;

public class UploadAvatarException extends ServiceException {
    private static final long serialVersionUID = -4515392117341755879L;

    public UploadAvatarException() {
    }

    public UploadAvatarException(String message) {
        super(message);
    }

    public UploadAvatarException(String message, Throwable cause) {
        super(message, cause);
    }

    public UploadAvatarException(Throwable cause) {
        super(cause);
    }

    public UploadAvatarException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
