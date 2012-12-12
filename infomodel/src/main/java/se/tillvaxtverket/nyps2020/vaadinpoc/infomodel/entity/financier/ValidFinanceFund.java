package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "valid_finance_fund")
@SequenceGenerator(name = "base_sequence", sequenceName = "valid_finance_fund_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "ValidFinanceFund.findByFinancier", query = "select v from ValidFinanceFund v where v.financier = :financier")
})
public class ValidFinanceFund extends BaseEntity implements Comparable<ValidFinanceFund> {

    private static final long serialVersionUID = -5356560048643596581L;

    @ManyToOne
    @NotNull
    @ForeignKey(name = "validfund_financefund_fk")
    private FinanceFund financeFund;

    @ManyToOne
    @NotNull
    @ForeignKey(name = "validfund_financier_fk")
    private Financier financier;

    public ValidFinanceFund() {
        this.financeFund = null;
        this.financier = null;
    }

    public ValidFinanceFund(final FinanceFund financeFund, final Financier financier) {
        this.financeFund = financeFund;
        this.financier = financier;
    }

    public FinanceFund getFinanceFund() {
        return financeFund;
    }

    public void setFinanceFund(final FinanceFund financeFund) {
        this.financeFund = financeFund;
    }

    public Financier getFinancier() {
        return financier;
    }

    public void setFinancier(final Financier financier) {
        this.financier = financier;
    }

    public int compareTo(final ValidFinanceFund o) {
        int result = 0;
        if (this.financeFund != null && o.financeFund != null) {
            result = this.financeFund.compareTo(o.financeFund);
        }
        return result;
    }

    @Override
    public String toString() {
        return (financeFund != null) ? financeFund.getDisplayName() : "";
    }
}
