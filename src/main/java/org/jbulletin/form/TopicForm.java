package org.jbulletin.form;

import org.hibernate.validator.constraints.NotEmpty;

public class TopicForm {
    @NotEmpty(message="Field cannot be empty") //make sure name is not empty
    private String topicName;
    
    @NotEmpty(message="Field cannot be empty") //make sure name is not empty
    private String topicContent;

    public String getTopicName() {
	return topicName;
    }

    public void setTopicName(String topicName) {
	this.topicName = topicName;
    }

    public String getTopicContent() {
	return topicContent;
    }

    public void setTopicContent(String topicContent) {
	this.topicContent = topicContent;
    }

}
