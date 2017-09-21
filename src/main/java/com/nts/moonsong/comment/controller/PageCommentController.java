package com.nts.moonsong.comment.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.comment.model.PageComment;
import com.nts.moonsong.comment.service.PageCommetService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/api/page/comment")
public class PageCommentController {

	@Autowired
	PageCommetService pageCommentService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addPageComment(@RequestBody PageComment pageComment, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		pageComment.setWriterId(loginId);
		pageCommentService.addPageComment(pageComment);
	}

	@GetMapping(value = "/{pageId}")
	@ResponseBody
	public List<PageComment> getPageComments(@PathVariable("pageId") Long pageId) {
		return pageCommentService.getPageComments(pageId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(consumes = "application/json")
	@ResponseBody
	public void modifyPageComment(@RequestBody PageComment pageComment) {
		pageCommentService.modifyPageComment(pageComment);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public boolean removePageComment(@RequestBody PageComment pageComment, HttpServletRequest req) throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return pageCommentService.removePageComment(pageComment, loginId);
	}

}
