package com.nts.moonsong.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.search.dto.SearchView;
import com.nts.moonsong.search.service.SearchService;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/api/search")
public class SearchController {
	private final Long startIndex = 0l;
	private final Long offset = 10l;

	@Autowired
	private SearchService searchService;

	@GetMapping(produces = "application/json")
	@ResponseBody
	public SearchView getSearchData(@RequestParam(value = "keyword", required = true) String keyword) {
		return searchService.getSearchView(keyword, startIndex, offset);
	}

}
