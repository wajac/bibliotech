package com.wajac.bibliotech.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class DTOSearchBooks implements Serializable {
	
	// ======================================
	// =             Attributes             =
	// ======================================
	
	private static final long serialVersionUID = 1;
	private Integer bookUid;
	private String bookTitle;
	private String bookCategories;
	private String bookAuthors;
	
	// ======================================
	// =            Constructors            =
	// ======================================
	
	public DTOSearchBooks() {
		super();
	}
	
	public DTOSearchBooks(Integer uid, String title, String categories, String authors) {
		this.bookUid = uid;
		this.bookTitle = title;
		this.bookCategories = categories;
		this.bookAuthors = authors;
	}
	
	// ======================================
	// =          Getters & Setters         =
	// ======================================
	
	public Integer getBookUid() {
		return this.bookUid;
	}

	public void setBookUid(Integer uid) {
		this.bookUid = uid;
	}

	public String getBookTitle() {
		return this.bookTitle;
	}

	public void setBookTitle(String title) {
		this.bookTitle = title;
	}

	public String getBookCategories() {
		return this.bookCategories;
	}

	public void setBookCategories(String categories) {
		this.bookCategories = categories;
	}

	public String getBookAuthors() {
		return this.bookAuthors;
	}

	public void setBookAuthors(String authors) {
		this.bookAuthors = authors;
	}
	
	// ======================================
	// =         hash, equals, toString     =
	// ======================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookUid == null) ? super.hashCode() : bookUid.hashCode());
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
		DTOSearchBooks other = (DTOSearchBooks) obj;
		if (bookUid == null) {
			if (other.bookUid != null)
				return false;
		} else if (!bookUid.equals(other.bookUid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DTOSearchBooks [uid=" + bookUid + ", title=" + bookTitle + ", "
				+ "categories=" + bookCategories + ", authors=" + bookAuthors + "]";
	}

}
