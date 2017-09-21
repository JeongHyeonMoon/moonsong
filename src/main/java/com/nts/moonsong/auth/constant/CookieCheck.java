/**
 * 
 */
package com.nts.moonsong.auth.constant;

import javax.servlet.http.Cookie;

/**
 * @author Naver 송주용
 *
 */
public enum CookieCheck {
	RandomCookie("ms_cookie_auth", 0),
	IdCookie("ms_cookie_id", 1),
	LevelCookie("ms_cookie_level", 2);

	private static final int cookieNumber = 3;
	private String cookieName;
	private int cookieIndex;

	private CookieCheck(String cookieName, int cookieIndex) {
		this.cookieName = cookieName;
		this.cookieIndex = cookieIndex;
	}

	private static int cookieIndexSum() {
		return (cookieNumber * (cookieNumber - 1)) / 2;
	}

	public static CookieCheck getCookieCheck(int cookieIndex) {
		switch (cookieIndex) {
			case 0:
				return RandomCookie;
			case 1:
				return IdCookie;
			case 2:
				return LevelCookie;
			default:
				return null;
		}
	}

	public static CookieCheck getCookieCheck(String cookieName) {
		switch (cookieName) {
			case "ms_cookie_auth":
				return RandomCookie;
			case "ms_cookie_id":
				return IdCookie;
			case "ms_cookie_level":
				return LevelCookie;
			default:
				return null;
		}
	}

	public static int getCookieNumber() {
		return cookieNumber;
	}

	public static boolean isAuthCookieExist(Cookie cookies[]) {
		int expectedCookieIndexSum = cookieIndexSum() + cookieNumber;
		int realCookieIndexSum = 0;

		if (cookies == null) {
			return false;
		}
		for (Cookie cookie : cookies) {
			CookieCheck cookieCheck = getCookieCheck(cookie.getName());
			if (cookieCheck == null) {
				continue;
			}
			realCookieIndexSum += (cookieCheck.cookieIndex + 1);
		}
		return realCookieIndexSum == expectedCookieIndexSum;
	}

	public String getCookieName() {
		return this.cookieName;
	}

	public int getCookieIndex() {
		return cookieIndex;
	}

	public static Cookie[] reorderCookies(Cookie targetCookies[]) {
		Cookie reorderedCookies[] = new Cookie[cookieNumber];

		for (Cookie cookie : targetCookies) {
			CookieCheck cookieCheck = CookieCheck.getCookieCheck(cookie.getName());

			if (cookieCheck == null) {
				continue;
			}

			reorderedCookies[cookieCheck.cookieIndex] = cookie;
		}
		return reorderedCookies;
	}
}
