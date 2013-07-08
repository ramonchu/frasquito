/**
 *
 */
package com.rarnau.fastquickproto.repository;

import com.rarnau.fastquickproto.model.Persona;


/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public interface PersonaRepository  {

	/**
	 * @return
	 */
	Iterable<Persona> getPersonas();

	/**
	 * @param persona
	 */
	void savePersona(Persona persona);

}
