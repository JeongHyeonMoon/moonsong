/**
 * 
 */

var reviewId;
var reviewImageList;
var tagList;
var removedTagDataList;
var tagBuffer;
var tagText;
var tagUserInputFormat;
var tagResponseInputFormat;

$(document).ready(function(){
	tagBuffer = {};
	removedTagDataList = [];
	tagList = $('#tag-list');
	tagText = $('#review-tag');
	tagUserInputFormat = $('#tag-user-input-format').html();
	tagResponseInputFormat = $('#tag-response-input-format').html();
	reviewId = $('#review-id').val();
	reviewImageList = $('#review-image-list');
	
	sendGet('/api/reviews/' + reviewId, {}, function(success){
		$('#review-reg-date').text(success.regDate);
		$('#review-title').val(success.reviewTitle);
		$('#review-content').val(success.content);
		$('#review-writer').text(success.writerNickname);
		var reviewImages = success.reviewImages;
		
		addTagFromResponse(success.tags);
		for(var i = 0;i < reviewImages.length;i++){
			reviewImageList.append(createImageElement(reviewImages[i]));
		}
	});
	
	$('#review-update').on('click', function(){
		sendPutHandleError('/api/reviews/' + reviewId, buildReviewData(), function(success){
			window.opener.getTab('/reviews');
			self.close();
		}, function(){
			location.reload();
		});
	});
	
	$('#review-image-add').on('click', function(){
		if($('#review-new-image').val() == ''){
			return;
		}
		sendPostWithFile('/api/reviews/' + reviewId + '/reviewImages', buildImageData(), function(success){
			$('#review-new-image').val('');
			reviewImageList.append(createImageElement(success));
		});
	});
});

function buildReviewData(){
	return {
		reviewId : reviewId,
		reviewTitle : $('#review-title').val(),
		content : $('#review-content').val(),
		addedTags : parseFromTagBufferToTagArray(),
		removedTags : removedTagDataList
	};
}

function buildImageData(){
	var formData = new FormData();
	
	formData.append('file', $('#review-new-image')[0].files[0]);
	
	return formData;
}

function createImageElement(imageData){
	var imageDivElement = $('<div class="col-lg-2 media"></div>');
	var imageMediaMiddle = $('<div class="media-middle"></div>');
	var imageMediaBottom = $('<div class="media-bottom"></div>');
	var imageElement = $('<img />', {
		src : imageData.imageUrlPath,
		height:'80px',
		class : 'media-object'
	});
	var imageFileNameForId = imageData.imageFileName.replace(/./gi, "-");
	var imageDeleteButtonElement = $('<button class="btn btn-sm btn-danger">삭제</button>')
	
	imageDeleteButtonElement.css({
		padding : 0,
		margin : 0,
		width : '100%'
	});
	
	imageDivElement.attr('id', imageFileNameForId);
	imageDivElement.css({
		width : '100px',
		height : '110px',
		float : 'left',
		'margin-top' : 0
	});
	
	imageMediaMiddle.append(imageElement);
	
	imageMediaBottom.append(imageDeleteButtonElement);
	
	imageDivElement.append(imageMediaMiddle);
	imageDivElement.append(imageMediaBottom);
	
	imageDeleteButtonElement.on('click', function(){
		sendDelete(imageData.imageUrlPath, {}, function(){
			$('#' + imageFileNameForId).remove();
		});
	});
	
	return imageDivElement;
}
function removeUserInputTag(tagIndex){
	$('#tag-index-' + tagIndex).remove();
	delete tagBuffer[tagIndex.toString()];
}
function removeResponseTag(tagId, tagName){
	$('#tag-id-' + tagId).remove();
	removedTagDataList.push({
		tagId : tagId,
		tagName : tagName
	});
}
function addTagFromResponse(tags){
	for(var i = 0;i < tags.length;i++){
		var renderedTagFormat = tagResponseInputFormat.replace(/%tag_name%/gi, tags[i].tagName).replace(/%tag_id%/gi, tags[i].tagId);
		tagList.append($(renderedTagFormat));
	}
	
}
function addTagFromUser(){
	var tagTextData = tagText.val();
	tagText.val('');
	var renderedTagFormat = tagUserInputFormat.replace(/%tag_name%/gi, tagTextData).replace(/%tag_index%/gi, Object.keys(tagBuffer).length);
	tagBuffer[(Object.keys(tagBuffer).length).toString()] = tagTextData;
	tagList.append($(renderedTagFormat));
}

function parseFromTagBufferToTagArray(){
	var tagArray = [];
	for(var i in tagBuffer){
		tagArray.push({tagName : tagBuffer[i]});
	}
	return tagArray;
}