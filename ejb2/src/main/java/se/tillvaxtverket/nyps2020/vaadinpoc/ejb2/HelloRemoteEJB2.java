package se.tillvaxtverket.nyps2020.vaadinpoc.ejb2;

import javax.ejb.EJBObject;
import java.rmi.RemoteException;

public interface HelloRemoteEJB2 extends EJBObject {
    String sayHello() throws RemoteException;
}
