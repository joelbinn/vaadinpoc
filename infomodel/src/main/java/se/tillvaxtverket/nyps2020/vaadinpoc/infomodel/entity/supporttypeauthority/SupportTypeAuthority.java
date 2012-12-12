package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.supporttypeauthority;

import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier.ConnectedOwnFinanceFund;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "support_type_authority")
@SequenceGenerator(name = "base_sequence", sequenceName = "support_type_authority_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "SupportTypeAuthority.findBySupportTypeIdAndAuthorityName", query = "select u from SupportTypeAuthority u where u.supportType.id = :supportTypeId and u.authority.name = :authorityName"),
        @NamedQuery(name = "SupportTypeAuthority.findSupportTypeNameAndAuthorityName", query = "select u from SupportTypeAuthority u where u.supportType.name = :supportTypeName and u.authority.name = :authorityName"),
        @NamedQuery(name = "SupportTypeAuthority.findByAuthority", query = "select sta from SupportTypeAuthority sta where sta.authority = :authority"),
        @NamedQuery(name = "SupportTypeAuthority.findAll", query = "select sta from SupportTypeAuthority sta")
})

public class SupportTypeAuthority extends BaseEntity {

    private static final long serialVersionUID = 198404842192116966L;

    @ManyToOne
    @JoinColumn(name = "st_support_type_id")
    private SupportType supportType;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "activation_date")
    private Date activeFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inactivation_date")
    private Date activeTo;

    @OneToMany(mappedBy = "supportTypeAuthority", fetch = FetchType.EAGER)
    private List<ConnectedOwnFinanceFund> connectedOwnFinanceFunds;

    public SupportTypeAuthority() {
        setCreated(new Date());
        setUpdated(new Date());
        setActiveFrom(new Date());
    }

    public SupportType getSupportType() {
        return supportType;
    }

    public void setSupportType(final SupportType supportType) {
        this.supportType = supportType;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(final Authority authority) {
        this.authority = authority;
    }

    public Date getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(final Date activeFrom) {
        this.activeFrom = activeFrom;
    }

    public Date getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(final Date activeTo) {
        this.activeTo = activeTo;
    }

    public String getName() {
        return null;
    }

    public List<ConnectedOwnFinanceFund> getConnectedOwnFinanceFunds() {
        return connectedOwnFinanceFunds;
    }

    @Override
    public String toString() {
        return super.toString() + ", supportType=" + supportType + ", authority=" + authority + ", activeFrom=" + activeFrom
                + ", activeTo= " + activeTo;
    }
}
