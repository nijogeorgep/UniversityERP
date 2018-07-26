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
public class StateNotFoundException extends Exception {

  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public StateNotFoundException(String string) {
    LOGGER.error(string);
  }
}
