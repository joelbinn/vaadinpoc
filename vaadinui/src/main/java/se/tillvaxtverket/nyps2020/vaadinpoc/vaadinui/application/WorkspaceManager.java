/*
 * User: joel
 * Date: 2012-11-23
 * Time: 20:09
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.AuthenticationChangedEvent;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.EventBus;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.docpreview.DocPreviewView;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.docpreview.DocPreviewViewFactory;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.ejbtest.EjbTestViewFactory;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504.MIN504TestViewFactory;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util.WarningEvent;

import javax.inject.Inject;
import java.io.File;
import java.io.Serializable;
import java.util.EventObject;

public class WorkspaceManager implements EventBus.Listener, Serializable,
        TabSheet.SelectedTabChangeListener {

    private Layout workArea;

    @Inject
    private DocPreviewViewFactory docPreviewViewFactory;

    @Inject
    private EjbTestViewFactory ejbTestViewFactory;

    @Inject
    private MIN504TestViewFactory min504TestViewFactory;
    private Component todoTab;
    private Component inboxTab;
    private Component handleCaseTab;
    private Component min504Tab;
    private Component ejbTestTab;
    private Component docViewerTab;

    public void init() {
        SessionAppData.getEventBus().addListener(this, null);
    }

    @Override
    public void onEvent(EventObject event) {
        if (event instanceof AuthenticationChangedEvent) {
            AuthenticationChangedEvent ace = (AuthenticationChangedEvent) event;
            Layout workSpace = SessionAppData.getMainView().getWorkSpaceArea();
            if (ace.isAuthenticated()) {
                workSpace.removeAllComponents();
                Component wsPanel = buildWorkspace();
                workSpace.addComponent(wsPanel);
            } else {
                workSpace.removeAllComponents();
            }
        }
    }

    private Component buildWorkspace() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        splitPanel.setSizeFull();

        Accordion accordion = new Accordion();
        todoTab = accordion.addTab(new Label("Att göra"), "Att göra").getComponent();
        inboxTab = accordion.addTab(new Label("Inbox"), "Inbox").getComponent();
        handleCaseTab =
                accordion.addTab(new Label("Hantera ärende"), "Hantera ärende").getComponent();
        min504Tab = accordion.addTab(new Label("MIN-504"), "MIN-504").getComponent();
        ejbTestTab = accordion
                .addTab(new Label("EJB3/EJB2/JPA test"), "EJB3/EJB2/JPA test").getComponent();
        docViewerTab = accordion
                .addTab(new Label("Document viewer"), "Document viewer").getComponent();
        accordion.setSizeFull();
        accordion.addListener(this);
        splitPanel.setFirstComponent(accordion);

        workArea = new HorizontalLayout();
        workArea.setSizeFull();
        workArea.addComponent(new Label("Work Area"));
        splitPanel.setSecondComponent(workArea);

        splitPanel.setSplitPosition(25, Sizeable.UNITS_PERCENTAGE);

        return splitPanel;
    }

    @Override
    public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
        try {
            workArea.removeAllComponents();
            Component component;
            if (event.getTabSheet().getSelectedTab() == min504Tab) {
                component = min504TestViewFactory.newView();
            } else if (event.getTabSheet().getSelectedTab() == todoTab) {
                component = new Label("Arbetsyta för 'Att göra'");
            } else if (event.getTabSheet().getSelectedTab() == inboxTab) {
                component = new Label("Arbetsyta för 'Inbox'");
            } else if (event.getTabSheet().getSelectedTab() == handleCaseTab) {
                component = new Label("Arbetsyta för 'Hantera ärende'");
            } else if (event.getTabSheet().getSelectedTab() == ejbTestTab) {
                component = ejbTestViewFactory.newView();
            } else if (event.getTabSheet().getSelectedTab() == docViewerTab) {
                DocPreviewView docPreviewView = docPreviewViewFactory.newView();
                docPreviewView.setDocument(new File("test.pdf"));
                component = docPreviewView;
            } else {
                component = new Label("Okänd arbetsyta");
            }
            workArea.addComponent(component);
        } catch (Throwable e) {
            SessionAppData.getEventBus().fireEvent(new WarningEvent(this,
                    SessionAppData.getLocalizedString(
                            "docpreview.internal_failure", e.getLocalizedMessage()), e));
             e.printStackTrace();
        }
    }
}
