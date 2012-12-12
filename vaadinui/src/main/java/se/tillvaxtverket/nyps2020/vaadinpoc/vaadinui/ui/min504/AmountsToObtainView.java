package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.ui.min504;

import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertyFormatter;
import com.vaadin.ui.*;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.application.SessionAppData;
import se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data.Budget;

public class AmountsToObtainView extends VerticalLayout
        implements GroupedBudgetView.Listener {
    private Table table = new Table();

    private Container dataSource = new AmountsToObtainContainer();
    AmountToObtain cost = new AmountToObtain(
            SessionAppData.getLocalizedString(
                    "percentage_of_total_approved_costs"),
            0, 0);
    AmountToObtain financing = new AmountToObtain(
            SessionAppData.getLocalizedString("percentage_of_total_financing"),
            0, 0);
    private Budget financingBudget;
    private Budget costBudget;
    private Property totalApprovedEuSupport;
    private Property eligibleSupport;
    private Property subtractedPrev;
    private Property nowEligible;

    public AmountsToObtainView(Budget costBudget,
                               Budget financingBudget) {
        this.costBudget = costBudget;
        this.financingBudget = financingBudget;
        dataSource.addItem(cost);
        dataSource.addItem(financing);

        addStyleName("amount-to-obtain-view");
        setSpacing(true);
        setWidth("");
        Label title = new Label(SessionAppData
                .getLocalizedString("amounts_to_obtain_view_title"));
        title.addStyleName("section-title");
        addComponent(title);

        table.setPageLength(0);
        table.setContainerDataSource(dataSource);
        table.setVisibleColumns(new Object[]{"name", "percentage", "amount"});
        table.setColumnHeaders(
                new String[]{
                        SessionAppData.getLocalizedString("name"),
                        SessionAppData.getLocalizedString("percentage"),
                        SessionAppData.getLocalizedString("amount")
                });
        addComponent(table);

        addDividerBar();

        totalApprovedEuSupport = new ObjectProperty<Float>(0f);
        addComponent(createLabelWithCaption(totalApprovedEuSupport,
                "total_approved_eu_support"));
        eligibleSupport = new ObjectProperty<Float>(0f);
        addComponent(
                createLabelWithCaption(eligibleSupport, "eligible_support"));
        subtractedPrev = new ObjectProperty<Float>(0f);
        addComponent(
                createLabelWithCaption(subtractedPrev, "subtracted_prev"));

        addDividerBar();

        nowEligible = new ObjectProperty<Float>(0f);
        HorizontalLayout eligible =
                createLabelWithCaption(nowEligible, "now_eligible");
        eligible.addStyleName("bold");
        addComponent(eligible);

        onValueChange(null);
    }

    private void addDividerBar() {
        Label dividerBar = new Label("<hr/>", Label.CONTENT_XHTML);
        addComponent(dividerBar);
    }

    private HorizontalLayout createLabelWithCaption(Property p,
                                                    String stringId) {
        HorizontalLayout hl = new HorizontalLayout();
        hl.setSpacing(true);
        Label caption =
                new Label(SessionAppData.getLocalizedString(stringId) + ":");
        hl.addComponent(caption);
        hl.setComponentAlignment(caption, Alignment.MIDDLE_LEFT);
        Label value = new Label(new AmountFormatter(p));
        hl.addComponent(value);
        hl.setComponentAlignment(value, Alignment.MIDDLE_LEFT);
        return hl;
    }

    @Override
    public void onValueChange(GroupedBudgetView source) {
        float decidedEuSupport = financingBudget.getRunningSums()
                .get("Summa, total medfinansiering").getApproved();
        float decidedPublicSupport = financingBudget.getRunningSums()
                .get("Summa, offentlig medfinansiering").getApproved();
        float accumulatedPublicSupport = financingBudget.getRunningSums()
                .get("Summa, offentlig medfinansiering").getAccumulated();

        float actualCosts = costBudget.getRunningSums()
                .get("Summa, faktiska kostnader").getApproved();
        float accumulatedActualCosts = costBudget.getRunningSums()
                .get("Summa, faktiska kostnader").getAccumulated();
        float actualPrevApproved = costBudget.getRunningSums()
                .get("Summa, faktiska kostnader").getEarlierApproved();

        float x = decidedEuSupport / actualCosts;
        dataSource.getItem(cost).getItemProperty("percentage")
                .setValue(x * 100);
        float eligibleX = x * accumulatedActualCosts;
        dataSource.getItem(cost).getItemProperty("amount").setValue(
                eligibleX);

        float y = decidedEuSupport / (decidedEuSupport + decidedPublicSupport);
        dataSource.getItem(financing).getItemProperty("percentage").setValue(
                y * 100);
        float eligibleY = y * accumulatedPublicSupport / (1 - y);
        dataSource.getItem(financing).getItemProperty("amount").setValue(
                eligibleY);

        totalApprovedEuSupport.setValue(decidedEuSupport);
        eligibleSupport.setValue(Math.min(eligibleX, eligibleY));
        subtractedPrev.setValue(eligibleX < eligibleY ?
                actualPrevApproved * x :
                actualPrevApproved * y);
        nowEligible.setValue(eligibleX < eligibleY ?
                eligibleX - (actualPrevApproved * x) :
                eligibleY - (actualPrevApproved * y));
    }

    private static class AmountsToObtainContainer
            extends BeanItemContainer<AmountToObtain> {
        public AmountsToObtainContainer()
                throws IllegalArgumentException {
            super(AmountToObtain.class);
        }

        @Override
        public Property getContainerProperty(Object itemId, Object propertyId) {
            if ("amount".equals(propertyId)) {
                return new AmountFormatter(
                        super.getContainerProperty(itemId, propertyId));
            } else if ("percentage".equals(propertyId)) {
                return new PercentageFormatter(
                        super.getContainerProperty(itemId, propertyId));
            }
            return super.getContainerProperty(itemId, propertyId);
        }

        private class PercentageFormatter extends PropertyFormatter {
            public PercentageFormatter(Property property) {
                super(property);
            }

            @Override
            public String format(Object value) {
                return String
                        .format(SessionAppData.getLocale(), "%.2f%%",
                                (Float) value);
            }

            @Override
            public Object parse(String formattedValue) throws Exception {
                return Util.getFloat(formattedValue.replace("%",""));
            }
        }
    }

    public static class AmountToObtain {
        private String name;
        private float percentage;
        private float amount;

        public AmountToObtain(String name, float percentage, float amount) {
            this.name = name;
            this.percentage = percentage;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getPercentage() {
            return percentage;
        }

        public void setPercentage(float percentage) {
            this.percentage = percentage;
        }

        public float getAmount() {
            return amount;
        }

        public void setAmount(float amount) {
            this.amount = amount;
        }
    }
}
