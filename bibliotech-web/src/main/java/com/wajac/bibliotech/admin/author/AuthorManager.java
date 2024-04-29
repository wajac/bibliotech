package com.wajac.bibliotech.admin.author;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.wajac.bibliotech.dto.DTOAuthor;
import com.wajac.bibliotech.dto.DTOBook;
import com.wajac.bibliotech.session.BibliotechFacadeLocal;
import com.wajac.bibliotech.kernel.AbstractManager;
import com.wajac.bibliotech.kernel.enums.ApplicationPageName;

@Named
@ViewScoped
public class AuthorManager extends AbstractManager {
	private static final long serialVersionUID = 1;
	private Integer uid;
	private DTOAuthor author;

	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;

	public AuthorManager() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
		if (uid != null && (author == null || !uid.equals(author.getUid()))) {
			author = facade.getAuthor(uid);
		}
	}

	public DTOAuthor getAuthor() {
		if (author == null) {
			author = new DTOAuthor();
		}
		return author;
	}

	public void setAuthor(DTOAuthor author) {
		this.author = author;
	}

	public String display() {
		return formatURL(getPageURL(ApplicationPageName.AUTHOR_DETAIL), true);
	}

	public String saveOrUpdate() {
		try {
			facade.saveOrUpdateAuthor(author);
			sendFacesMessage(formatMessage("author.save.message", null), FacesMessage.SEVERITY_INFO, true);
			return formatToCaller(true);
		} catch (Exception e) {
			sendFacesMessage(e, false);
		}
		return null;
	}

	public String delete() {
		try {
			facade.deleteAuthor(author.getUid());
			sendFacesMessage(formatMessage("author.delete.message", null), FacesMessage.SEVERITY_INFO, true);
			return formatToCaller(true);
		} catch (Exception e) {
			sendFacesMessage(formatMessage("author.delete.error.message", null), FacesMessage.SEVERITY_ERROR, false);
		}
		return null;
	}

	public void addBook() {
		author.getBooks().add(new DTOBook());
	}
	
	public void deleteBook(DTOBook book) {
		author.getBooks().remove(book);
	}

	public boolean autorisation(String action) {
		if (author == null) {
			return false;
		}
		if ("delete".equals(action) && author.getUid() != null) {
			return true;
		}
		if ("save".equals(action)) {
			return true;
		}
		return false;
	}
}
