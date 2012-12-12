package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.Budget;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.BudgetGroup;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.BudgetItem;

import java.util.Arrays;
import java.util.List;

public class MIN504TestViewFactory {
    private List<BudgetItem> publicCashCoFinancing = Arrays.asList(
            new BudgetItem(
                    "Kontant finansiering",
                    "Region Gävleborg",
                    650000,
                    0),
            new BudgetItem(
                    "Kontant finansiering",
                    "Länsstyrelsen Dalarna",
                    2500000,
                    2500000),
            new BudgetItem(
                    "Kontant finansiering",
                    "Tierps kommun",
                    1950000,
                    0)
    );
    private List<BudgetItem> publicDirectCoFinancing = Arrays.asList(
            new BudgetItem(
                    "Direktfinansiering",
                    "Region Dalarna",
                    822500,
                    0),
            new BudgetItem(
                    "Direktfinansiering",
                    "Länsstyrelsen Dalarna",
                    100000,
                    0)
    );
    private List<BudgetItem> privateCoFinancing = Arrays.asList(
            new BudgetItem(
                    "Privat finansiering",
                    "Mellanskog AB",
                    100000,
                    0)
    );
    private List<BudgetItem> actualCosts = Arrays.asList(
            new BudgetItem(
                    "Personal",
                    "Personalkostnader",
                    283500,
                    25643),
            new BudgetItem(
                    "Köp av tjänst",
                    "", 70000,
                    0),
            new BudgetItem(
                    "Lokal",
                    "Lokalkostnader",
                    0,
                    0),
            new BudgetItem(
                    "Investeringar",
                    "",
                    10800000,
                    1350),
            new BudgetItem(
                    "Övriga kostnader",
                    "",
                    196000,
                    0),
            new BudgetItem(
                    "Intäkter",
                    "",
                    0,
                    0)
    );
    private List<BudgetItem> directlyFinancedCosts = Arrays.asList(
            new BudgetItem(
                    "Offentligt bidrag i annat än pengar",
                    "",
                    922500, 0)

    );


    public Component newView() {
        Panel mainPanel = new Panel();
        mainPanel.setSizeFull();
        mainPanel.setCaption("Utbetalning");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(true);
        mainLayout.setMargin(true);
        mainLayout.setSizeFull();

        mainLayout.addComponent(createButtonBar());
        TabSheet tabSheet = createTabSheet();
        mainLayout.addComponent(tabSheet);
        mainLayout.setExpandRatio(tabSheet, 1.0f);

        mainPanel.setContent(mainLayout);

        return mainPanel;
    }

    private HorizontalLayout createButtonBar() {
        HorizontalLayout buttonBar = new HorizontalLayout();
        buttonBar.setSpacing(true);
        buttonBar.setMargin(true);
        buttonBar.setSizeFull();

        Button export = new Button("Exportera");
        buttonBar.addComponent(export);
        buttonBar.setComponentAlignment(export, Alignment.TOP_LEFT);
        Button save = new Button("Spara");
        buttonBar.addComponent(save);
        buttonBar.setComponentAlignment(save, Alignment.TOP_LEFT);
        Button checkAndSend = new Button("Kontrollera och skicka");
        buttonBar.addComponent(checkAndSend);
        buttonBar.setComponentAlignment(checkAndSend, Alignment.TOP_LEFT);

        return buttonBar;
    }

    private TabSheet createTabSheet() {
        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();

        Label contentLabel1 = new Label("Lite innehåll");
        contentLabel1.setSizeFull();
        tabSheet.addTab(contentLabel1, "Uppgifter om stödmottagare");

        tabSheet.addTab(createEconomyView(), "Ekonomi");

        Label contentLabel2 = new Label("Lite innehåll");
        contentLabel2.setSizeFull();
        tabSheet.addTab(contentLabel2, "Uppföljning");

        Label contentLabel3 = new Label("Lite innehåll");
        contentLabel3.setSizeFull();
        tabSheet.addTab(contentLabel3, "Bifoga filer");

        return tabSheet;
    }

    private VerticalLayout createEconomyView() {
        VerticalLayout economyView = new VerticalLayout();
        economyView.addStyleName("economy-view");
        economyView.setSpacing(true);
        economyView.setMargin(true);

        Label title = new Label("Ekonomi");
        title.addStyleName("economy-title");
        economyView.addComponent(title);

        HorizontalLayout periodPanel = new HorizontalLayout();
        periodPanel.setSpacing(true);
        DateField periodFrom = new DateField();
        periodFrom.setCaption("Period från");
        periodFrom.setDescription("Ange period från");
        periodPanel.addComponent(periodFrom);
        DateField periodTo = new DateField();
        periodTo.setCaption("Period till");
        periodTo.setDescription("Ange period till");
        periodPanel.addComponent(periodTo);
        economyView.addComponent(periodPanel);

        Budget costBudget = createCostBudget();
        GroupedBudgetView costBudgetView = createCostBudgetView(costBudget);
        economyView.addComponent(costBudgetView);

        Budget financingBudget = createFinancingBudget();
        GroupedBudgetView financingBudgetView =
                createFinancingBudgetView(financingBudget);
        economyView.addComponent(financingBudgetView);

        AmountsToObtainView amountsToObtainView =
                new AmountsToObtainView(costBudget, financingBudget);
        costBudgetView.addBudgetChangedListener(amountsToObtainView);
        financingBudgetView.addBudgetChangedListener(amountsToObtainView);
        economyView.addComponent(amountsToObtainView);

        return economyView;
    }

    private GroupedBudgetView createFinancingBudgetView(Budget budget) {
        List<ColumnInfo> columnInfo = Arrays.asList(
                new ColumnInfo.Builder()
                        .fieldName("specification")
                        .headerName("Finansiering")
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("description")
                        .headerName("Finansiär")
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("approved")
                        .headerName("Godkänt för ärendet")
                        .isNumber()
                        .isAccumulated()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("earlierApproved")
                        .headerName("Tidigare mottaget")
                        .isNumber()
                        .isAccumulated()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("forPeriod")
                        .headerName(
                                "Mottaget för perioden")
                        .isNumber()
                        .isAccumulated()
                        .isEditable()
                        .isVirtual()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("accumulated")
                        .headerName("Ackumulerat")
                        .isNumber()
                        .isAccumulator()
                        .isVirtual()
                        .build()
        );

        GroupedBudgetView groupedBudgetView = new GroupedBudgetView(
                "Finansieringsbudget",
                new BudgetRowContainer(budget, columnInfo),
                columnInfo
        );
        return groupedBudgetView;
    }

    private Budget createFinancingBudget() {
        Budget budget = new Budget();
        budget.addBudgetGroup("Summa, offentlig kontant medfinansiering",
                new BudgetGroup(publicCashCoFinancing));
        budget.addBudgetGroup("Summa, offentlig medfinansiering",
                new BudgetGroup(publicDirectCoFinancing));
        budget.addBudgetGroup("Summa, total medfinansiering",
                new BudgetGroup(privateCoFinancing));
        return budget;
    }

    private GroupedBudgetView createCostBudgetView(Budget budget) {
        List<ColumnInfo> columnInfo = Arrays.asList(
                new ColumnInfo.Builder()
                        .fieldName("specification")
                        .headerName("Kostnadsslag/ specifikation")
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("description")
                        .headerName("Beskrivning")
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("approved")
                        .headerName("Godkänt för ärendet")
                        .isNumber()
                        .isAccumulated()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("earlierApproved")
                        .headerName("Tidigare godkänt")
                        .isNumber()
                        .isAccumulated()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("forPeriod")
                        .headerName(
                                "Kostnader för perioden")
                        .isNumber()
                        .isAccumulated()
                        .isEditable()
                        .isVirtual()
                        .build(),
                new ColumnInfo.Builder()
                        .fieldName("accumulated")
                        .headerName("Ackumulerat")
                        .isNumber()
                        .isAccumulator()
                        .isVirtual()
                        .build()
        );

        GroupedBudgetView groupedBudgetView = new GroupedBudgetView(
                "Kostnadsbudget",
                new BudgetRowContainer(budget, columnInfo),
                columnInfo
        );
        return groupedBudgetView;
    }

    private Budget createCostBudget() {
        Budget budget = new Budget();
        budget.addBudgetGroup("Summa, faktiska kostnader",
                new BudgetGroup(actualCosts));
        budget.addBudgetGroup("Summa, totala kostnader",
                new BudgetGroup(directlyFinancedCosts));
        return budget;
    }
}
