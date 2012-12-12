package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BudgetGroup {
    private List<BudgetItem> budgetItems = new ArrayList<BudgetItem>();

    public BudgetGroup(List<BudgetItem> budgetItems) {
        this.budgetItems.addAll(budgetItems);

    }


    public void addBudgetItem(BudgetItem budgetItem) {
        budgetItems.add(budgetItem);
    }

    public List<BudgetItem> getBudgetItems() {
        return Collections.unmodifiableList(budgetItems);
    }
}
