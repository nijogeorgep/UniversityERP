/**
 * 
 */
package com.university.college.dto;

import java.time.LocalDate;

/**
 * @author 553243
 *
 */
public class StudentDto {

  private String name;

  private String rollNo;

  private LocalDate dob;

  private String email;

  private String mobileNumber;

  private Integer year;

  private Integer yearOfJoining;

  private String college;

  private String hostel;

  private boolean activeStatus;

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
  public String getCollege() {
    return college;
  }

  /**
   * @param college the college to set
   */
  public void setCollege(String college) {
    this.college = college;
  }

  /**
   * @return the hostel
   */
  public String getHostel() {
    return hostel;
  }

  /**
   * @param hostel the hostel to set
   */
  public void setHostel(String hostel) {
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


}
