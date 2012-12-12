package se.tillvaxtverket.nyps2020.vaadinpoc.ejb;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
public class HelloBean implements HelloLocal {

    @Override
    public String sayHello() {
        return "Halloj!!! Ett meddelande fr�n EJB3-b�nan: 'Klockan �r " +
                new SimpleDateFormat("HH:mm:ss").format(new Date())+"'";
    }
}
