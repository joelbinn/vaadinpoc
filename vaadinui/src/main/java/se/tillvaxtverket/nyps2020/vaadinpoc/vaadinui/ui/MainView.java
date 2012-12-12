package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.AuthenticationChangedEvent;

import java.util.logging.Logger;

public class MainView extends Window implements Button.ClickListener {
    public static final ThemeResource TVV_LOGO =
            new ThemeResource("images/tvv-logo-transparent-background.png");
    public static final ThemeResource LOGIN_ICON =
            new ThemeResource("images/icons/login.png");
    public static final ThemeResource LOGOUT_ICON =
            new ThemeResource("images/icons/logout.png");
    public final String LOGIN =
            SessionAppData.getLocalizedString("login");
    public final String LOGOUT =
            SessionAppData.getLocalizedString("logout");
    private static final Logger logger = Logger.getLogger(MainView.class.getName());
    private VerticalLayout mainPanel = new VerticalLayout();
    private HorizontalLayout topPanel = new HorizontalLayout();
    private Button loginButton = new Button(null, this);
    private HorizontalLayout workSpaceArea = new HorizontalLayout();
    private boolean authenticated;

    public MainView() {
        setContent(mainPanel);
        mainPanel.setSizeFull();
        initTopPanel();
        mainPanel.addComponent(topPanel);
        workSpaceArea.setSizeFull();
        mainPanel.addComponent(workSpaceArea);
        mainPanel.setExpandRatio(workSpaceArea, 1.0f);
    }

    private void initTopPanel() {
        Embedded tvvLogo = new Embedded(null, TVV_LOGO);
        topPanel.addComponent(tvvLogo);
        topPanel.setComponentAlignment(tvvLogo, Alignment.MIDDLE_LEFT);

        Label nyps2020Logo = new Label("NYPS-2020");
        nyps2020Logo.setStyleName("nypslogo");
        topPanel.addComponent(nyps2020Logo);
        topPanel.setComponentAlignment(nyps2020Logo, Alignment.MIDDLE_CENTER);

        setAuthenticated(false);
        topPanel.addComponent(loginButton);
        topPanel.setComponentAlignment(loginButton, Alignment.MIDDLE_RIGHT);

        topPanel.setStyleName("toppanel");
        topPanel.setMargin(true);
        topPanel.setSpacing(true);
        topPanel.setWidth("100%");
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        loginButton.setIcon(authenticated ? LOGOUT_ICON : LOGIN_ICON);
        loginButton.setCaption(authenticated ? LOGOUT : LOGIN);
    }

    public Layout getWorkSpaceArea() {
        return workSpaceArea;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (event.getSource() == loginButton) {
            setAuthenticated(!authenticated);
            logger.finest("Button click: " + Thread.currentThread().getName());
            SessionAppData.getEventBus().fireEvent(new AuthenticationChangedEvent(authenticated, this));
        }
    }
}
