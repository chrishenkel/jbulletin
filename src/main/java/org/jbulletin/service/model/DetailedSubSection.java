package org.jbulletin.service.model;

import org.jbulletin.model.Post;
import org.jbulletin.model.SubSection;

public class DetailedSubSection {
    private int id;

    private String name;

    private String description;

    private Post mostRecentPost;

    private int topicCount;

    private int replyCount;

    public DetailedSubSection(int topicCount, int replyCount,
	    SubSection subSection, Post mostRecentPost) {
	this.topicCount = topicCount;
	this.replyCount = replyCount;
	this.id = subSection.getId();
	this.name = subSection.getName();
	this.description = subSection.getDescription();
	this.mostRecentPost = mostRecentPost;
    }

    public int getReplyCount() {
	return replyCount;
    }

    public void setReplyCount(int replyCount) {
	this.replyCount = replyCount;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Post getMostRecentPost() {
	return mostRecentPost;
    }

    public void setMostRecentPost(Post mostRecentPost) {
	this.mostRecentPost = mostRecentPost;
    }

    public int getTopicCount() {
	return topicCount;
    }

    public void setTopicCount(int topicsCount) {
	this.topicCount = topicsCount;
    }

}
