package com.nts.moonsong.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.auth.service.AuthService;
import com.nts.moonsong.member.dto.MemberView;
import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.member.repository.MemberRepository;

/**
 * 
 * @author Naver 문정현
 *
 */
@Service
public class MemberService {
	@Autowired
	private AuthService authService;

	@Autowired
	private MemberRepository memberRepository;

	public void addMember(Member member) {
		memberRepository.insertMember(member);
	}

	public boolean isExistNickname(String nickname) {
		return memberRepository.isExistNickname(nickname);
	}

	public List<Member> getMembers() {
		return memberRepository.selectMembers();
	}

	public Member searchNickname(String nickname) {
		return memberRepository.selectMemberByNickname(nickname);
	}

	/**
	 * 회원의 강퇴, 탈퇴시 사용하면
	 * 기존의 로그인 인증 정보를 삭제한다
	 * @param member
	 */
	public void removeMember(Member member) {
		authService.removeAuthByMemberId(member.getMemberId());
		memberRepository.deleteMember(member);
	}

	public void addAdmin(Member member) {
		memberRepository.insertAdmin(member.getMemberId());
	}

	public boolean isExistAdmin(long memberId) {
		return memberRepository.isExistAdmin(memberId);
	}

	public Member getMember(Long loginId) {
		return memberRepository.selectMember(loginId);
	}

	public MemberView getMemberView(Long loginId) {
		return memberRepository.selectMemberView(loginId);

	}

	public List<MemberView> getMemberSections() {
		return memberRepository.selectMemberSections();
	}

	public void modifyMember(Member member) {
		memberRepository.updateMember(member);
	}

	public void modifyPassword(Member member) {
		memberRepository.updatePassword(member);

	}
}
