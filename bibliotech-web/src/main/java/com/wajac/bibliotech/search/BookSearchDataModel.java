package com.wajac.bibliotech.search;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortOrder;

import com.wajac.bibliotech.dto.DTOSearchBooks;
import com.wajac.bibliotech.session.BibliotechFacadeLocal;
import com.wajac.bibliotech.utils.QueryResult;
import com.wajac.bibliotech.utils.QueryUtils;
import com.wajac.bibliotech.kernel.AbstractSearchDataModel;

@Named
@ViewScoped
public class BookSearchDataModel extends AbstractSearchDataModel<DTOSearchBooks> {
	private static final long serialVersionUID = 1;
	@Inject
	private BookSearchOptions options;
	@EJB(lookup = "java:global/bibliotech/bibliotech-ejb/BibliotechFacade")
	private BibliotechFacadeLocal facade;

	@PostConstruct
	public void postConstruct() {
		options.setChanged(true);
	}

	@Override
	public List<DTOSearchBooks> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		boolean count = options.isChanged() ? true : false;
		options.setFirst(first);
		options.setPageSize(pageSize);
		options.setSortField(sortField);
		options.setSortOrder(sortOrder == null ? null : sortOrder.name());

		QueryResult<DTOSearchBooks> result = facade.search(options.getCategory(),
				QueryUtils.replaceJoker(options.getBook()), QueryUtils.replaceJoker(options.getAuthor()),
				count, options.getFirst(), options.getPageSize(), options.getSortField(), options.getSortOrder());

		if (count) {
			setRowCount(result.getTotalRecords().intValue());
			options.setChanged(false);
		}

		return result.getRecords();
	}
}
