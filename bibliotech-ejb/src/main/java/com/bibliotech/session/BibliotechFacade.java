package com.bibliotech.session;

import java.util.HashSet;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bibliotech.dto.*;
import com.bibliotech.entity.*;
import com.bibliotech.utils.*;

@Stateless
public class BibliotechFacade implements BibliotechFacadeLocale {
	@PersistenceContext(name = "BibliotechPU")
	private EntityManager entityManager;

	@EJB
	private AuthorFacadeLocal ejbAuthor;
	@EJB
	private BookFacadeLocal ejbBook;
	@EJB
	private CategoryFacadeLocal ejbCategory;
	
	/*
	 * Authors
	 */
	
	/**
	 * Search authors
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<DTOAuthor> searchAuthors(String firstName, String lastName, boolean count, int first, int pageSize, String sortField, String sortOrder) {
		QueryResult<DTOAuthor> result = new QueryResult<>();
		StringBuilder sb = new StringBuilder();
		
		if (count) {
			StringBuilder sbCount = new StringBuilder(sb);
			sbCount.append("select count(a) from Author a where 1=1");
			QueryUtils.addQueryParameter(sbCount, "a.firstName", "firstName", "and", "=", firstName);
			QueryUtils.addQueryParameter(sbCount, "a.lastName", "lastName", "and", "like", lastName);
			
			Query countQuery = entityManager.createQuery(sbCount.toString());
			
			QueryUtils.setQueryParameter(countQuery, "firstName", firstName);
			QueryUtils.setQueryParameter(countQuery, "lastName", lastName);
			
			result.setTotalRecords((Long) countQuery.getSingleResult());
		}
		
		sb.append("select a from Author a where 1=1");
		
		QueryUtils.addQueryParameter(sb, "a.firstName", "firstName", "and", "=", firstName);
		QueryUtils.addQueryParameter(sb, "a.lastName", "lastName", "and", "like", lastName);
		
		Query query = entityManager.createQuery(sb.toString());
		
		QueryUtils.setQueryParameter(query, "firstName", firstName);
		QueryUtils.setQueryParameter(query, "lastName", lastName);
		
		if (first > 0) {
			query.setFirstResult(first);
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize);
		}
		
		for (Author author : (List<Author>) query.getResultList()) {
			result.getRecords().add(author2DTOAuthor(author));
		}
		
		return result;
	}
	
	@Override
	public DTOAuthor getAuthor(Integer uid) {
		Author author = ejbAuthor.get(uid);
		return author2DTOAuthor(author);
	}
	
	@Override
	public DTOAuthor saveOrUpdateAuthor(DTOAuthor dto) throws Exception {
		Author author = dtoAuthor2Author(dto);
		author = ejbAuthor.saveOrUpdate(author);
		return author2DTOAuthor(author);
	}
	
	@Override
	public void deleteAuthor(Integer uid) throws Exception {
		ejbAuthor.delete(uid);
	}
	
	/**
	 * Conversion Entity -> DTO
	 * 
	 * @param author
	 * @return
	 */
	public DTOAuthor author2DTOAuthor(Author author) {
		if (author == null) {
			return null;
		}
		
		DTOAuthor dtoAuthor = new DTOAuthor(author.getUid(), author.getCivility(), author.getGender(), author.getFirstName(), author.getLastName(),
				author.getAddress1(), author.getAddress2(), author.getZipCode(), author.getCity(), author.getCountry(), author.getMajDate(), new HashSet<DTOBook>());
		
		for (Book book : author.getBooks()) { // refactor book2DTOBook
			dtoAuthor.getBooks().add(new DTOBook(book.getUid(), book.getTitle(), book.getDescription(), book.getIsbn(), book.getNbPages(), book.getPublished(),
					book.getIllustrations(), book.getCover(), book.getMajDate(), new HashSet<DTOAuthor>(), new HashSet<DTOCategory>()));
		}
		
		return dtoAuthor;
	}
	
	/**
	 * Conversion DTO -> Entity
	 * 
	 * @param dto
	 * @return
	 */
	public Author dtoAuthor2Author(DTOAuthor dto) {
		if (dto == null) {
			return null;
		}
		
		Author author = new Author(dto.getUid(), dto.getCivility(), dto.getGender(), dto.getFirstName(), dto.getLastName(), 
				dto.getAddress1(), dto.getAddress2(), dto.getZipCode(), dto.getCity(), dto.getCountry(),
				dto.getMajDate(), new HashSet<Book>());
		
		for (DTOBook dtoBook : dto.getBooks()) {
			author.getBooks().add(dtoBook2Book(dtoBook));
		}
		
		return author;
	}
	
	/*
	 * Books
	 */
	
	/**
	 * Search books
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<DTOBook> searchBooks(String title, boolean count, int first, int pageSize, String sortField, String sortOrder) {
		QueryResult<DTOBook> result = new QueryResult<>();
		StringBuilder sb = new StringBuilder();
		
		if (count) {
			StringBuilder sbCount = new StringBuilder(sb);
			sbCount.append("select count(b) from Book b where 1=1");
			QueryUtils.addQueryParameter(sbCount, "b.title", "title", "and", "=", title);
			
			Query countQuery = entityManager.createQuery(sbCount.toString());
			
			QueryUtils.setQueryParameter(countQuery, "title", title);
			
			result.setTotalRecords((Long) countQuery.getSingleResult());
		}
		
		sb.append("select b from Book b where 1=1");
		
		QueryUtils.addQueryParameter(sb, "b.title", "title", "and", "=", title);
		
		Query query = entityManager.createQuery(sb.toString());
		
		QueryUtils.setQueryParameter(query, "title", title);
		
		if (first > 0) {
			query.setFirstResult(first);
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize);
		}
		
		for (Book book : (List<Book>) query.getResultList()) {
			result.getRecords().add(book2DTOBook(book));
		}
		
		return result;
	}
	
	@Override
	public DTOBook getBook(Integer uid) {
		Book book = ejbBook.get(uid);
		return book2DTOBook(book);
	}
	
	@Override
	public DTOBook saveOrUpdateBook(DTOBook dto) throws Exception {
		Book book = dtoBook2Book(dto);
		book = ejbBook.saveOrUpdate(book);
		return book2DTOBook(book);
	}
	
	@Override
	public void deleteBook(Integer uid) throws Exception {
		ejbBook.delete(uid);
	}
	
	/**
	 * Conversion Entity -> DTO
	 * 
	 * @param book
	 * @return
	 */
	public DTOBook book2DTOBook(Book book) {
		if (book == null) {
			return null;
		}
		
		DTOBook dto = new DTOBook(book.getUid(), book.getTitle(), book.getDescription(), book.getIsbn(), book.getNbPages(), book.getPublished(), book.getIllustrations(),
				book.getCover(), book.getMajDate(), new HashSet<DTOAuthor>(), new HashSet<DTOCategory>());
		
		for (Author author : book.getAuthors()) {
			dto.getAuthors().add(author2DTOAuthor(author));
		}
		
		for (Category category : book.getCategories()) {
			dto.getCategories().add(category2DTOCategory(category));
		}
		
		return dto;
	}
	
	/**
	 * Conversion DTO -> Entity
	 * 
	 * @param dto
	 * @return
	 */
	public Book dtoBook2Book(DTOBook dto) {
		if (dto == null) {
			return null;
		}
		
		Book book = new Book(dto.getUid(), dto.getTitle(), dto.getDescription(), dto.getIsbn(), dto.getNbPages(), dto.getPublished(), dto.getIllustrations(),
				dto.getCover(), dto.getMajDate(), new HashSet<Author>(), new HashSet<Category>());
		
		for (DTOAuthor dtoAuthor : dto.getAuthors()) {
			book.getAuthors().add(dtoAuthor2Author(dtoAuthor));
		}
		
		for (DTOCategory dtoCategory : dto.getCategories()) {
			book.getCategories().add(dtoCategory2Category(dtoCategory));
		}
		
		return book;
	}
	
	/*
	 * Categories
	 */
	
	/**
	 * Search categories
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<DTOCategory> searchCategories(boolean count, int first, int pageSize, String sortField, String sortOrder) {
		QueryResult<DTOCategory> result = new QueryResult<>();
		StringBuilder sb = new StringBuilder();
		
		if (count) {
			StringBuilder sbCount = new StringBuilder(sb);
			sbCount.append("select count(c) from Category c where 1=1");
			
			Query countQuery = entityManager.createQuery(sbCount.toString());
			
			result.setTotalRecords((Long) countQuery.getSingleResult());
		}
		
		sb.append("select c from Category c where 1=1");
		
		Query query = entityManager.createQuery(sb.toString());
		
		if (first > 0) {
			query.setFirstResult(first);
		}
		if (pageSize > 0) {
			query.setMaxResults(pageSize);
		}
		
		for (Category category : (List<Category>) query.getResultList()) {
			result.getRecords().add(category2DTOCategory(category));
		}
		
		return result;
	}
	
	@Override
	public DTOCategory getCategory(Integer uid) {
		Category category = ejbCategory.get(uid);
		return category2DTOCategory(category);
	}
	
	@Override
	public DTOCategory saveOrUpdateCategory(DTOCategory dto) throws Exception {
		Category category = dtoCategory2Category(dto);
		category = ejbCategory.saveOrUpdate(category);
		return category2DTOCategory(category);
	}
	
	@Override
	public void deleteCategory(Integer uid) throws Exception {
		ejbCategory.delete(uid);
	}
	
	/**
	 * Conversion Entity -> DTO
	 * 
	 * @param category
	 * @return
	 */
	public DTOCategory category2DTOCategory(Category category) {
		if (category == null) {
			return null;
		}
		
		DTOCategory dto = new DTOCategory(category.getUid(), category.getCode(), category.getLabel(), new HashSet<DTOBook>());
		
		for (Book book : category.getBooks()) {
			dto.getBooks().add(book2DTOBook(book));
		}
		
		return dto;
	}
	
	/**
	 * Conversion DTO -> Entity
	 * 
	 * @param dto
	 * @return
	 */
	public Category dtoCategory2Category(DTOCategory dto) {
		if (dto == null) {
			return null;
		}
		
		Category category = new Category(dto.getUid(), dto.getCode(), dto.getLabel(), new HashSet<Book>());
		
		for (DTOBook dtoBook : dto.getBooks()) {
			category.getBooks().add(dtoBook2Book(dtoBook));
		}
		
		return category;
	}

}