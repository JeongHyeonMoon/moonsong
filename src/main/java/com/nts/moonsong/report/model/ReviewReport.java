package com.nts.moonsong.report.model;

public class ReviewReport extends Report{
	private Long reviewId;
	private String reviewTitle;
	
	public ReviewReport() {
		super();
	}
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
}
