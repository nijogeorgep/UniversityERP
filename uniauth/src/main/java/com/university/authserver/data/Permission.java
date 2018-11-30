/**
 * 
 */
package com.university.authserver.data;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author 553243
 *
 */
@Entity
@Table(name = "permissions")
public class Permission extends BaseEntity implements GrantedAuthority {

  /**
   * 
   */
  private static final long serialVersionUID = 4236519318158817987L;

  @NotEmpty(message = "Name is required")
  private String name;

  @ManyToMany(mappedBy = "permissions")
  private Set<Role> roles;

  public Permission() {
    super();
  }

  public Permission(String permissionName, Date createdDate) {
    this.name = permissionName;
    this.setCreated(createdDate);
  }

  @Override
  public String getAuthority() {
    return name;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the roles
   */
  public Set<Role> getRoles() {
    return roles;
  }

  /**
   * @param roles the roles to set
   */
  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }



}
