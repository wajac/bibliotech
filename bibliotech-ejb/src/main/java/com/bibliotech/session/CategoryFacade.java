package com.bibliotech.session;

import javax.ejb.Stateless;

import com.bibliotech.entity.Category;

@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {

}
