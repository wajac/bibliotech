package com.wajac.bibliotech.session;

public interface JPAFacade<T extends EntityUID> {
	public T get(Integer uid);
	public T saveOrUpdate(T entity);
	public void delete(T entity);
	public void delete(Integer uid);
}
