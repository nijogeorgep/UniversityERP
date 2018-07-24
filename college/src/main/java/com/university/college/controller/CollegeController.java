/**
 * 
 */
package com.university.college.controller;

import java.net.URI;
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
import com.university.college.exceptions.CollegeNotFoundException;
import com.university.college.repository.CollegeRepository;

/**
 * @author 553243
 *
 */
@RestController
public class CollegeController {

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/colleges")
  public ResponseEntity<List<College>> getColleges() {
    List<College> colleges = collegeRepository.findAll();
    if (colleges.isEmpty()) {
      return new ResponseEntity<List<College>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<College>>(HttpStatus.OK);
  }

  @GetMapping("/colleges/{id}")
  public ResponseEntity<College> getCollege(@PathVariable String collegeId)
      throws CollegeNotFoundException {
    Optional<College> college = collegeRepository.findById(collegeId);

    if (!college.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<College>(college.get(), HttpStatus.OK);
  }

  @PostMapping("/college")
  public ResponseEntity<Void> saveCollege(@RequestBody College college) {
    College collegesExists = mongoOperations.findOne(
        Query.query(Criteria.where("collegeId").is(college.getCollegeId())), College.class);

    if (collegesExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    collegeRepository.save(college);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(college.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/colleges/{id}")
  public ResponseEntity<College> updateCollege(@PathVariable String collegeId,
      @RequestBody College college) {
    Optional<College> collegeOptional = collegeRepository.findById(collegeId);
    if (!collegeOptional.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }
    college.setId(collegeId);

    collegeRepository.save(college);

    return new ResponseEntity<College>(collegeOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/colleges/{id}")
  public ResponseEntity<College> deleteCollege(@PathVariable String collegeId) {
    Optional<College> collegeOptional = collegeRepository.findById(collegeId);
    if (!collegeOptional.isPresent()) {
      return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
    }
    collegeRepository.deleteById(collegeId);
    return new ResponseEntity<College>(HttpStatus.NO_CONTENT);
  }
}
