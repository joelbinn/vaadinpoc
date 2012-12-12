package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application;

import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Window;
import se.tillvaxtverket.nyps2020.vaadinpoc.ejb.HelloLocal;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.dao.Dao;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.EventBus;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.impl.EventBusImpl;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.MainView;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util.WarningEvent;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.EventObject;

@SuppressWarnings("serial")
@SessionScoped // Important!!!
public class VaadinInEarUiApplication extends com.vaadin.Application
        implements EventBus.Listener {
    @EJB
    private HelloLocal helloService;

    @Inject
    private WorkspaceManager workspaceManager;

    @Inject
    @Any
    private Dao dao;

    private MainView mainView;
    private EventBus eventBus;


    @Override
    public void init() {
        SessionAppData sessionAppData = new SessionAppData(this);
        // Register it as a listener in the application context
        getContext().addTransactionListener(sessionAppData);

        // Initialize the session-global data
        SessionAppData.initLocale(getLocale());

        // Note mainView must be set after initialization of session data
        mainView = new MainView();
        eventBus = new EventBusImpl();
        workspaceManager.init();
        setMainWindow(mainView);

        setTheme("nyps2020");
        eventBus.addListener(this, new EventBus.Filter() {
            @Override
            public boolean isAccepted(EventObject event) {
                return event instanceof WarningEvent;
            }
        });
    }

    /**
     * Important!!!
     */
    @Override
    public void close() {
        super.close();
        WebApplicationContext webCtx = (WebApplicationContext) getContext();
        HttpSession session = webCtx.getHttpSession();
        session.invalidate();
    }

    public MainView getMainView() {
        return mainView;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public void onEvent(EventObject event) {
        if (event instanceof WarningEvent) {
            WarningEvent we = (WarningEvent) event;
            mainView.getWindow().showNotification(we.getMessage(),
                    Window.Notification.TYPE_WARNING_MESSAGE);
        }
    }
}
