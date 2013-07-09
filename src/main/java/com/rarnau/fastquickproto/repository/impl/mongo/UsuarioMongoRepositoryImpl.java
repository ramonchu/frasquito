/**
 *
 */
package com.rarnau.fastquickproto.repository.impl.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.rarnau.fastquickproto.model.Usuario;
import com.rarnau.fastquickproto.repository.UsuarioRepository;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Repository
public class UsuarioMongoRepositoryImpl implements UsuarioRepository {

	@Autowired
	private MongoOperations mongoTemplate;

	@Override
	public long count() {
		Query query = new Query();
		return mongoTemplate.count(query, Usuario.class);
	}

	@Override
	public void save(Usuario usuario) {
		mongoTemplate.save(usuario);
	}

	@Override
	public Usuario getUsuarioById(String userId) {
		Query query = Query.query(Criteria.where("username").is(userId));
		List<Usuario> usuarios = mongoTemplate.find(query, Usuario.class);
		if (usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
	}

	@Override
	public Usuario getUsuarioByEmail(String email) {
		Query query = Query.query(Criteria.where("email").is(email));
		List<Usuario> usuarios = mongoTemplate.find(query, Usuario.class);
		if (usuarios.size() > 0) {
			return usuarios.get(0);
		}
		return null;
	}

}
