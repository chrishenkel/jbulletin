package org.jbulletin.service;

import java.util.Collection;
import java.util.List;

import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.model.DetailedSection;
import org.jbulletin.service.model.DetailedSubSection;
import org.springframework.stereotype.Service;

public interface ForumService {

    Collection<Topic> getTopicsFromSubSection(int subSectionId, int pageIndex,
	    int topicsPerPage);

    public int topicsPerSubSection(int subSectionId);

    public void savePost(int topicId, Post post);

    public DetailedSubSection getDetailedSubSection(SubSection subSection);

    public DetailedSection getDetailedSection(Section section);

    public List<DetailedSection> getDetailedSections();

    public void saveSection(Section section2);

    public Section getSection(int id);

    public UserDetails getUserByName(String userName);

    public void saveTopic(Topic topic);

    public void incrementViewCount(Topic topic, UserDetails userDetails);

    public void saveUser(UserDetails userDetails1);

    public SubSection getSubSection(int subSectionId);

    public void saveSubSection(SubSection subSection);

    public void incrementPostCountForUser(UserDetails userDetails);

    public void saveUserImage(UserDetails userDetails, byte[] byteArray);

    public UserDetails getUserById(int userId);
}
