package se.tillvaxtverket.nyps2020.vaadinpoc.ejb2;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import java.rmi.RemoteException;

public interface HelloRemoteHomeEJB2 extends EJBHome {
    HelloRemoteEJB2 create() throws RemoteException, CreateException;
}
