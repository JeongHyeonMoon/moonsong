package com.nts.moonsong.bookmark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/member/mybookmarks")
public class BookMarkJSPController {

	@GetMapping()
	public String showBookMark() {
		return "/member/mybookmarks";
	}

}
