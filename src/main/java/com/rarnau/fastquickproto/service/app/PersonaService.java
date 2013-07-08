package com.rarnau.fastquickproto.service.app;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rarnau.fastquickproto.model.Persona;
import com.rarnau.fastquickproto.repository.DireccionRepository;
import com.rarnau.fastquickproto.repository.PersonaRepository;

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

}
