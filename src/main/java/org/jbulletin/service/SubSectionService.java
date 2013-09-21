package org.jbulletin.service;

import java.util.List;

import org.jbulletin.model.SubSection;

public interface SubSectionService {
	public SubSection get(int id);
	public void save(SubSection subSection);
	public List<SubSection> findBySectionId(int sectionId);
}
