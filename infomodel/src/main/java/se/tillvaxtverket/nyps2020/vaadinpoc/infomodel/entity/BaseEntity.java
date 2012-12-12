package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity;

import org.hibernate.validator.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 8222376358893905687L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_sequence")
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date created;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @NotNull
    private Date updated;

    @NotNull
    @Column(name = "updated_by_user")
    private String updatedByUser;

    public BaseEntity() {
        this.created = this.updated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(final Date updated) {
        this.updated = updated;
    }

    public String getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(final String updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": id=" + id + " created=" + created;
    }
}
