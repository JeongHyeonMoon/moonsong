/**
 * @author Naver 문정현
 */

var memberFormat;

$(document).ready(function() {
	// 신고내역 보기
	sendGet('/api/report', {}, function(report) {
		for (var i = 0; i < report['reviewReports'].length; i++) {
			appendReviewReport(report['reviewReports'][i], i);
		}
		for (var i = 0; i < report['pageReports'].length; i++) {
			appendPageReport(report['pageReports'][i], i);
		}
		for (var i = 0; i < report['reviewCommentReports'].length; i++) {
			appendReviewCommentReport(report['reviewCommentReports'][i], i);
		}
		for (var i = 0; i < report['pageCommentReports'].length; i++) {
			appendPageCommentReport(report['pageCommentReports'][i], i);
		}
	});

	// 신고 요청 삭제
	$('#reviewDeleteBtn').on("click", function(event) {
		deleteRequest('deleteReview', 'review')
	});

	$('#pageDeleteBtn').on("click", function(event) {
		deleteRequest('deletePage', 'page')
	});

	$('#commentReviewDeleteBtn').on("click", function(event) {
		deleteRequest('deleteReviewComment', 'review-comment')
	});

	$('#commentPageDeleteBtn').on("click", function(event) {
		deleteRequest('deletePageComment', 'page-comment')
	});

});

// 신고된 내용을 삭제하는 함수
function deleteRequest(reqKind, reqUrl) {
	var r = confirm('신고된 요청을 삭제하시겠습니까? \n복구 불가능합니다.');

	if (r) {
		var data;
		var checkArr = [];
		switch (reqUrl) {
		case 'review':
			$("input[name='" + reqKind + "']:checked").each(function() {
				checkArr.push({
					reviewId : Number($(this).attr('id'))
				});
			});
			data = {
				reviewReports : checkArr
			};
			break;
		case 'page':
			$("input[name='" + reqKind + "']:checked").each(function() {
				checkArr.push({
					pageId : Number($(this).attr('id'))
				});
			});
			data = {
				pageReports : checkArr
			};
			break;
		case 'review-comment':
			$("input[name='" + reqKind + "']:checked").each(function() {
				checkArr.push({
					reviewCommentId : Number($(this).attr('id'))
				});
			});
			data = {
				reviewCommentReports : checkArr
			};
			break;
		default:
			$("input[name='" + reqKind + "']:checked").each(function() {
				checkArr.push({
					pageCommentId : Number($(this).attr('id'))
				});
			});
			data = {
				pageCommentReports : checkArr
			};
			break;
		}

		sendDelete("/api/report/" + reqUrl, data, function(success) {
			getTab('/admin/reports');
		});

	}
}

// report 를 추가하는 메소드
function appendReviewReport(report, index) {
	var myReviewReportFormat = $('#review-report-format').html();
	var renderedHtml = myReviewReportFormat.replace(/%index%/, index + 1)
			.replace(/%reviewId%/gi, report.reviewId).replace(
					/%reviewTitle%/gi, report.reviewTitle).replace(
					/%writer%/gi, report.writer).replace(/%reporter%/gi,
					report.reporter).replace(/%regDate%/gi, report.regDate);

	$('#review-report').append(renderedHtml);
}

function appendPageReport(report, index) {
	var myPageReportFormat = $('#page-report-format').html();
	var renderedHtml = myPageReportFormat.replace(/%index%/, index + 1)
			.replace(/%pageId%/gi, report.pageId).replace(/%pageTitle%/gi,
					report.pageTitle).replace(/%writer%/gi, report.writer)
			.replace(/%reporter%/gi, report.reporter).replace(/%regDate%/gi,
					report.regDate);

	$('#page-report').append(renderedHtml);
}

function appendReviewCommentReport(report, index) {
	var myCommentReportFormat = $('#review-comment-report-format').html();
	var renderedHtml = myCommentReportFormat.replace(/%index%/, index + 1)
			.replace(/%commentId%/gi, report.commentId).replace(/%content%/gi,
					report.content).replace(/%writer%/gi, report.writer)
			.replace(/%reporter%/gi, report.reporter).replace(/%regDate%/gi,
					report.regDate);

	$('#review-comment-report').append(renderedHtml);
}

function appendPageCommentReport(report, index) {
	var myCommentReportFormat = $('#page-comment-report-format').html();
	var renderedHtml = myCommentReportFormat.replace(/%index%/, index + 1)
			.replace(/%commentId%/gi, report.commentId).replace(/%content%/gi,
					report.content).replace(/%writer%/gi, report.writer)
			.replace(/%reporter%/gi, report.reporter).replace(/%regDate%/gi,
					report.regDate);

	$('#page-comment-report').append(renderedHtml);
}
