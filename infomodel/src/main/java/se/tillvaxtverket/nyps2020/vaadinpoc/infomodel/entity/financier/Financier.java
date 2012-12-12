package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ove Noros, CAG Contactor AB,
 * @see Finansier It should not be possible to delete a financier if it
 *      previously have been referenced in a payment.
 */

@Entity
@Table(name = "financier",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"company_number"}),
                @UniqueConstraint(columnNames = {"foreign_company_number"}),
                @UniqueConstraint(columnNames = {"abbreviation"}),
                @UniqueConstraint(columnNames = {"shortCode"})
        }
)
@SequenceGenerator(name = "base_sequence", sequenceName = "financier_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "Financier.findByCompanyNumber", query = "select f from Financier f where f.companyNumber = :companyNumber"),
        @NamedQuery(name = "Financier.findById", query = "select f from Financier f where f.id = :id"),
        @NamedQuery(name = "Financier.findByAbbreviation", query = "select f from Financier f where f.abbreviation = :abbreviation"),
        @NamedQuery(name = "Financier.findAll", query = "select f from Financier f "),
        @NamedQuery(name = "Financier.findAllActive", query = "select f from Financier f where f.active = true"),
        @NamedQuery(name = "Financier.findActiveByCountry", query = "select f from Financier f where f.active = true and f.country = :country"),
        @NamedQuery(name = "Financier.findAllActiveNotNor", query = "select f from Financier f where f.active = true and f.country <> :country")
})

public class Financier extends BaseEntity {

    private static final long serialVersionUID = -6339095375739919628L;

    @OneToMany(mappedBy = "financier", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ValidFinanceFund> validFinanceFunds;

    @OneToOne
    @NotNull
    @ForeignKey(name = "financier_financiertype_fk")
    private FinancierType financierType;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "company_number")
    private String companyNumber;

    @NotNull
    @Column(name = "is_fake_company_number")
    private boolean isFakeCompanyNumber;

    @Column(name = "foreign_company_number")
    private String foreignCompanyNumber;

    @OneToOne
    @NotNull
    @ForeignKey(name = "financier_country_fk")
    private FinancierCountry country;

    @NotNull
    private boolean active;

    private String shortCode;

    private String abbreviation;

    @NotNull
    private boolean hasFunds;

    public Financier() {
        this.active = true;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }


    public void setCompanyNumber(final String companyNumber) {
        this.companyNumber = companyNumber;
    }


    public boolean isFakeCompanyNumber() {
        return isFakeCompanyNumber;
    }


    public void setFakeCompanyNumber(final boolean isFakeCompanyNumber) {
        this.isFakeCompanyNumber = isFakeCompanyNumber;
    }


    public String getForeignCompanyNumber() {
        return foreignCompanyNumber;
    }


    public void setForeignCompanyNumber(final String foreignCompanyNumber) {
        this.foreignCompanyNumber = foreignCompanyNumber;
    }

    public FinancierCountry getCountry() {
        return country;
    }

    public void setCountry(final FinancierCountry country) {
        this.country = country;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(final String shortCode) {
        this.shortCode = shortCode;
    }

    public FinancierType getFinancierType() {
        return financierType;
    }

    public void setFinancierType(final FinancierType financierType) {
        this.financierType = financierType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<ValidFinanceFund> getValidFinanceFunds() {
        if (validFinanceFunds == null) {
            validFinanceFunds = new ArrayList<ValidFinanceFund>();
        }
        return validFinanceFunds;
    }

    public void setValidFinanceFunds(final List<ValidFinanceFund> validFinanceFunds) {
        this.validFinanceFunds = validFinanceFunds;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(final String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public boolean isHasFunds() {
        return hasFunds;
    }

    public void setHasFunds(final boolean hasFunds) {
        this.hasFunds = hasFunds;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((abbreviation == null) ? 0 : abbreviation.hashCode());
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result
                + ((financierType == null) ? 0 : financierType.hashCode());
        result = prime * result
                + ((foreignCompanyNumber == null) ? 0 : foreignCompanyNumber.hashCode());
        result = prime * result + (hasFunds ? 1231 : 1237);
        result = prime * result + (isFakeCompanyNumber ? 1231 : 1237);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((companyNumber == null) ? 0 : companyNumber.hashCode());
        result = prime * result + ((shortCode == null) ? 0 : shortCode.hashCode());
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
        Financier other = (Financier) obj;
        if (abbreviation == null) {
            if (other.abbreviation != null) {
                return false;
            }
        } else if (!abbreviation.equals(other.abbreviation)) {
            return false;
        }
        if (active != other.active) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (financierType == null) {
            if (other.financierType != null) {
                return false;
            }
        } else if (!financierType.equals(other.financierType)) {
            return false;
        }
        if (foreignCompanyNumber == null) {
            if (other.foreignCompanyNumber != null) {
                return false;
            }
        } else if (!foreignCompanyNumber.equals(other.foreignCompanyNumber)) {
            return false;
        }
        if (hasFunds != other.hasFunds) {
            return false;
        }
        if (isFakeCompanyNumber != other.isFakeCompanyNumber) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (companyNumber == null) {
            if (other.companyNumber != null) {
                return false;
            }
        } else if (!companyNumber.equals(other.companyNumber)) {
            return false;
        }
        if (shortCode == null) {
            if (other.shortCode != null) {
                return false;
            }
        } else if (!shortCode.equals(other.shortCode)) {
            return false;
        }
        return true;
    }
}
