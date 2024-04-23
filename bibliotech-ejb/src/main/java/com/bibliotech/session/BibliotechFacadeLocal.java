package com.bibliotech.session;

import javax.ejb.Local;

import com.bibliotech.dto.DTOAuthor;
import com.bibliotech.dto.DTOBook;
import com.bibliotech.dto.DTOCategory;
import com.bibliotech.dto.DTOSearchBooks;
import com.bibliotech.utils.QueryResult;

@Local
public interface BibliotechFacadeLocal {
	/*
	 * Author
	 */
	
	public QueryResult<DTOAuthor> searchAuthors(String firstName, String lastName, boolean count, int first, int pageSize, String sortField, String sortOrder);
	public DTOAuthor getAuthor(Integer uid);
	public DTOAuthor saveOrUpdateAuthor(DTOAuthor dto) throws Exception;
	public void deleteAuthor(Integer uid) throws Exception;
	
	/*
	 * Book
	 */
	
	public QueryResult<DTOBook> searchBooks(String title, boolean count, int first, int pageSize, String sortField, String sortOrder);
	public DTOBook getBook(Integer uid);
	public DTOBook saveOrUpdateBook(DTOBook dto) throws Exception;
	public void deleteBook(Integer uid) throws Exception;
	
	/*
	 * Category
	 */
	
	public QueryResult<DTOCategory> searchCategories(boolean count, int first, int pageSize, String sortField, String sortOrder);
	public DTOCategory getCategory(Integer uid);
	public DTOCategory saveOrUpdateCategory(DTOCategory dto) throws Exception;
	public void deleteCategory(Integer uid) throws Exception;
	
	/*
	 * Search authors, books and categories
	 */
	
	public QueryResult<DTOSearchBooks> search(String category, String title, String author, boolean count, int first, int pageSize, String sortField, String sortOrder);
}
