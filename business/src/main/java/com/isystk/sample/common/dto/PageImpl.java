package com.isystk.sample.common.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PageImpl<T> implements Page<T>, Serializable {

	private static final long serialVersionUID = -4365096766976038677L;

	List<T> data;

	long count = -1;

	int page = 1;

	public int getPage() {
		return page;
	}

	public List<T> getData() {
		return data;
	}

	public long getCount() {
		return count;
	}

	public int getTotalPages() {
		return totalPages;
	}

	int perpage = 10;

	public int getPerpage() {
		return perpage;
	}

	int totalPages = -1;

	/**
	 * コンストラクタ
	 *
	 * @param data
	 * @param pageable
	 */
	public PageImpl(List<T> data, Pageable pageable, long count) {
		this.data = (data == null) ? Collections.emptyList() : data;
		this.count = count;
		this.page = pageable.getPage();
		this.perpage = pageable.getPerpage();
		this.totalPages = Math.max(1, (int) Math.ceil((double) count / perpage));
	}

    public void setData(List<T> data) {
        this.data = data;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
