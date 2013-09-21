package org.jbulletin.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AccountRegisterForm {
    @Size(min=4, max=32)
    @NotEmpty(message = "Field cannot be empty")
    // make sure name is not empty
    private String userName;

    @Size(min=4, max=32)
    @NotEmpty(message = "Field cannot be empty")
    // make sure name is not empty
    private String password;

    @Size(min=4, max=32)
    @NotEmpty(message = "Field cannot be empty")
    // make sure name is not empty
    private String rePassword;

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

    public String getRePassword() {
	return rePassword;
    }

    public void setRePassword(String rePassword) {
	this.rePassword = rePassword;
    }

}
