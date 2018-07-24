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
@Document(collection = "colleges")
public class College {

  @Id
  private String Id;

  @Field
  @Indexed
  private Long collegeId;

  @Field
  @Indexed
  private String name;

  @Field
  private String addressLine1;

  @Field
  private String addressLine2;

  @DBRef
  private City city;

  @DBRef
  private State state;

  @DBRef
  private Country country;

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
  public College() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return the id
   */
  public String getId() {
    return Id;
  }

  /**
   * @return the collegeId
   */
  public Long getCollegeId() {
    return collegeId;
  }

  /**
   * @param collegeId the collegeId to set
   */
  public void setCollegeId(Long collegeId) {
    this.collegeId = collegeId;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    Id = id;
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
   * @return the addressLine1
   */
  public String getAddressLine1() {
    return addressLine1;
  }

  /**
   * @param addressLine1 the addressLine1 to set
   */
  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  /**
   * @return the addressLine2
   */
  public String getAddressLine2() {
    return addressLine2;
  }

  /**
   * @param addressLine2 the addressLine2 to set
   */
  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  /**
   * @return the city
   */
  public City getCity() {
    return city;
  }

  /**
   * @param city the city to set
   */
  public void setCity(City city) {
    this.city = city;
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
   * @return the country
   */
  public Country getCountry() {
    return country;
  }

  /**
   * @param country the country to set
   */
  public void setCountry(Country country) {
    this.country = country;
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
