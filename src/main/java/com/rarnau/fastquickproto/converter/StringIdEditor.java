/**
 *
 */
package com.rarnau.fastquickproto.converter;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Ramón Arnau Gómez, 2013
 *
 */
public class StringIdEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) {
		setValue(StringUtils.trimToNull(text));
	}

	@Override
	public String getAsText() {
		String value = (String) getValue();
		if (value == null) {
			return StringUtils.EMPTY;
		}
		return StringUtils.trimToEmpty(value);
	}

}
