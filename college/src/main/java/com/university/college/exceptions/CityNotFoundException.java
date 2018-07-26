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
public class CityNotFoundException extends Exception {

  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public CityNotFoundException(String string) {
    LOGGER.error(string);
  }

}
