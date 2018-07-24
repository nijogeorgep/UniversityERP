/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.university.college.domain.Country;

/**
 * @author 553243
 *
 */
public interface CountryRepository extends MongoRepository<Country, String> {

}
