package com.nts.moonsong.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/search")
public class SearchJSPController {
	private static final String prefix = "search/";

	@GetMapping
	public String view() {
		return prefix + "search";
	}
}
