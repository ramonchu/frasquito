package com.rarnau.fastquickproto.test.unitcase;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rarnau.fastquickproto.model.Persona;
import com.rarnau.fastquickproto.service.app.PersonaService;
import com.rarnau.fastquickproto.test.config.AbstractTest;

public class TestCase00 extends AbstractTest {

	@Autowired
	private PersonaService personaService;

	@Test
	public void test00() {
		Set<Persona> personas = new HashSet<Persona>();
		for (Persona p : personaService.getPersonas()) {
			personas.add(p);
		}

		Assert.assertTrue(personas.size() > 0);
	}

}
