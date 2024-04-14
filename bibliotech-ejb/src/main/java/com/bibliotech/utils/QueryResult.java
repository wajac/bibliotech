package com.bibliotech.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QueryResult<T> implements Serializable {
	private static final long serialVersionUID = 1;
	private Long totalRecords;
	private List<T> records = new ArrayList<>();

	public QueryResult() {
		super();
	}

	public QueryResult(long totalRecords, List<T> records) {
		super();
		this.totalRecords = totalRecords;
		this.records = records;
	}

	public Long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}
}
