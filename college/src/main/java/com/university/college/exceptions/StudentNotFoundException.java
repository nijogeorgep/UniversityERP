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
public class StudentNotFoundException extends Exception {

  Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  public StudentNotFoundException(String string) {
    LOGGER.error(string);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#getCause()
   */
  @Override
  public synchronized Throwable getCause() {
    // TODO Auto-generated method stub
    return super.getCause();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#getMessage()
   */
  @Override
  public String getMessage() {
    // TODO Auto-generated method stub
    return super.getMessage();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#toString()
   */
  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return super.toString();
  }

}
