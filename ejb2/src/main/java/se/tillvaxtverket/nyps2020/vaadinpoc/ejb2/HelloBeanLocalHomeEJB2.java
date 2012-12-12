package se.tillvaxtverket.nyps2020.vaadinpoc.ejb2;

import javax.ejb.CreateException;

public interface HelloBeanLocalHomeEJB2 {
    HelloLocalEJB2 create() throws CreateException;
}
