/**
 * @author 문정현

 */

var imageFileElement;


$(document).ready(function(){
	imageFileElement = $('page-new-image');
	
	// tagName 중복 검사
	$('#init-tagName').on('keyup',function(){
		$.ajax({
			type:'POST',
			url:'/page/tagNamecheck',
			data:{
				'initTagName':$.trim($(this).val())
			},
			success:function(data){
				if(data){
					$('#tagcheck').html('<b style="font-size:13px;color:green">사용가능합니다</b>');
				}else{
					$('#tagcheck').html('<b style="font-size:13px;color:red">이미 존재합니다</b>');	
				}
			}
		})
	});
	
	// 파일과 함께 등록
	$('#page-add').on('click', function() {
		sendPostWithFile('/page/add', buildData(), function(data) {
			//location.replace("상세페이지");
			alert("등록 성공");
		});
	});
})

function buildData() {
	var data = new FormData();
	var content = {
		'init-tagName' : $('#init-tagName').val(),
		'page-title' : $('#page-title').val(),
		'category-id' : $('#category-id').val(),
		'content' : $('#content').val(),
		'writer-id' : $('#writer-id').val()
	};

	data.append('data', JSON.stringify(content));
	data.append('files', $('#page-main-image')[0].files[0]);
	
	return data;
}
function sendPageImageFile(){
	if(imageFileElement.val() == ''){
		return ;
	}
	
}