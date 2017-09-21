/**
 * 
 */

var reviewId;
var reviewImageList;
var reviewTagList;
var reviewTagForm;
$(document).ready(function(){
	reviewTagForm = $('#review-tag-format').html();
	reviewTagList = $('#review-tag-list');
	reviewId = $('#review-id').val();
	reviewImageList = $('#review-image-list');
	
	sendGet('/api/reviews/' + reviewId, {}, function(success){
		var reviewImages = success.reviewImages;
		$('#review-writer').text(success.writerNickname);
		$('#review-reg-date').text(success.regDate);
		$('#review-title').val(success.reviewTitle);
		$('#review-content').text(success.content);
		
		for(var i = 0;i < success.tags.length;i++){
			reviewTagList.append(reviewTagForm.replace(/%tag_name%/gi, success.tags[i].tagName));
		}
		for(var i = 0;i < reviewImages.length;i++){
			reviewImageList.append(createImageElement(reviewImages[i].imageUrlPath));
		}
		
		reviewImageList.css({
			clear : 'both'
		});
	});
	
	// 맨처음 댓글 보이기
	sendGet('/api/reviews/'+reviewId+'/comments',{},function(comments){
		$('#comment-list').empty();
		
		var commentsLength = comments.length;
		for(var i = 0; i<commentsLength; i++){
			appendComment(comments[i]);
		}
	})
	
	// 댓글 추가
	$('#comment-form').on('click','#comment-btn',function(){
		sendPost('/api/reviews/'+reviewId+'/comments',{
			'reviewId' : reviewId,
			'content' : $('#content').val()
		},
		function(success){
			// 댓글 내용을 다시 읽와서 화면 출력
			sendGet('/api/reviews/'+reviewId+'/comments',{},function(comments){
				$('#comment-list').empty();
				
				var commentsLength = comments.length;
				for(var i = 0; i<commentsLength; i++){
					appendComment(comments[i]);
				}
			})
		})
	});
	
	// 댓글 삭제
	$('#comment-form').on('click','.comment-delete',function(){
		sendDelete('/api/reviews/'+reviewId+'/comments',{
			'commentId' : $(this).attr('id'),
			'writerId' : $(this).attr('writer-id')
		},function(check){
			if(check){
				// 다시 댓글 보여줌
				sendGet('/api/reviews/'+reviewId+'/comments',{},function(comments){
					$('#comment-list').empty();
					
					var commentsLength = comments.length;
					for(var i = 0; i<commentsLength; i++){
						appendComment(comments[i]);
					}
				})
			}else{
				alert("자신이 쓴 댓글만 삭제가능합니다.");
			}
			
		});
	});
	
	//댓글 신고
	$("#comment-form").on("click",".comment-report",function(){
		sendPost('/api/report/review-comment',{
			"reviewCommentId" : $(this).attr('comment-id')
		},function(){
			alert("신고 접수");
		})
	})
	
});


function createImageElement(imageURL){
	var imageFile = extractFileFromURL(imageURL);
	var imageDivElement = $('<div class="col-lg-2 media"></div>');
	var imageMediaMiddle = $('<div class="media-middle"></div>');
	
	var imageElement = $('<img />', {
		src : imageURL,
		height:'80px',
		class : 'media-object'
	});
	
	imageDivElement.attr('id', imageFile.fileName);
	imageDivElement.css({
		width : '100px',
		height : '110px',
		float : 'left',
		'margin-top' : 0
	});
	
	imageMediaMiddle.append(imageElement);
	
	imageDivElement.append(imageMediaMiddle);
	return imageDivElement;
}
function extractFileFromURL(url){
	var fullFileNameSplit = extractFullFileNameFromURL(url).split('.');
	return {
		fileName : fullFileNameSplit[0],
		fileExtension : fullFileNameSplit[1]
	};
}
function extractFullFileNameFromURL(url){
	var splitString = url.split('/');
	return splitString[splitString.length - 1];
}

function appendComment(comment){
	var commentFormat = $('#comment-format').html();
	
	var renderedHtml = commentFormat.replace(/%writerNickName%/gi,comment.writerNickName)
	.replace(/%regDate%/gi,comment.regDate)
	.replace(/%content%/gi,comment.content)
	.replace(/%commentId%/gi,comment.commentId)
	.replace(/%writerId%/gi,comment.writerId);
	
	$('#comment-list').append(renderedHtml);
}