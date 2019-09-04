package dev.paieapitest.exception;

public class BulletinSalaireNonTrouveException extends RuntimeException {

    public BulletinSalaireNonTrouveException() {
    }

    public BulletinSalaireNonTrouveException(String message) {
        super(message);
    }

    public BulletinSalaireNonTrouveException(String message, Throwable cause) {
        super(message, cause);
    }

    public BulletinSalaireNonTrouveException(Throwable cause) {
        super(cause);
    }

    public BulletinSalaireNonTrouveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
