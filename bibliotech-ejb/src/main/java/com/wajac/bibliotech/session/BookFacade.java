package com.wajac.bibliotech.session;

import javax.ejb.Stateless;

import com.wajac.bibliotech.entity.Book;

@Stateless
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {

}
