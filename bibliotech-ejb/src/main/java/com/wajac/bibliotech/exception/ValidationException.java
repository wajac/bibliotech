package com.wajac.bibliotech.exception;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.ApplicationException;
import javax.ejb.EJBException;

@ApplicationException(rollback = true)
public class ValidationException extends EJBException {
	public static final long serialVersionUID = 1;
	private final List<ExceptionCause> causes = new ArrayList<>();

	public ValidationException() {
		super();
	}

	public List<ExceptionCause> getCauses() {
		return causes;
	}

	public void addExceptionCause(ExceptionCause cause) {
		causes.add(cause);
	}

	public boolean hasErrors() {
		if (causes.isEmpty()) {
			return false;
		}
		return true;
	}
}
