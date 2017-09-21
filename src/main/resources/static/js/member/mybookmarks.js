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

$(document).ready(function() {
	startIndex = 0;
	keywordElement = $('#search-keyword');
	searchPageFormat = $('#search-page-format').html();
	searchReviewFormat = $('#search-review-format').html();
	searchStoreFormat = $('#search-store-format').html();
	searchPageList = $('#search-page-list');
	searchReviewList = $('#search-review-list');
	searchStoreList = $('#search-store-list');

	sendGet('/api/page/bookmark', {}, function(pages) {
		startIndex = pages.length;
		searchPageList.empty();
		for (var i = 0; i < pages.length; i++) {
			var renderedHtml = renderPage(searchPageFormat, pages[i]);
			renderedHtml.insertBefore(searchPageList.last());
		}
	});
});

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