package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.MethodProperty;
import com.vaadin.data.util.ObjectProperty;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.Budget;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.BudgetRow;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.RunningSumItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BudgetRowContainer implements Container {
    List<BeanItem<BudgetRow>> budgetRows =
            new ArrayList<BeanItem<BudgetRow>>();

    public BudgetRowContainer(Budget budget, List<ColumnInfo> columnInfo) {
        int id = 0;
        for (BudgetRow budgetRow : budget.getAllBudgetRows()) {
            // Add rows for a budget group
            BeanItem<BudgetRow> item = new BeanItem<BudgetRow>(budgetRow);
            for (ColumnInfo column : columnInfo) {
                if (column.isVirtual) {
                    item.addItemProperty(column.fieldName,
                            (column.isNumber ?
                                    new ObjectProperty<Float>(0f) :
                                    new ObjectProperty<String>("")));
                }
            }
            budgetRows.add(item);
            id++;
        }
    }

    @Override
    public BeanItem<BudgetRow> getItem(Object itemId) {
        return budgetRows.get((Integer) itemId);
    }

    @Override
    public Collection<?> getContainerPropertyIds() {
        if (budgetRows.size() > 0) {
            return budgetRows.get(0).getItemPropertyIds();
        }
        return null;
    }

    @Override
    public Collection<Integer> getItemIds() {
        List<Integer> itemIds = new ArrayList<Integer>();
        for (int i = 0; i < budgetRows.size(); i++) {
            itemIds.add(i);
        }
        return itemIds;
    }

    @Override
    public Property getContainerProperty(Object itemId, Object propertyId) {
        if (budgetRows.size() > 0) {
            return budgetRows.get((Integer) itemId)
                    .getItemProperty(propertyId);
        }
        return null;
    }

    @Override
    public Class<?> getType(Object propertyId) {
        if (budgetRows.size() > 0) {
            return budgetRows.get(0).getItemProperty(propertyId).getType();
        }
        return null;
    }

    @Override
    public int size() {
        return budgetRows.size();
    }

    @Override
    public boolean containsId(Object itemId) {
        int id = (Integer) itemId;
        return id < budgetRows.size();
    }

    @Override
    public Item addItem(Object itemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object addItem() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeItem(Object itemId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addContainerProperty(Object propertyId, Class<?> type,
                                        Object defaultValue)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeContainerProperty(Object propertyId)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAllItems() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    public void refreshItems() {
        for (BeanItem<BudgetRow> budgetRow : budgetRows) {
            if (budgetRow.getBean() instanceof RunningSumItem) {
                for (Object pid : budgetRow.getItemPropertyIds()) {
                    MethodProperty p =
                            (MethodProperty) budgetRow.getItemProperty(pid);
                    p.fireValueChange();
                }
            } else {
                MethodProperty p =
                        (MethodProperty) budgetRow
                                .getItemProperty("accumulated");
                p.fireValueChange();
            }
        }
    }
}
