/**
 *
 */
package com.rarnau.fastquickproto.social;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rarnau.fastquickproto.model.Usuario;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public class MongoUserDetailsService implements UserDetailsService {

	private MongoTemplate mongoTemplate;

	public MongoUserDetailsService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query query = query(where("username").is(username));
		List<Usuario> usuarios = mongoTemplate.find(query, Usuario.class);
		if (usuarios.size() == 0) {
			usuarios = mongoTemplate.find(query(where("email").is(username)), Usuario.class);
		}

		if (usuarios.size() > 0) {
			Usuario usuario = usuarios.get(0);
			Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			if (usuario.getRoles() != null) {
				for (String userRole : usuario.getRoles()) {
					authorities.add(new SimpleGrantedAuthority(userRole));
				}
			}
			UserDetails user = new User(username, usuario.getPassword(), authorities);
			return user;
		}
		return null;
	}
}
