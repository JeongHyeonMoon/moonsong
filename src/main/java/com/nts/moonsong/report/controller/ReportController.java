package com.nts.moonsong.report.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.report.dto.ReportView;
import com.nts.moonsong.report.model.PageCommentReport;
import com.nts.moonsong.report.model.PageReport;
import com.nts.moonsong.report.model.ReviewCommentReport;
import com.nts.moonsong.report.model.ReviewReport;
import com.nts.moonsong.report.service.ReportService;

/**
 * @author Naver 문정현
 *
 */
@Controller
@RequestMapping(value = "/api/report")
public class ReportController {
	@Autowired
	private ReportService reportService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/review")
	@ResponseBody
	public void addReviewReport(@RequestBody ReviewReport reviewReport, HttpServletRequest req) throws IOException {
		Long reporterId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		reviewReport.setReporterId(reporterId);
		reportService.addReviewReport(reviewReport);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/page")
	@ResponseBody
	public void addPageReport(@RequestBody PageReport pageReport, HttpServletRequest req) throws IOException {
		Long reporterId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		pageReport.setReporterId(reporterId);
		reportService.addPageReport(pageReport);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/page-comment")
	@ResponseBody
	public void addPageCommentReport(@RequestBody PageCommentReport pageCommentReport, HttpServletRequest req)
		throws IOException {
		Long reporterId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		pageCommentReport.setReporterId(reporterId);
		reportService.addPageCommentReport(pageCommentReport);
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(value = "/review-comment")
	@ResponseBody
	public void addReviewCommentReport(@RequestBody ReviewCommentReport reviewCommentReport, HttpServletRequest req)
		throws IOException {
		Long reporterId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		reviewCommentReport.setReporterId(reporterId);
		reportService.addReviewCommentReport(reviewCommentReport);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@GetMapping(produces = "application/json")
	@ResponseBody
	public ReportView getReports() {
		return reportService.getReports();
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@GetMapping(value = "/my", produces = "application/json")
	@ResponseBody
	public ReportView getMyReportViews(HttpServletRequest req) throws IOException {
		Long memberId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		return reportService.getReportsByMemberId(memberId);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@DeleteMapping(value = "/review")
	@ResponseBody
	public void removeReview(@RequestBody ReportView reviewArr) {
		reportService.removeReview(reviewArr.getReviewReports());
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@DeleteMapping(value = "/page")
	@ResponseBody
	public void removePage(@RequestBody ReportView reviewArr) {
		reportService.removePage(reviewArr.getPageReports());
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@DeleteMapping(value = "/review-comment")
	@ResponseBody
	public void removeReviewComment(@RequestBody ReportView reviewArr) {
		reportService.removeReviewComment(reviewArr.getReviewCommentReports());
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@DeleteMapping(value = "/page-comment")
	@ResponseBody
	public void removePageComment(@RequestBody ReportView pageArr) {
		reportService.removePageComment(pageArr.getPageCommentReports());
	}

}
