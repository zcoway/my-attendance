package org.lch.attendance.common;

import java.util.List;

import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class DisplayTagPageResult implements PaginatedList {

	private static final long serialVersionUID = 226687182034214461L;
	
	// desc or asc, not implemented yet
	private String sortCriterion;
	
	// order sequence, not implemented yet
	private SortOrderEnum sortDirection;
	
	// ITAF pageResult 对象
	private PageResult pageResult;
	
	public DisplayTagPageResult() {}
	
	public DisplayTagPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}

	public int getFullListSize() {
		return pageResult.getTotalCount();
	}

	public List getList() {
		return pageResult.getContent();
	}

	public int getObjectsPerPage() {
		return pageResult.getPageSize();
	}

	public int getPageNumber() {
		return pageResult.getCurrentPage();
	}

	public String getSearchId() {
        // Not implemented for now.
        // This is required, if we want the ID to be included in the paginated
        // purpose.
		return null;
	}

	public String getSortCriterion() {
		return sortCriterion;
	}

	public void setSortCriterion(String sortCriterion) {
		this.sortCriterion = sortCriterion;
	}

	public SortOrderEnum getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(SortOrderEnum sortDirection) {
		this.sortDirection = sortDirection;
	}
/*
	// 翻页的本页开始位置
	public int getCurrentPageStart() {
		int currentPage = getPageNumber();
		if (currentPage > 1) {
			return (currentPage-1) * getObjectsPerPage() + 1;
		} else {
			return 1;
		}
	}

	// 翻页的本页结束位置
	public int getCurrentPageStop() {
		return currentPageStop;
	}
*/
}
