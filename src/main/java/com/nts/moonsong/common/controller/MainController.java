package com.nts.moonsong.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;

/**
 * @author Naver 송주용
 *
 */
@Controller
public class MainController {
	@AuthCheck(level = AuthLevel.ALL)
	@GetMapping(path = "/")
	public String getView(HttpServletRequest req) {
		return "index";
	}
}
