package com.wajac.bibliotech.kernel.jsf.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.wajac.bibliotech.dto.DTOCategory;
import com.wajac.bibliotech.session.BibliotechFacadeLocal;

@FacesConverter(value = "categoryConverter", managed = true)
public class CategoryConverter implements Converter<Object> {
	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return facade.getCategory(Integer.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof DTOCategory) {
			return ((DTOCategory) value).getUid().toString();
		}
		return value.toString();
	}
}
