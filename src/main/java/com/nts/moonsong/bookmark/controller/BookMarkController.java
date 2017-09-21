package com.nts.moonsong.bookmark.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.bookmark.model.BookMark;
import com.nts.moonsong.bookmark.service.BookMarkService;
import com.nts.moonsong.page.dto.PageView;

@Controller
@RequestMapping(value = "/api/page/bookmark")
public class BookMarkController {

	@Autowired
	BookMarkService bookMarkService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addBookMark(@RequestBody BookMark bookMark, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		bookMark.setMemberId(loginId);
		bookMarkService.addBookMark(bookMark);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(value = "/is-bookmarked/{pageId}", produces = "application/json")
	@ResponseBody
	public boolean isBookMarked(@PathVariable(name = "pageId", required = true) Long pageId, HttpServletRequest req)
		throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return bookMarkService.isBookMarked(pageId, loginId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<PageView> getMyBookmarks(HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return bookMarkService.getMyBookmarks(loginId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public void removeBookMark(@RequestBody BookMark bookMark, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		bookMark.setMemberId(loginId);
		bookMarkService.removeBookMark(bookMark);
	}
}
