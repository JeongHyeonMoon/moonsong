<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Review</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script src='/js/common.js'>
	
</script>


<script src='/js/review/review-create.js?v=1'>
	
</script>


</head>
<body>
	<div class='panel panel-default' style='padding: 0 2% 0 2%;'>
		<div class='panel-heading'>
			<h2>
				<strong>새 리뷰</strong>
			</h2>
		</div>
	</div>
	<div class='panel-body form-horizontal'>
		<div class='row' id='review-image-list'></div>
		<div class='form-group'>
			<label for='review-title'><strong>리뷰 제목</strong></label><input
				class='form-control' type='text' id='review-title' />
		</div>
		<div class='form-group'>
			<div id='tag-list' class='breadcrumb'></div>
			<label for='review-tag'><strong><B>Tag</B></strong></label><input
				class='form-control' id='review-tag' type='text' />
				<button class='btn btn-success' onclick='addTag();'>태그 추가</button>
		</div>
		<div class='form-group'>
			<label for='review-content'><strong>내용</strong></label>
			<textarea rows='10' class='form-control' id='review-content'></textarea>
		</div>
	</div>
	<div class='form-group form-inline'>
		<label for='review-image-1'><strong>이미지1</strong></label><input
			id='review-image-1' type='file' class='form-control' /> <label
			for='review-image-2'><strong>이미지2</strong></label><input
			id='review-image-2' type='file' class='form-control' /> <label
			for='review-image-3'><strong>이미지3</strong></label><input
			id='review-image-3' type='file' class='form-control' /> <label
			for='review-image-4'><strong>이미지4</strong></label><input
			id='review-image-4' type='file' class='form-control' /> <label
			for='review-image-5'><strong>이미지5</strong></label><input
			id='review-image-5' type='file' class='form-control' />
	</div>
	<div class='panel-footer'>
		<button id='review-add' class='btn btn-success'>추가</button>
		<button class='btn btn-danger'
			onclick='javascript:closeCurrentWindow();'>닫기</button>
	</div>
	<ul id='tag-format' class='breadcrumb' style='display:none;'>
		<li id='tag-%tag_index%'>%tag_name%&nbsp;<a href='javascript:removeUserInputTag("%tag_index%");'>X</a></li>
	</ul>
</body>
</html>