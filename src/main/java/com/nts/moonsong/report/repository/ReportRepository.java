package com.nts.moonsong.report.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.report.model.PageCommentReport;
import com.nts.moonsong.report.model.PageReport;
import com.nts.moonsong.report.model.ReviewCommentReport;
import com.nts.moonsong.report.model.ReviewReport;

/**
 * @author Naver 문정현
 *
 */
@Repository
public interface ReportRepository {

	public void insertReviewReport(ReviewReport reviewReport);

	public void insertPageReport(PageReport pageReport);

	public void insertReviewCommentReport(ReviewCommentReport reviewCommentReport);

	public void insertPageCommentReport(PageCommentReport pageCommentReport);

	public List<ReviewReport> selectReviewReports();

	public List<PageReport> selectPageReports();

	public List<ReviewCommentReport> selectReviewCommentReports();

	public List<PageCommentReport> selectPageCommentReports();

	public List<ReviewReport> selectReviewReportsByMemberId(Long memberId);

	public List<PageReport> selectPageReportsByMemberId(Long memberId);

	public List<ReviewCommentReport> selectReviewCommentReportsByMemberId(Long memberId);

	public List<PageCommentReport> selectPageCommentReportsByMemberId(Long memberId);

	public void deleteReview(Long reviewId);

	public void deleteReviewReport(Long reviewId);

	public void deletePage(Long pageId);

	public void deletePageReport(Long pageId);

	public void deletePageComment(Long pageCommentId);

	public void deletePageCommentReport(Long pageCommentId);

	public void deleteReviewComment(Long reviewCommentId);

	public void deleteReviewCommentReport(Long reviewCommentId);

}
