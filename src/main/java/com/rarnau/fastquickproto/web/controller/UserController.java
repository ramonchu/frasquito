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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.rarnau.fastquickproto.common.exception.DuplicateUserException;
import com.rarnau.fastquickproto.model.Usuario;
import com.rarnau.fastquickproto.service.UsuarioService;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Controller
public class UserController extends WebController {

	@Autowired
	private UsuarioService usuarioService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		super.initBinder(binder);
		binder.addValidators(new SignupFormValidator());
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
	public String checkAdminUser(Model model) throws DuplicateUserException {
		if (usuarioService.getNumUsuarios() == 0) {
			Usuario usuario = new Usuario();
			usuario.setPassword(DigestUtils.md5Hex("entrar"));
			usuario.setUsername("ramonchu");
			usuario.setEmail("ramon.arnau@gmail.com");
			usuario.setRoles(Arrays.asList("admin", "user"));
			usuarioService.saveUsuario(usuario);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm(WebRequest request, Model model) {
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
		SignupForm form = null;
		if (connection != null) {
			form = SignupForm.fromProviderUser(connection.fetchUserProfile());
		} else {
			form = new SignupForm();
		}
		model.addAttribute("signupForm", form);
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Valid SignupForm form, BindingResult formBinding, WebRequest request, Model model) throws DuplicateUserException {
		if (formBinding.hasErrors()) {
			model.addAttribute("signupForm", form);
			return "signup";
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
	 * @throws DuplicateUserException
	 */
	private Usuario createUsuario(SignupForm form, BindingResult formBinding) throws DuplicateUserException {
		Usuario usuario = new Usuario();
		usuario.setEmail(form.getEmail());
		usuario.setUsername(form.getUsername());
		usuario.setRoles(Arrays.asList("user"));
		usuario.setPassword(DigestUtils.md5Hex(form.getPassword()));
		usuarioService.saveUsuario(usuario);
		return usuario;
	}

}
