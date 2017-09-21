package com.nts.moonsong.section.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.moonsong.member.model.Member;
import com.nts.moonsong.section.model.Section;
import com.nts.moonsong.section.repository.SectionRepository;

/**
 * @author Naver 문정현
 *
 */
@Service
public class SectionService {

	@Autowired
	private SectionRepository sectionRepository;

	public List<Section> getSections() {
		return sectionRepository.selectSections();
	}

	public String[] getSectionNames(List<Member> members) {
		String[] sectionNames = new String[members.size()];

		for (int i = 0; i < members.size(); i++) {
			sectionNames[i] = sectionRepository.getSectionName(members.get(i)).getSectionName();
		}
		return sectionNames;
	}
}
