/**
 * 
 */
package com.nts.moonsong.auth.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.auth.model.Auth;

/**
 * @author Naver 송주용
 *
 */
@Repository
public interface AuthRepository {
	public void insertAuthValue(@Param("target") Auth target);

	public Auth selectAuth(Long memberId);

	public boolean isValidAuth(Auth target);

	public boolean isExistAuth(Long memberId);

	public void deleteAuth(@Param("target") Auth target);

	public void deleteAuthByMemberId(Long memberId);
}
