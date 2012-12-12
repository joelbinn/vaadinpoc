package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.annotations.ForeignKey;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.supporttypeauthority.SupportTypeAuthority;

import javax.persistence.*;

/**
 * This is a relation between an {@link OwnFinanceFund} and a {@link SupportTypeAuthority}.
 * <p/>
 * It defines the own finance funds that an authority has associated with a specific support type.
 */
@Entity
@Table(name = "connected_own_finance_fund", uniqueConstraints = {
        //@UniqueConstraint(columnNames = {"ownfinancefund_id", "supporttypeauthority_id"})
})
@SequenceGenerator(name = "base_sequence", sequenceName = "connected_own_finance_fund_seq")
@NamedQueries({
        @NamedQuery(name = "ConnectedOwnFinanceFund.findBySupportTypeAutoAndOwnFinance",
                query = "select o from ConnectedOwnFinanceFund o where "
                        + "o.ownFinanceFund.id = :ownFinanceFundId and "
                        + "o.supportTypeAuthority.id = :supportTypeAuthorityId"),
        @NamedQuery(name = "ConnectedOwnFinanceFund.findAll", query = "select o from ConnectedOwnFinanceFund o"),
        @NamedQuery(name = "ConnectedOwnFinanceFund.findByOrgAndSupportType",
                query = "select o from ConnectedOwnFinanceFund o where " +
                        "o.supportTypeAuthority.authority.name = :authorityName and " +
                        "o.supportTypeAuthority.supportType.name = :supportTypeName"),
        @NamedQuery(name = "ConnectedOwnFinanceFund.findByFinanceFundAndSupportTypeAuthority",
                query = "select o from ConnectedOwnFinanceFund o where " +
                        "o.ownFinanceFund = :financeFund and " +
                        "o.supportTypeAuthority = :supportTypeAuthority")
})
public class ConnectedOwnFinanceFund extends BaseEntity {

    private static final long serialVersionUID = 3172968810563784921L;

    @OneToOne
    @ForeignKey(name = "ownfinancefund_fk")
    private OwnFinanceFund ownFinanceFund;

    @ManyToOne
    @JoinColumn(name = "supporttypeauthority_id")
    @ForeignKey(name = "supporttypeauthority_fk")
    private SupportTypeAuthority supportTypeAuthority;

    public ConnectedOwnFinanceFund() {
        this.ownFinanceFund = null;
        this.supportTypeAuthority = null;
    }

    public ConnectedOwnFinanceFund(final OwnFinanceFund ownFinanceFund, final SupportTypeAuthority supportTypeAuthority) {
        this.ownFinanceFund = ownFinanceFund;
        this.supportTypeAuthority = supportTypeAuthority;
    }

    public OwnFinanceFund getOwnFinanceFund() {
        return ownFinanceFund;
    }

    public void setOwnFinanceFund(OwnFinanceFund ownFinanceFund) {
        this.ownFinanceFund = ownFinanceFund;
    }

    public SupportTypeAuthority getSupportTypeAuthority() {
        return supportTypeAuthority;
    }

    public void setSupportTypeAuthority(SupportTypeAuthority supportTypeAuthority) {
        this.supportTypeAuthority = supportTypeAuthority;
    }

    public ConnectedOwnFinanceFund createCopy() {
        ConnectedOwnFinanceFund copy = new ConnectedOwnFinanceFund();
        copy.ownFinanceFund = ownFinanceFund;
        copy.supportTypeAuthority = supportTypeAuthority;
        return copy;
    }
}
