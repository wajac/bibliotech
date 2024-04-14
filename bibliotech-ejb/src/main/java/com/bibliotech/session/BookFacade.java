package com.bibliotech.session;

import javax.ejb.Stateless;

import com.bibliotech.entity.Book;

@Stateless
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {

}
