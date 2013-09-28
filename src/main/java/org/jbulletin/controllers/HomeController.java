package org.jbulletin.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jbulletin.beans.session.UserSession;
import org.jbulletin.model.Post;
import org.jbulletin.model.Section;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.model.DetailedSection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
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

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mav, HttpServletRequest req) {
	List<DetailedSection> sections = forumService.getDetailedSections();
	mav.setViewName("index");
	mav.addObject("sections", sections);
	System.out.println("context path = " + req.getContextPath());
	System.out.println("servlet path = " + req.getServletPath());
	return mav;
    }
    
    private static int id = 0;

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
	userDetails1.setName("Robert" + id++);
	userDetails1.setPassword("123456");

	UserDetails userDetails2 = new UserDetails();
	userDetails2.setName("Sally" + id++);
	userDetails2.setPassword("123456");

	section.getSubSections().add(subSection);

	forumService.saveSection(section);
	forumService.saveSection(section2);

	forumService.saveUser(userDetails1);
	forumService.saveUser(userDetails2);

	for (int i = 0; i < 40; i++) {
	    Topic topic = new Topic();
	    topic.setName("Test Topic " + i);

	    if ((i % 2) == 0) {
		topic.setPoster(userDetails1);
	    } else {
		topic.setPoster(userDetails2);
	    }
	    
	    subSection.addTopic(topic);

	    forumService.saveTopic(topic);

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

		forumService.savePost(topic.getId(), post);
	    }
	}

	
	return "redirect:/index";
    }
}
