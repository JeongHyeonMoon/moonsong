package com.nts.moonsong.report.dto;

import java.util.List;

import com.nts.moonsong.report.model.PageCommentReport;
import com.nts.moonsong.report.model.PageReport;
import com.nts.moonsong.report.model.ReviewCommentReport;
import com.nts.moonsong.report.model.ReviewReport;

public class ReportView {
	List<ReviewReport> reviewReports;
	List<PageReport> pageReports;
	List<ReviewCommentReport> reviewCommentReports;
	List<PageCommentReport> pageCommentReports;
	
	public ReportView() {}

	public List<ReviewReport> getReviewReports() {
		return reviewReports;
	}

	public void setReviewReports(List<ReviewReport> reviewReports) {
		this.reviewReports = reviewReports;
	}

	public List<PageReport> getPageReports() {
		return pageReports;
	}

	public void setPageReports(List<PageReport> pageReports) {
		this.pageReports = pageReports;
	}

	public List<ReviewCommentReport> getReviewCommentReports() {
		return reviewCommentReports;
	}

	public void setReviewCommentReports(List<ReviewCommentReport> reviewCommentReports) {
		this.reviewCommentReports = reviewCommentReports;
	}

	public List<PageCommentReport> getPageCommentReports() {
		return pageCommentReports;
	}

	public void setPageCommentReports(List<PageCommentReport> pageCommentReports) {
		this.pageCommentReports = pageCommentReports;
	}

}
