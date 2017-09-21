package com.nts.moonsong.report.model;

public class PageCommentReport extends Report{
	private Long pageCommentId;
	private String content;
	public PageCommentReport() {
		super();
	}
	public Long getPageCommentId() {
		return pageCommentId;
	}
	public void setPageCommentId(Long pageCommentId) {
		this.pageCommentId = pageCommentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
