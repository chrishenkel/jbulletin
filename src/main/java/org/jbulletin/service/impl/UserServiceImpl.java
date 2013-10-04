package org.jbulletin.service.impl;

import org.jbulletin.dao.UserDao;
import org.jbulletin.form.AccountRegisterForm;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    
    public UserDao getUserDao() {
	return userDao;
    }

    public void setUserDao(UserDao userDao) {
	this.userDao = userDao;
    }

    @Override
    public int getUserCount() {
	return userDao.getUserCount();
    }

    @Override
    public UserDetails getUserByName(String userName) {
	return userDao.getUserByName(userName);
    }

    @Override
    public void saveUser(UserDetails userDetails1) {
	userDao.saveUser(userDetails1);
    }

    @Override
    public boolean userExists(String userName) {
	if (userDao.getUserByName(userName) != null) {
	    return true;
	}
	return false;
    }

    @Override
    public UserDetails createNewUser(AccountRegisterForm registerForm) {
	UserDetails user = new UserDetails();
	user.setName(registerForm.getUserName());
	user.setPassword(registerForm.getPassword());
	userDao.saveUser(user);
	return user;
    }

    @Override
    public UserDetails userMatch(String userName, String password) {
	return userDao.findUser(userName, password);
    }

    @Override
    public void incrementPostCountForUser(UserDetails userDetails) {
	userDetails = userDao.getUser(userDetails.getId());	
	userDetails.setPostCount(userDetails.getPostCount() + 1);
	userDao.saveUser(userDetails);
    }

    @Override
    public UserDetails getUserById(int userId) {
	return userDao.getUser(userId);
    }
}
