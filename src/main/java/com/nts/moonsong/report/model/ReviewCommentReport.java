package com.nts.moonsong.report.model;

public class ReviewCommentReport extends Report{
	private Long reviewCommentId;
	private String content;
	public ReviewCommentReport() {
		super();
	}
	public Long getReviewCommentId() {
		return reviewCommentId;
	}
	public void setReviewCommentId(Long reviewCommentId) {
		this.reviewCommentId = reviewCommentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
