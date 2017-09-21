package com.nts.moonsong.review.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nts.moonsong.review.model.Review;
import com.nts.moonsong.tag.dto.ReviewTagView;
import com.nts.moonsong.tag.model.ReviewTag;

/**
 * @author Naver 송주용
 *
 */
public class ReviewForUpdateView extends Review {
	@JsonProperty("addedTags")
	private List<ReviewTagView> addedTags;
	@JsonProperty("removedTags")
	private List<ReviewTag> removedTags;

	/**
	 * 
	 */
	public ReviewForUpdateView(Review review) {
		this.addedTags = new ArrayList<>();
		this.removedTags = new ArrayList<>();
		content = review.getContent();
		regDate = review.getRegDate();
		reviewId = review.getReviewId();
		reviewTitle = review.getReviewTitle();
		writerId = review.getWriterId();
	}

	/**
	 * 
	 */
	public ReviewForUpdateView() {
		this.addedTags = new ArrayList<>();
		this.removedTags = new ArrayList<>();
	}

	public List<ReviewTagView> getAddedTags() {
		return addedTags;
	}

	public void setAddedTags(List<ReviewTagView> addedTags) {
		this.addedTags = addedTags;
	}

	public List<ReviewTag> getRemovedTags() {
		return removedTags;
	}

	public void setRemovedTags(List<ReviewTag> removedTags) {
		this.removedTags = removedTags;
	}

}
