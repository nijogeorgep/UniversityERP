/**
 * 
 */
package com.university.college.domain;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author 553243
 *
 */
@Document(collection = "cities")
public class City {

  @Id
  private String id;

  @Field
  @Indexed
  private String name;

  @DBRef(lazy = true)
  private State state;

  @Field
  @Indexed
  private boolean activeStatus;

  @Field
  private LocalDate createdOn;

  @Field
  private LocalDate updatedOn;

  /**
   * 
   */
  public City() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return the id
   */
  public String getId() {
    return id;
  }



  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
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
   * @return the state
   */
  public State getState() {
    return state;
  }

  /**
   * @param state the state to set
   */
  public void setState(State state) {
    this.state = state;
  }

  /**
   * @return the activeStatus
   */
  public boolean isActiveStatus() {
    return activeStatus;
  }

  /**
   * @param activeStatus the activeStatus to set
   */
  public void setActiveStatus(boolean activeStatus) {
    this.activeStatus = activeStatus;
  }

  /**
   * @return the createdOn
   */
  public LocalDate getCreatedOn() {
    return createdOn;
  }

  /**
   * @param createdOn the createdOn to set
   */
  public void setCreatedOn(LocalDate createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * @return the updatedOn
   */
  public LocalDate getUpdatedOn() {
    return updatedOn;
  }

  /**
   * @param updatedOn the updatedOn to set
   */
  public void setUpdatedOn(LocalDate updatedOn) {
    this.updatedOn = updatedOn;
  }

}
