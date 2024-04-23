package com.bibliotech.kernel.enums;

public enum ApplicationPageName {
	HOME_PAGE("page.home"), BOOK_SEARCH("page.book.search"),
	BOOK_DETAIL("page.book.detail"), AUTHOR_DETAIL("page.author.detail"),
	CATEGORY_SEARCH("page.category.search");

	private String name;

	ApplicationPageName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public static ApplicationPageName fromValue(String name) {
		return valueOf(name);
	}
}
