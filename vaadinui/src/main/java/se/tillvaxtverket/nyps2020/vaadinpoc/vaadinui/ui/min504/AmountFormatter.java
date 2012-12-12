package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.data.Property;
import com.vaadin.data.util.PropertyFormatter;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;

public class AmountFormatter extends PropertyFormatter {
    public AmountFormatter(Property property) {
        super(property);
    }

    @Override
    public String format(Object value) {
        return String
                .format(SessionAppData.getLocale(), "%,.0f",
                        (Float) value);
    }

    @Override
    public Object parse(String formattedValue)
            throws Exception {
        return Util.getFloat(formattedValue);
    }
}
