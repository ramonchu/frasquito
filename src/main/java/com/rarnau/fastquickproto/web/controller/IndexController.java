/**
 *
 */
package com.rarnau.fastquickproto.web.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.validation.Valid;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.rarnau.fastquickproto.model.Usuario;
import com.rarnau.fastquickproto.service.UsuarioService;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Controller
public class IndexController extends WebController {

	@Autowired
	private UsuarioService personaService;

	@RequestMapping
	public String index(Model model) {
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(Model model) {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "login";
	}

	@RequestMapping("/checkAdminUser")
	public String checkAdminUser(Model model) {
		if (personaService.getNumUsuarios() == 0) {
			Usuario usuario = new Usuario();
			usuario.setPassword(DigestUtils.md5Hex("entrar"));
			usuario.setUsername("ramonchu");
			usuario.setEmail("ramon.arnau@gmail.com");
			usuario.setRoles(Arrays.asList("admin", "user"));
			personaService.saveUsuario(usuario);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public SignupForm signupForm(WebRequest request) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		if (connection != null) {
			return SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			return new SignupForm();
		}
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request) {
		if (formBinding.hasErrors()) {
			return null;
		}
		Usuario usuario = createUsuario(form, formBinding);
		if (usuario != null) {
			signin(usuario);
			ProviderSignInUtils.handlePostSignUp(usuario.getUsername(), request);
			return "redirect:/";
		}
		return null;
	}

	/**
	 * @param username
	 */
	private void signin(Usuario usuario) {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		if (usuario.getRoles() != null) {
			for (String role : usuario.getRoles()) {
				authorities.add(new SimpleGrantedAuthority(role));
			}
		}
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(usuario.getUsername(), null, authorities));

	}

	/**
	 * @param form
	 * @param formBinding
	 * @return
	 */
	private Usuario createUsuario(SignupForm form, BindingResult formBinding) {
		Usuario usuario = new Usuario();
		usuario.setEmail(form.getEmail());
		usuario.setUsername(form.getUsername());
		usuario.setRoles(Arrays.asList("user"));
		personaService.saveUsuario(usuario);
		return usuario;
	}

}
