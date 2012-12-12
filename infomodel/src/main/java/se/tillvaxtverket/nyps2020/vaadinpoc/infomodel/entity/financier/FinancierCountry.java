package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "financier_country", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"country_code"})
}
)
@SequenceGenerator(name = "base_sequence", sequenceName = "financier_country_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "FinancierCountry.findAll", query = "select f from FinancierCountry f"),
        @NamedQuery(name = "FinancierCountry.findByName", query = "select f from FinancierCountry f where name =:param1")
})
public class FinancierCountry extends BaseEntity {

    private static final long serialVersionUID = -3663462136224482645L;

    @NotNull
    private String name;

    @Column(name = "country_code")
    private Integer countryCode;

    @NotNull
    private boolean active;

    public FinancierCountry() {
        this.active = true;
    }

    public FinancierCountry(final String name, final Integer countryCode) {
        this.name = name;
        this.countryCode = countryCode;
        this.active = true;
    }

    public FinancierCountry(final String name, final Integer countryCode, final boolean active) {
        this(name, countryCode);
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final Integer countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((countryCode == null) ? 0 : countryCode.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + (active ? 1331 : 1337);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FinancierCountry other = (FinancierCountry) obj;
        if (countryCode == null) {
            if (other.countryCode != null) {
                return false;
            }
        } else if (!countryCode.equals(other.countryCode)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (active != other.active) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
