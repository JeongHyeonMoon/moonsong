package com.nts.moonsong.member.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.member.dto.MemberView;
import com.nts.moonsong.member.model.Member;

/**
 * 
 * @author Naver 문정현
 *
 */
@Repository
public interface MemberRepository {
	public void insertMember(Member member);

	public void insertAdmin(Long memberId);

	public boolean isValidMember(@Param("nickname") String nickName,
		@Param("password") String password, @Param("isDeleted")Integer validationValue);

	public List<Member> selectMembers();

	public Member selectMember(Long loginId);

	public List<MemberView> selectMemberSections();

	public MemberView selectMemberView(Long loginId);

	public Member selectMemberByNickname(String nickname);

	public boolean isExistNickname(String nickname);

	public boolean isAdminByNickname(String nickName);

	public boolean isExistAdmin(Long memberId);

	public void updateMember(Member member);

	public void deleteMember(Member member);

	public void updatePassword(Member member);

}
