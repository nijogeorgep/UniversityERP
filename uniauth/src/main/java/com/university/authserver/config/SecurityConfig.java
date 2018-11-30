/**
 * 
 */
package com.university.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import com.university.authserver.properties.SecurityProperties;
import com.university.authserver.service.MyUserDetailsService;

/**
 * @author 553243
 *
 */
@Configuration
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private SecurityProperties securityProperties;

  @Autowired
  private MyUserDetailsService userDetailsService;

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }



  /**
   * Service interface for encoding passwords.
   */
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(securityProperties.getStrengthPasswordEncoder());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
   * configure(org.springframework.security.config.annotation.authentication.builders.
   * AuthenticationManagerBuilder)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    /*
     * auth.parentAuthenticationManager(authenticationManager).inMemoryAuthentication()
     * .withUser("admin").password("admin").roles("ADMIN");
     */

    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
   * configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.requestMatchers().antMatchers("/login", "/oauth/authorize").and().authorizeRequests()
        .anyRequest().authenticated().and().formLogin().permitAll();
  }


}
