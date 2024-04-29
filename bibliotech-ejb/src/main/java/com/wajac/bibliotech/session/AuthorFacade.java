package com.wajac.bibliotech.session;

import javax.ejb.Stateless;

import com.wajac.bibliotech.entity.Author;

@Stateless
public class AuthorFacade extends AbstractFacade<Author> implements AuthorFacadeLocal {

}
