package org.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.UserService;
import org.jbulletin.service.model.DetailedSection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class ForumServiceTest {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;
    
    public ForumService getForumService() {
	return forumService;
    }

    public void setForumService(ForumService forumService) {
	this.forumService = forumService;
    }

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    @Test
    public void testTopicListings() {
	Section section = new Section();
	section.setName("TestSection");

	SubSection subSection = new SubSection();
	
	Topic topic1 = new Topic();
	Topic topic2 = new Topic();

	Post post1 = new Post();
	Post post2 = new Post();

	topic1.addPost(post1);
	topic2.addPost(post2);

	topic1.setSubSection(subSection);
	topic2.setSubSection(subSection);

	subSection.addTopic(topic1);
	subSection.addTopic(topic2);

	section.getSubSections().add(subSection);

	forumService.saveSection(section);

	Collection<Topic> topics = forumService.getTopicsFromSubSection(
		subSection.getId(), 0, 10);
	
	DetailedSection ds = forumService.getDetailedSection(section);

	assertEquals(2, topics.size());
	assertEquals(2, forumService.topicsPerSubSection(subSection.getId()));
	assertEquals("TestSection", ds.getName());
    }

    @Test
    public void testSaveTopic() {
	Topic topic = new Topic();
	topic.setName("TestName");

	Post post = new Post();
	post.setContent("Test");

	topic.addPost(post);

	forumService.saveTopic(topic);

	assertEquals("Test", forumService
		.getPostsFromTopic(topic.getId(), 0, 1).iterator().next()
		.getContent());
    }
    
    @Test
    public void testSavePost() {
	Topic topic = new Topic();
	topic.setName("TestName");

	Post post = new Post();
	post.setContent("Test");

	topic.addPost(post);

	forumService.saveTopic(topic);
	
	Post post2 = new Post();
	post.setContent("Test2");
	
	topic.addPost(post);
	
	forumService.savePost(post2);

	assertEquals("Test", forumService
		.getPostsFromTopic(topic.getId(), 0, 1).iterator().next()
		.getContent());
    }
    
    @Test
    public void testSaveSubSection() {
	SubSection subSection = new SubSection();
	subSection.setName("Test");

	forumService.saveSubSection(subSection);
	assertEquals("Test", forumService.getSubSection(subSection.getId()).getName());
    }

    @Test
    public void testSaveSection() {
	Section section = new Section();
	section.setName("Test");

	forumService.saveSection(section);
	assertEquals("Test", forumService.getSection(section.getId()).getName());
    }

    @Test
    public void testIncrementViewCount() {
	Topic topic = new Topic();
	int before = topic.getViewCount();
	forumService.saveTopic(topic);
	forumService.incrementViewCount(topic);
	assertEquals(before+1, topic.getViewCount());
	assertEquals(before+1, forumService.getTopic(topic.getId()).getViewCount());
    }
}
