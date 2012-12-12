package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util;

import java.util.EventObject;

public class WarningEvent extends EventObject {
    private final Throwable throwable;
    private final String message;

    public WarningEvent(Object source, String message, Throwable throwable) {
        super(source);
        this.message = message;
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public String getMessage() {
        return message;
    }
}
