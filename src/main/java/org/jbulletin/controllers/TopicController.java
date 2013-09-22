package org.jbulletin.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jbulletin.beans.session.UserSession;
import org.jbulletin.dao.SubSectionDao;
import org.jbulletin.dao.TopicDao;
import org.jbulletin.form.TopicForm;
import org.jbulletin.model.Post;
import org.jbulletin.model.SubSection;
import org.jbulletin.model.Topic;
import org.jbulletin.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopicController {

    @Autowired
    private TopicDao topicDao;

    @Autowired
    private SubSectionDao subSectionDao;

    @Autowired
    private ForumService forumService;

    private int postsPerPage = 10;

    @Autowired
    private UserSession userSession;

    @ModelAttribute("userSession")
    public UserSession getUserSession() {
	return userSession;
    }

    public void setUserSession(UserSession userSession) {
	this.userSession = userSession;
    }

    public TopicDao getTopicDao() {
	return topicDao;
    }

    public void setTopicDao(TopicDao topicDao) {
	this.topicDao = topicDao;
    }

    public SubSectionDao getSubSectionDao() {
	return subSectionDao;
    }

    public void setSubSectionDao(SubSectionDao subSectionDao) {
	this.subSectionDao = subSectionDao;
    }

    public ForumService getForumService() {
	return forumService;
    }

    public void setForumService(ForumService forumService) {
	this.forumService = forumService;
    }

    public int getPostsPerPage() {
	return postsPerPage;
    }

    public void setPostsPerPage(int postsPerPage) {
	this.postsPerPage = postsPerPage;
    }

    @RequestMapping("/sub/{subSectionId}/topic/{topicId}")
    public ModelAndView topic(
	    @RequestParam(value = "page", defaultValue = "0") int page,
	    @RequestParam(value = "last", defaultValue = "false") boolean last,
	    @PathVariable int subSectionId, @PathVariable int topicId,
	    ModelAndView mav, HttpServletRequest request) {
	System.out.println("context path = " +  request.getContextPath());
	System.out.println("servlet path = " + request.getServletPath());

	Topic topic = topicDao.getTopic(topicId);

	forumService.incrementViewCount(topic);
	
	int pageCount = (topic.getPostCount() / postsPerPage)
		+ (((topic.getPostCount() % postsPerPage) > 0) ? 1 : 0);

	if (last) {
	    page = pageCount - 1;
	}

	int index = page * postsPerPage;

	int windowLength = Math.min(postsPerPage, topic.getPostCount()
		- (page * postsPerPage));

	Collection<Post> posts = topicDao.getPostsFromTopic(topicId, index,
		windowLength);

	int previousPage = Math.max(0, page - 1);
	int nextPage = Math.min(pageCount - 1, page + 1);

	mav.setViewName("view-topic");
	mav.addObject("posts", posts);
	mav.addObject("pageCount", pageCount);
	mav.addObject("previousPage", previousPage);
	mav.addObject("nextPage", nextPage);
	mav.addObject("page", page);
	mav.addObject("postsPerPage", postsPerPage);
	mav.addObject("topic", topic);
	mav.addObject("ckeditorRoot", request.getContextPath()
		+ "/resources/ckeditor/");
	return mav;
    }

    @RequestMapping(value = "/sub/{subSectionId}/topic/{topicId}/post", method = RequestMethod.POST)
    public String postTopic(
	    @RequestParam(value = "index", defaultValue = "0") int pageIndex,
	    @RequestParam(value = "forum-editor", defaultValue = "0") String content,
	    @PathVariable int subSectionId, @PathVariable int topicId,
	    HttpServletRequest request) {

	if (!userSession.isLoggedIn()) {
	    return "redirect:/account/login";
	}

	Post post = new Post();
	post.setContent(content);
	post.setPoster(userSession.getUserDetails());
	forumService.savePost(topicId, post);
	return "redirect:/sub/" + subSectionId + "/topic/" + topicId
		+ "?last=true";
    }

    @RequestMapping("/sub/{subSectionId}/topic/{topicId}/post/new")
    public ModelAndView newPost(ModelAndView mav,
	    @PathVariable int subSectionId, @PathVariable int topicId,
	    HttpServletRequest request) {

	if (!userSession.isLoggedIn()) {
	    mav.setViewName("redirect:/account/login");
	    return mav;
	}

	Topic topic = topicDao.getTopic(topicId);

	mav.setViewName("new-post");
	mav.addObject("topic", topic);
	mav.addObject("ckeditorRoot", request.getContextPath()
		+ "/resources/ckeditor/");
	return mav;
    }

    @RequestMapping("/sub/{subSectionId}/topic/new")
    public ModelAndView newTopic(ModelAndView mav,
	    @PathVariable int subSectionId, HttpServletRequest request) {

	if (!userSession.isLoggedIn()) {
	    mav.setViewName("redirect:/account/login");
	    return mav;
	}

	SubSection subSection = subSectionDao.getSubSection(subSectionId);

	mav.setViewName("new-topic");
	mav.addObject("topicForm", new TopicForm());
	mav.addObject("subSection", subSection);
	mav.addObject("postUrl", request.getContextPath() + "/sub/"
		+ subSectionId + "/topic");
	mav.addObject("ckeditorRoot", request.getContextPath()
		+ "/resources/ckeditor/");
	return mav;
    }

    @RequestMapping(value = "/sub/{subSectionId}/topic", method = RequestMethod.POST)
    public ModelAndView newTopicPost(ModelAndView mav,
	    @PathVariable int subSectionId, HttpServletRequest request,
	    @ModelAttribute("topicForm") @Valid TopicForm topicForm,
	    BindingResult result) {
	mav.addObject("ckeditorRoot", request.getContextPath()
		+ "/resources/ckeditor/");

	if (!userSession.isLoggedIn()) {
	    mav.setViewName("redirect:/account/login");
	    return mav;
	}

	if (result.hasErrors()) {
	    mav.addObject("subSection",
		    subSectionDao.getSubSection(subSectionId));
	    mav.setViewName("new-topic");
	    return mav;
	}

	Topic topic = new Topic();

	Post post = new Post();
	System.out.println("user sessesion = " + userSession.getUserDetails()
		+ " when posting");
	post.setPoster(userSession.getUserDetails());
	post.setContent(topicForm.getTopicContent());

	topic.setName(topicForm.getTopicName());
	topic.setPoster(userSession.getUserDetails());
	topic.addPost(post);

	SubSection subSection = subSectionDao.getSubSection(subSectionId);

	topic.setSubSection(subSection);

	forumService.saveTopic(topic);

	mav.setViewName("redirect:/sub/" + subSectionId);
	return mav;
    }
}
