/**
 * 
 */
package com.university.college.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 553243
 *
 */
@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public RecordNotFoundException(String string) {
    LOGGER.error(string);
  }

}
