package com.bibliotech.search;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.bibliotech.kernel.AbstractManager;
import com.bibliotech.kernel.enums.ApplicationPageName;
import com.bibliotech.dto.DTOSearchBooks;

@Named
@ViewScoped
public class BookSearchManager extends AbstractManager {
	public static final long serialVersionUID = 1;

	public String display() {
		return formatURL(getPageURL(ApplicationPageName.BOOK_SEARCH), true);
	}

	public void onRowClick(SelectEvent event) {
		DTOSearchBooks dtoSearchBooks = (DTOSearchBooks) event.getObject();
		if (dtoSearchBooks != null) {
			navigateTo(getPageURL(ApplicationPageName.BOOK_DETAIL), true,
					ApplicationPageName.BOOK_SEARCH.name(), dtoSearchBooks.getBookUid());
		}
	}

	public void create() {
		navigateTo(getPageURL(ApplicationPageName.BOOK_DETAIL), true, ApplicationPageName.BOOK_SEARCH.name(),
				null);
	}
}
