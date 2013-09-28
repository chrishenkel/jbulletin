package org.jbulletin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.jbulletin.beans.session.UserSession;
import org.jbulletin.form.AccountLoginForm;
import org.jbulletin.form.AccountRegisterForm;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.ForumService;
import org.jbulletin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
public class AccountController {

    @Autowired
    private UserService userService;

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

    public UserService getUserService() {
	return userService;
    }

    public void setUserService(UserService userService) {
	this.userService = userService;
    }

    @RequestMapping("/account/new")
    public ModelAndView accountRegister(ModelAndView mav,
	    HttpServletRequest request) {
	mav.addObject("registerForm", new AccountRegisterForm());
	mav.addObject("postUrl", request.getContextPath() + "/account");
	mav.setViewName("register");
	return mav;
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ModelAndView accountRegisterPost(
	    ModelAndView mav,
	    HttpServletRequest request,
	    @ModelAttribute("registerForm") @Valid AccountRegisterForm registerForm,
	    BindingResult result) {
	if (result.hasErrors()) {
	    mav.setViewName("register");
	    return mav;
	}

	if (!registerForm.getPassword().equals(registerForm.getRePassword())) {
	    result.addError(new FieldError("registerForm", "password",
		    "Retype passwords to match"));
	    mav.setViewName("register");
	    return mav;
	}
	
	UserDetails userDetails = null;

	if ((userDetails = forumService.getUserByName(registerForm.getUserName())) != null) {
	    result.addError(new FieldError("registerForm", "userName",
		    "A user already exists with that name."));
	    mav.setViewName("register");
	    return mav;
	}

	userDetails = userService.createNewUser(registerForm);
	userSession.setLoggedIn(true);
	System.out.println("user details is " + userDetails);
	userSession.setUserDetails(userDetails);

	mav.setViewName("redirect:/");
	return mav;
    }

    @RequestMapping("/account/login")
    public ModelAndView accountLogin(ModelAndView mav,
	    HttpServletRequest request) {
	mav.addObject("loginForm", new AccountLoginForm());
	mav.addObject("postUrl", request.getContextPath() + "/account/login");
	mav.setViewName("login");
	return mav;
    }

    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public ModelAndView accountLoginPost(ModelAndView mav,
	    HttpServletRequest request,
	    @ModelAttribute("loginForm") @Valid AccountLoginForm loginForm,
	    BindingResult result) {
	if (result.hasErrors()) {
	    mav.setViewName("login");
	    return mav;
	}

	UserDetails userDetails = null;

	if ((userDetails = userService.userMatch(loginForm.getUserName(),
		loginForm.getPassword())) == null) {
	    result.addError(new FieldError("registerForm", "password",
		    "No user found with that password"));
	    mav.setViewName("login");
	    return mav;
	}

	userSession.setLoggedIn(true);
	userSession.setUserDetails(userDetails);

	mav.setViewName("redirect:/");
	return mav;
    }

    @RequestMapping(value = "/account/logout")
    public ModelAndView accountLoginPost(ModelAndView mav) {
	userSession.setLoggedIn(false);

	mav.setViewName("redirect:/");
	return mav;
    }
}
