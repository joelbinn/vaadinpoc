package se.tillvaxtverket.nyps2020.vaadinpoc.infomodel.entity.supporttypeauthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: Ove Noros, Contactor AB
 * Date: 2012-02-23
 * Time: 11:35
 */
@Entity
@Table(name = "SUPPORT_TYPE")
@NamedQueries({
        @NamedQuery(name = "SupportType.findByName", query = "select s from SupportType s where s.name = :name")
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "SupportType.findLeafs", query =
                "select st.* " +
                        "from " +
                        "(select * " +
                        "  from SUPPORT_TYPE_CHILDREN stc " +
                        "  start with stc.ST_SUPPORT_TYPE_ID=(select stc2.st_support_type_id from support_type stc2 where stc2.st_name = :supportTypeName) " +
                        "  connect by prior stc.ST_SUPPORT_TYPE_CHILD_ID=stc.ST_SUPPORT_TYPE_ID " +
                        ") stc1," +
                        "support_type st " +
                        "where " +
                        "  stc1.st_support_type_child_id=st.st_support_type_id and " +
                        "  not exists (select 1 from support_type_children stc2 where stc2.st_support_type_id=stc1.st_support_type_child_id)",
                resultClass = SupportType.class
        ),
        @NamedNativeQuery(name = "SupportType.findParents", query =
                "select st.* " +
                        "from " +
                        "(select * " +
                        "  from SUPPORT_TYPE_CHILDREN stc " +
                        "  start with stc.ST_SUPPORT_TYPE_CHILD_ID=(select stc2.st_support_type_id from support_type stc2 where stc2.st_name = :supportTypeName) " +
                        "  connect by prior stc.ST_SUPPORT_TYPE_ID=stc.ST_SUPPORT_TYPE_CHILD_ID " +
                        ") stc1," +
                        "support_type st " +
                        "where " +
                        "  stc1.st_support_type_id=st.st_support_type_id",
                resultClass = SupportType.class
        )
})
public class SupportType implements Serializable {

    private static final long serialVersionUID = -1255395324508812143L;

    @Id
    @Column(name = "ST_SUPPORT_TYPE_ID")
    private Long id;

    @Column(name = "ST_NAME")
    private String name;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "ST_ACTIVATION_DATE")
    private Date activationDate;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name = "ST_INACTIVATION_DATE")
    private Date inActivationDate;

    @Column(name = "ST_EXTERNAL_NAME")
    private String externalName;

    @Column(name = "ST_ROOT_NAME")
    private String rootName;

    @Column(name = "ST_NODE_LEVEL")
    private Integer nodeLevel;

    @Column(name = "ST_APPLICATION_ID")
    private String applicationId;

    @Column(name = "ST_PAY_APPLICATION_ID")
    private String payApplicationId;

    @Column(name = "ST_DECREE")
    private String decree;

    @Column(name = "ST_FUND")
    private String fund;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNameWithoutPrefix() {
        String result = this.name;
        if (this.name != null && this.name.length() > 0) {
            int index = this.name.indexOf(" ");
            if (index > 0) {
                result = this.name.substring(index + 1);
            }
        }
        return result;
    }

    public Date getActivationDate() {
        return activationDate;
    }

    public void setActivationDate(final Date activationDate) {
        this.activationDate = activationDate;
    }

    public Date getInActivationDate() {
        return inActivationDate;
    }

    public void setInActivationDate(final Date inActivationDate) {
        this.inActivationDate = inActivationDate;
    }

    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(final String externalName) {
        this.externalName = externalName;
    }

    public String getRootName() {
        return rootName;
    }

    public void setRootName(final String rootName) {
        this.rootName = rootName;
    }

    public Integer getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(final Integer nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(final String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPayApplicationId() {
        return payApplicationId;
    }

    public void setPayApplicationId(final String payApplicationId) {
        this.payApplicationId = payApplicationId;
    }

    public String getDecree() {
        return decree;
    }

    public void setDecree(final String decree) {
        this.decree = decree;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(final String fund) {
        this.fund = fund;
    }

    @Override
    public String toString() {
        return getName();
    }
}
