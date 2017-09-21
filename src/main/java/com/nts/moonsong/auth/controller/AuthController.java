package com.nts.moonsong.auth.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.model.Auth;
import com.nts.moonsong.auth.service.AuthService;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.common.exception.LoginRequiredException;
import com.nts.moonsong.common.exception.NotExistElementException;

/**
 * 
 * @author Naver 송주용
 *
 */

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@ResponseBody
	@PostMapping(path = "/login", consumes = "application/json")
	public void createCookie(HttpServletResponse response, @RequestBody Map<String, String> resquestBody)
		throws IOException, NotExistElementException {
		String nickname = resquestBody.get("id");
		String password = resquestBody.get("password");
		String randomValue = AuthUtil.generateRandomValue(64);
		Auth generatedAuth;
		Cookie authCookies[];

		if (nickname == null || password == null) {
			throw new IOException();
		}

		generatedAuth = authService.checkMemberAndInsertOrReadAuth(nickname, password,
			randomValue);
		authCookies = AuthUtil.generateCookiesFromAuth(generatedAuth);

		for (Cookie cookie : authCookies) {
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	@AuthCheck(level = AuthLevel.LOGINED_USER)
	@ResponseBody
	@DeleteMapping(path = "/logout", consumes = "application/json")
	public void destroyCookie(HttpServletRequest req, HttpServletResponse res)
		throws LoginRequiredException, IOException {
		Auth targetAuthorization = AuthUtil.parseAuthFromCookies(req.getCookies());

		authService.removeAuth(targetAuthorization);

		Cookie parsedCookies[] = AuthUtil.generateCookiesFromAuth(targetAuthorization);

		for (Cookie cookie : parsedCookies) {
			cookie.setMaxAge(0);
			res.addCookie(cookie);
		}
	}
}
