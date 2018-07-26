/**
 * 
 */
package com.university.college.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 553243
 *
 */
@SuppressWarnings("serial")
public class CollegeNotFoundException extends Exception {

  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public CollegeNotFoundException(String string) {
    LOGGER.error(string);
  }
}