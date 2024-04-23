package com.bibliotech.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DTOBook implements Serializable {
	
	// ======================================
	// =             Attributes             =
	// ======================================
	
	private static final long serialVersionUID = 1;
	private Integer uid;
	private String title;
	private String description;
	private String isbn;
	private Integer nbPages;
	private Date publishedDate;
	private Boolean illustrations;
	private byte[] cover;
	private Date majDate;
	private Set<DTOAuthor> authors = new HashSet<DTOAuthor>();
	private Set<DTOCategory> categories = new HashSet<DTOCategory>();
	
	// ======================================
	// =            Constructors            =
	// ======================================
	
	public DTOBook() {
	}

	public DTOBook(String title, Date majDate) {
		this.title = title;
		this.majDate = majDate;
	}

	public DTOBook(String title, String description, String isbn, Integer nbPages, Date publishedDate, Boolean illustrations,
			byte[] cover, Date majDate, Set<DTOAuthor> authors, Set<DTOCategory> categories) {
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.nbPages = nbPages;
		this.publishedDate = publishedDate;
		this.illustrations = illustrations;
		this.cover = cover;
		this.majDate = majDate;
		this.authors = authors;
		this.categories = categories;
	}

	public DTOBook(Integer uid, String title, String description, String isbn, Integer nbPages, Date publishedDate, Boolean illustrations,
			byte[] cover, Date majDate, Set<DTOAuthor> authors, Set<DTOCategory> categories) {
		this.uid = uid;
		this.title = title;
		this.description = description;
		this.isbn = isbn;
		this.nbPages = nbPages;
		this.publishedDate = publishedDate;
		this.illustrations = illustrations;
		this.cover = cover;
		this.majDate = majDate;
		this.authors = authors;
		this.categories = categories;
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

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getNbPages() {
		return this.nbPages;
	}

	public void setNbPages(Integer nbPages) {
		this.nbPages = nbPages;
	}

	public Date getPublishedDate() {
		return this.publishedDate;
	}

	public void setPublishedDate(Date published) {
		this.publishedDate = published;
	}

	public Boolean getIllustrations() {
		return this.illustrations;
	}

	public void setIllustrations(Boolean illustrations) {
		this.illustrations = illustrations;
	}

	public byte[] getCover() {
		return this.cover;
	}

	public void setCover(byte[] cover) {
		this.cover = cover;
	}

	public Date getMajDate() {
		return this.majDate;
	}

	public void setMajDate(Date majDate) {
		this.majDate = majDate;
	}

	public Set<DTOAuthor> getAuthors() {
		return this.authors;
	}

	public void setAuthors(Set<DTOAuthor> authors) {
		this.authors = authors;
	}

	public Set<DTOCategory> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<DTOCategory> categories) {
		this.categories = categories;
	}
	
	// ======================================
	// =         hash, equals, toString     =
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
		DTOBook other = (DTOBook) obj;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "DTOBook [uid=" + uid + ", title=" + title + "]";
	}

}
