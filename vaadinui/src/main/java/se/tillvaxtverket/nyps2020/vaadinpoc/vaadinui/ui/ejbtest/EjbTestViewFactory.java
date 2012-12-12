package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.ejbtest;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.ejb.HelloLocal;
import se.tillvaxtverket.nyps2020.vaadinpoc.ejb2.HelloRemoteEJB2;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.dao.Dao;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier.Appropriation;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;

import javax.ejb.EJB;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.rmi.RemoteException;
import java.util.List;

public class EjbTestViewFactory {
    @EJB
    private HelloLocal helloService;

    @EJB
    private HelloRemoteEJB2 helloRemoteEJB2;

    @Inject
    @Any
    private Dao dao;

    public Component newView() {
        VerticalLayout view = new VerticalLayout();
        view.setSpacing(true);
        view.setMargin(true);

        Table table = new Table("Anslag");
        table.setSizeFull();
        List<Appropriation> appropriations =
                dao.findAll(Appropriation.class, 0, 100);
        System.out.printf("###### users: %s\n", appropriations + "");
        BeanItemContainer<Appropriation> container =
                new BeanItemContainer<Appropriation>(Appropriation.class,
                        appropriations);
        table.setContainerDataSource(container);
        view.addComponent(table);

        final Button button = new Button(SessionAppData.getLocalizedString(
                "test_button"));
        view.addComponent(button);

        final Label label = new Label("<h1>Här kommer EJB resultat visas.</h1>");
        label.setContentMode(Label.CONTENT_XHTML);

        view.addComponent(label);
        button.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                String labelValue = "<h1>EJB resultat</h1>";
                labelValue += "<p><b>The EJB3 helloService says '" +
                        helloService.sayHello() + "'</b></p>";
                try {
                    labelValue += "<p><b>The EJB2 helloService says '" +
                            helloRemoteEJB2.sayHello() + "'</b></p>";
                } catch (RemoteException e) {
                    labelValue +=
                            "<p><b>The EJB2 helloService threw exception '" +
                                    e.getMessage() + "'</b></p>";
                }
                label.setValue(labelValue);
            }
        });

        return view;
    }
}
