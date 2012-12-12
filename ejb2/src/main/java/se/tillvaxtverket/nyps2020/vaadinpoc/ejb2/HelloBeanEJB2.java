package se.tillvaxtverket.nyps2020.vaadinpoc.ejb2;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloBeanEJB2 implements SessionBean {
    private SessionContext sessionContext;

    String sayHello() {
        return "Halloj!!! Ett meddelande fr�n EJB2-b�nan: 'Klockan �r " +
                new SimpleDateFormat("HH:mm:ss").format(new Date())+"'";
    }

    @Override
    public void setSessionContext(SessionContext sessionContext)
            throws EJBException, RemoteException {
        this.sessionContext = sessionContext;
    }

    // Beh�vs den h�r???
    public void ejbCreate() throws EJBException, RemoteException {
        System.out.println("ejbCreate()");
    }

    @Override
    public void ejbRemove() throws EJBException, RemoteException {
        System.out.println("ejbRemove()");
    }

    @Override
    public void ejbActivate() throws EJBException, RemoteException {
        System.out.println("ejbActivate()");
    }

    @Override
    public void ejbPassivate() throws EJBException, RemoteException {
        System.out.println("ejbPassivate()");
    }
}
