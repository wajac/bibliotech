package com.bibliotech.kernel.jsf.converter;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("localeConverter")
public class LocaleConverter implements Converter<Object> {
	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		String[] parts = value.split("_");
		if (parts.length == 1) {
			return new Locale(parts[0]);
		}
		return new Locale(parts[0], parts[1]);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Locale) {
			Locale locale = (Locale) value;
			if (locale.getCountry() == null) {
				return locale.getLanguage();
			}
			return locale.getLanguage() + "_" + locale.getCountry();
		}
		return value.toString();
	}
}
