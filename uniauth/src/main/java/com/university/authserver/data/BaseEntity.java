/**
 * 
 */
package com.university.authserver.data;

import java.util.UUID;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

/**
 * @author 553243
 *
 */
@MappedSuperclass
public class BaseEntity extends AuditEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1775306341544298093L;

  @Basic(optional = false)
  @Column(name = "unique_id", nullable = false, unique = true, updatable = false)
  private String uniqueId;

  /**
   * Default constructor.
   */
  BaseEntity() {
    super();
  }

  /**
   * Generate a uniqueId if it is null.
   */
  @PrePersist
  protected void onCreateBaseEntity() {
    // Make sure we have an uuid if one wasn't entered beforehand
    if (this.uniqueId == null) {
      this.uniqueId = UUID.randomUUID().toString();
    }
  }

  /**
   * @return the uniqueId
   */
  public String getUniqueId() {
    return uniqueId;
  }

  /**
   * @param uniqueId the uniqueId to set
   */
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
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
    builder.append("BaseEntity [uniqueId=");
    builder.append(uniqueId);
    builder.append("]");
    return builder.toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    BaseEntity other = (BaseEntity) obj;
    if (uniqueId == null) {
      if (other.uniqueId != null)
        return false;
    } else if (!uniqueId.equals(other.uniqueId))
      return false;
    return true;
  }


}
