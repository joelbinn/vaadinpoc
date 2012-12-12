package se.tillvaxtverket.nyps2020.vaadinpoc.ejb;

import javax.ejb.Local;

@Local
public interface HelloLocal {
    public String sayHello();
}
