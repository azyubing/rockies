package com.rockies.common.utils;

import java.util.List;

/**
 * 分页
 * @param <T>
 */
public class PageResult<T> {
	private List<T> pageResultList;// 数据集合
	private int total;// 数据总条数
	private int pageSize = 10;// 每页多少条，默认10
	private int page = 0;// 页数
	private int currPage;// 当前页数
	private int pageCount;// 总页数
	private int offset;
	private T t;

	public PageResult(int pageSize, int page) {
		super();
		this.pageSize = pageSize<=0?10:pageSize;
		this.page = page<=0?1:page;
		setCurrPage(page);
	}

	public PageResult() {
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	public List<T> getPageResultList() {
		return pageResultList;
	}

	public void setPageResultList(List<T> pageResultList) {
		this.pageResultList = pageResultList;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCurrPage() {
		int validPage = currPage <= 0 ? 1 : currPage;
		return validPage;
	}

	public void setCurrPage(int currPage) {
		int validPage = currPage <= 0 ? 1 : currPage;
		this.currPage = validPage;
	}

	public int getPageCount() {
		int size = total / pageSize;// 总条数/每页显示的条数=总页数
		int mod = total % pageSize;// 最后一页的条数
		if (mod != 0)
			size++;
		pageCount = size;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getOffset() {
		int validPage = page <= 0 ? 1 : page;
		offset = (validPage - 1) * pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

}
