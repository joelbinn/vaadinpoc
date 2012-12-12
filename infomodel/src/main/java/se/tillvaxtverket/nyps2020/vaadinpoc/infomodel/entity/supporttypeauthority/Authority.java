package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.supporttypeauthority;


import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Authority", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@SequenceGenerator(name = "base_sequence", sequenceName = "authority_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Authority.findByOrganisation", query = "select a from Authority a where a.name = :name"),
        @NamedQuery(name = "Authority.findAll", query = "select a from Authority a")
})

public class Authority extends BaseEntity {

    private static final long serialVersionUID = 4283102150978177481L;

    @NotNull
    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "activation_date")
    private Date activationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(name = "inactivation_date")
    private Date inactivationDate;

    @Column(name = "external_name")
    private String externalName;

    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER)
    private Set<SupportTypeAuthority> supportTypeAuthorities;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(final Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(final Date inactivationDate) {
        this.inactivationDate = inactivationDate;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(final String externalName) {
        this.externalName = externalName;
    }

    public Set<SupportTypeAuthority> getSupportTypeAuthorityList() {
        return supportTypeAuthorities;
    }

    public void setSupportTypeAuthorityList(final Set<SupportTypeAuthority> supportTypesAuthorities) {
        this.supportTypeAuthorities = supportTypesAuthorities;
    }

    @Override
    public String toString() {
        return name;
    }
}
