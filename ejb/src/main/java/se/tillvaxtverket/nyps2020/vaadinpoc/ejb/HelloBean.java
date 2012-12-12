package se.tillvaxtverket.nyps2020.vaadinpoc.ejb;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
public class HelloBean implements HelloLocal {

    @Override
    public String sayHello() {
        return "Halloj!!! Ett meddelande från EJB3-bönan: 'Klockan är " +
                new SimpleDateFormat("HH:mm:ss").format(new Date())+"'";
    }
}
