<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>This Page For Tag Test</title>
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
<!-- bootstrap CDN -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<script src='/js/tag/tag.js'>
</script>
<script src='/js/common.js'>
</script>
</head>
<body>
	<div class='form-group'>
		<input type='text' id='writer-id' />
		<button id='read-concern-tag-btn' class='btn btn-success'>Read Concern Tag By Writer-Id</button>
	</div>
	<div id='concern-tag-list' class='list-group'>
	</div>
	<div class='form-group'>
		<input type='text' id='tag-name' />
		<button id='new-concern-tag-name' class='btn btn-success'>New Concern Tag</button>
	</div>
	
	<div id='tag-format' style='display:none;'>
		<a href='#' id='%tag-id%'>
			%tag-name%
		</a>
	</div>
</body>
</html>