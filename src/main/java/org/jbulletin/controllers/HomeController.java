package org.jbulletin.controllers;

import java.util.List;

import org.jbulletin.beans.session.UserSession;
import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.model.DetailedSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class HomeController {
    @Autowired
    private ForumService forumService;

    @Autowired
    private UserSession userSession;

    @ModelAttribute("userSession")
    public UserSession getUserSession() {
	return userSession;
    }

    public void setUserSession(UserSession userSession) {
	this.userSession = userSession;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav) {
	List<DetailedSection> sections = forumService.getDetailedSections();
	mav.setViewName("index");
	mav.addObject("sections", sections);
	return mav;
    }

    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    public String setupDatabase() {
	Section section = new Section();
	section.setName("Test Section");

	Section section2 = new Section();
	section.setName("Test Section 2");

	SubSection subSection = new SubSection();
	subSection.setName("Test Sub Section");
	subSection.setDescription("Ask noobie questions here");
	
	UserDetails userDetails1 = new UserDetails();
	userDetails1.setName("Robert");
	userDetails1.setPassword("123456");

	UserDetails userDetails2 = new UserDetails();
	userDetails2.setName("Sally");
	userDetails2.setPassword("123456");

	for (int i = 0; i < 40; i++) {
	    Topic topic = new Topic();
	    topic.setName("Test Topic " + i);

	    if((i % 2) == 0)
	    {
		topic.setPoster(userDetails1);
	    } 
	    else
	    {
		topic.setPoster(userDetails2);		
	    }

	    for (int j = 0; j < 30; j++) {
		Post post = new Post();
		post.setContent("Content " + j);
		topic.addPost(post);
		
		if((j % 2) == 0)
		{
		    post.setPoster(userDetails1);
		} 
		else
		{
		    post.setPoster(userDetails2);
		}
	    }

	    subSection.addTopic(topic);
	}

	section.getSubSections().add(subSection);
	
	forumService.saveUser(userDetails1);
	forumService.saveUser(userDetails2);

	forumService.saveSection(section);
	forumService.saveSection(section2);

	return "redirect:/";
    }
}
