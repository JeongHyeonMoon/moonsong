/**
 * 
 */
package com.nts.moonsong.tag.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.tag.model.ConcernTag;
import com.nts.moonsong.tag.model.Tag;

/**
 * @author Naver 송주용
 *
 */
@Repository
public interface ConcernTagRepository {
	public void insertConcernTag(ConcernTag target);

	public List<Tag> selectConcernTagsByMemberId(Long memberId);

	public void deleteConcernTag(@Param("memberId") Long memberId, @Param("tagId") Long tagId);
}
