package com.wajac.bibliotech.session;

import javax.ejb.Local;

import com.wajac.bibliotech.entity.Book;

@Local
public interface BookFacadeLocal extends JPAFacade<Book> {

}
