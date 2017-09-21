/**
 * 
 */
package com.nts.moonsong.auth.model;

/**
 * @author Naver 송주용
 *
 */
public class Auth {
	Long memberId;
	String randomValue;
	Integer authLevel;

	/**
	 * @return the memberId
	 */
	public Long getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the randomValue
	 */
	public String getRandomValue() {
		return randomValue;
	}

	/**
	 * @param randomValue the randomValue to set
	 */
	public void setRandomValue(String randomValue) {
		this.randomValue = randomValue;
	}

	/**
	 * @return the authorizationLevel
	 */
	public Integer getAuthLevel() {
		return authLevel;
	}

	/**
	 * @param authorizationLevel the authorizationLevel to set
	 */
	public void setAuthLevel(Integer authorizationLevel) {
		this.authLevel = authorizationLevel;
	}

}
