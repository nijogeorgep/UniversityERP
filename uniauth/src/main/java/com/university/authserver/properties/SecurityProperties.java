/**
 * 
 */
package com.university.authserver.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 553243
 *
 */
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
  // Pattern
  /**
   * Pattern
   */
  private String pattern;

  // Form Login
  /**
   * The login page
   */
  private String loginPage;
  /**
   * Username parameter
   */
  private String usernameParameter;
  /**
   * Password parameter
   */
  private String passwordParameter;

  // Logout
  /**
   * Invalidate HTTP session
   */
  private boolean invalidateHttpSession;
  /**
   * Clear authentication
   */
  private boolean clearAuthentication;
  /**
   * Logout address
   */
  private String logoutRequestMatcher;
  /**
   * Address of successful logout
   */
  private String logoutSuccessUrl;

  // Session Management
  /**
   * Invalid session URL
   */
  private String invalidSessionUrl;
  /**
   * Maximum number of sessions
   */
  private int maximumSessions;
  /**
   * Expired URL
   */
  private String expiredUrl;

  // Remember Me
  /**
   * Remember-me parameter
   */
  private String rememberMeParameter;
  /**
   * Remember-me cookie name
   */
  private String rememberMeCookieName;
  /**
   * Token validity seconds
   */
  private int tokenValiditySeconds;

  // Password Encoder
  /**
   * The strength of the password encoder
   */
  private int strengthPasswordEncoder;

  /**
   * @return the pattern
   */
  public String getPattern() {
    return pattern;
  }

  /**
   * @param pattern the pattern to set
   */
  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  /**
   * @return the loginPage
   */
  public String getLoginPage() {
    return loginPage;
  }

  /**
   * @param loginPage the loginPage to set
   */
  public void setLoginPage(String loginPage) {
    this.loginPage = loginPage;
  }

  /**
   * @return the usernameParameter
   */
  public String getUsernameParameter() {
    return usernameParameter;
  }

  /**
   * @param usernameParameter the usernameParameter to set
   */
  public void setUsernameParameter(String usernameParameter) {
    this.usernameParameter = usernameParameter;
  }

  /**
   * @return the passwordParameter
   */
  public String getPasswordParameter() {
    return passwordParameter;
  }

  /**
   * @param passwordParameter the passwordParameter to set
   */
  public void setPasswordParameter(String passwordParameter) {
    this.passwordParameter = passwordParameter;
  }

  /**
   * @return the invalidateHttpSession
   */
  public boolean isInvalidateHttpSession() {
    return invalidateHttpSession;
  }

  /**
   * @param invalidateHttpSession the invalidateHttpSession to set
   */
  public void setInvalidateHttpSession(boolean invalidateHttpSession) {
    this.invalidateHttpSession = invalidateHttpSession;
  }

  /**
   * @return the clearAuthentication
   */
  public boolean isClearAuthentication() {
    return clearAuthentication;
  }

  /**
   * @param clearAuthentication the clearAuthentication to set
   */
  public void setClearAuthentication(boolean clearAuthentication) {
    this.clearAuthentication = clearAuthentication;
  }

  /**
   * @return the logoutRequestMatcher
   */
  public String getLogoutRequestMatcher() {
    return logoutRequestMatcher;
  }

  /**
   * @param logoutRequestMatcher the logoutRequestMatcher to set
   */
  public void setLogoutRequestMatcher(String logoutRequestMatcher) {
    this.logoutRequestMatcher = logoutRequestMatcher;
  }

  /**
   * @return the logoutSuccessUrl
   */
  public String getLogoutSuccessUrl() {
    return logoutSuccessUrl;
  }

  /**
   * @param logoutSuccessUrl the logoutSuccessUrl to set
   */
  public void setLogoutSuccessUrl(String logoutSuccessUrl) {
    this.logoutSuccessUrl = logoutSuccessUrl;
  }

  /**
   * @return the invalidSessionUrl
   */
  public String getInvalidSessionUrl() {
    return invalidSessionUrl;
  }

  /**
   * @param invalidSessionUrl the invalidSessionUrl to set
   */
  public void setInvalidSessionUrl(String invalidSessionUrl) {
    this.invalidSessionUrl = invalidSessionUrl;
  }

  /**
   * @return the maximumSessions
   */
  public int getMaximumSessions() {
    return maximumSessions;
  }

  /**
   * @param maximumSessions the maximumSessions to set
   */
  public void setMaximumSessions(int maximumSessions) {
    this.maximumSessions = maximumSessions;
  }

  /**
   * @return the expiredUrl
   */
  public String getExpiredUrl() {
    return expiredUrl;
  }

  /**
   * @param expiredUrl the expiredUrl to set
   */
  public void setExpiredUrl(String expiredUrl) {
    this.expiredUrl = expiredUrl;
  }

  /**
   * @return the rememberMeParameter
   */
  public String getRememberMeParameter() {
    return rememberMeParameter;
  }

  /**
   * @param rememberMeParameter the rememberMeParameter to set
   */
  public void setRememberMeParameter(String rememberMeParameter) {
    this.rememberMeParameter = rememberMeParameter;
  }

  /**
   * @return the rememberMeCookieName
   */
  public String getRememberMeCookieName() {
    return rememberMeCookieName;
  }

  /**
   * @param rememberMeCookieName the rememberMeCookieName to set
   */
  public void setRememberMeCookieName(String rememberMeCookieName) {
    this.rememberMeCookieName = rememberMeCookieName;
  }

  /**
   * @return the tokenValiditySeconds
   */
  public int getTokenValiditySeconds() {
    return tokenValiditySeconds;
  }

  /**
   * @param tokenValiditySeconds the tokenValiditySeconds to set
   */
  public void setTokenValiditySeconds(int tokenValiditySeconds) {
    this.tokenValiditySeconds = tokenValiditySeconds;
  }

  /**
   * @return the strengthPasswordEncoder
   */
  public int getStrengthPasswordEncoder() {
    return strengthPasswordEncoder;
  }

  /**
   * @param strengthPasswordEncoder the strengthPasswordEncoder to set
   */
  public void setStrengthPasswordEncoder(int strengthPasswordEncoder) {
    this.strengthPasswordEncoder = strengthPasswordEncoder;
  }


}
