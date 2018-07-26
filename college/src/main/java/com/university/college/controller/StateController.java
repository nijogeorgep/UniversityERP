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
import com.university.college.domain.State;
import com.university.college.exceptions.CollegeNotFoundException;
import com.university.college.exceptions.CountryNotFoundException;
import com.university.college.exceptions.StateNotFoundException;
import com.university.college.repository.CollegeRepository;
import com.university.college.repository.StateRepository;

/**
 * @author 553243
 *
 */
@RestController
public class StateController {
  @Autowired
  private StateRepository stateRepository;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/states")
  public ResponseEntity<List<State>> getStates() {
    List<State> states = stateRepository.findAll();
    if (states.isEmpty()) {
      return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<State>>(states, HttpStatus.OK);
  }

  @GetMapping("/states/active")
  public ResponseEntity<List<State>> getActiveStates() {
    List<State> activeStates = stateRepository.findByActiveStatus(true);
    if (activeStates.isEmpty()) {
      return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<State>>(activeStates, HttpStatus.OK);
  }

  @GetMapping("/states/inactive")
  public ResponseEntity<List<State>> getInactiveStates() {
    List<State> inactiveStates = stateRepository.findByActiveStatus(false);
    if (inactiveStates.isEmpty()) {
      return new ResponseEntity<List<State>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<State>>(inactiveStates, HttpStatus.OK);
  }

  @GetMapping("/states/{stateId}")
  public ResponseEntity<State> getState(@PathVariable String stateId)
      throws StateNotFoundException {
    Optional<State> state = stateRepository.findById(stateId);

    if (!state.isPresent()) {
      return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<State>(state.get(), HttpStatus.OK);
  }

  @GetMapping("/states/{stateId}/colleges")
  public ResponseEntity<List<College>> getHostelsByCollege(
      @PathVariable(required = true, value = "stateId") String stateId)
      throws CollegeNotFoundException, CountryNotFoundException {
    Optional<State> stateOptional = stateRepository.findById(stateId);
    if (!stateOptional.isPresent()) {
      throw new CountryNotFoundException("State Not Found");
    }

    Optional<List<College>> collegeOptional =
        collegeRepository.findAllByCity(stateOptional.get().getId());
    if (!collegeOptional.isPresent()) {
      throw new CollegeNotFoundException("College Not Found");
    }

    return new ResponseEntity<List<College>>(collegeOptional.get(), HttpStatus.OK);
  }

  @PostMapping("/state")
  public ResponseEntity<Void> saveState(@RequestBody State state) {
    State statesExists =
        mongoOperations.findOne(Query.query(Criteria.where("_id").is(state.getId())), State.class);

    if (statesExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    stateRepository.save(state);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(state.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/states/{stateId}")
  public ResponseEntity<State> updateState(@PathVariable String stateId, @RequestBody State state) {
    Optional<State> stateOptional = stateRepository.findById(stateId);
    if (!stateOptional.isPresent()) {
      return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
    }
    state.setId(stateId);

    stateRepository.save(state);

    return new ResponseEntity<State>(stateOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/states/{stateId}")
  public ResponseEntity<State> deleteState(@PathVariable String stateId) {
    Optional<State> stateOptional = stateRepository.findById(stateId);
    if (!stateOptional.isPresent()) {
      return new ResponseEntity<State>(HttpStatus.NOT_FOUND);
    }
    stateRepository.deleteById(stateId);
    return new ResponseEntity<State>(HttpStatus.NO_CONTENT);
  }
}
