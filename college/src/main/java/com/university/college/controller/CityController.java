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
import com.university.college.domain.City;
import com.university.college.domain.College;
import com.university.college.exceptions.CityNotFoundException;
import com.university.college.exceptions.CollegeNotFoundException;
import com.university.college.repository.CityRepository;
import com.university.college.repository.CollegeRepository;

/**
 * @author 553243
 *
 */
@RestController
public class CityController {

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private CollegeRepository collegeRepository;

  @Autowired
  private MongoOperations mongoOperations;

  @GetMapping("/cities")
  public ResponseEntity<List<City>> getCitys() {
    List<City> cities = cityRepository.findAll();
    if (cities.isEmpty()) {
      return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
  }

  @GetMapping("/cities/active")
  public ResponseEntity<List<City>> getActiveCitys() {
    List<City> activeCities = cityRepository.findByActiveStatus(true);
    if (activeCities.isEmpty()) {
      return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<City>>(activeCities, HttpStatus.OK);
  }

  @GetMapping("/cities/inactive")
  public ResponseEntity<List<City>> getInactiveCitys() {
    List<City> inactiveCities = cityRepository.findByActiveStatus(false);
    if (inactiveCities.isEmpty()) {
      return new ResponseEntity<List<City>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<City>>(inactiveCities, HttpStatus.OK);
  }

  @GetMapping("/cities/{cityId}")
  public ResponseEntity<City> getCity(@PathVariable String cityId) throws CityNotFoundException {
    Optional<City> city = cityRepository.findById(cityId);

    if (!city.isPresent()) {
      return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<City>(city.get(), HttpStatus.OK);
  }

  @GetMapping("/cities/{cityId}/colleges")
  public ResponseEntity<List<College>> getHostelsByCollege(
      @PathVariable(required = true, value = "cityId") String cityId)
      throws CollegeNotFoundException, CityNotFoundException {
    Optional<City> city = cityRepository.findById(cityId);
    if (!city.isPresent()) {
      throw new CityNotFoundException("City Not Found");
    }

    Optional<List<College>> collegeOptional = collegeRepository.findAllByCity(city.get().getId());
    if (!collegeOptional.isPresent()) {
      throw new CollegeNotFoundException("College Not Found");
    }

    return new ResponseEntity<List<College>>(collegeOptional.get(), HttpStatus.OK);
  }

  @PostMapping("/city")
  public ResponseEntity<Void> saveCity(@RequestBody City city) {
    City citiesExists =
        mongoOperations.findOne(Query.query(Criteria.where("_id").is(city.getId())), City.class);

    if (citiesExists != null) {
      return new ResponseEntity<Void>(HttpStatus.CONFLICT);
    }

    cityRepository.save(city);

    HttpHeaders headers = new HttpHeaders();
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(city.getId()).toUri();
    headers.setLocation(location);

    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
  }

  @PutMapping("/cities/{cityId}")
  public ResponseEntity<City> updateCity(@PathVariable String cityId, @RequestBody City city) {
    Optional<City> cityOptional = cityRepository.findById(cityId);
    if (!cityOptional.isPresent()) {
      return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
    }
    city.setId(cityId);

    cityRepository.save(city);

    return new ResponseEntity<City>(cityOptional.get(), HttpStatus.OK);
  }

  @DeleteMapping("/cities/{cityId}")
  public ResponseEntity<City> deleteCity(@PathVariable String cityId) {
    Optional<City> cityOptional = cityRepository.findById(cityId);
    if (!cityOptional.isPresent()) {
      return new ResponseEntity<City>(HttpStatus.NOT_FOUND);
    }
    cityRepository.deleteById(cityId);
    return new ResponseEntity<City>(HttpStatus.NO_CONTENT);
  }
}
