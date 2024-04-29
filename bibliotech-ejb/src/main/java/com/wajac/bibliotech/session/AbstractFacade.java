package com.wajac.bibliotech.session;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractFacade<T extends EntityUID> implements JPAFacade<T> {
	@PersistenceContext(name = "BibliotechPU")
	private EntityManager entityManager;
	private Class<?> clazz;

	public AbstractFacade() {
		super();
		clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Integer uid) {
		try {
			return (T) entityManager.find(clazz, uid);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public T saveOrUpdate(T entity) {
		if (entity.getUid() == null) {
			entityManager.persist(entity);
		} else {
			entity = entityManager.merge(entity);
		}
		return entity;
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void delete(Integer uid) {
		entityManager.remove(get(uid));
	}

}
