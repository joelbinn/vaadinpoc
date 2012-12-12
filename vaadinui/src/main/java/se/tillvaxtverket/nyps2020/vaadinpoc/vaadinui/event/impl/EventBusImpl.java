/*
 * User: joel
 * Date: 2012-11-23
 * Time: 20:17
 */
package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.impl;

import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.event.EventBus;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.util.Ref;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class EventBusImpl implements EventBus {

    private Map<Listener, Ref<Filter>> listeners = new HashMap<Listener, Ref<Filter>>();

    @Override
    public void addListener(Listener listener, Filter filter) {
        listeners.put(listener, new Ref<Filter>(filter));
    }

    @Override
    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void fireEvent(EventObject event) {
        for (Listener listener : listeners.keySet()) {
            if (listeners.get(listener).get() == null ||
                    listeners.get(listener).get().isAccepted(event)) {
                listener.onEvent(event);
            }
        }
    }
}
