package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.validator.NotNull;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name = "CoFinanceFund.findAllActive", query = "select c from CoFinanceFund c where c.inactive = false"),
        @NamedQuery(name = "CoFinanceFund.findByDisplayName", query = "select c from CoFinanceFund c where c.displayName = :name"),
        @NamedQuery(name = "CoFinanceFund.findAll", query = "select c from CoFinanceFund c")
})
@Entity
public class CoFinanceFund extends FinanceFund {

    private static final long serialVersionUID = 7422946153615090001L;

    @NotNull
    @Column(name = "default_fund")
    private boolean defaultFund;

    @Enumerated(EnumType.STRING)
    @Column(name = "fund_type")
    private FinanceFundType fundType;

    public CoFinanceFund() {
    }

    public CoFinanceFund(final String name, final String displayName, final boolean inactive, final boolean defaultFund, final FinanceFundType fundType) {
        super(name, displayName, inactive);
        this.defaultFund = defaultFund;
        this.fundType = fundType;
    }

    public boolean isDefaultFund() {
        return defaultFund;
    }

    public void setDefaultFund(final boolean defaultFund) {
        this.defaultFund = defaultFund;
    }

    public FinanceFundType getFundType() {
        return fundType;
    }

    public void setFundType(final FinanceFundType fundType) {
        this.fundType = fundType;
    }
}
