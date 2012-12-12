package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "finance_fund", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "dtype"}),
        @UniqueConstraint(columnNames = {"display_name", "dtype"})
}
)
@SequenceGenerator(name = "base_sequence", sequenceName = "finance_fund_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "FinanceFund.isUsedByFinanceEvent",
                query = "select count(fe) from " +
                        "FinanceEvent fe where " +
                        "fe.financeFund=:financeFund")
})
public abstract class FinanceFund extends BaseEntity implements Comparable<FinanceFund> {

    private static final long serialVersionUID = 4409418935548167998L;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "display_name")
    private String displayName;

    @NotNull
    private boolean inactive;

    public FinanceFund() {
        this.name = null;
        this.displayName = null;
        this.inactive = false;
    }

    public FinanceFund(final String name, final String displayName, final boolean inactive) {
        this.name = name;
        this.displayName = displayName;
        this.inactive = inactive;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public boolean isInactive() {
        return inactive;
    }

    public void setInactive(final boolean inactive) {
        this.inactive = inactive;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public int compareTo(final FinanceFund o) {
        int result = 0;
        if (this.displayName != null && o.displayName != null) {
            result = this.displayName.compareTo(o.displayName);
        } else if (this.name != null && o.name != null) {
            result = this.name.compareTo(o.name);
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((displayName == null) ? 0 : displayName.hashCode());
        result = prime * result + (inactive ? 1231 : 1237);
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
        FinanceFund other = (FinanceFund) obj;
        if (displayName == null) {
            if (other.displayName != null) {
                return false;
            }
        } else if (!displayName.equals(other.displayName)) {
            return false;
        }
        if (inactive != other.inactive) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
