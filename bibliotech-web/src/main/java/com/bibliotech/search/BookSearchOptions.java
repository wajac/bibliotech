package com.bibliotech.search;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.bibliotech.kernel.AbstractSearchOptions;
import com.bibliotech.dto.DTOSearchBooks;

@Named
@SessionScoped
public class BookSearchOptions extends AbstractSearchOptions {
	private static final long serialVersionUID = 1;
	private String category;
	private String book;
	private String author;
	private DTOSearchBooks selected;

	public BookSearchOptions() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		checkChanged(this.category, category);
		this.category = category;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		checkChanged(this.book, book);
		this.book = book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		checkChanged(this.author, author);
		this.author = author;
	}

	public DTOSearchBooks getSelected() {
		return selected;
	}

	public void setSelected(DTOSearchBooks selected) {
		this.selected = selected;
	}
}
