package com.wajac.bibliotech.session;

import com.wajac.bibliotech.enums.PersistenceMode;
import com.wajac.bibliotech.exception.ValidationException;

public interface JPAFacade<T extends EntityUID> {
	public T get(Integer uid);
	public T saveOrUpdate(T entity) throws ValidationException;
	public void delete(T entity);
	public void delete(Integer uid);
	public void check(T entity, PersistenceMode persistenceMode) throws ValidationException;
}
