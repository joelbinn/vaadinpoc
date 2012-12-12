package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data;

import java.util.*;

public class Budget {
    private Map<BudgetGroup, String> budgetGroup2runningSum =
            new HashMap<BudgetGroup, String>();
    private Map<String, RunningSumItem> runningSums =
            new HashMap<String, RunningSumItem>();
    private List<BudgetGroup> budgetGroups = new ArrayList<BudgetGroup>();

    public void addBudgetGroup(String runningSumName, BudgetGroup budgetGroup) {
        budgetGroups.add(budgetGroup);
        budgetGroup2runningSum.put(budgetGroup, runningSumName);
    }

    public List<BudgetGroup> getBudgetGroups() {
        return Collections.unmodifiableList(budgetGroups);
    }

    public String getRunningSumName(BudgetGroup budgetGroup) {
        return budgetGroup2runningSum.get(budgetGroup);
    }

    public List<BudgetRow> getAllBudgetRows() {
        List<BudgetRow> budgetRows = new ArrayList<BudgetRow>();
        List<BudgetItem> budgetItems = new ArrayList<BudgetItem>();
        for (BudgetGroup budgetGroup : budgetGroups) {
            for (BudgetItem budgetItem : budgetGroup.getBudgetItems()) {
                budgetRows.add(budgetItem);
                budgetItems.add(budgetItem);
            }
            String runningSumName = budgetGroup2runningSum.get(budgetGroup);
            RunningSumItem runningSumItem =
                    new RunningSumItem(runningSumName,
                            new ArrayList<BudgetItem>(budgetItems));
            runningSums.put(runningSumName, runningSumItem);
            budgetRows.add(runningSumItem);
        }
        return budgetRows;
    }

    public Map<String, RunningSumItem> getRunningSums() {
        return Collections.unmodifiableMap(runningSums);
    }
}
