package com.rarnau.fastquickproto.service;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rarnau.fastquickproto.model.Persona;
import com.rarnau.fastquickproto.model.Usuario;
import com.rarnau.fastquickproto.repository.DireccionRepository;
import com.rarnau.fastquickproto.repository.PersonaRepository;
import com.rarnau.fastquickproto.repository.UsuarioRepository;

/**
 *
 */

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
@Service
@Transactional
@MonitoredWithSpring
public class PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DireccionRepository direccionRepository;

	public Iterable<Persona> getPersonas() {
		return personaRepository.getPersonas();
	}

	/**
	 * @param persona
	 */
	public void savePersona(Persona persona) {
		personaRepository.savePersona(persona);
	}

	/**
	 * @return
	 */
	public long getNumUsuarios() {
		return usuarioRepository.count();
	}

	/**
	 * @param usuario
	 */
	public void saveUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	/**
	 * @param userId
	 * @return
	 */
	public Usuario getUsuarioById(String userId) {
		return usuarioRepository.getUsuarioById(userId);
	}

}
