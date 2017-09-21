<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Review</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script src='/js/common.js'>
	
</script>
<script src='/js/review/review-update.js'>
	
</script>
</head>
<body>
	<input type='hidden' id='review-id' value='${requestScope.reviewId}' />
	<div class='panel panel-default' style='padding: 0 2% 0 2%;'>
		<div class='panel-heading'>
			<h2>
				<strong>리뷰 수정하기</strong>
			</h2>
		</div>
		<div class='panel-body form-horizontal'>
			<div class='form-group form-inline'>
				<input id='review-new-image' class='form-control' type='file' />
				<button class='btn btn-sm btn-success form-control'
					id='review-image-add'>이미지 추가</button>
			</div>
			<div class='row' id='review-image-list'></div>
			<div class='form-group'>
				<label for='review-writer'><strong>작성자 닉네임</strong></label>
				<div id='review-writer'></div>
			</div>
			<div class='form-group'>
				<label for='review-reg-date'><strong>작성일</strong></label>
				<div id='review-reg-date'></div>
			</div>
			<div class='form-group'>
				<label for='review-title'><strong>리뷰 제목</strong></label><input
					class='form-control' type='text' id='review-title' />
			</div>
			<div class='form-group'>
				<ul id='tag-list' class="form-control breadcrumb"></ul>
				<div class='form-group'>
					<label for='review-tag'><strong><B>Tag</B></strong></label><input
						class='form-control' id='review-tag' type='text' />
						<button id='add-tag' onclick='javascript:addTagFromUser();' class='btn btn-success'>태그 추가</button>
				</div>
			</div>
			<div class='form-group'>
				<label for='review-content'><strong>내용</strong></label>
				<textarea rows='10' class='form-control' id='review-content'></textarea>
			</div>
		</div>
		<div class='panel-footer'>
			<button id='review-update' class='btn btn-success'>수정</button>
			<button class='btn btn-danger'
				onclick='javascript:closeCurrentWindow();'>닫기</button>
		</div>
	</div>
	<ul id='tag-user-input-format' class='breadcrumb' style='display: none;'>
		<li id='tag-index-%tag_index%'>%tag_name%<a href='javascript:removeUserInputTag("%tag_index%");'>&nbsp;X</a></li>
	</ul>
	<ul id='tag-response-input-format' class='breadcrumb' style='display:none;'>
		<li id='tag-id-%tag_id%'>%tag_name%<a href='javascript:removeResponseTag("%tag_id%", "%tag_name%");'>&nbsp;X</a></li>
	</ul>
</body>
</html>