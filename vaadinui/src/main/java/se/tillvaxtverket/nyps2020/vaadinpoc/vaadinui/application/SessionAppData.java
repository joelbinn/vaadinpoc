package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application;

import com.vaadin.Application;
import com.vaadin.service.ApplicationContext;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.EventBus;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.MainView;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class SessionAppData implements ApplicationContext.TransactionListener,
        Serializable {
    private static final Logger logger =
            Logger.getLogger(SessionAppData.class.getName());
    private static ThreadLocal<SessionAppData> instance =
            new ThreadLocal<SessionAppData>();
    private VaadinInEarUiApplication application;
    private Locale locale;
    private ResourceBundle resourceBundle;

    SessionAppData(VaadinInEarUiApplication application) {
        this.application = application;
        instance.set(this);
    }

    @Override
    public void transactionStart(Application application,
                                 Object transactionData) {
        // Set this data instance of this application
        // as the one active in the current thread.
        logger.fine("Tx start: " + Thread.currentThread().getName());
        if (application == this.application) {
            instance.set(this);
        }
    }

    @Override
    public void transactionEnd(Application application,
                               Object transactionData) {
        // Clear the reference to avoid potential issues
        logger.fine("Tx start: " + Thread.currentThread().getName());
        if (application == this.application) {
            instance.set(null);
        }
    }

    public static void initLocale(Locale locale) {
        instance.get().locale = locale;
        instance.get().resourceBundle =
                ResourceBundle.getBundle("messages", locale);
    }

    public static Locale getLocale() {
        return instance.get().locale;
    }

    public static String getLocalizedString(String messageId, Object... args) {
        return String.format(
                instance.get().locale,
                instance.get().resourceBundle.getString(messageId),
                args);
    }

    public static EventBus getEventBus() {
        return instance.get().application.getEventBus();
    }

    public static MainView getMainView() {
        return instance.get().application.getMainView();
    }

    public static VaadinInEarUiApplication getApplication() {
        return instance.get().application;
    }
}
