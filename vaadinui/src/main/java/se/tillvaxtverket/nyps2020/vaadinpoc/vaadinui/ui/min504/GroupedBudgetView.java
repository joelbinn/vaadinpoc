package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.BudgetRow;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.RunningSumItem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupedBudgetView extends GridLayout
        implements Property.ValueChangeListener {
    public interface Listener {
        void onValueChange(GroupedBudgetView source);
    }

    private final String budgetTitle;
    private List<ColumnInfo> columnInfo;
    private BudgetRowContainer containerDataSource;
    private List<Listener> listeners = new LinkedList<Listener>();

    public GroupedBudgetView(String budgetTitle,
                             BudgetRowContainer budgetRowContainer,
                             List<ColumnInfo> columnInfo) {
        super(columnInfo.size(), 1);
        this.budgetTitle = budgetTitle;
        this.columnInfo = columnInfo;
        containerDataSource = budgetRowContainer;
        init();
    }

    public void addBudgetChangedListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeBudgetChangedListener(Listener listener) {
        listeners.remove(listener);
    }

    private void init() {
        setSpacing(true);
        addStyleName("grouped-budget");
        addBudgetTitle();
        addHeaderRow();
        Util.addDivider(this);
        for (Object itemId : containerDataSource.getItemIds()) {
            BeanItem<BudgetRow> item = containerDataSource.getItem(itemId);
            boolean isRunningSum = item.getBean() instanceof RunningSumItem;
            if (isRunningSum) {
                Util.addDivider(this);
                addRunningSumItem(item);
                Util.addDivider(this);
            } else {
                addBudgetItem(item);
            }
        }
    }

    private void addBudgetTitle() {
        Label title = new Label(budgetTitle);
        title.addStyleName("section-title");
        int row = getCursorY();
        insertRow(row);
        addComponent(title, 0, row, 5, row);
    }

    private void addHeaderRow() {
        for (ColumnInfo columnInfoItem : columnInfo) {
            Label headerLabel = new Label(columnInfoItem.headerName);
            headerLabel.setContentMode(Label.CONTENT_XHTML);
            headerLabel.addStyleName("grouped-budget-header");
            headerLabel.addStyleName("budget-column");
            // this sets the width of the grid cell thus causing label text
            // to be wrapped if exceeding the width for all cells in the
            // column
            headerLabel.setWidth("120px");
            addComponent(headerLabel);
            Util.alignCell(this, headerLabel, columnInfoItem);
        }
    }

    private void addRunningSumItem(BeanItem<BudgetRow> budgetRowBeanItem) {
        Map<String, Label> totalSumColumns = new HashMap<String, Label>();
        int row = getCursorY();
        insertRow(row);
        int col = 0;
        for (ColumnInfo column : columnInfo) {
            Property property =
                    budgetRowBeanItem.getItemProperty(column.fieldName);
            if (property == null) {
                throw new Error("No property with name: " + column.fieldName);
            }
            if (column.isNumber) {
                property = new AmountFormatter(property);
            }

            Label totalSumColumnLabel = new Label(property);
            totalSumColumnLabel.addStyleName("sum");
            totalSumColumnLabel.addStyleName("budget-column");
            totalSumColumns.put(column.fieldName, totalSumColumnLabel);
            if (col == 0) {
                totalSumColumnLabel.addStyleName("total-sum-first-column");
                addComponent(totalSumColumnLabel, 0, row, 1, row);
            } else if (col > 1) {
                addComponent(totalSumColumnLabel, col, row);
            }
            Util.alignCell(this, totalSumColumnLabel, column);
            col++;
        }
    }

    private void addBudgetItem(BeanItem<BudgetRow> item) {
        for (ColumnInfo column : columnInfo) {
            Property property = item.getItemProperty(column.fieldName);
            if (property == null) {
                throw new Error("No property with name: " + column.fieldName);
            }
            if (column.isNumber) {
                property = new AmountFormatter(property);
            }
            Component c;
            if (column.isEditable) {
                c = new TextField(property);
                final TextField tf = (TextField) c;
                tf.setImmediate(true);
                tf.addListener(this);
                if (column.isNumber) {
                    tf.addValidator(new AmountValidator(tf));
                }
            } else {
                c = new Label(property);
            }
            c.addStyleName("budget-group-item-property");
            c.addStyleName("budget-column");

            if (column.isNumber) {
                c.addStyleName("right-align");
            }

            addComponent(c);
            Util.alignCell(this, c, column);
        }
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        containerDataSource.refreshItems();
        fireValueChangedEvent();
    }


    private void fireValueChangedEvent() {
        for (Listener listener : listeners) {
            listener.onValueChange(this);
        }
    }

}

