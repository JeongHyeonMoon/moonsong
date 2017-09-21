package com.nts.moonsong.review.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/reviews")
public class ReviewJSPController {
	private static final String prefix = "review/";

	@GetMapping()
	public String view() throws IOException {
		return prefix + "get-all";
	}

	@GetMapping(path = "/{type}")
	public String view(@PathVariable("type") String type, HttpServletRequest req) {
		return prefix + type;
	}

	@GetMapping(path = "/{type}/{reviewId}")
	public String view(@PathVariable("type") String type,
		@PathVariable(name = "reviewId", required = false) Long reviewId, HttpServletRequest req)
		throws IOException {
		req.setAttribute("reviewId", reviewId);
		return prefix + type;
	}
}
