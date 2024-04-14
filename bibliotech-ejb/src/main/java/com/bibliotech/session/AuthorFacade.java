package com.bibliotech.session;

import javax.ejb.Stateless;

import com.bibliotech.entity.Author;

@Stateless
public class AuthorFacade extends AbstractFacade<Author> implements AuthorFacadeLocal {

}
