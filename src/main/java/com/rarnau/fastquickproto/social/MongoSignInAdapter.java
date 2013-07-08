/**
 *
 */
package com.rarnau.fastquickproto.social;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.rarnau.fastquickproto.model.Usuario;
import com.rarnau.fastquickproto.service.UsuarioService;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public class MongoSignInAdapter implements SignInAdapter {

	private final UsuarioService personaService;

	public MongoSignInAdapter(UsuarioService personaService) {
		this.personaService = personaService;
	}

	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
		Usuario usuario = personaService.getUsuarioById(userId);
		if (usuario != null && usuario.getRoles() != null) {
			Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			for (String rol : usuario.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(rol));
			}
			SecurityContextHolder.getContext().setAuthentication(
					new UsernamePasswordAuthenticationToken(userId, null, authorities));
		}
		return null;
	}

}
