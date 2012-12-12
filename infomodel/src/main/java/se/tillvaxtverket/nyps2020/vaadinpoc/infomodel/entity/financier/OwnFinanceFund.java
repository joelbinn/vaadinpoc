package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
        @NamedQuery(name = "OwnFinanceFund.findAllActive", query = "select o from OwnFinanceFund o where o.inactive = false"),
        @NamedQuery(name = "OwnFinanceFund.findByDisplayName", query = "select o from OwnFinanceFund o where o.displayName = :name"),
        @NamedQuery(name = "OwnFinanceFund.findAll", query = "select o from OwnFinanceFund o"),
        @NamedQuery(name = "OwnFinanceFund.findByCoFinanceFund", query = "select o from OwnFinanceFund o where o.coFinanceFund = :coFinanceFund"),
        @NamedQuery(name = "OwnFinanceFund.isUsedByConnectedOwnFinanceFund", query = "select count(coff) from ConnectedOwnFinanceFund coff where coff.ownFinanceFund=:ownFinanceFund")
})
public class OwnFinanceFund extends FinanceFund {

    private static final long serialVersionUID = -8736426973075582427L;

    @OneToOne
    private CoFinanceFund coFinanceFund;

    @OneToOne
    private Appropriation appropriation;

    public CoFinanceFund getCoFinanceFund() {
        return coFinanceFund;
    }

    public void setCoFinanceFund(final CoFinanceFund coFinanceFund) {
        this.coFinanceFund = coFinanceFund;
    }

    public Appropriation getAppropriation() {
        return appropriation;
    }

    public void setAppropriation(final Appropriation appropriation) {
        this.appropriation = appropriation;
    }
}
