package com.wajac.bibliotech.session;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.wajac.bibliotech.entity.Author;
import com.wajac.bibliotech.enums.PersistenceMode;
import com.wajac.bibliotech.exception.ExceptionCause;
import com.wajac.bibliotech.exception.ValidationException;
import com.wajac.bibliotech.utils.QueryUtils;
import com.wajac.bibliotech.utils.Utils;

@Stateless
public class AuthorFacade extends AbstractFacade<Author> implements AuthorFacadeLocal {
	@Override
	public void check(Author entity, PersistenceMode persistenceMode) throws ValidationException {
		ValidationException ve = null;

		// Performing the standard checks
		try {
			super.check(entity, persistenceMode);
			ve = new ValidationException();
		} catch (ValidationException e) {
			ve = e;
		}

		// Checking for duplicates
		if (Utils.isSet(entity.getFirstName()) && Utils.isSet(entity.getLastName())) {
			StringBuilder sb = new StringBuilder();
			sb.append("select count(a) from Author a where 1=1");
			
			QueryUtils.addQueryParameter(sb, "a.uid", "uid", "and", "<>", entity.getUid());
			QueryUtils.addQueryParameter(sb, "a.firstName", "firstName", "and", "=", entity.getFirstName());
			QueryUtils.addQueryParameter(sb, "a.lastName", "lastName", "and", "=", entity.getLastName());

			Query query = getEntityManager().createQuery(sb.toString());

			QueryUtils.setQueryParameter(query, "uid", entity.getUid());
			QueryUtils.setQueryParameter(query, "firstName", entity.getFirstName());
			QueryUtils.setQueryParameter(query, "lastName", entity.getLastName());

			Number count = (Number) query.getSingleResult();
			if (count.intValue() > 0) {
				ve.addExceptionCause(new ExceptionCause("exception.duplicate",
						new Object[] { "author", entity.getFirstName() + " " + entity.getLastName() }));
			}
		}

		// Any validation errors been caused?
		if (ve.hasErrors()) {
			throw ve;
		}
	}
}
