package org.jbulletin.service;

import java.util.List;

import org.jbulletin.model.Section;

public interface SectionService {
	public Section getSection(int id);
	public void saveSection(Section section);
	public List<Section> getSections();
}