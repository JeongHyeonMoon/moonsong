/**
 * 
 */
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.comment.model.ReviewComment;
import com.nts.moonsong.comment.service.ReviewCommentService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(path = "/api/reviews/{reviewId}/comments")
public class ReviewCommentController {
	@Autowired
	ReviewCommentService reviewCommentService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void addReviewComment(@RequestBody ReviewComment reviewComment, HttpServletRequest req) throws IOException {
		Long memberId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		reviewComment.setWriterId(memberId);
		reviewCommentService.addReviewComment(reviewComment);
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<ReviewComment> getComment(@PathVariable("reviewId") Long reviewId) {
		return reviewCommentService.readCommentByReviewId(reviewId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public boolean removeReviewComment(@RequestBody ReviewComment reviewComment, HttpServletRequest req)
		throws IOException {
		Long memberId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return reviewCommentService.removeReviewComment(reviewComment, memberId);
	}
}
