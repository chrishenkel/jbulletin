package org.jbulletin.dao;

import org.jbulletin.model.Post;
import org.jbulletin.model.SubSection;

public interface SubSectionDao {

    public SubSection getSubSection(int id);

    public void saveSection(SubSection subSection);
    
    public Post mostRecentPost(SubSection subSection);

    public int topicsPerSubSection(int subSectionId);

    public int repliesPerSubSection(int subSectionId);
}
