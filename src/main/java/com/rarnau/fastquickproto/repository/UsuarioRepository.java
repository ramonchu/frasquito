/**
 *
 */
package com.rarnau.fastquickproto.repository;

import com.rarnau.fastquickproto.model.Usuario;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public interface UsuarioRepository {

	/**
	 * @return
	 */
	long count();

	/**
	 * @param usuario
	 */
	void save(Usuario usuario);

	/**
	 * @param userId
	 * @return
	 */
	Usuario getUsuarioById(String userId);

}
