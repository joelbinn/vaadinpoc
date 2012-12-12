package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.ui.*;

/**
 * Utilities for the MIN-504 UI components.
 */
abstract class Util {
    static void addDivider(GridLayout gridLayout) {
        Label dividerBar = new Label("<hr/>", Label.CONTENT_XHTML);
        int row = gridLayout.getCursorY();
        gridLayout.insertRow(row);
        gridLayout.addComponent(dividerBar, 0, row, 5, row);
    }

    static float getFloatValue(Component component) {
        if (component instanceof TextField) {
            return (Float) ((TextField) component).getPropertyDataSource()
                    .getValue();
        }
        if (component instanceof Label) {
            return (Float) ((Label) component).getValue();
        }
        throw new Error("Cannot get float value from " + component);
    }

    public static void setFloatValue(Component component, float value) {
        if (component instanceof TextField) {
            ((TextField) component).getPropertyDataSource()
                    .setValue(value);

        } else if (component instanceof Label) {
            ((Label) component).setValue(value);
        } else {
            throw new Error("Cannot set float value to " + component);
        }
    }

    public static void alignCell(GridLayout gridLayout, Component component,
                                 ColumnInfo columnInfo) {
        if (columnInfo.isNumber) {
            component.addStyleName("right-align");
            gridLayout.setComponentAlignment(component, Alignment.TOP_RIGHT);
        } else {
            gridLayout.setComponentAlignment(component, Alignment.TOP_LEFT);
        }
    }

    public static float getFloat(Object o) {
        if (o == null || o.toString().length() == 0) {
            return (float) 0;
        }
        String s = o.toString();
        s = s.replaceAll("[\\s\\xA0]", "");
        return Float.parseFloat(s);
    }
}
