/**
 * 
 */

var tagForm;
var itemForm;
var controllerForm;
var listBody;
var startReviewIndex = 0;
var offset = 10;
var memberId;
var isSearchCalled = false;

$(window).scroll(function() {
	if ($(window).scrollTop() == $(document).height() - $(window).height()) {
		if(isSearchCalled){
			return;
		}
		readReviewWithPaging();
	}
});

$(document).ready(function() {
	tagForm = $('#tag-format').html();
	itemForm = $('#review-data-format').html();
	listBody = $('#review-data-list');
	controllerForm = $('#review-controller-format').html();
	memberId = $('#hidden-member-id').val();
	
	readReviewWithPaging();
});

function readReviewWithPaging() {
	isSearchCalled = true;
	sendGet('/api/reviews?start-index=' + startReviewIndex + '&offset='
			+ offset, {}, function(success) {
		startReviewIndex += success.length;
		for (var i = 0; i < success.length; i++) {
			listBody.append(renderHtml(itemForm, success[i]));
		}
		isSearchCalled = false;
	});
}
function min(val1, val2) {
	return (val1 < val2) ? val1 : val2;
}

function renderHtml(htmlString, data) {
	var images = data.reviewImages;
	var tags = data.tags;
	var htmlElement = parseHTMLToElement(htmlString, data);
	var tagList = htmlElement.find('#tag-data-list-' + data.reviewId);

	if (memberId != undefined && memberId == data.writerId) {
		htmlElement.find('#review-controller-' + data.reviewId).append(
				controllerForm.replace(/%review_id%/gi, data.reviewId));
	}

	for (var i = 0; i < images.length; i++) {
		var imageElement = buildImageElement(i, images[i].imageUrlPath,
				images.length);

		htmlElement.children('#review-prev-image-list-' + data.reviewId)
				.append(imageElement);
	}
	for (var k = 0; k < tags.length; k++) {
		tagList.append(parseTagToElement(tags[k]));
	}
	return htmlElement;
}
function buildImageElement(index, imageUrl, length) {
	var imageElement;
	if (index == 0) {
		imageElement = $('<img />', {
			src : imageUrl,
			width : '90px'
		});
	} else if (index + 1 != length) {
		imageElement = $('<img />', {
			src : imageUrl,
			width : '21px',
			height : '18px'
		});
		imageElement.css({
			margin : '2px 2px 1px 0',
			padding : '0'
		});
	} else {
		imageElement = $('<img />', {
			src : imageUrl,
			width : '21px',
			height : '18px'
		});
		imageElement.css({
			margin : '2px 0px 1px 0',
			padding : '0'
		});
	}
	return imageElement;
}
function parseHTMLToElement(htmlString, data) {
	return $(htmlString.replace(/%review_id%/gi, data.reviewId).replace(
			/%review_title%/gi, data.reviewTitle).replace(/%content%/gi,
			data.content).replace(/%reg_date%/gi, data.regDate));
}
function parseTagToElement(tagData) {
	return $(tagForm.replace(/%tag_name%/gi, tagData.tagName));
}
function searchKeyword(keyword) {
	$('#search-keyword').val(keyword);
	getTab('/search');
}

function sendReviewReport(reviewId) {
	sendPost('/api/report/review', {
		reviewId : reviewId
	}, function(success) {
		alert('신고가 접수되었습니다.');
	});
}