<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script src='/js/admin/admin-reports.js'></script>


<!-- 리뷰 신고 -->
<div id="ReviewTable">
	<table class='table table-hover'>
		<thead>
			<tr><th scope='col' colspan='7'><caption><strong>리뷰 신고</strong></caption></th></tr>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">리뷰 ID</th>
				<th scope="col">신고 리뷰 제목</th>
				<th scope="col">쓴 사람</th>
				<th scope="col">신고자</th>
				<th scope="col">신고 날짜</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody id = "review-report">
		</tbody>
	</table>
	<div style='float: right;'>
		<button id="reviewDeleteBtn" class='btn btn-danger'>신고 리뷰 삭제</button>
	</div>
</div>


<!-- 페이지 신고 -->
<div id="PageTable">
	<table class='table table-hover'>
		<thead>
			<tr><th scope='col' colspan='7'><caption><strong>업체 신고</strong></caption></th></tr>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">페이지 ID</th>
				<th scope="col">페이지 제목</th>
				<th scope="col">페이지 주인</th>
				<th scope="col">신고자</th>
				<th scope="col">신고 날짜</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody id = "page-report">
		</tbody>
	</table>
	<div style='float:right;'>
		<button id="pageDeleteBtn" class='btn btn-danger'>신고 페이지 삭제</button>
	</div>
</div>

<!-- 리뷰 댓글 신고 -->
<div id="reviewCommentTable">
	<table class='table table-hover'>
		<thead>
			<tr><th scope='col' colspan='7'><caption><strong>댓글 신고</strong></caption></th></tr>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">댓글 ID</th>
				<th scope="col">댓글 내용</th>
				<th scope="col">작성자</th>
				<th scope="col">신고자</th>
				<th scope="col">신고 날짜</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody id = "review-comment-report">
		</tbody>
	</table>
	<div style='float:right;'>
		<button id="reviewCommentDeleteBtn" class='btn btn-danger'>신고 댓글 삭제</button>
	</div>
</div>

<!-- 페이지 댓글 신고 -->
<div id="pageCommentTable">
	<table class='table table-hover'>
		<thead>
			<tr><th scope='col' colspan='7'><caption><strong>댓글 신고</strong></caption></th></tr>
			<tr>
				<th scope="col">번호</th>
				<th scope="col">댓글 ID</th>
				<th scope="col">댓글 내용</th>
				<th scope="col">작성자</th>
				<th scope="col">신고자</th>
				<th scope="col">신고 날짜</th>
				<th scope="col">삭제</th>
			</tr>
		</thead>
		<tbody id = "page-comment-report">
		</tbody>
	</table>
	<div style='float:right;'>
		<button id="pageCommentDeleteBtn" class='btn btn-danger'>신고 댓글 삭제</button>
	</div>
</div>

<table style='display: none;'>
	<tbody id = "review-report-format">
		<tr>
			<td>%index%</td>
			<td>%reviewId%</td>
			<td>%reviewTitle%</td>
			<td>%writer%</td>
			<td>%reporter%</td>
			<td>%regDate%</td>
			<td><input type="checkbox" name="deleteReview"
				id="%reviewId%"></td>
		</tr>
	</tbody>
</table>

<table style='display: none;'>
	<tbody id = "page-report-format">
			<tr>
				<td>%index%</td>
				<td>%pageId%</td>
				<td>%pageTitle%</td>
				<td>%writer%</td>
				<td>%reporter%</td>
				<td>%regDate%</td>
				<td><input type="checkbox" name="deletePage"
					id="%pageId%"></td>
			</tr>
	</tbody>
</table>



<table style='display: none;'>
	<tbody id = "review-comment-report-format">
			<tr>
				<td>%index%</td>
				<td>%commentId%</td>
				<td>%content%</td>
				<td>%writer%</td>
				<td>%reporter%</td>
				<td>%regDate%</td>
				<td><input type="checkbox" name="deleteReviewComment"
					id="%reviewCommentId%"></td>
			</tr>
	</tbody>
</table>


<table style='display: none;'>
	<tbody id = "page-comment-report-format">
			<tr>
				<td>%index%</td>
				<td>%commentId%</td>
				<td>%content%</td>
				<td>%writer%</td>
				<td>%reporter%</td>
				<td>%regDate%</td>
				<td><input type="checkbox" name="deletePageComment"
					id="%pageCommentId%"></td>
			</tr>
	</tbody>
</table>