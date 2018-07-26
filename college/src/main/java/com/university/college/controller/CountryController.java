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
import com.university.college.domain.Country;
import com.university.college.exceptions.CollegeNotFoundException;
import com.university.college.exceptions.CountryNotFoundException;
import com.university.college.repository.CollegeRepository;
import com.university.college.repository.CountryRepository;

/**
 * @author 553243
 *
 */
@RestController
public class CountryController {
  @Autowired
  private CountryRepository countryRepository;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/countries")
  public ResponseEntity<List<Country>> getCountrys() {
    List<Country> countries = countryRepository.findAll();
    if (countries.isEmpty()) {
      return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
  }

  @GetMapping("/countries/active")
  public ResponseEntity<List<Country>> getActiveCountrys() {
    List<Country> activeCountries = countryRepository.findByActiveStatus(true);
    if (activeCountries.isEmpty()) {
      return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Country>>(activeCountries, HttpStatus.OK);
  }

  @GetMapping("/countries/inactive")
  public ResponseEntity<List<Country>> getInactiveCountrys() {
    List<Country> inactiveCountries = countryRepository.findByActiveStatus(false);
    if (inactiveCountries.isEmpty()) {
      return new ResponseEntity<List<Country>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<Country>>(inactiveCountries, HttpStatus.OK);
  }

  @GetMapping("/countries/{countryId}")
  public ResponseEntity<Country> getCountry(@PathVariable String countryId)
      throws CountryNotFoundException {
    Optional<Country> country = countryRepository.findById(countryId);

    if (!country.isPresent()) {
      return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Country>(country.get(), HttpStatus.OK);
  }

  @GetMapping("/countries/{countryId}/colleges")
  public ResponseEntity<List<College>> getHostelsByCollege(
      @PathVariable(required = true, value = "countryId") String countryId)
      throws CollegeNotFoundException, CountryNotFoundException {
    Optional<Country> countryOptional = countryRepository.findById(countryId);
    if (!countryOptional.isPresent()) {
      throw new CountryNotFoundException("Country Not Found");
    }

    Optional<List<College>> collegeOptional =
        collegeRepository.findAllByCity(countryOptional.get().getId());
    if (!collegeOptional.isPresent()) {
      throw new CollegeNotFoundException("College Not Found");
    }

    return new ResponseEntity<List<College>>(collegeOptional.get(), HttpStatus.OK);
  }

  @PostMapping("/country")
  public ResponseEntity<Void> saveCountry(@RequestBody Country country) {
    Country countriesExists = mongoOperations
        .findOne(Query.query(Criteria.where("_id").is(country.getId())), Country.class);

    if (countriesExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    countryRepository.save(country);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(country.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/countries/{countryId}")
  public ResponseEntity<Country> updateCountry(@PathVariable String countryId,
      @RequestBody Country country) {
    Optional<Country> countryOptional = countryRepository.findById(countryId);
    if (!countryOptional.isPresent()) {
      return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
    }
    country.setId(countryId);

    countryRepository.save(country);

    return new ResponseEntity<Country>(countryOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/countries/{countryId}")
  public ResponseEntity<Country> deleteCountry(@PathVariable String countryId) {
    Optional<Country> countryOptional = countryRepository.findById(countryId);
    if (!countryOptional.isPresent()) {
      return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
    }
    countryRepository.deleteById(countryId);
    return new ResponseEntity<Country>(HttpStatus.NO_CONTENT);
  }
}
