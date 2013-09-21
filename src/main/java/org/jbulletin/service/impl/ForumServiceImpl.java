package org.jbulletin.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jbulletin.dao.PostDao;
import org.jbulletin.dao.SectionDao;
import org.jbulletin.dao.SubSectionDao;
import org.jbulletin.dao.TopicDao;
import org.jbulletin.dao.UserDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.model.DetailedSection;
import org.jbulletin.service.model.DetailedSubSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ForumServiceImpl implements ForumService {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private SubSectionDao subSectionDao;

    @Autowired
    private SectionDao sectionDao;

    public TopicDao getTopicDao() {
	return topicDao;
    }

    public void setTopicDao(TopicDao topicDao) {
	this.topicDao = topicDao;
    }

    public PostDao getPostDao() {
	return postDao;
    }

    public void setPostDao(PostDao postDao) {
	this.postDao = postDao;
    }

    public Collection<Topic> getTopicsFromSubSection(int subSectionId,
	    int index, int results) {
	return topicDao.getTopicsFromSubSection(subSectionId, index, results);
    }

    public int topicsPerSubSection(int subSectionId) {
	return subSectionDao.topicsPerSubSection(subSectionId);
    }
    
    @Override
    public void savePost(int topicId, Post post) {
	Topic topic = topicDao.getTopic(topicId);
	topic.addPost(post);
	topicDao.saveTopic(topic);
    }

    @Override
    public DetailedSubSection getDetailedSubSection(SubSection subSection) {
	Post mostRecentPost = subSectionDao.mostRecentPost(subSection);
	return new DetailedSubSection(
		subSectionDao.topicsPerSubSection(subSection.getId()), 
		subSectionDao.repliesPerSubSection(subSection.getId()),
		subSection, 
		mostRecentPost);
    }

    @Override
    public DetailedSection getDetailedSection(Section section) {
	Collection<SubSection> subSections = section.getSubSections();
	Collection<DetailedSubSection> detailedSubSections = new ArrayList();
	for (SubSection subSection : subSections) {
	    detailedSubSections.add(getDetailedSubSection(subSection));
	}
	return new DetailedSection(section.getName(), detailedSubSections);
    }

    @Override
    public List<DetailedSection> getDetailedSections() {
	List<Section> sections = sectionDao.getSections();
	List<DetailedSection> detailedSections = new ArrayList();
	for (Section section : sections) {
	    detailedSections.add(getDetailedSection(section));
	}
	return detailedSections;
    }

    @Override
    public void saveSection(Section section) {
	sectionDao.saveSection(section);
    }

    @Override
    public Section getSection(int id) {
	return sectionDao.getSection(id);
    }

    @Override
    public UserDetails getUserByName(String userName) {
	return userDao.getUserByName(userName);
    }

    @Override
    public void saveTopic(Topic topic) {
	topicDao.saveTopic(topic);
    }

    @Override
    public void incrementViewCount(Topic topic) {
	topic.setViewCount(topic.getViewCount() + 1);
	System.out.println("count = " + topic.getViewCount());
	topicDao.saveTopic(topic);
    }

    @Override
    public void saveUser(UserDetails userDetails1) {
	userDao.saveUser(userDetails1);
    }

    @Override
    public SubSection getSubSection(int subSectionId) {
	return subSectionDao.getSubSection(subSectionId);
    }

}
