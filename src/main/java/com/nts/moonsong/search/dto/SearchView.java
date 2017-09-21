package com.nts.moonsong.search.dto;

import java.util.List;

import com.nts.moonsong.page.dto.PageView;
import com.nts.moonsong.review.dto.ReviewView;

/**
 * @author Naver 송주용
 *
 */
public class SearchView {
	private String keyWord;
	private List<ReviewView> reviews;
	private List<PageView> pages;

	public List<PageView> getPages() {
		return pages;
	}

	public void setPages(List<PageView> pages) {
		this.pages = pages;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public List<ReviewView> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewView> reviews) {
		this.reviews = reviews;
	}

}
