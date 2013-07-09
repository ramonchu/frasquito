package com.rarnau.fastquickproto.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.rarnau.fastquickproto.common.Messages;
import com.rarnau.fastquickproto.common.exception.FrasquitoException;

public class WebExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView mav = new ModelAndView("error");
		if (ex instanceof FrasquitoException) {
			String msgCode = ((FrasquitoException) ex).getMessageCode();
			mav.addObject("msgCode", msgCode);

		} else {
			mav.addObject("msgCode", Messages.CODE_GENERIC_ERROR);
		}
		return mav;
	}
}
