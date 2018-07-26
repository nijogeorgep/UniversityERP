/**
 * 
 */
package com.university.college.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author 553243
 *
 */
@Profile("test")
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.university.college"})
@EnableMongoRepositories(basePackages = {"com.university.college"})
@EnableAutoConfiguration
@AutoConfigureDataMongo
public class MongoConfig {

}
