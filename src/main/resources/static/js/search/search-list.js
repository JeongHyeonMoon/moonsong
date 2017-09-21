/**
 * 
 */
var offset = 10;
var isSearchCalled = false;
var startIndex;
var searchPageList;
var searchPageFormat;
var keyword;

$(window).scroll(
		function() {
			if ($(window).scrollTop() == $(document).height()
					- $(window).height()) {
				if (isSearchCalled) {
					return;
				}
				isSearchCalled = true;
				sendGet('/api/pages?keyword="' + keyword + '"&start-index='
						+ startIndex + '&offset=' + offset, {}, function(
						success) {
					console.log(success);
					startIndex += success.length;
					for (var i = 0; i < success.length; i++) {
						var renderedElement = parsePageToElement(
								searchPageFormat, success[i]);
						renderedElement.insertBefore(searchPageList.last());
					}
					isSearchCalled = false;
				});
			}
		});

$(document).ready(
		function() {
			startIndex = 0;
			searchPageList = $('#search-page-list');
			searchPageFormat = $('#search-page-format').html();
			keyword = $('#keyword').val();

			isSearchCalled = true;
			sendGet('/api/pages?keyword=' + keyword + '&start-index='
					+ startIndex + '&offset=' + offset, {}, function(success) {
				startIndex += success.length;
				for (var i = 0; i < success.length; i++) {
					var renderedElement = parsePageToElement(searchPageFormat,
							success[i]);
					renderedElement.insertBefore(searchPageList.last());
				}
				isSearchCalled = false;
			});
		});

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