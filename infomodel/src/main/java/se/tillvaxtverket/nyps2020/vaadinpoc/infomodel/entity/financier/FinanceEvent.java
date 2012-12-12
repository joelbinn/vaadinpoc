package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "finance_event")
@SequenceGenerator(name = "base_sequence", sequenceName = "finance_event_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "FinanceEvent.findById", query = "select f from FinanceEvent f where f.id = :id"),
        @NamedQuery(name = "FinanceEvent.findByFinancier", query = "select f from FinanceEvent f where f.financier = :financier")
})
public class FinanceEvent extends BaseEntity {

    private static final long serialVersionUID = 5691347928140538254L;

    @OneToOne
    @NotNull
    @ForeignKey(name = "finance_event_f_method_fk")
    private FinanceMethod financeMethod;

    private String description;

    @Column(name = "financier_name")
    private String financierName;

    @OneToOne
    @ForeignKey(name = "finance_event_financier_fk")
    private Financier financier;

    @OneToOne
    @ForeignKey(name = "finance_event_finance_fund_fk")
    private FinanceFund financeFund;

    public FinanceMethod getFinanceMethod() {
        return financeMethod;
    }

    public void setFinanceMethod(final FinanceMethod financeMethod) {
        this.financeMethod = financeMethod;
    }

    public String getFinancierName() {
        String fName = null;
        if (getFinanceMethod().isPrivate()) {
            fName = this.financierName;
        } else if (getFinancier() != null) {
            fName = getFinancier().getName();
        }
        return fName;
    }

    public void setFinancierName(final String name) {
        this.financierName = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getFinancierName();
    }

    public Financier getFinancier() {
        return financier;
    }

    public void setFinancier(final Financier financier) {
        this.financier = financier;
    }

    public FinanceFund getFinanceFund() {
        return financeFund;
    }

    public void setFinanceFund(final FinanceFund financeFund) {
        this.financeFund = financeFund;
    }
}
