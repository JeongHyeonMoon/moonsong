package com.nts.moonsong.bookmark.model;

public class BookMark {
	private Long memberId;
	private Long pageId;

	public BookMark() {}

	public BookMark(Long memberId, Long pageId) {
		this.memberId = memberId;
		this.pageId = pageId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

}
