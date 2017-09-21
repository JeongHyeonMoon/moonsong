<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read Review</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script src='/js/common.js'>
	
</script>

<script src='/js/review/review-read-single.js'>
	
</script>
</head>
<body>
	<input type='hidden' id='review-id' value='${requestScope.reviewId}' />
	<div class='panel panel-default' style='padding: 0 2% 0 2%;'>
		<div class='panel-heading'>
			<h2>
				<strong>리뷰 자세히 보기</strong>
			</h2>
		</div>
		<div class='panel-body form-horizontal'>
			<div class='row' id='review-image-list'></div>
			<div class='form-group'>
				<label for='review-writer'><strong>작성자</strong></label>
				<div id='review-writer'></div>
			</div>
			<div class='form-group'>
				<label for='review-reg-date'><strong>작성일</strong></label>
				<div id='review-reg-date'></div>
			</div>
			<div class='form-group'>
				<label for='review-title'><strong>리뷰 제목</strong></label> <input
					type='text' class='form-control' id='review-title' disabled />
			</div>
			<div class='form-group'>
				<label for='review-tag-list'><strong><B>Tag</B></strong></label>
				<ul id='review-tag-list' class='breadcrumb'></ul>
			</div>
			<div class='form-group'>
				<label for='review-content'><strong>내용</strong></label>
				<textarea rows='10' class='form-control' id='review-content'
					disabled></textarea>
			</div>
		</div>
		
		<div id = "comment-form">
			<input type ="text" id = "content">
			<input type = "button" id = "comment-btn" value = "댓글 입력">
			<div id='comment-list' class='list-group'></div>
			
			<div id='comment-format' style='display: none;'>
				<div class='media'>
					<div class='media-body'>
						<h3 class='media-heading'>
							<b>%writerNickName%</b>
						</h3>
						%regDate%
						<div>%content%</div>
					</div>
					<input type="button" value = "삭제" class = "comment-delete" writer-id = "%writerId%" id = "%commentId%">
					<input type="button" value = "신고" class = "comment-report" comment-id = "%commentId%">
					
				</div>
			</div>
		</div>

		<div class='panel-footer'>
			<button class='btn btn-danger'
				onclick='javascript:closeCurrentWindow();'>닫기</button>
		</div>
	</div>

	<ul id='review-tag-format' class='breadcrumb' style='display: none;'>
		<li>%tag_name%</li>
	</ul>
</body>
</html>