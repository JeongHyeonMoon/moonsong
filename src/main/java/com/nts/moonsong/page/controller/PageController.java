package com.nts.moonsong.page.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.common.exception.DuplicateInsertException;
import com.nts.moonsong.common.exception.InvalidAccessException;
import com.nts.moonsong.common.util.JSONUtil;
import com.nts.moonsong.page.dto.PageLogView;
import com.nts.moonsong.page.dto.PageView;
import com.nts.moonsong.page.model.Page;
import com.nts.moonsong.page.model.PageImage;
import com.nts.moonsong.page.service.PageService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/api/pages")
public class PageController {

	@Autowired
	private PageService pageService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/existTitle", consumes = "application/json")
	@ResponseBody
	public boolean isExistTitle(@RequestBody Page page) {
		return pageService.isExistTitle(page.getPageTitle());
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(produces = "application/json")
	@ResponseBody
	public void addPage(@RequestParam("data") String jsonString,
		@RequestParam("files") MultipartFile files[], HttpServletRequest req)
		throws JsonParseException, JsonMappingException, IOException, IllegalStateException, DuplicateInsertException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		PageView parsedPageView = JSONUtil.parseJSONString(jsonString, PageView.class);

		parsedPageView.setWriterId(writerId);
		parsedPageView.setPageImages(PageImage.parseMultipartFile(files, writerId));
		pageService.addPageView(parsedPageView);
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<PageView> getPageViews(@RequestParam(required = true, value = "start-index") Long startIndex,
		@RequestParam(required = true, value = "offset") Long offset, @RequestParam(value = "keyword") String keyword,
		HttpServletRequest req) {
		if (keyword == null || "".equals(keyword)) {
			return pageService.getPageViews(startIndex, offset);
		}
		return pageService.getPageViewByKeyword(keyword, startIndex, offset);
	}

	@GetMapping(path = "/{pageId}", produces = "application/json")
	@ResponseBody
	public PageView getPageView(@PathVariable Long pageId) {
		return pageService.getPageViewByPageId(pageId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(path = "/{pageId}", consumes = "application/json")
	@ResponseBody
	public void modifyPage(@PathVariable(value = "pageId", required = true) Long pageId,
		@RequestBody(required = true) PageView target, HttpServletRequest req)
		throws IOException, InvalidAccessException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		target.setPageId(pageId);
		//target.setWriterId(writerId);
		pageService.modifyPage(target, writerId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(path = "/{pageId}", consumes = "application/json")
	@ResponseBody
	public void removePage(@PathVariable(value = "pageId", required = true) Long pageId, HttpServletRequest req)
		throws IOException, InvalidAccessException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		pageService.removePage(pageId, loginId);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@PutMapping(consumes = "application/json")
	@ResponseBody
	public void downAuthority(@RequestParam Long pageId, HttpServletRequest req)
		throws IOException, InvalidAccessException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		pageService.downAuthority(pageId, loginId);
	}

	@GetMapping(value = "/is-owned/{pageId}", consumes = "application/json")
	@ResponseBody
	public String isOwnedPage(@PathVariable Long pageId) {
		return pageService.isOwnedPage(pageId);
	}

	@GetMapping(value = "/loginfo/{pageId}", produces = "application/json")
	@ResponseBody
	public List<PageLogView> getPageLogs(@PathVariable Long pageId) {
		return pageService.getPageLogs(pageId);
	}

}
