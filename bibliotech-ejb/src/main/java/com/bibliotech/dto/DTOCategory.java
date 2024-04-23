package com.bibliotech.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DTOCategory implements Serializable {
	
	// ======================================
	// =             Attributes             =
	// ======================================
	
	private static final long serialVersionUID = 1;
	private Integer uid;
	private String code;
	private String label;
	private Set<DTOBook> books = new HashSet<DTOBook>();
	
	// ======================================
	// =            Constructors            =
	// ======================================
	
	public DTOCategory() {
	}
	
	public DTOCategory(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public DTOCategory(String code, String label, Set<DTOBook> books) {
		this.code = code;
		this.label = label;
		this.books = books;
	}
	
	public DTOCategory(Integer uid, String code, String label, Set<DTOBook> books) {
		this.uid = uid;
		this.code = code;
		this.label = label;
		this.books = books;
	}
	
	// ======================================
	// =          Getters & Setters         =
	// ======================================
	
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<DTOBook> getBooks() {
		return this.books;
	}

	public void setBooks(Set<DTOBook> books) {
		this.books = books;
	}
	
	// ======================================
	// =            hash, equals            =
	// ======================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? super.hashCode() : uid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOCategory other = (DTOCategory) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}

}
