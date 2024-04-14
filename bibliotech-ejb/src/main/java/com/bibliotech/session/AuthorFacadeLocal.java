package com.bibliotech.session;

import javax.ejb.Local;

import com.bibliotech.entity.Author;

@Local
public interface AuthorFacadeLocal extends JPAFacade<Author> {

}
