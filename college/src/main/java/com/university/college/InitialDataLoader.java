/**
 * 
 */
package com.university.college;

import java.time.LocalDate;
import java.time.Month;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.university.college.domain.City;
import com.university.college.domain.College;
import com.university.college.domain.Country;
import com.university.college.domain.Hostel;
import com.university.college.domain.State;
import com.university.college.domain.Student;
import com.university.college.repository.CityRepository;
import com.university.college.repository.CollegeRepository;
import com.university.college.repository.CountryRepository;
import com.university.college.repository.HostelRepository;
import com.university.college.repository.StateRepository;
import com.university.college.repository.StudentRepository;

/**
 * @author 553243
 *
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

  private static Logger LOGGER = LoggerFactory.getLogger(InitialDataLoader.class);

  boolean alreadySetup = false;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private HostelRepository hostelRepository;

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private StateRepository stateRepository;

  @Autowired
  private CityRepository cityRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    try {

      if (alreadySetup) {
        return;
      }
      // Country
      Country newCountry = new Country();
      newCountry.setName("India");
      newCountry.setActiveStatus(true);
      countryRepository.save(newCountry);
      // State
      State newState = new State();
      newState.setName("Karnataka");
      newState.setActiveStatus(true);
      newState.setCreatedOn(LocalDate.now());
      newState.setCountry(newCountry);
      stateRepository.save(newState);
      // City
      City newCity = new City();
      newCity.setState(newState);
      newCity.setName("Bangalore");
      newCity.setActiveStatus(true);
      newCity.setCreatedOn(LocalDate.now());
      cityRepository.save(newCity);
      // College
      College newCollege = new College();
      newCollege.setName("AIMS");
      newCollege.setAddressLine1("2nd Cross, Peenya");
      newCollege.setAddressLine2("Jallahalli Cross");
      newCollege.setCity(newCity);
      newCollege.setState(newState);
      newCollege.setCountry(newCountry);
      newCollege.setActiveStatus(true);
      newCollege.setCreatedOn(LocalDate.now());
      collegeRepository.save(newCollege);
      // Hostel
      Hostel newHostel = new Hostel();
      newHostel.setName("Metro Hostel");
      newHostel.setCreatedOn(LocalDate.now());
      newHostel.setCollege(newCollege);
      newHostel.setActiveStatus(true);
      hostelRepository.save(newHostel);
      // Student
      Student newStudent = new Student();
      newStudent.setCollege(newCollege);
      newStudent.setHostel(newHostel);
      newStudent.setName("Nijo George");
      newStudent.setEmail("nijomon@gmail.com");
      newStudent.setDob(LocalDate.of(1988, Month.NOVEMBER, 4));
      newStudent.setMobileNumber("9961813268");
      newStudent.setRollNo("07SKSB3038");
      newStudent.setYear(2010);
      newStudent.setYearOfJoining(2007);
      newStudent.setCreatedOn(LocalDate.now());

      studentRepository.save(newStudent);
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
    }
    alreadySetup = true;
  }

}
