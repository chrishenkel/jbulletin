package org.jbulletin.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.jbulletin.beans.session.UserSession;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubSectionController {

    @Autowired
    private ForumService forumService;

    @Autowired
    @Qualifier("topicsPerPage")
    private Integer topicsPerPage;
    
    @Autowired
    private UserSession userSession;

    @ModelAttribute("userSession")
    public UserSession getUserSession() {
	return userSession;
    }

    public void setUserSession(UserSession userSession) {
	this.userSession = userSession;
    }

    public ForumService getForumService() {
	return forumService;
    }

    public void setForumService(ForumService impl) {
	this.forumService = impl;
    }

    @RequestMapping("/sub/{subSectionId}")
    public ModelAndView sub(
	    @RequestParam(value = "page", defaultValue = "0") int page,
	    @PathVariable int subSectionId, ModelAndView mav,
	    HttpServletRequest request) {

	int topicCount = forumService.topicsPerSubSection(subSectionId);

	int index = page * topicsPerPage;

	int windowLength = Math.min(topicsPerPage, topicCount
		- (page * topicsPerPage));

	int pageCount = (topicCount / topicsPerPage)
		+ (((topicCount % topicsPerPage) > 0) ? 1 : 0);

	Collection<Topic> topics = forumService.getTopicsFromSubSection(
		subSectionId, index, windowLength);

	SubSection subSection = forumService.getSubSection(subSectionId);

	int previousPage = Math.max(0, page - 1);
	int nextPage = Math.min(pageCount - 1, page + 1);

	mav.addObject("topics", topics);
	mav.addObject("topicsPerPage", topicsPerPage);
	mav.addObject("topicCount", topicCount);
	mav.addObject("subSection", subSection);
	mav.addObject("page", page);
	mav.addObject("previousPage", previousPage);
	mav.addObject("nextPage", nextPage);
	mav.addObject("pageCount", pageCount);
	mav.addObject("ckeditorRoot", request.getContextPath()
		+ "/resources/ckeditor/");
	mav.setViewName("topic-listings");
	return mav;
    }
}
