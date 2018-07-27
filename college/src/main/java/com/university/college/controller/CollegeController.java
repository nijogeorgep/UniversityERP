/**
 * 
 */
package com.university.college.controller;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.university.college.domain.College;
import com.university.college.domain.Hostel;
import com.university.college.domain.Student;
import com.university.college.dto.CollegeDto;
import com.university.college.exceptions.RecordNotFoundException;
import com.university.college.repository.CityRepository;
import com.university.college.repository.CollegeRepository;
import com.university.college.repository.CountryRepository;
import com.university.college.repository.HostelRepository;
import com.university.college.repository.StateRepository;
import com.university.college.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author 553243
 *
 */
@Api(basePath = "/colleges", description = "CRUD on College", produces = "application/json")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Successful"),
    @ApiResponse(code = 401, message = "Unauthorized"),
    @ApiResponse(code = 403, message = "Forbidden"),
    @ApiResponse(code = 404, message = "Not Found")})
@RestController
public class CollegeController {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private HostelRepository hostelRepository;

  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private StateRepository stateRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @ApiOperation(value = "View List of all colleges", response = List.class)
  @GetMapping("/colleges")
  public ResponseEntity<List<College>> getColleges() {
    List<College> colleges = collegeRepository.findAll();
    if (colleges.isEmpty()) {
      return new ResponseEntity<List<College>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<College>>(colleges, HttpStatus.OK);
  }

  @ApiOperation(value = "View List of all active colleges", response = List.class)
  @GetMapping("/colleges/active")
  public ResponseEntity<List<College>> getActiveColleges() {
    List<College> activeColleges = collegeRepository.findByActiveStatus(true);
    if (activeColleges.isEmpty()) {
      return new ResponseEntity<List<College>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<College>>(activeColleges, HttpStatus.OK);
  }

  @ApiOperation(value = "View List of all inactive colleges", response = List.class)
  @GetMapping("/colleges/inactive")
  public ResponseEntity<List<College>> getInactiveColleges() {
    List<College> inactiveColleges = collegeRepository.findByActiveStatus(false);
    if (inactiveColleges.isEmpty()) {
      return new ResponseEntity<List<College>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<College>>(inactiveColleges, HttpStatus.OK);
  }

  @ApiOperation(value = "View a college", response = College.class)
  @GetMapping("/colleges/{collegeId}")
  public ResponseEntity<College> getCollege(@PathVariable String collegeId)
      throws RecordNotFoundException {
    Optional<College> college = collegeRepository.findById(collegeId);

    if (!college.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<College>(college.get(), HttpStatus.OK);
  }

  @ApiOperation(value = "View a list hostels by college", response = List.class)
  @GetMapping("/college/{collegeId}/hostels")
  public ResponseEntity<List<Hostel>> getHostelsByCollege(@PathVariable String collegeId)
      throws RecordNotFoundException {
    Optional<College> college = collegeRepository.findById(collegeId);
    if (!college.isPresent()) {
      throw new RecordNotFoundException("College Not Found");
    }

    Optional<List<Hostel>> hostelsOptional =
        hostelRepository.findAllByCollege(college.get().getId());
    if (!hostelsOptional.isPresent()) {
      throw new RecordNotFoundException("Hostels Not Found");
    }

    return new ResponseEntity<List<Hostel>>(hostelsOptional.get(), HttpStatus.OK);
  }

  @ApiOperation(value = "View a list students by college", response = List.class)
  @GetMapping("/college/{collegeId}/students")
  public ResponseEntity<List<Student>> getStudentsByCollege(@PathVariable String collegeId)
      throws RecordNotFoundException {
    Optional<College> college = collegeRepository.findById(collegeId);
    if (!college.isPresent()) {
      throw new RecordNotFoundException("College Not Found");
    }

    Optional<List<Student>> studentsOptional =
        studentRepository.findAllByCollege(college.get().getId());
    if (!studentsOptional.isPresent()) {
      throw new RecordNotFoundException("Hostels Not Found");
    }

    return new ResponseEntity<List<Student>>(studentsOptional.get(), HttpStatus.OK);
  }

  @ApiOperation(value = "Create a college", response = Void.class)
  @PostMapping("/college")
  public ResponseEntity<Void> saveCollege(@RequestBody CollegeDto collegeDto) {
    College collegesExists = mongoOperations.findOne(
        Query.query(Criteria.where("collegeId").is(collegeDto.getCollegeId())), College.class);

    if (collegesExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }
    collegesExists = new College();
    collegesExists.setName(collegeDto.getName());
    collegesExists.setCollegeId(collegeDto.getCollegeId());
    collegesExists.setAddressLine1(collegeDto.getAddressLine1());
    collegesExists.setAddressLine2(collegeDto.getAddressLine2());
    collegesExists.setCity(cityRepository.findById(collegeDto.getCity()).get());
    collegesExists.setState(stateRepository.findById(collegeDto.getState()).get());
    collegesExists.setCountry(countryRepository.findById(collegeDto.getCountry()).get());
    collegesExists.setActiveStatus(collegeDto.isActive());
    collegesExists.setCreatedOn(LocalDate.now());
    collegeRepository.save(collegesExists);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(collegesExists.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @ApiOperation(value = "Update college details by id", response = College.class)
  @PutMapping("/colleges/{collegeId}")
  public ResponseEntity<College> updateCollege(@PathVariable String collegeId,
      @RequestBody CollegeDto collegeDto) {

    Optional<College> collegeOptional = collegeRepository.findById(collegeId);
    if (!collegeOptional.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }
    College college = collegeOptional.get();
    college.setId(collegeId);
    college.setName(collegeDto.getName());
    college.setCollegeId(collegeDto.getCollegeId());
    college.setAddressLine1(collegeDto.getAddressLine1());
    college.setAddressLine2(collegeDto.getAddressLine2());
    college.setCity(cityRepository.findById(collegeDto.getCity()).get());
    college.setState(stateRepository.findById(collegeDto.getState()).get());
    college.setCountry(countryRepository.findById(collegeDto.getCountry()).get());
    college.setActiveStatus(collegeDto.isActive());
    college.setUpdatedOn(LocalDate.now());
    collegeRepository.save(college);

    return new ResponseEntity<College>(college, HttpStatus.OK);
  }

  @ApiOperation(value = "Delete college details by id", response = College.class)
  @DeleteMapping("/colleges/{collegeId}")
  public ResponseEntity<College> deleteCollege(@PathVariable String collegeId) {
    Optional<College> collegeOptional = collegeRepository.findById(collegeId);
    if (!collegeOptional.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }
    collegeRepository.deleteById(collegeId);
    return new ResponseEntity<College>(HttpStatus.NO_CONTENT);
  }
}
