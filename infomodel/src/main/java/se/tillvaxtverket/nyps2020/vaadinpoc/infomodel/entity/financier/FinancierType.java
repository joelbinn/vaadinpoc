package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.financier;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.NotNull;
import se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "financiertype", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"sortIndex"})
}
)
@SequenceGenerator(name = "base_sequence", sequenceName = "financiertype_seq", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name = "findAllFinancierTypes", query = "select f from FinancierType f")
})
public class FinancierType extends BaseEntity {

    private static final long serialVersionUID = 8230998728191028464L;

    @NotNull
    private String name;

    @OneToOne
    @ForeignKey(name = "parent_financiertype_fk")
    private FinancierType parent;

    @NotNull
    private Integer sortIndex;

    @NotNull
    private boolean active;

    public FinancierType() {
        super();
        this.name = null;
        this.parent = null;
        this.sortIndex = null;
        this.active = true;
    }

    public FinancierType(final String name, final FinancierType parent, final Integer sortIndex) {
        super();
        this.name = name;
        this.parent = parent;
        this.sortIndex = sortIndex;
        this.active = true;

    }

    public FinancierType(final String name, final FinancierType parent, final Integer sortIndex, final boolean active) {
        this(name, parent, sortIndex);
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public FinancierType getParent() {
        return parent;
    }

    public void setParent(final FinancierType parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
