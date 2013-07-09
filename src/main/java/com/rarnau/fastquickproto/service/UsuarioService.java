package com.rarnau.fastquickproto.service;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rarnau.fastquickproto.common.exception.DuplicateUserException;
import com.rarnau.fastquickproto.model.Usuario;
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
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/**
	 * @return
	 */
	public long getNumUsuarios() {
		return usuarioRepository.count();
	}

	/**
	 * @param usuario
	 * @throws DuplicateUserException 
	 */
	public void saveUsuario(Usuario usuario) throws DuplicateUserException {
		if (null != usuarioRepository.getUsuarioById(usuario.getUsername()) || null != usuarioRepository.getUsuarioByEmail(usuario.getEmail())) {
			throw new DuplicateUserException();
		}

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
