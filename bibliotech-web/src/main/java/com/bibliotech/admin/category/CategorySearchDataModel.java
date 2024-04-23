package com.bibliotech.admin.category;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.bibliotech.dto.DTOCategory;
import com.bibliotech.session.BibliotechFacadeLocal;

@Named
@ApplicationScoped
public class CategorySearchDataModel implements Serializable {
	public static final long serialVersionUID = 1;
	private List<DTOCategory> categories;
	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;

	public List<DTOCategory> getCategories() {
		if (categories == null) {
			categories = facade.searchCategories(false, 0, 0, null, null).getRecords();
		}
		return categories;
	}

	public void reset() {
		categories = null;
	}
}
