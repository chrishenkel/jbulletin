package org.jbulletin.dao;

import java.util.List;

import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;

public interface SubSectionDao {

    public SubSection getSubSection(int id);

    public void saveSection(SubSection subSection);
    
    public Post mostRecentPost(SubSection subSection);

    int topicsPerSubSection(int subSectionId);

    int repliesPerSubSection(int subSectionId);
}
