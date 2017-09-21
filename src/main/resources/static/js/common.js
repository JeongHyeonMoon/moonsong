/**
 * @author Naver 송주용
 */

var bodyContent;

$(document).ready(function() {
	bodyContent = $('#body-content');

	getTab('/reviews');
});

function errorHandler(status) {
	switch (status) {
	case 203:
		alert('인증 정보가 없습니다.');
		break;
	case 400:
		alert('잘못된 요청입니다.');
		break;
	case 401:
		alert('권한이 없습니다.');
		break;
	case 404:
		alert('해당 데이터를 찾을 수 없습니다.');
		break;
	case 500:
		alert('서버 내부에서 에러가 발생했습니다. 잠시후 다시 실행해 주세요');
		break;
	case 512 : 
		alert('이미 존재하는 객체를 생성할 수 없습니다.');
		break;
	default:
		 alert(status + ' 에러가 발생 했습니다.');
		break;
	}
}

function changeTag(targetUrl) {
	$.ajax({
		method : 'GET',
		url : targetUrl,
		success : function(success) {
			bodyContent.empty();
			bodyContent.html(success);
		}
	});
}
function getTab(targetUrl) {
	$.ajax({
		method : 'GET',
		url : targetUrl,
		success : function(success) {
			bodyContent.empty();
			bodyContent.html(success);
		}
	});
}
function openNewWindow(url) {
	window.open(url, '_blank');
}
function openNewPopUp(url) {
	window.open(url, '_blank', 'width=700, height=800, left=200, top=100');
}

function closeCurrentWindow() {
	window.close();
}

function sendPost(url, data, callback) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}

function sendPostHandleError(url, data, successCallBack, errorCallBack) {
	$.ajax({
		url : url,
		method : 'POST',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			successCallBack(success, xhr.status);
		},
		error : function(error) {
			errorCallBack(error.status);
		}
	});
}

function sendPostWithFile(url, data, callback) {
	$.ajax({
		url : url,
		method : 'POST',
		headers : {
			accept : 'application/json'
		},
		processData : false,
		contentType : false,
		data : data,
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}
function sendPostWithFileHandleError(url, data, successCallback, errorCallback) {
	$.ajax({
		url : url,
		method : 'POST',
		headers : {
			accept : 'application/json'
		},
		processData : false,
		contentType : false,
		data : data,
		success : function(success, textStatusCode, xhr) {
			successCallback(success, xhr.status);
		},
		error : function(error) {
			errorCallback(error.status);
		}
	});
}

function sendPut(url, data, callback) {
	$.ajax({
		url : url,
		method : 'PUT',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}

function sendPutWithFile(url, data, callback) {
	$.ajax({
		url : url,
		method : 'PUT',
		contentType : false,
		processData : false,
		data : data,
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}
function sendPutHandleError(url, data, successCallback, errorCallback) {
	$.ajax({
		url : url,
		method : 'PUT',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			successCallback(success, xhr.status);
		},
		error : function(error) {
			errorCallback(error.status);
		}
	});
}

function sendDelete(url, data, callback) {
	$.ajax({
		url : url,
		method : 'DELETE',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}
function sendDeleteHandleError(url, data, successCallBack, errorCallBack) {
	$.ajax({
		url : url,
		method : 'DELETE',
		contentType : 'application/json',
		data : JSON.stringify(data),
		success : function(success, textStatusCode, xhr) {
			successCallBack(success, xhr.status);
		},
		error : function(error) {
			errorCallBack(error.status);
		}
	});
}

function sendGet(url, data, callback) {
	$.ajax({
		url : url,
		headers : {
			accept : 'application/json'
		},
		method : 'GET',
		data : data,
		success : function(success, textStatusCode, xhr) {
			callback(success, xhr.status);
		},
		error : function(error) {
			errorHandler(error.status);
		}
	});
}

function sendGetHandleError(url, data, successCallBack, errorCallBack) {
	$.ajax({
		url : url,
		method : 'GET',
		contentType : 'application/json',
		data : data,
		success : function(success, textStatusCode, xhr) {
			successCallBack(success, xhr.status);
		},
		error : function(error) {
			errorCallBack(error.status);
		}
	});
}
