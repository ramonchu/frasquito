/**
 *
 */
package com.rarnau.fastquickproto.web.controller.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rarnau.fastquickproto.converter.StringIdEditor;
import com.rarnau.fastquickproto.model.Persona;
import com.rarnau.fastquickproto.service.app.PersonaService;
import com.rarnau.fastquickproto.web.controller.WebController;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Controller
public class PersonaController extends WebController {

	@Autowired
	private PersonaService personaService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(true);
		binder.registerCustomEditor(String.class, "id", new StringIdEditor());

	}

	@RequestMapping("/listPersonas")
	public String listPersonas(Model model) {
		model.addAttribute("personas", personaService.getPersonas());
		model.addAttribute("persona", new Persona());
		return "personas";
	}

	@RequestMapping("/addPersona")
	public String addPersona(@Validated Persona persona, BindingResult bindingResult, Model model) {
		model.addAttribute("persona", persona);
		if (!bindingResult.hasErrors()) {
			personaService.savePersona(persona);
			return "redirect:/listPersonas";
		} else {
			return "personas";
		}
	}

}
