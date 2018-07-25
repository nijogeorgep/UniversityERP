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
@Document(collection = "students")
public class Student {

  @Id
  private String Id;

  @Field
  @Indexed
  private String name;

  @Field
  @Indexed
  private String rollNo;

  @Field
  @Indexed
  private LocalDate dob;

  @Field
  @Indexed
  private String email;

  @Field
  @Indexed
  private String mobileNumber;

  @Field
  @Indexed
  private Integer year;

  @Field
  @Indexed
  private Integer yearOfJoining;

  @DBRef(lazy = true)
  private College college;

  @DBRef(lazy = true)
  private Hostel hostel;

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
  public Student() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @return the id
   */
  public String getId() {
    return Id;
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
   * @return the rollNo
   */
  public String getRollNo() {
    return rollNo;
  }

  /**
   * @param rollNo the rollNo to set
   */
  public void setRollNo(String rollNo) {
    this.rollNo = rollNo;
  }

  /**
   * @return the dob
   */
  public LocalDate getDob() {
    return dob;
  }

  /**
   * @param dob the dob to set
   */
  public void setDob(LocalDate dob) {
    this.dob = dob;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the mobileNumber
   */
  public String getMobileNumber() {
    return mobileNumber;
  }

  /**
   * @param mobileNumber the mobileNumber to set
   */
  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  /**
   * @return the year
   */
  public Integer getYear() {
    return year;
  }

  /**
   * @param year the year to set
   */
  public void setYear(Integer year) {
    this.year = year;
  }

  /**
   * @return the yearOfJoining
   */
  public Integer getYearOfJoining() {
    return yearOfJoining;
  }

  /**
   * @param yearOfJoining the yearOfJoining to set
   */
  public void setYearOfJoining(Integer yearOfJoining) {
    this.yearOfJoining = yearOfJoining;
  }

  /**
   * @return the college
   */
  public College getCollege() {
    return college;
  }

  /**
   * @param college the college to set
   */
  public void setCollege(College college) {
    this.college = college;
  }

  /**
   * @return the hostel
   */
  public Hostel getHostel() {
    return hostel;
  }

  /**
   * @param hostel the hostel to set
   */
  public void setHostel(Hostel hostel) {
    this.hostel = hostel;
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
