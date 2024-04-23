package com.bibliotech.kernel;

import org.primefaces.model.LazyDataModel;

public abstract class AbstractSearchDataModel<T> extends LazyDataModel<T> {
	public static final long serialVersionUID = 1;

	@Override
	public T getRowData(String rowKey) {
		for (T dto : getWrappedData()) {
			if (dto.hashCode() == Integer.valueOf(rowKey)) {
				return dto;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(T object) {
		return object == null ? null : object.hashCode();
	}
}
