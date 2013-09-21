package org.test.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.jbulletin.dao.TopicDao;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.service.SectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class TopicDaoTest {
    @Autowired
    private SessionFactory factory;

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private SectionService sectionService;

    public SessionFactory getFactory() {
	return factory;
    }

    public void setFactory(SessionFactory factory) {
	this.factory = factory;
    }

    public TopicDao getTopicDao() {
	return topicDao;
    }

    public void setTopicDao(TopicDao topicDao) {
	this.topicDao = topicDao;
    }

    public SectionService getSectionService() {
	return sectionService;
    }

    public void setSectionService(SectionService sectionService) {
	this.sectionService = sectionService;
    }

    @Test
    public void testTopicListings() {

	Section section = new Section();
	section.setName("Test Section");

	SubSection subSection = new SubSection();
	subSection.setName("Test Sub Section");
	subSection.setDescription("Ask noobie questions here");

	section.getSubSections().add(subSection);

	Topic topic1 = new Topic();
	topic1.setName("Topic 1");

	Topic topic2 = new Topic();
	topic2.setName("Topic 2");

	subSection.addTopic(topic1);
	subSection.addTopic(topic2);

	sectionService.saveSection(section);

	Collection<Topic> topics = topicDao.getTopicsFromSubSection(
		section.getId(), 0, 10);

	assertEquals(2, topics.size());
    }
}
