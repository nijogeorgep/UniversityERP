/**
 * 
 */
package com.university.authserver.data;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author 553243
 *
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditEntity extends IdEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5110159153655204473L;

  @Basic(optional = false)
  @Column(name = "created", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @CreatedDate
  private Date created;

  @Basic(optional = false)
  @Column(name = "modified", nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  @LastModifiedDate
  private Date modified;

  @Version
  @Column(name = "entity_version", nullable = false)
  private Integer entityVersion;

  /**
   * @return the created
   */
  public Date getCreated() {
    return created;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(Date created) {
    this.created = created;
  }

  /**
   * @return the entityVersion
   */
  public Integer getEntityVersion() {
    return entityVersion;
  }

  /**
   * @param entityVersion the entityVersion to set
   */
  public void setEntityVersion(Integer entityVersion) {
    this.entityVersion = entityVersion;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    super.toString();
    StringBuilder builder = new StringBuilder();
    builder.append("AuditEntity [created=");
    builder.append(created);
    builder.append(", modified=");
    builder.append(modified);
    builder.append("]");
    return builder.toString();
  }



}
