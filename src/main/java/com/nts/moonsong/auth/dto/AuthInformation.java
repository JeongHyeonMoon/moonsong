/**
 * 
 */
package com.nts.moonsong.auth.dto;

import com.nts.moonsong.auth.model.Auth;

/**
 * RequestScope에 달리게 되는 객체,
 * 접속한 유저의 인증 정보를 담고 있다.
 * 
 * @author Naver 송주용
 *
 */
public class AuthInformation {
	public static final String name = "AuthInformation";
	private boolean isAdmin;
	private boolean isAuthorized;
	private Auth authorization;

	/**
	 * 
	 */
	public AuthInformation() {
		isAdmin = false;
		isAuthorized = false;
		authorization = null;
	}

	/**
	 * 
	 */
	public AuthInformation(boolean isAdmin, boolean isAuthorized, Auth authorization) {
		this.isAdmin = isAdmin;
		this.isAuthorized = isAuthorized;
		this.authorization = authorization;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean getAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the isAuthorized
	 */
	public boolean getAuthorized() {
		return isAuthorized;
	}

	/**
	 * @param isAuthorized the isAuthorized to set
	 */
	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	/**
	 * @return the authorization
	 */
	public Auth getAuthorization() {
		return authorization;
	}

	/**
	 * @param authorization the authorization to set
	 */
	public void setAuthorization(Auth authorization) {
		this.authorization = authorization;
	}

}
