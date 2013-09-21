package org.jbulletin.beans.session;

import org.jbulletin.model.UserDetails;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private boolean loggedIn;

    private UserDetails userDetails;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    
    
}