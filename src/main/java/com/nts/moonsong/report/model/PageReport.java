package com.nts.moonsong.report.model;

public class PageReport extends Report{
	private Long pageId;
	private String pageTitle;
	public PageReport() {
		super();
	}
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	
}
