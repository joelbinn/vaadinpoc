package se.tillvaxtverket.nyps2020.vaadinpoc.vaadinui.data;

public class BudgetItem implements BudgetRow {
    private final String specification;
    private final String description;
    private float approved;
    private float earlierApproved;
    private float forPeriod;

    public BudgetItem(String specification, String description, float approved,
                      float earlierApproved) {
        this.specification = specification;
        this.description = description;
        this.approved = approved;
        this.earlierApproved = earlierApproved;
    }

    @Override
    public String getSpecification() {
        return specification;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getApproved() {
        return approved;
    }

    public void setApproved(float approved) {
        this.approved = approved;
    }

    @Override
    public float getEarlierApproved() {
        return earlierApproved;
    }

    public void setEarlierApproved(float earlierApproved) {
        this.earlierApproved = earlierApproved;
    }

    @Override
    public float getForPeriod() {
        return forPeriod;
    }

    public void setForPeriod(float forPeriod) {
        this.forPeriod = forPeriod;
    }

    @Override
    public float getAccumulated() {
        return earlierApproved + forPeriod;
    }
}
