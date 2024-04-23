package com.bibliotech.admin.category;

import java.util.HashSet;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import com.bibliotech.kernel.AbstractManager;
import com.bibliotech.kernel.enums.ApplicationPageName;
import com.bibliotech.dto.DTOBook;
import com.bibliotech.dto.DTOCategory;
import com.bibliotech.session.BibliotechFacadeLocal;

@Named
@ViewScoped
public class CategorySearchManager extends AbstractManager {
	public static final long serialVersionUID = 1;
	private DTOCategory category;
	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;
	@Inject
	private CategorySearchDataModel dataModel;

	public DTOCategory getCategory() {
		return category;
	}

	public void setCategory(DTOCategory category) {
		this.category = category;
	}

	public String getDialogTitle() {
		if (category == null) {
			return null;
		}
		if (category.getUid() == null) {
			return formatMessage("category.dialog.title.create", null);
		}
		return formatMessage("category.dialog.title.update", null);
	}

	public String display() {
		return formatURL(getPageURL(ApplicationPageName.CATEGORY_SEARCH), true);
	}

	public void create() {
		category = new DTOCategory();
	}

	public void updateRow(SelectEvent event) {
		DTOCategory dto = (DTOCategory) event.getObject();
		category = new DTOCategory(dto.getUid(), dto.getCode(), dto.getLabel(), new HashSet<DTOBook>());
		for (DTOBook dtoBook : dto.getBooks()) {
			category.getBooks().add(dtoBook);
		}
	}

	public void delete(Integer uid) {
		try {
			facade.deleteCategory(uid);
			dataModel.reset();
			PrimeFaces.current().ajax().update("form1:list1");
		} catch (Exception e) {
			sendFacesMessage(e, false);
		}
	}

	public String saveOrUpdate() {
		try {
			category = facade.saveOrUpdateCategory(category);
			dataModel.reset();
			PrimeFaces.current().executeScript("PF('createWidget').hide()");
			PrimeFaces.current().ajax().update("form1:list1");
		} catch (Exception e) {
			sendFacesMessage(e, false);
		}
		return null;
	}
}
