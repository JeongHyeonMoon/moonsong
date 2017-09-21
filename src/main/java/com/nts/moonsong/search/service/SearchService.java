package com.nts.moonsong.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.page.service.PageService;
import com.nts.moonsong.review.service.ReviewService;
import com.nts.moonsong.search.dto.SearchView;

/**
 * @author Naver 송주용
 *
 */
@Service
public class SearchService {
	@Autowired
	PageService pageService;

	@Autowired
	ReviewService reviewService;

	public SearchView getSearchView(String keyword, Long startIndex, Long offset) {
		SearchView searchView = new SearchView();

		searchView.setKeyWord(keyword);
		searchView.setPages(pageService.getPageViewByKeyword(keyword, startIndex, offset));
		searchView.setReviews(reviewService.getReviewViewByKeyword(keyword, startIndex, offset));

		return searchView;
	}
}
