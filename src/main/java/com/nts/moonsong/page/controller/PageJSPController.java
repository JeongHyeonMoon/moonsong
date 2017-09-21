package com.nts.moonsong.page.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/page")
public class PageJSPController {

	@GetMapping(value = "/form")
	public String addPageFrom() {
		return "/page/form";
	}

	@GetMapping(value = "/main/{pageId}")
	public String getPageDetail(@PathVariable Long pageId, Model model) {
		model.addAttribute("pageId", pageId);
		return "/page/main";
	}

	@GetMapping(value = "/my-own-pages")
	public String showMyOwnPages() {
		return "/member/myOwnPages";
	}

	@GetMapping()
	public String showPages(@RequestParam(name = "keyword", required = true) String keyword, HttpServletRequest req) {
		req.setAttribute("keyword", keyword);
		return "/page/pageList";
	}
}
