/**
 * 
 */

var commentFormat;
var commentList;

$(document).ready(function() {
	commentFormat  = $('#comment-format').html();
	commentList = $('#comment-list');
	var reviewId = 1;

	sendGet('/reviews/' + reviewId + '/comments', {}, function(success) {
		var successLength = success.length;

		for (var i = 0; i < successLength; i++) {
			var renderedHtml = renderHtml(commentFormat, success[i]);

			commentList.append(renderedHtml);
		}
	});
});

function renderHtml(htmlString, data) {
	return htmlString.replace(/%writer-id%/gi, data.writerId).replace(
			/%reg-date%/gi, data.regDate).replace(/%content%/gi, data.content);
}