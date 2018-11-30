/**
 * 
 */
package com.university.authserver.data;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.Type;


/**
 * @author 553243
 *
 */
@Entity
@Table(name = "user")
public class UserDetails extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4362647647146177208L;

  @NotEmpty(message = "Username is required.")
  private String username;

  @NotEmpty(message = "Password is required.")
  private String password;

  @Column(name = "failed_attempts")
  private Integer failedAttempts;

  @Column(name = "password_expiry_date")
  private Date passwordExpiryDate;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  @Column(name = "role", nullable = false, updatable = false)
  private List<Role> roles;

  @Type(type = "yes_no")
  private Boolean locked;

  @Type(type = "yes_no")
  private Boolean enabled;


  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the serialversionuid
   */
  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the failedAttempts
   */
  public Integer getFailedAttempts() {
    return failedAttempts;
  }

  /**
   * @param failedAttempts the failedAttempts to set
   */
  public void setFailedAttempts(Integer failedAttempts) {
    this.failedAttempts = failedAttempts;
  }

  /**
   * @return the passwordExpiryDate
   */
  public Date getPasswordExpiryDate() {
    return passwordExpiryDate;
  }

  /**
   * @param passwordExpiryDate the passwordExpiryDate to set
   */
  public void setPasswordExpiryDate(Date passwordExpiryDate) {
    this.passwordExpiryDate = passwordExpiryDate;
  }

  /**
   * @return the roles
   */
  public List<Role> getRoles() {
    return roles;
  }

  /**
   * @param roles the roles to set
   */
  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  /**
   * @return the locked
   */
  public Boolean getLocked() {
    return locked;
  }

  /**
   * @param locked the locked to set
   */
  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  /**
   * @return the enabled
   */
  public Boolean getEnabled() {
    return enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }



}
