package org.jbulletin.dao;

import org.jbulletin.model.UserDetails;

public interface UserDao {
    public int getUserCount();

    public UserDetails getUserByName(String userName);

    public void saveUser(UserDetails user);

    public UserDetails findUser(String userName, String password);

    public UserDetails getUser(int id);
}
