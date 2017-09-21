/**
 * @author 문정현
 */

function openPage(evt, tabName) {
	document.getElementById("default").style.display = "none";
	
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(tabName).style.display = "block";
    evt.currentTarget.className += " active";
}


var pageId;
var pageImageTitle;
var pageImageList;
var content;
var writerId;
var pageTitle;
var sectionId;
var authorized;
var ownerId;
var menuName;

$(document).ready(function(){
	document.getElementById("defaultOpen").click();
	
	// 기본 내용
	pageId = $('#pageId').val();
	pageImageTitle = $('#page-image-title');
	pageImageList = $('#profileImageInfo');
	

	getBasicInfo();
	
	// section
	if(authorized){
		$('#section-name').show();
	}else{
		$('#section-name').hide();
	}
	
	sendGet('/api/section',{},function(sections){
		var sectionStr = "";
		
		for (var i = 0; i < sections.length; i++) {
			sectionStr += "<option value=" + sections[i].sectionId + ">"
			sectionStr += sections[i].sectionName + "</option>";
		}
		$("#section-select").append(sectionStr);
		$("#section-select").val(sectionId).attr("selected", "selected");
	})
	
	// 이미지 리스트 추가
	$("#page-image-add").on('click',function(){
		if($('#page-new-image').val() == ''){
			return;
		}
		sendPostWithFile('/api/pages/' + pageId + '/pageImages', buildImageData(), function(success){
			$('#page-new-image').val('');
			pageImageList.append(createImageElement(success.imageUrlPath));
		});
	});
	

	
	// 수정 완료 버튼
	$('#page-modify').on('click',function(){		
		var r = confirm('수정하시겠습니까?');
		
		if(r){
			var phoneNumber = $('#phone-number').val();
			var categoryId = $('#category-id').val();
			var sectionId = $('#section-id').val();
			var startTime = $('#start-time').val();
			var endTime = $('#end-time').val();
			var address = $('#address').val();			
			
			console.log(startTime);
			var updateData = {
				pageId : pageId, 
				pageTitle : pageTitle,
				isAuthorized : authorized,
				content : $('#content').val()
			};
			
			if(authorized){
				if(!ownerId){
					//updateData.ownerId = ownerId;
				}
				updateData.phoneNumber = phoneNumber;
				updateData.categoryId = categoryId;
				updateData.startTime = startTime;
				updateData.endTime = endTime;
				updateData.address = address;
			}
			sendPut('/api/pages/'+pageId,updateData,function(success){
				alert("수정성공");
			});
		}
	})
	
	
	// 주인 인증 버튼
	$("#author-page-btn").on('click',function(){
		var r = confirm(pageTitle+'페이지의 주인으로 인증 요청하시겠습니까?');
		if(r){
			$.ajax({
				method:'POST',
				url:'/api/page/request-page',
				contentType : 'application/json',
				data:  JSON.stringify({
					'pageId' : pageId
				}),
				success:function(data){
					alert("인증 요청 완료되었습니다");
					
				},
				error : function(error){
					alert("인증 요청 실패");
				}
			})
		}
	});

		
	// 이력관리
	$("#log-info").on('click',function(){
		sendGet('/api/pages/loginfo/'+pageId,{},function(logs){
			for(var i = 0; i< logs.length; i++){
				appendLog(logs[i],i);
			}
		})
	});
	
	// 댓글 DB 내용을 읽어옴
	sendGet('/api/page/comment/'+pageId,{},function(comments){
		var commentsLength = comments.length;
		for(var i = 0; i<commentsLength; i++){
			appendComment(comments[i]);
		}
	});
	
	// 댓글 입력 버튼
	$("#comment-form").on("click","#comment-btn",function(){
		sendPost('/api/page/comment',{
			'pageId' : pageId
			, 'content' : $('#comment-content').val()
		},
		function(){
			// 댓글 내용을 다시 읽와서 화면 출력
			sendGet('/api/page/comment/'+pageId,{},function(comments){
				$('#comment-list').empty();
				
				var commentsLength = comments.length;
				for(var i = 0; i<commentsLength; i++){
					appendComment(comments[i]);
				}
			})
		})
	});
	
	// 댓글 삭제 버튼
	$("#comment-form").on("click",".comment-delete",function(){
		sendDelete('/api/page/comment',{
			'commentId' : $(this).attr('id')
			, 'writerId' : $(this).attr('writer-id')
		},function(check){
			if(check == true){
				// 다시 댓글 보여줌
				sendGet('/api/page/comment/'+pageId,{},function(comments){
					$('#comment-list').empty();
					
					var commentsLength = comments.length;
					for(var i = 0; i<commentsLength; i++){
						appendComment(comments[i]);
					}
				})	
			}else{
				alert("자신이 쓴 댓글만 삭제 가능합니다");
			}
			
		

		});
	});
	
	// 댓글 신고 버튼
	$("#comment-form").on("click",".comment-report",function(){
		sendPost('/api/report/page-comment',{
			"pageCommentId" : $(this).attr('comment-id')
		},function(){
			alert("신고 접수");
		})
	})
	
	
	// 전체 메뉴 보이기
	sendGet('/api/page/menu/get/'+pageId,{},function(menus){
		for(var i = 0; i<menus.length; i++){
			appendMenu(menus[i],i);
		}
	});
	
	// 메뉴 추가
	$("#addMenuForm").on("click","#add-menu-btn",function(){
		$.ajax({
			url : '/api/page/menu',
			contentType : 'application/json',
			method : 'POST',
			dataType : 'json',
			data : JSON.stringify({
				'menuName' : $('#menuName').val()
				, 'pageId' : pageId
				, 'price' : $('#price').val()
				}),
			success : function(success){
				if(success == false){
					alert("주인만 등록 가능합니다");
				}else{
					$('#menu').empty();
					sendGet('/api/page/menu/get/'+pageId,{},function(menus){
						for(var i = 0; i<menus.length; i++){
							appendMenu(menus[i],i);
						}
					})
				}
				
			}
		});
	});
	
	// 메뉴 삭제
	$("#addMenuForm").on("click","input[name=menu-delete]",function(){
		sendDelete('/api/page/menu',{
			'menuName' : $(this).attr('id'),
			'pageId' : pageId
		},function(){
			sendGet('/api/page/menu/get/'+pageId,{},function(menus){
				$('#menu').empty();
				for(var i = 0; i<menus.length; i++){
					appendMenu(menus[i],i);
				}
			})
		});
	})
	
	// 이 페이지가 즐겨찾기가 되어 있는지 확인
	sendGetHandleError('/api/page/bookmark/is-bookmarked/' + pageId, {}, function(isbookmarked){
		if(isbookmarked){
			$('#favorite').hide();
			$('#unfavorite').show();
		}else{
			$('#favorite').show();
			$('#unfavorite').hide();
		}
	}, function(error){
		$('#favorite').show();
		$('#unfavorite').hide();
	});
	
	// 즐겨찾기 추가
	$('#favorite').on('click',function(){
		sendPost('/api/page/bookmark',{
			"pageId" : pageId
		},function(){
			alert("즐겨찾기 추가되었습니다");
			$('#favorite').hide();
			$('#unfavorite').show();
		})
	});
	
	// 즐겨찾기 취소
	$('#unfavorite').on('click',function(){
		sendDelete('/api/page/bookmark',{
			"pageId" : pageId
		},function(){
			alert("즐겨찾기 취소되었습니다");
			$('#favorite').show();
			$('#unfavorite').hide();
		})
	});
	

});

function createImageElement(imageURL){
	var divElement = $('<div style="float: left;padding:5px; margin:2px;"></div>', {
		id : imageURL,
		width : '200px',
		height : '100%',
		class : 'form-control'
	});
	var imageElement = $('<img />',{
		src : imageURL,
		width : '100%'
	});
	divElement.append(imageElement);
	return divElement;
}

function buildImageData(){
	var formData = new FormData();
	
	formData.append('file', $('#page-new-image')[0].files[0]);
	
	return formData;
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

function appendMenu(menu,index){
	var menuFormat = $('#menu-format').html();
	
	var renderedHtml = menuFormat.replace(/%index%/,index+1)
	.replace(/%menuName%/gi,menu.menuName)
	.replace(/%price%/gi,menu.price);
	
	$('#menu').append(renderedHtml);
	
	menuName = menu.menuName;
}

function appendLog(log,index){
var myMemberFormat = $('#page-log-format').html();
	
	var renderedHtml = myMemberFormat.replace(/%index%/,index+1)
	.replace(/%pageId%/gi,log.pageId)
	.replace(/%content%/gi,log.content)
	.replace(/%categoryId%/gi,log.categoryName)
	.replace(/%address%/gi,log.address)
	.replace(/%phoneNumber%/gi,log.phoneNumber)
	.replace(/%sectionId%/gi,log.sectionName)
	.replace(/%startTime%/gi,log.startTime)
	.replace(/%endTime%/gi,log.endTime)
	.replace(/%changeDate%/gi,log.changeDate)
	.replace(/%loginId%/gi,log.changerNicname);
	
	$('#page-log').append(renderedHtml);
}

function getBasicInfo(){
	sendGet('/api/pages/'+pageId, {} ,function(page){
		
		var pageImages = page.pageImages;
		
		$('#main-page-title').text(page.pageTitle);
		$('#page-title').text(page.pageTitle);
		$('#page-id').text(page.pageId);
		$('#content').val(page.content);
		$('#writer-nickname').text(page.writerNickname);
		$('#reg-date').text(page.regDate);
		$('#is-authorized').text(page.authorized);
		
		$('#owner-id').text(page.ownerId);
		$('#address').val(page.address);
		$('#phone-number').val(page.phoneNumber);
		$('#start-time').val(page.startTime);
		$('#end-time').val(page.endTime);
		
		content = page.content;
		writerId = page.writerId;
		pageTitle = page.pageTitle;
		sectionId = page.sectionId;
		categoryId = page.categoryId;
		authorized = page.authorized;
		ownerId = page.ownerId;
		
		for(var i = 0;i < pageImages.length;i++){
			var createdImage = createImageElement(pageImages[i].imageUrlPath);
			$('#profileImageInfo').append(createdImage);
		}
		
		// 카테고리 select box
		sendGet('/api/categories',{},function(sections){
			var sectionStr = "";
			
			for (var i = 0; i < sections.length; i++) {
				sectionStr += "<option value=" + sections[i].categoryId + ">"
				sectionStr += sections[i].categoryName + "</option>";
			}
			$("#category-select").append(sectionStr);
			$("#category-select").val(categoryId).attr("selected", "selected");
		});
	});
}
