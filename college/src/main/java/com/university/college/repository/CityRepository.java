/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.City;

/**
 * @author 553243
 *
 */
public interface CityRepository extends MongoRepository<City, String> {

}
