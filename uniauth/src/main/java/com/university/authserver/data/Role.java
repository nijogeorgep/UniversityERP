/**
 * 
 */
package com.university.authserver.data;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author 553243
 *
 */
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 8663497732751241098L;


  @NotEmpty(message = "Name is required")
  private String name;

  @ManyToMany(mappedBy = "roles")
  private List<UserDetails> users;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "role_permission",
      joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "permission_id", referencedColumnName = "id")})
  private List<Permission> permissions;

  public Role() {
    super();
  }

  public Role(String roleName, Date createdDate) {
    this.name = roleName;
    this.setCreated(createdDate);
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
   * @return the permissions
   */
  public List<Permission> getPermissions() {
    return permissions;
  }

  /**
   * @param permissions the permissions to set
   */
  public void setPermissions(List<Permission> permissions) {
    this.permissions = permissions;
  }

  /**
   * @return the users
   */
  public List<UserDetails> getUsers() {
    return users;
  }

  /**
   * @param users the users to set
   */
  public void setUsers(List<UserDetails> users) {
    this.users = users;
  }

}
