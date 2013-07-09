/**
 *
 */
package com.rarnau.fastquickproto.security;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
public class MongoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// Revisar la implementación de
		// org.springframework.security.authentication.dao.DaoAuthenticationProvider.retrieveUser(...)
		UserDetails loadedUser;
		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
		} catch (UsernameNotFoundException notFound) {
			// if (authentication.getCredentials() != null) {
			// String presentedPassword =
			// authentication.getCredentials().toString();
			// passwordEncoder.matches(presentedPassword, nu ll);
			// }
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		if (!authentication.isAuthenticated()) {
			String enc = DigestUtils.md5Hex(authentication.getCredentials().toString());
			if (!StringUtils.equals(loadedUser.getPassword(), enc)) {
				throw new AuthenticationServiceException("credentials not correct");
			}
		}

		if (loadedUser == null) {
			throw new AuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	// public PasswordEncoder getPasswordEncoder() {
	// return passwordEncoder;
	// }
	//
	// public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
	// this.passwordEncoder = passwordEncoder;
	// }

}
