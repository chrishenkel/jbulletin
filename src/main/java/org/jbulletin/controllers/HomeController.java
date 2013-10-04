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
import org.jbulletin.service.UserService;
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
    private UserService userService;

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
    
}
