package org.jbulletin.service;

import java.util.Collection;
import java.util.List;

import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.service.model.DetailedSection;
import org.jbulletin.service.model.DetailedSubSection;

public interface ForumService {

    public Collection<Topic> getTopicsFromSubSection(int subSectionId, int pageIndex,
	    int topicsPerPage);

    public int topicsPerSubSection(int subSectionId);

    public void savePost(int topicId, Post post);

    public DetailedSubSection getDetailedSubSection(SubSection subSection);

    public DetailedSection getDetailedSection(Section section);

    public List<DetailedSection> getDetailedSections();

    public void saveSection(Section section2);

    public Section getSection(int id);

    public void saveTopic(Topic topic);

    public void incrementViewCount(Topic topic);

    public SubSection getSubSection(int subSectionId);

    public void saveSubSection(SubSection subSection);

    public void savePost(Post post2);

    public Topic getTopic(int topicId);

    public Collection<Post> getPostsFromTopic(int topicId, int index,
	    int windowLength);
}


