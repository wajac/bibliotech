package com.wajac.bibliotech.session;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wajac.bibliotech.enums.PersistenceMode;
import com.wajac.bibliotech.exception.ExceptionCause;
import com.wajac.bibliotech.exception.ValidationException;
import com.wajac.bibliotech.utils.ColumnAttributes;
import com.wajac.bibliotech.utils.Utils;

public abstract class AbstractFacade<T extends EntityUID> implements JPAFacade<T> {
	@PersistenceContext(name = "BibliotechPU")
	private EntityManager entityManager;
	private Class<?> clazz;
	private Map<String, ColumnAttributes> mapAttributes;

	public AbstractFacade() {
		clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Map<String, ColumnAttributes> getMapAttributes() throws Exception {
		if (mapAttributes == null) {
			mapAttributes = new HashMap<String, ColumnAttributes>();
			for (PropertyDescriptor pdesc : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
				ColumnAttributes columnAttributes = new ColumnAttributes(clazz);
				columnAttributes.setName(pdesc.getName());
				columnAttributes.setColumnClass(pdesc.getPropertyType());
				columnAttributes.setReadMethod(pdesc.getReadMethod());
				columnAttributes.setWriteMethod(pdesc.getWriteMethod());
				for (Column annotation : pdesc.getReadMethod().getAnnotationsByType(Column.class)) {
					columnAttributes.setNullable(annotation.nullable());
					columnAttributes.setLength(annotation.length());
				}
				mapAttributes.put(columnAttributes.getLogicalName(), columnAttributes);
			}
		}
		return mapAttributes;
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
	public T saveOrUpdate(T entity) throws ValidationException {
		if (entity.getUid() == null) {
			check(entity, PersistenceMode.CREATE);
			entityManager.persist(entity);
		} else {
			check(entity, PersistenceMode.UPDATE);
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
	
	@Override
	public void check(T entity, PersistenceMode persistenceMode) throws ValidationException {
		ValidationException ve = new ValidationException();

		try {
			for (ColumnAttributes columnAttributes : getMapAttributes().values()) {
				Object value = columnAttributes.getReadMethod().invoke(entity, new Object[] {});
				if ("majDate".equals(columnAttributes.getName())) {
					columnAttributes.getWriteMethod().invoke(entity, new Date());
				} else {
					// Standard checks for nullable and length constraints
					if (!"uid".equals(columnAttributes.getName()) && !columnAttributes.isNullable()
							&& !Utils.isSet(value)) {
						ve.addExceptionCause(new ExceptionCause("exception.field.required",
								new Object[] { columnAttributes.getLogicalName() }));
					}
					if (value != null && columnAttributes.getColumnClass() == String.class
							&& value.toString().length() > columnAttributes.getLength()) {
						ve.addExceptionCause(new ExceptionCause("exception.field.length.exceeded",
								new Object[] { columnAttributes.getLogicalName(), columnAttributes.getLength() }));
					}
				}
			}
		} catch (Exception e) {
			ve.addExceptionCause(new ExceptionCause("exception.unknown", new Object[] { e }));
		}

		if (ve.hasErrors()) {
			throw ve;
		}
	}
}
