package com.nts.moonsong.review.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nts.moonsong.review.model.Review;
import com.nts.moonsong.review.model.ReviewImage;
import com.nts.moonsong.tag.dto.ReviewTagView;

/**
 * @author Naver 송주용
 *
 */
public class ReviewView extends Review {
	@JsonProperty("tags")
	private List<ReviewTagView> tags;
	@JsonProperty("reviewImages")
	private List<ReviewImage> reviewImages;
	private String writerNickname;

	public String getWriterNickname() {
		return writerNickname;
	}

	public void setWriterNickname(String writerNickname) {
		this.writerNickname = writerNickname;
	}

	/**
	 * 
	 */
	public ReviewView(Review review) {
		this.tags = new ArrayList<>();
		this.reviewImages = new ArrayList<>();
		content = review.getContent();
		regDate = review.getRegDate();
		reviewId = review.getReviewId();
		reviewTitle = review.getReviewTitle();
		writerId = review.getWriterId();
	}

	/**
	 * 
	 */
	public ReviewView() {
		this.tags = new ArrayList<>();
		this.reviewImages = new ArrayList<>();
	}

	public List<ReviewTagView> getTags() {
		return tags;
	}

	public void setTags(List<ReviewTagView> tags) {
		this.tags = tags;
	}

	public List<ReviewImage> getReviewImages() {
		return reviewImages;
	}

	public void setReviewImages(List<ReviewImage> reviewImages) {
		this.reviewImages = reviewImages;
	}

	@Override
	public void setReviewId(Long reviewId) {
		super.setReviewId(reviewId);
	}

}
