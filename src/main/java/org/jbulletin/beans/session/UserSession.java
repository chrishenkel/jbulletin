package org.jbulletin.beans.session;

import java.io.Serializable;

import org.jbulletin.model.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserSession {
    private static final long serialVersionUID = -2432342912796200644L;

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
