package com.rarnau.fastquickproto.repository.impl.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rarnau.fastquickproto.model.Direccion;
import com.rarnau.fastquickproto.model.Persona;
import com.rarnau.fastquickproto.repository.PersonaRepository;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
@Repository
public class PersonaMongoRepositoryImpl implements PersonaRepository {

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Iterable<Persona> getPersonas() {
		return mongoOperations.findAll(Persona.class);
	}

	@Override
	public void savePersona(Persona persona) {
		mongoOperations.save(persona);
		if (persona.getDirecciones() != null) {
			for (Direccion d : persona.getDirecciones()) {
				mongoOperations.save(d);
			}
		}
	}

}
