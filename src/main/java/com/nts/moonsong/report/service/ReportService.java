package com.nts.moonsong.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.moonsong.report.dto.ReportView;
import com.nts.moonsong.report.model.PageCommentReport;
import com.nts.moonsong.report.model.PageReport;
import com.nts.moonsong.report.model.ReviewCommentReport;
import com.nts.moonsong.report.model.ReviewReport;
import com.nts.moonsong.report.repository.ReportRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class ReportService {
	@Autowired
	private ReportRepository reportRepository;

	public void addReviewReport(ReviewReport reviewReport) {
		reportRepository.insertReviewReport(reviewReport);
	}

	public void addPageReport(PageReport pageReport) {
		reportRepository.insertPageReport(pageReport);
	}

	public void addReviewCommentReport(ReviewCommentReport reviewCommentReport) {
		reportRepository.insertReviewCommentReport(reviewCommentReport);
	}

	public void addPageCommentReport(PageCommentReport pageCommentReport) {
		reportRepository.insertPageCommentReport(pageCommentReport);
	}

	public ReportView getReports() {
		ReportView reportView = new ReportView();

		reportView.setReviewReports(reportRepository.selectReviewReports());
		reportView.setPageReports(reportRepository.selectPageReports());
		reportView.setReviewCommentReports(reportRepository.selectReviewCommentReports());
		reportView.setPageCommentReports(reportRepository.selectPageCommentReports());

		return reportView;
	}

	public ReportView getReportsByMemberId(Long memberId) {
		ReportView reportView = new ReportView();

		reportView.setReviewReports(reportRepository.selectReviewReportsByMemberId(memberId));
		reportView.setPageReports(reportRepository.selectPageReportsByMemberId(memberId));
		reportView.setReviewCommentReports(reportRepository.selectReviewCommentReportsByMemberId(memberId));
		reportView.setPageCommentReports(reportRepository.selectPageCommentReportsByMemberId(memberId));
		return reportView;
	}

	@Transactional
	public void removeReview(List<ReviewReport> reviewArr) {

		for (ReviewReport review : reviewArr) {
			reportRepository.deleteReview(review.getReviewId());
			reportRepository.deleteReviewReport(review.getReviewId());
		}
	}

	@Transactional
	public void removePage(List<PageReport> pageArr) {
		for (PageReport page : pageArr) {
			reportRepository.deletePage(page.getPageId());
			reportRepository.deletePageReport(page.getPageId());
		}

	}

	@Transactional
	public void removeReviewComment(List<ReviewCommentReport> commentArr) {
		for (ReviewCommentReport comment : commentArr) {
			reportRepository.deleteReviewComment(comment.getReviewCommentId());
			reportRepository.deleteReviewCommentReport(comment.getReviewCommentId());
		}
	}

	@Transactional
	public void removePageComment(List<PageCommentReport> commentArr) {
		for (PageCommentReport comment : commentArr) {
			reportRepository.deletePageComment(comment.getPageCommentId());
			reportRepository.deletePageCommentReport(comment.getPageCommentId());
		}
	}

}
