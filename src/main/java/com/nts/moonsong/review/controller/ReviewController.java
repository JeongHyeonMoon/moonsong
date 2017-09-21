package com.nts.moonsong.review.controller;

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
import com.nts.moonsong.common.exception.FileDeleteFailException;
import com.nts.moonsong.common.exception.InvalidAccessException;
import com.nts.moonsong.common.exception.NotExistElementException;
import com.nts.moonsong.common.util.JSONUtil;
import com.nts.moonsong.review.dto.ReviewForUpdateView;
import com.nts.moonsong.review.dto.ReviewView;
import com.nts.moonsong.review.model.ReviewImage;
import com.nts.moonsong.review.service.ReviewService;

/**
 * 
 * @author Naver 송주용
 *
 */
@Controller
@RequestMapping(path = "/api/reviews")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(produces = "application/json")
	@ResponseBody
	public void addReviewView(@RequestParam(value = "data", required = true) String jsonString,
		@RequestParam("files") MultipartFile files[],
		HttpServletRequest req) throws JsonParseException, JsonMappingException, IOException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		ReviewView parsedReviewView = JSONUtil.parseJSONString(jsonString, ReviewView.class);

		parsedReviewView.setWriterId(writerId);
		parsedReviewView.setReviewImages(ReviewImage.parseMultipartFile(files, writerId));
		reviewService.addReviewView(parsedReviewView);
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<ReviewView> getReviewViews(@RequestParam(value = "start-index", required = true) Long startIndex,
		@RequestParam(value = "offset", required = true) Long offset,
		@RequestParam(value = "keyword", required = false) String keyword) {
		if (keyword == null || "".equals(keyword)) {
			return reviewService.getReviewViews(startIndex, offset);
		}
		return reviewService.getReviewViewByKeyword(keyword, startIndex, offset);
	}

	@GetMapping(produces = "application/json", path = "/{reviewId}")
	@ResponseBody
	public ReviewView getReviewView(@PathVariable Long reviewId) {
		return reviewService.getReviewViewByReviewId(reviewId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PutMapping(path = "/{reviewId}", consumes = "application/json")
	@ResponseBody
	public void modifyReview(@PathVariable(value = "reviewId", required = true) Long reviewId,
		@RequestBody(required = true) ReviewForUpdateView target, HttpServletRequest req)
		throws JsonParseException, JsonMappingException, IOException, InvalidAccessException, NotExistElementException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		target.setReviewId(reviewId);
		target.setWriterId(writerId);

		reviewService.modifyReview(target, writerId);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@ResponseBody
	@DeleteMapping(path = "/{reviewId}", consumes = "application/json")
	public void removeReview(@PathVariable(value = "reviewId", required = true) Long reviewId, HttpServletRequest req)
		throws InvalidAccessException, IOException, FileDeleteFailException {
		Long writerId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		reviewService.removeReview(reviewId,
			writerId);
	}
}
