package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data;

import java.util.ArrayList;
import java.util.List;

public class RunningSumItem implements BudgetRow {
    private List<BudgetItem> budgetItems = new ArrayList<BudgetItem>();
    private String title;

    public RunningSumItem(String title, ArrayList<BudgetItem> budgetItems) {
        this.budgetItems = budgetItems;
        this.title = title;
    }

    @Override
    public String getSpecification() {
        return title;
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public float getApproved() {
        float sum = 0;
        for (BudgetItem budgetItem : budgetItems) {
            sum += budgetItem.getApproved();
        }
        return sum;
    }

    @Override
    public float getEarlierApproved() {
        float sum = 0;
        for (BudgetItem budgetItem : budgetItems) {
            sum += budgetItem.getEarlierApproved();
        }
        return sum;
    }


    @Override
    public float getForPeriod() {
        float sum = 0;
        for (BudgetItem budgetItem : budgetItems) {
            sum += budgetItem.getForPeriod();
        }
        return sum;
    }

    @Override
    public float getAccumulated() {
        float sum = 0;
        for (BudgetItem budgetItem : budgetItems) {
            sum += budgetItem.getAccumulated();
        }
        return sum;
    }
}
