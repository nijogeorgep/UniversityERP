/**
 * 
 */
package com.university.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author 553243
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

  @Autowired(required = true)
  private AuthenticationManager authenticationManager;

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.oauth2.config.annotation.web.configuration.
   * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
   * annotation.web.configurers.AuthorizationServerEndpointsConfigurer)
   */
  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.oauth2.config.annotation.web.configuration.
   * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
   * annotation.web.configurers.AuthorizationServerSecurityConfigurer)
   */
  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.oauth2.config.annotation.web.configuration.
   * AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.
   * annotation.configurers.ClientDetailsServiceConfigurer)
   */
  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory().withClient("myClientId").secret("mySecret")
        .authorizedGrantTypes("authorization_code").scopes("user_info")
        .autoApprove(true)
    /* .accessTokenValiditySeconds(3000) */;
  }



}
