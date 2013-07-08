/**
 *
 */
package com.rarnau.fastquickproto.repository.impl.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rarnau.fastquickproto.repository.DireccionRepository;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
@Repository
public class DireccionMongoRepositoryImpl implements DireccionRepository {

	@Autowired
	private MongoOperations mongoTemplate;

}
