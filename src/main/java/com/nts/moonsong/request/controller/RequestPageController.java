package com.nts.moonsong.request.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.request.model.RequestPage;
import com.nts.moonsong.request.service.RequestPageService;

@Controller
@RequestMapping(value = "/api/page/request-page")
public class RequestPageController {
	@Autowired
	private RequestPageService requestPageService;

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public void requestPageCertification(@RequestBody RequestPage requestPage, HttpServletRequest req)
		throws IOException {
		Long loginId = AuthUtil.getMemberIdFromCookie(req.getCookies());
		requestPage.setRequesterId(loginId);
		requestPageService.addRequestPage(requestPage);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@GetMapping(produces = "application/json")
	@ResponseBody
	public List<RequestPage> getRequestPages() {
		return requestPageService.getRequestPages();
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@PutMapping(path = "/authorize-page", consumes = "application/json")
	@ResponseBody
	public void addAuthorizePage(@RequestBody RequestPage requestPage) throws IOException {
		requestPageService.addAuthorizedPage(requestPage);
	}

	@AuthCheck(level = AuthLevel.ADMIN)
	@DeleteMapping(consumes = "application/json")
	@ResponseBody
	public void removePageCertification(@RequestBody RequestPage requestPage) {
		requestPageService.removePageCertification(requestPage);
	}
}
