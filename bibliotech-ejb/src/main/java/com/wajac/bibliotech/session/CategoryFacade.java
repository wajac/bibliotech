package com.wajac.bibliotech.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.wajac.bibliotech.entity.Category;
import com.wajac.bibliotech.enums.PersistenceMode;
import com.wajac.bibliotech.exception.ValidationException;
import com.wajac.bibliotech.exception.ExceptionCause;
import com.wajac.bibliotech.utils.QueryUtils;
import com.wajac.bibliotech.utils.Utils;

@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal {
	@Override
	public void check(Category entity, PersistenceMode persistenceMode) throws ValidationException {
		ValidationException ve = null;

		// Performing the standard checks
		try {
			super.check(entity, persistenceMode);
			ve = new ValidationException();
		} catch (ValidationException e) {
			ve = e;
		}

		// Checking for duplicates
		if (Utils.isSet(entity.getCode())) {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(c) from Category c where upper(c.code) = upper(:code)");
			
			QueryUtils.addQueryParameter(sb, "c.uid", "uid", "and", "<>", entity.getUid());

			Query query = getEntityManager().createQuery(sb.toString());

			query.setParameter("code", entity.getCode());
			QueryUtils.setQueryParameter(query, "uid", entity.getUid());

			Number count = (Number) query.getSingleResult();
			if (count.intValue() > 0) {
				ve.addExceptionCause(new ExceptionCause("exception.duplicate",
						new Object[] { "category.code", entity.getCode() }));
			}
		}

		// Any validation errors been caused?
		if (ve.hasErrors()) {
			throw ve;
		}
	}
}
