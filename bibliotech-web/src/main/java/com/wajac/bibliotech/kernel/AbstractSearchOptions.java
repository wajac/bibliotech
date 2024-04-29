package com.wajac.bibliotech.kernel;

import java.io.Serializable;

import javax.faces.event.AjaxBehaviorEvent;

import com.wajac.bibliotech.utils.Utils;

public abstract class AbstractSearchOptions implements Serializable {
	public static final long serialVersionUID = 1;
	private int first;
	private int pageSize = 20;
	private String sortField;
	private String sortOrder;
	private boolean changed;

	public void checkChanged(Object object1, Object object2) {
		if (Utils.isDifferent(object1, object2)) {
			setChanged(true);
		}
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public void forceLoad(AjaxBehaviorEvent event) {
		setChanged(true);
	}
}
