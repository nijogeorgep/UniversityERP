/**
 * 
 */
package com.university.college.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.City;

/**
 * @author 553243
 *
 */
public interface CityRepository extends MongoRepository<City, String> {

  List<City> findByActiveStatus(boolean active);

}
