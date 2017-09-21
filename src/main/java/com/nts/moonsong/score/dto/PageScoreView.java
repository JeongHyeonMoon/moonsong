package com.nts.moonsong.score.dto;

import com.nts.moonsong.score.model.Score;

public class PageScoreView extends Score {
	private String pageTitle;

	public PageScoreView() {}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

}
