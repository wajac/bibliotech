package com.wajac.bibliotech.session;

import javax.ejb.Stateless;

import com.wajac.bibliotech.entity.Category;

@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {

}
