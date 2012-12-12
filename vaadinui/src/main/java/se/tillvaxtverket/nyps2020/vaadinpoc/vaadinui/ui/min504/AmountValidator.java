package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.data.Validator;
import com.vaadin.ui.Component;

class AmountValidator implements Validator {
    private final Component component;

    public AmountValidator(Component component) {
        this.component = component;
    }

    @Override
    public void validate(Object value)
            throws InvalidValueException {
        setIndication(value);
    }

    @Override
    public boolean isValid(Object value) {
        return setIndication(value);
    }

    private boolean setIndication(Object value) {
        try {
            Util.getFloat(value);
            component.removeStyleName("error");
            return true;
        } catch (NumberFormatException e) {
            component.addStyleName("error");
            return false;
        }
    }
}
