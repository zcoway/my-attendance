package org.lch.attendance.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageResult<T> implements Serializable {

	private static final long serialVersionUID = 9142640042556747848L;

	/**
	 * 当前记录索引
	 */
	private int currentIndex = 0;

	/**
	 * 总记录数
	 */
	private int totalCount = 0;

	/**
	 * 当前页面
	 */
	private int currentPage = 1;

	/**
	 * 总记录的页面数
	 */
	private int totalPage = 0;

	/**
	 * 每页显示的记录数，默认为20条记录 如果要显示全部记录数，可以设定每页记录数为 0
	 */
	private int pageSize = 20;

	/**
	 * 返回的数据库记录列表
	 */
	private List<T> content = new ArrayList<T>(0);

	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @param currentIndex
	 *            the currentIndex to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount
	 *            the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @param currentPage
	 *            the currentPage to set
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the countPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param countPage
	 *            the countPage to set
	 */
	public void setTotalPage(int countPage) {
		this.totalPage = countPage;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the content
	 */
	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		if (content == null)
			this.content = new ArrayList<T>(0);
		else
			this.content = content;
	}
}
