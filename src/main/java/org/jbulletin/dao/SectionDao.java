package org.jbulletin.dao;

import java.util.List;

import org.jbulletin.model.Section;

public interface SectionDao {

    public Section getSection(int id);

    public void saveSection(Section section);

    public List<Section> getSections();

}
