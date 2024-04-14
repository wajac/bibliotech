package com.bibliotech.session;

import javax.ejb.Local;

import com.bibliotech.entity.Category;

@Local
public interface CategoryFacadeLocal extends JPAFacade<Category> {

}
