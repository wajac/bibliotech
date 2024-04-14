package com.bibliotech.session;

import javax.ejb.Local;

import com.bibliotech.entity.Book;

@Local
public interface BookFacadeLocal extends JPAFacade<Book> {

}
