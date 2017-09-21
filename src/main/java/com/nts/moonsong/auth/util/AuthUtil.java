package com.nts.moonsong.auth.util;

import java.io.IOException;
import java.util.Random;

import javax.servlet.http.Cookie;

import com.nts.moonsong.auth.constant.CookieCheck;
import com.nts.moonsong.auth.model.Auth;

/**
 * @author Naver 송주용
 *
 */
public class AuthUtil {
	private AuthUtil() {}

	public static Auth parseAuthFromCookies(Cookie cookies[]) throws IOException {
		if (CookieCheck.isAuthCookieExist(cookies) == false) {
			throw new IOException();
		}

		Cookie reorderedCookies[] = CookieCheck.reorderCookies(cookies);
		Auth parsedAuth = new Auth();

		try {
			parsedAuth
				.setMemberId(Long.parseLong(reorderedCookies[CookieCheck.IdCookie.getCookieIndex()].getValue()));
			parsedAuth.setAuthLevel(
				Integer.parseInt(reorderedCookies[CookieCheck.LevelCookie.getCookieIndex()].getValue()));
			parsedAuth.setRandomValue(reorderedCookies[CookieCheck.RandomCookie.getCookieIndex()].getValue());
		} catch (Exception e) {
			throw new IOException();
		}

		return parsedAuth;
	}

	public static Cookie[] generateCookiesFromAuth(Auth target) {
		int cookieNumber = CookieCheck.getCookieNumber();
		Cookie cookies[] = new Cookie[cookieNumber];

		cookies[CookieCheck.IdCookie.getCookieIndex()] = new Cookie(CookieCheck.IdCookie.getCookieName(),
			String.format("%d", target.getMemberId()));
		cookies[CookieCheck.RandomCookie.getCookieIndex()] = new Cookie(CookieCheck.RandomCookie.getCookieName(),
			target.getRandomValue());
		cookies[CookieCheck.LevelCookie.getCookieIndex()] = new Cookie(CookieCheck.LevelCookie.getCookieName(),
			String.format("%d", target.getAuthLevel()));

		return cookies;
	}

	public static String generateRandomValue(int generateValueLength) {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < generateValueLength; i++) {
			int rIndex = random.nextInt(3);
			switch (rIndex) {
				case 0:
					// a-z
					buffer.append((char)((random.nextInt(26)) + 97));
					break;
				case 1:
					// A-Z
					buffer.append((char)((random.nextInt(26)) + 65));
					break;
				case 2:
					// 0-9
					buffer.append((random.nextInt(10)));
					break;
			}
		}
		return buffer.toString();
	}

	public static Long getMemberIdFromCookie(Cookie cookies[]) throws IOException {
		return parseAuthFromCookies(cookies).getMemberId();
	}
}
