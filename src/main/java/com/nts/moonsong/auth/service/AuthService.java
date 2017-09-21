package com.nts.moonsong.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.auth.constant.AuthLevel;
import com.nts.moonsong.auth.constant.DeletedValue;
import com.nts.moonsong.auth.model.Auth;
import com.nts.moonsong.auth.repository.AuthRepository;
import com.nts.moonsong.common.exception.NotExistElementException;
import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.member.repository.MemberRepository;

/**
 * 
 * @author Naver 송주용
 *
 */

@Service
public class AuthService {
	@Autowired
	AuthRepository authRepository;

	@Autowired
	MemberRepository memberRepository;

	public boolean isValidAuth(Auth auth) {
		return authRepository.isValidAuth(auth);
	}

	public void removeAuthByMemberId(Long memberId) {
		authRepository.deleteAuthByMemberId(memberId);
	}

	public void removeAuth(Auth targetAuth) {
		authRepository.deleteAuth(targetAuth);
	}

	/**
	 * 유효한 유저인지 확인해서 인증을 내려주는 메소드
	 * @param memberNickName
	 * @param password
	 * @param randomValue
	 * @return
	 * @throws NotExistElementException
	 */
	public Auth checkMemberAndInsertOrReadAuth(String memberNickName, String password,
		String randomValue) throws NotExistElementException {
		if (memberRepository.isValidMember(memberNickName, password, DeletedValue.NOT_DELETED) == false) {
			throw new NotExistElementException();
		}
		Member targetMember = memberRepository.selectMemberByNickname(memberNickName);
		if (authRepository.isExistAuth(targetMember.getMemberId())) {
			return authRepository.selectAuth(targetMember.getMemberId());
		}
		boolean isAdmin = memberRepository.isAdminByNickname(memberNickName);
		Auth newAuthorization = new Auth();

		newAuthorization.setMemberId(targetMember.getMemberId());
		newAuthorization.setAuthLevel((isAdmin) ? AuthLevel.ADMIN : AuthLevel.NORMAL);
		newAuthorization.setRandomValue(randomValue);

		authRepository.insertAuthValue(newAuthorization);

		return newAuthorization;
	}
}
