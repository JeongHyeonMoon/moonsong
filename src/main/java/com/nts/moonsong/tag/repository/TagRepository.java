/**
 * 
 */
package com.nts.moonsong.tag.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.nts.moonsong.tag.model.Tag;

/**
 * @author Naver 송주용
 *
 */
@Repository
public interface TagRepository {
	public void insertTag(@Param("target") Tag target);

	public boolean isExistTagByTagName(String tagName);

	public Tag selectTag(Long tagId);

	public Tag selectTagByTagName(String tagName);

	public List<Tag> selectTagsByReviewId(Long reviewId);
}
