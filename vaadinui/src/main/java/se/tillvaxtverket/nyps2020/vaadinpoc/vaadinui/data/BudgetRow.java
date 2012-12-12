package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data;

public interface BudgetRow {
    String getSpecification();

    String getDescription();

    float getApproved();

    float getEarlierApproved();

    float getForPeriod();

    float getAccumulated();

}
