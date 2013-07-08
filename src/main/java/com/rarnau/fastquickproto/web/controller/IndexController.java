/**
 *
 */
package com.rarnau.fastquickproto.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
@Controller
public class IndexController extends WebController {

	@RequestMapping
	public String index(Model model) {
		return "index";
	}

}
