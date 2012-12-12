/*
 * User: joel
 * Date: 2012-11-23
 * Time: 20:16
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event;

import java.util.EventObject;

public interface EventBus {
    public interface Listener {
        void onEvent(EventObject event);
    }

    public interface Filter {
        boolean isAccepted(EventObject event);
    }

    void addListener(Listener listener, Filter filter);

    void removeListener(Listener listener);

    void fireEvent(EventObject event);
}
