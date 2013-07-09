/**
 *
 */
package com.rarnau.fastquickproto.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.rarnau.fastquickproto.common.Messages;

/**
 * @author Ramón Arnau Gómez, 2013
 * 
 */
public class SignupFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignupForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignupForm form = (SignupForm) target;

		if (StringUtils.isBlank(form.getEmail()) || !StringUtils.equals(form.getEmail(), form.getEmail2())) {
			errors.rejectValue("email", Messages.CODE_EMAIL_INCORRECT);
		}

		if (StringUtils.isBlank(form.getPassword()) || !StringUtils.equals(form.getPassword(), form.getPassword2())) {
			errors.rejectValue("email", Messages.CODE_PASSWD_INCORRECT);
		}
	}

}
