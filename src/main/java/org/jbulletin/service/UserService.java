package org.jbulletin.service;

import org.jbulletin.form.AccountRegisterForm;
import org.jbulletin.model.UserDetails;

public interface UserService {

    public int getUserCount();
    
    public boolean userExists(String userName);

    public UserDetails createNewUser(AccountRegisterForm registerForm);

    public UserDetails userMatch(String userName, String password);

    public void incrementPostCountForUser(UserDetails userDetails);
    
    public UserDetails getUserById(int userId);

    public UserDetails getUserByName(String userName);

    public void saveUser(UserDetails userDetails1);
}
