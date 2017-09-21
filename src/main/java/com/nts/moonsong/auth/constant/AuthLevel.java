/**
 * 
 */
package com.nts.moonsong.auth.constant;

/**
 * @author Naver 송주용
 *
 */
public class AuthLevel {
	private AuthLevel() {}

	/**
	 * Normal 유저만 허용
	 */
	public static final int NORMAL = 1;
	/**
	 * Admin 유저만 허용
	 */
	public static final int ADMIN = 2;

	/**
	 * 로그인한 유저만 허용
	 */
	public static final int LOGINED_USER = 3;

	/**
	 * Normal, Admin, Guest 둘다 허용
	 */
	public static final int ALL = 4;

	/**
	 * 회원이 아닌 접속만 허용(회원 가입)
	 */
	public static final int NOT_LOGINED_USER = 5;
}
