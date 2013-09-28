package org.jbulletin.controllers;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.jbulletin.beans.session.UserSession;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController {

    @Autowired
    private UserSession userSession;

    @Autowired
    private ForumService forumService;

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

    public void setForumService(ForumService forumService) {
	this.forumService = forumService;
    }

    @RequestMapping("/profile/{userId}")
    public ModelAndView profile(@PathVariable int userId, ModelAndView mav) {
	if (!userSession.isLoggedIn()) {
	    mav.setViewName("redirect:/");
	    return mav;
	}

	if (userId != userSession.getUserDetails().getId()) {
	    mav.setViewName("redirect:/");
	    return mav;
	}

	mav.setViewName("user-profile");
	return mav;
    }
    
    @ResponseBody
    @RequestMapping(value = "/profile/{userId}/avatar", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] avatarUpload(@PathVariable int userId)
	    throws IOException {
	System.out.println("executing");
	UserDetails userDetails = forumService.getUserById(userId);
	return userDetails.getCurrentAvatar();
    }

    @RequestMapping(value = "/profile/{userId}/avatar", method = RequestMethod.POST)
    public String avatarUpload(@PathVariable int userId,
	    @RequestParam("file") MultipartFile file, ModelAndView mav)
	    throws IOException {

	if (!userSession.isLoggedIn()) {
	    mav.setViewName("redirect:/");
	}

	if (userId != userSession.getUserDetails().getId()) {
	    mav.setViewName("redirect:/");
	}

	UserDetails userDetails = userSession.getUserDetails();
	userDetails.setAvatar(IOUtils.toByteArray(file.getInputStream()));
	forumService.saveUser(userDetails);

	return "redirect:/profile/" + userId;
    }
}
