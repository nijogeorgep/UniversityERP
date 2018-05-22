/**
 * 
 */
package com.university.college.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.university.college.domain.Student;

/**
 * @author 553243
 *
 */
public interface StudentRepository extends MongoRepository<Student, String> {

}
