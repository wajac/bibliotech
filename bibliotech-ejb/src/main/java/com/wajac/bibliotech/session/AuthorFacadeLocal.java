package com.wajac.bibliotech.session;

import javax.ejb.Local;

import com.wajac.bibliotech.entity.Author;

@Local
public interface AuthorFacadeLocal extends JPAFacade<Author> {

}
