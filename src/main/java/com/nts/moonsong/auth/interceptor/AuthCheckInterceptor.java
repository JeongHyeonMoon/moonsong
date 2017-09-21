package com.nts.moonsong.auth.interceptor;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nts.moonsong.auth.annotation.AuthCheck;
import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.constant.CookieCheck;
import com.nts.moonsong.auth.dto.AuthInformation;
import com.nts.moonsong.auth.model.Auth;
import com.nts.moonsong.auth.service.AuthService;
import com.nts.moonsong.auth.util.AuthUtil;
import com.nts.moonsong.common.exception.InvalidAccessException;

/**
 * 
 * @author Naver 송주용
 *
 */

@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
	@Autowired
	private AuthService authorizationService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
		throws Exception {}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
		throws Exception {}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		AuthCheck authCheck = handlerMethod.getMethodAnnotation(AuthCheck.class);

		/**
		 * AuthorizationCheck annotation을 사용하지 않았을 경우,
		 * controller로 연결시킨다.
		 */
		if (authCheck == null) {
			authCheck = new AuthCheck() {
				@Override
				public Class<? extends Annotation> annotationType() {
					return null;
				}

				@Override
				public int level() {
					return AuthLevel.ALL;
				}
			};
		}

		Auth parsedAuthorization;
		AuthInformation authorizationInformation = new AuthInformation();

		/**
		 * 로그인시 발급한 인증에 필요한 값이 데이터베이스에 존재하는지 체크하는 곳
		 * 존재하지 않는 경우 false를 리턴
		 */
		if (authCheck.level() == AuthLevel.NOT_LOGINED_USER && CookieCheck.isAuthCookieExist(req.getCookies())) {
			throw new InvalidAccessException();
		}
		if (authCheck.level() == AuthLevel.ALL) {
			if (CookieCheck.isAuthCookieExist(req.getCookies())) {
				parsedAuthorization = AuthUtil.parseAuthFromCookies(req.getCookies());

				if (authorizationService.isValidAuth(parsedAuthorization)) {
					authorizationInformation
						.setAdmin(parsedAuthorization.getAuthLevel() == AuthLevel.ADMIN);
					authorizationInformation.setAuthorized(true);
					authorizationInformation.setAuthorization(parsedAuthorization);
				}
			}
			req.setAttribute(AuthInformation.name, authorizationInformation);
			return true;
		}

		if (CookieCheck.isAuthCookieExist(req.getCookies()) == false) {
			throw new InvalidAccessException();
		}
		parsedAuthorization = AuthUtil.parseAuthFromCookies(req.getCookies());

		if (authorizationService.isValidAuth(parsedAuthorization) == false) {
			throw new InvalidAccessException();
		}

		switch (authCheck.level()) {
			case AuthLevel.NORMAL:
				if (parsedAuthorization.getAuthLevel() != AuthLevel.NORMAL) {
					throw new InvalidAccessException();
				}
				authorizationInformation.setAuthorized(true);
				authorizationInformation.setAuthorization(parsedAuthorization);
				break;
			case AuthLevel.ADMIN:
				if (parsedAuthorization.getAuthLevel() != AuthLevel.ADMIN) {
					throw new InvalidAccessException();
				}
				authorizationInformation.setAuthorized(true);
				authorizationInformation.setAdmin(true);
				authorizationInformation.setAuthorization(parsedAuthorization);
				break;
			case AuthLevel.LOGINED_USER:
				authorizationInformation.setAuthorized(true);
				authorizationInformation
					.setAdmin(parsedAuthorization.getAuthLevel() == AuthLevel.ADMIN);
				authorizationInformation.setAuthorization(parsedAuthorization);
				break;
			default:
				throw new InvalidAccessException();
		}

		req.setAttribute(AuthInformation.name, authorizationInformation);
		return true;
	}

}
