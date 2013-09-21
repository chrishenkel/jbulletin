package org.jbulletin.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountLoginForm {
    @NotEmpty(message = "Field cannot be empty")
    // make sure name is not empty
    private String userName;

    @NotEmpty(message = "Field cannot be empty")
    // make sure name is not empty
    private String password;

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
