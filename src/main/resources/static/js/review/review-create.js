/**
 * @author Naver 송주용
 */

var tagListElement;
var tagFormat;
var tagTextElement;
var tagBuffer;

$(document).ready(function() {
	tagListElement = $('#tag-list');
	tagFormat = $('#tag-format').html();
	tagTextElement = $('#review-tag');
	tagBuffer = {};
	
	$('#review-add').on('click', function() {
		sendPostWithFile('/api/reviews', buildData(), function(data) {
			self.close();
		});
	});
});

function buildData() {
	var data = new FormData();
	var content = {
		'reviewTitle' : $('#review-title').val(),
		'content' : $('#review-content').val(),
		tags : parseFromTagBufferToTagArray()
	};
	data.append('data', JSON.stringify(content));

	for (var i = 1; i <= 5; i++) {
		if ($('#review-image-' + i).val() == '') {
			continue;
		}
		data.append('files', $('#review-image-' + i)[0].files[0]);
	}

	return data;
}

function removeUserInputTag(tagKey){
	$('#tag-' + tagKey).remove();
	delete tagBuffer[tagKey];
}

function addTag(){
	var tagIndex = Object.keys(tagBuffer).length;
	var tagText = tagTextElement.val();
	var renderedTagElement = tagFormat.replace(/%tag_name%/gi, tagText).replace(/%tag_index%/gi, tagIndex.toString());

	tagBuffer[tagIndex.toString()] = {tagName : tagText};
	tagTextElement.val('');
	tagListElement.append(renderedTagElement);
}
function parseFromTagBufferToTagArray(){
	var tagArray = [];
	for(var i in tagBuffer){
		tagArray.push(tagBuffer[i]);
	}
	return tagArray;
}