/*
 * User: joel
 * Date: 2012-11-23
 * Time: 20:06
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event;

import java.util.EventObject;

/**
 * TODO describe class!
 */
public class AuthenticationChangedEvent extends EventObject {
    private boolean authenticated;

    public AuthenticationChangedEvent(boolean authenticated, Object source) {
        super(source);
        this.authenticated = authenticated;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}
