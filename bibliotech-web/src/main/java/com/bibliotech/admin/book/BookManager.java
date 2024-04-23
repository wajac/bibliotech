package com.bibliotech.admin.book;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.bibliotech.kernel.AbstractManager;
import com.bibliotech.kernel.enums.ApplicationPageName;
import com.bibliotech.dto.DTOAuthor;
import com.bibliotech.dto.DTOBook;
import com.bibliotech.dto.DTOCategory;
import com.bibliotech.session.BibliotechFacadeLocal;

@Named
@ViewScoped
public class BookManager extends AbstractManager {
	private static final long serialVersionUID = 1;
	private Integer uid;
	private DTOBook book;
	private DTOCategory category;

	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;

	public BookManager() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
		if (uid != null && (book == null || !uid.equals(book.getUid()))) {
			book = facade.getBook(uid);
		}
	}

	public DTOBook getBook() {
		if (book == null) {
			book = new DTOBook();
		}
		return book;
	}

	public void setBook(DTOBook book) {
		this.book = book;
	}

	public DTOCategory getCategory() {
		return category;
	}

	public void setCategory(DTOCategory category) {
		this.category = category;
	}

	public String display() {
		return formatURL(getPageURL(ApplicationPageName.BOOK_DETAIL), true);
	}

	public String saveOrUpdate() {
		try {
			facade.saveOrUpdateBook(book);
			sendFacesMessage(formatMessage("book.save.message", null), FacesMessage.SEVERITY_INFO, true);
			return formatToCaller(true);
		} catch (Exception e) {
			sendFacesMessage(e, false);
		}
		return null;
	}

	public String delete() {
		try {
			facade.deleteBook(book.getUid());
			sendFacesMessage(formatMessage("book.delete.message", null), FacesMessage.SEVERITY_INFO, true);
			return formatToCaller(true);
		} catch (Exception e) {
			sendFacesMessage(formatMessage("book.delete.error.message", null), FacesMessage.SEVERITY_ERROR, false);
		}
		return null;
	}

	public void newCategory() {
		category = new DTOCategory();
	}

	public void addCategory(DTOCategory category) {
		if (category != null) {
			book.getCategories().add(category);
		}
	}
	
	public void deleteCategory(DTOCategory category) {
		book.getCategories().remove(category);
	}

	public void addAuthor() {
		book.getAuthors().add(new DTOAuthor());
	}
	
	public void deleteAuthor(DTOAuthor author) {
		book.getAuthors().remove(author);
	}

	public boolean autorisation(String action) {
		if (book == null) {
			return false;
		}
		if ("delete".equals(action) && book.getUid() != null) {
			return true;
		}
		if ("save".equals(action)) {
			return true;
		}
		return false;
	}
}
