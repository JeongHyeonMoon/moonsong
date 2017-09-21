<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Comment Test</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<script src='/js/comment/comment.js'>
	
</script>
<script src='/js/common.js'>
	
</script>
</head>
<body>
	<div class='form-group'>
		<input type='text' id='reviewId' />
		<button class='btn btn-success'>read Comment</button>
	</div>

	<div id='comment-list' class='list-group'></div>

</body>

<div id='comment-format' style='display: none;'>
	<div class='media'>
		<div class='media-body'>
			<h3 class='media-heading'>
				<b>%writer-id%</b>
			</h3>
			%reg-date%
			<div>%content%</div>
		</div>
	</div>
</div>
</html>

