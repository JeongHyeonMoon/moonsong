package com.nts.moonsong.section.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.section.model.Section;

/**
 * @author Naver 문정현
 *
 */
@Repository
public interface SectionRepository {
	List<Section> selectSections();

	Section getSectionName(Member member);
}
