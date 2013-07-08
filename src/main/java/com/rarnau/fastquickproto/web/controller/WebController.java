package com.rarnau.fastquickproto.web.controller;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.rarnau.fastquickproto.converter.StringIdEditor;

public abstract class WebController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(true);
		binder.registerCustomEditor(String.class, "id", new StringIdEditor());

	}
}
