package org.jbulletin.beans;

import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements
	ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserService userService;

    private static boolean initialized = false;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
	if (initialized) {
	    return;
	}

	Section section = new Section();
	section.setName("JBulletin Forum");

	SubSection subSection = new SubSection();
	subSection.setName("General");
	subSection.setDescription("Ask noobie questions here");

	UserDetails userDetails1 = new UserDetails();
	userDetails1.setName("Robert");
	userDetails1.setPassword("123456");

	UserDetails userDetails2 = new UserDetails();
	userDetails2.setName("Sally");
	userDetails2.setPassword("123456");

	section.getSubSections().add(subSection);

	for (int i = 0; i < 40; i++) {
	    Topic topic = new Topic();
	    topic.setName("Test Topic " + i);

	    if ((i % 2) == 0) {
		topic.setPoster(userDetails1);
		userDetails1.setPostCount(userDetails1.getPostCount() + 1);
	    } else {
		topic.setPoster(userDetails2);
		userDetails2.setPostCount(userDetails2.getPostCount() + 1);
	    }

	    subSection.addTopic(topic);

	    for (int j = 0; j < 30; j++) {
		Post post = new Post();
		post.setContent("Content " + j);

		if (j == 0) {
		    post.setPoster(topic.getPoster());
		} else {
		    if ((j % 2) == 0) {
			post.setPoster(userDetails1);
		    } else {
			post.setPoster(userDetails2);
		    }
		}

		post.getPoster().setPostCount(
			post.getPoster().getPostCount() + 1);

		topic.addPost(post);
	    }
	}

	userService.saveUser(userDetails1);
	userService.saveUser(userDetails2);

	forumService.saveSection(section);

	initialized = true;
    }
}