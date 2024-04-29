package com.wajac.bibliotech.session;

import javax.ejb.Local;

import com.wajac.bibliotech.entity.Category;

@Local
public interface CategoryFacadeLocal extends JPAFacade<Category> {

}
