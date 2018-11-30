/**
 * 
 */
package com.university.authserver.service;

import com.university.authserver.data.UserDetails;

/**
 * @author 553243
 *
 */
public interface AuthorizationService {
  /**
   * Get an authorized user object.
   *
   * @return Returns the user object
   */
  UserDetails getUser();

  /**
   * Get an authorised user ID.
   *
   * @return Returns the user ID
   */
  Long getUserId();

  /**
   * Get an authorised username.
   *
   * @return Returns the username
   */
  String getUsername();
}
