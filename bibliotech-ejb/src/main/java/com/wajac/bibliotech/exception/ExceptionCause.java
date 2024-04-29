package com.wajac.bibliotech.exception;

import java.io.Serializable;

public class ExceptionCause {
	public static final long serialVersionUID = 1;
	private String label;
	private Object[] parameters;

	public ExceptionCause() {
		super();
	}

	public ExceptionCause(String label, Object[] parameters) {
		this.label = label;
		this.parameters = parameters;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
}
