package com.wajac.bibliotech.session;

import javax.ejb.Stateless;

import com.wajac.bibliotech.entity.Book;
import com.wajac.bibliotech.enums.PersistenceMode;
import com.wajac.bibliotech.exception.ValidationException;

@Stateless
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {
	@Override
	public void check(Book entity, PersistenceMode persistenceMode) throws ValidationException {
		// No additional checks been used yet, since a book may be published in multiple editions
		super.check(entity, persistenceMode);
	}
}
