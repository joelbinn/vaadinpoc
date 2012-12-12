package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "finance_method", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@SequenceGenerator(name = "base_sequence", sequenceName = "finance_method_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "FinanceMethod.findAll", query = "select f from FinanceMethod f"),
        @NamedQuery(name = "FinanceMethod.findByName", query = "select f from FinanceMethod f where f.name = :name")
})
public class FinanceMethod extends BaseEntity {

    private static final long serialVersionUID = 7308104564012168176L;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type")
    private FinanceMethodType financeMethodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "specific_type")
    private FinanceMethodType financeMethodSpecificType;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "financing_type")
    private FinanceMethodFinancingType financeMethodFinancingType;

    @NotNull
    private boolean inActive;

    @NotNull
    private boolean ownFinancing;

    @NotNull
    @Column(name = "eu_based_financing")
    private boolean euBasedFinancing;


    public FinanceMethod() {
        this.name = null;
        this.financeMethodType = null;
        this.financeMethodFinancingType = null;
        this.inActive = false;
        this.ownFinancing = false;
        this.euBasedFinancing = false;
    }


    public FinanceMethod(String name, FinanceMethodType financeMethodType,
                         FinanceMethodFinancingType financeMethodFinancingType,
                         boolean inActive, boolean ownFinancing, boolean euBasedFinancing) {
        this.name = name;
        this.financeMethodType = financeMethodType;
        this.financeMethodFinancingType = financeMethodFinancingType;
        this.inActive = inActive;
        this.ownFinancing = ownFinancing;
        this.euBasedFinancing = euBasedFinancing;
    }

    public static boolean isPublicFinancingType(FinanceMethodType financeMethodType) {
        return (financeMethodType == FinanceMethodType.Offentlig);
    }

    public static boolean isPrivateFinancingtype(FinanceMethodType financeMethodType) {
        return (financeMethodType == FinanceMethodType.Privat);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isInActive() {
        return inActive;
    }

    public void setInActive(final boolean inActive) {
        this.inActive = inActive;
    }

    public FinanceMethodType getFinanceMethodType() {
        return financeMethodType;
    }

    public void setFinanceMethodType(FinanceMethodType financeMethodType) {
        this.financeMethodType = financeMethodType;
    }

    public FinanceMethodFinancingType getFinanceMethodFinancingType() {
        return financeMethodFinancingType;
    }

    public void setFinanceMethodFinancingType(
            final FinanceMethodFinancingType financeMethodFinancingType) {
        this.financeMethodFinancingType = financeMethodFinancingType;
    }

    public boolean isOwnFinancing() {
        return ownFinancing;
    }

    public void setOwnFinancing(final boolean ownFinancing) {
        this.ownFinancing = ownFinancing;
    }

    public boolean isEuBasedFinancing() {
        return euBasedFinancing;
    }

    public boolean getEuBasedFinancing() {
        return euBasedFinancing;
    }

    public void euBasedFinancing(final boolean euBasedFinancing) {
        this.euBasedFinancing = euBasedFinancing;
    }

    public FinanceMethodType getFinanceMethodSpecificType() {
        FinanceMethodType type = null;
        if (financeMethodSpecificType != null) {
            type = financeMethodSpecificType;
        } else {
            type = financeMethodType;
        }
        return type;
    }

    public void setFinanceMethodSpecificType(
            FinanceMethodType financeMethodSpecificType) {
        this.financeMethodSpecificType = financeMethodSpecificType;
    }

    public boolean isPrivate() {
        return (financeMethodType != null && isPrivateFinancingtype(financeMethodType));
    }

    public boolean isPublic() {
        return (financeMethodType != null && isPublicFinancingType(financeMethodType));
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((financeMethodFinancingType == null) ? 0
                : financeMethodFinancingType.hashCode());
        result = prime * result
                + ((financeMethodType == null) ? 0 : financeMethodType.hashCode());
        result = prime * result + (inActive ? 1231 : 1237);
        result = prime * result + (ownFinancing ? 1331 : 1337);
        result = prime * result + (euBasedFinancing ? 1331 : 1337);
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        FinanceMethod other = (FinanceMethod) obj;
        if (financeMethodFinancingType != other.financeMethodFinancingType) {
            return false;
        }
        if (financeMethodType != other.financeMethodType) {
            return false;
        }
        if (inActive != other.inActive) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (ownFinancing != other.ownFinancing) {
            return false;
        }
        if (euBasedFinancing != other.euBasedFinancing) {
            return false;
        }
        return true;
    }

}
