package com.nts.moonsong.tag.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nts.moonsong.tag.model.ReviewTag;
import com.nts.moonsong.tag.model.Tag;

/**
 * @author Naver 송주용
 *
 */
public class ReviewTagView extends ReviewTag {
	@JsonIgnore
	private Tag tag;
	private String tagName;

	/**
	 * 
	 */
	public ReviewTagView() {
		tag = new Tag();
	}

	public void parseTag(Tag tag) {
		this.tag = tag;
		this.tagId = tag.getTagId();
		this.tagName = tag.getTagName();
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Tag getTag() {
		return tag;
	}
}
