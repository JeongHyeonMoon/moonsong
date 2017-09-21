/**
 * 
 */
var keywordElement;
var searchReviewFormat;
var searchStoreFormat;
var searchPageFormat;
var searchStoreList;
var searchPageList;
var searchReviewList;
var searchNewPageElement;
var startIndex;
var categoryFormat;
var offset = 10;
var isSearchCalled = false;

$(window).scroll(
		function() {
			if ($(window).scrollTop() == $(document).height()
					- $(window).height()) {
				if (isSearchCalled) {
					return;
				}
				var keyword = keywordElement.val();
				isSearchCalled = true;
				sendGet('/api/reviews?keyword="' + keyword + '"&start-index='
						+ startIndex + '&offset=' + offset, {}, function(
						success) {
					startIndex += success.length;
					for (var i = 0; i < success.length; i++) {
						var renderedHtml = renderReview(searchReviewFormat,
								success[i]);
						renderedHtml.insertBefore(searchReviewList.last());
					}
					isSearchCalled = false;
				});
			}
		});

$(document).ready(
		function() {
			startIndex = 0;
			keywordElement = $('#search-keyword');
			searchPageFormat = $('#search-page-format').html();
			searchReviewFormat = $('#search-review-format').html();
			searchStoreFormat = $('#search-store-format').html();
			searchPageList = $('#search-page-list');
			searchReviewList = $('#search-review-list');
			searchStoreList = $('#search-store-list');
			searchNewPageElement = $('#search-new-page-format').html();
			categoryFormat = $('#category-format').html();

			sendGet('/api/search?keyword=' + keywordElement.val(), {},
					function(success) {
						var pages = success.pages;
						var reviews = success.reviews;

						if (!pages || pages.length == 0) {
							var renderedElement = $(searchNewPageElement
									.replace(/%page_title%/gi, keywordElement
											.val()));
							searchPageList.empty();
							getCategories(renderedElement.find('#'
									+ keywordElement.val() + '-category-id'));
							searchPageList.append(renderedElement);
						} else {
							startIndex = pages.length;
							searchPageList.empty();
							for (var i = 0; i < pages.length; i++) {
								var renderedHtml = renderPage(searchPageFormat,
										pages[i]);
								renderedHtml
										.insertBefore(searchPageList.last());
							}
						}

						for (var i = 0; i < reviews.length; i++) {
							var renderedHtml = renderReview(searchReviewFormat,
									reviews[i]);
							renderedHtml.insertBefore(searchReviewList.last());
						}
					});
		});

function getCategories(appendElement) {
	sendGet('/api/categories', {}, function(success) {
		appendElement.empty();
		for (var i = 0; i < success.length; i++) {
			var renderedHtml = renderCategory(categoryFormat, success[i]);
			appendElement.append(renderedHtml);
		}
	});
}

function addPage(pageTitle) {
	var data = new FormData();
	var pageImageElment = $('#' + pageTitle + '-page-main-image');
	var content = {
		categoryId : $('#' + pageTitle + '-category-id option:selected').val(),
		content : $('#' + pageTitle + '-content').val(),
		pageTitle : pageTitle
	};

	data.append('data', JSON.stringify(content));
	if (pageImageElment.val() != '') {
		data.append('files', pageImageElment[0].files[0]);
	}

	sendPostWithFileHandleError('/api/pages', data, function(success) {
		searchKeyword(pageTitle);
	}, function(errorStatus) {
		switch (errorStatus) {
		case 401:
			alert('권한이 없습니다.');
			break;
		case 512:
			alert('이미 존재하는 페이지 입니다.');
			searchKeyword(pageTitle);
			break;
		default:
			alert(errorStatus + ' error occurs!');
			break;
		}
	});
}

function renderCategory(htmlString, category) {
	return $(htmlString.replace(/%category_id%/gi, category.categoryId)
			.replace(/%category_name%/gi, category.categoryName));
}

function renderReview(htmlString, review) {
	var images = review.reviewImages;
	var tags = review.tags;
	var htmlElement = parseReviewToElement(htmlString, review);
	var tagList = htmlElement
			.find('#search-tag-review-list-' + review.reviewId);

	if (memberId != undefined && memberId == review.writerId) {
		htmlElement.children('#search-review-controller-' + review.reviewId)
				.append(
						controllerForm
								.replace(/%review_id%/gi, review.reviewId));
	}

	if (images) {
		for (var i = 0; i < images.length; i++) {
			var imageElement = buildImageElement(i, images[i].imageUrlPath,
					images.length);

			htmlElement.children(
					'#search-review-prev-image-list-' + review.reviewId)
					.append(imageElement);
		}
	}
	if (tags) {
		for (var k = 0; k < tags.length; k++) {
			tagList.append(parseTagToElement(tags[k]));
		}
	}

	return htmlElement;
}

function renderPage(htmlString, page) {
	var images = page.pageImages;
	var tags = page.tags;
	var htmlElement = parsePageToElement(htmlString, page);
	var tagList = htmlElement.find('#search-tag-page-list-' + page.pageId);

	if (images) {
		for (var i = 0; i < images.length; i++) {
			var imageElement = buildImageElement(i, images[i].imageUrlPath,
					images.length);

			htmlElement.children('#search-page-prev-image-list-' + page.pageId)
					.append(imageElement);
		}
	}
	if (tags) {
		for (var k = 0; k < tags.length; k++) {
			tagList.append(parseTagToElement(tags[k]));
		}
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

function parseReviewToElement(htmlString, data) {
	return $(htmlString.replace(/%review_id%/gi, data.reviewId).replace(
			/%review_title%/gi, data.reviewTitle).replace(/%content%/gi,
			data.content).replace(/%reg_date%/gi, data.regDate));
}

function parseTagToElement(tagData) {
	return $(tagForm.replace(/%tag_name%/gi, tagData.tagName));
}

function parsePageToElement(htmlString, page) {
	return $(htmlString
			.replace(/%page_id%/gi, page.pageId)
			.replace(/%page_title%/gi, page.pageTitle)
			.replace(
					/%authorized%/gi,
					((page.authorized) ? '<span class="glyphicon glyphicon-check"></span>&nbsp;인증된 페이지'
							: '')).replace(/%section_name%/gi,
					((!page.sectionName) ? '' : page.sectionName)).replace(
					/%category_name%/gi, page.categoryName));
}

function reportReview(reviewId){
	sendPost('/api/report/review', {
		reviewId : reviewId
	}, function(success){
		alert('리뷰 신고가 접수 되었습니다.');
	});
}
function reportPage(pageId){
	sendPost('/api/report/page', {
		pageId : pageId
	}, function(success){
		alert('리뷰 신고가 접수 되었습니다.');
	});
}
function openPageList(){
	openNewWindow("/page?keyword=" + keywordElement.val());
}