package org.jbulletin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private int id;
    
    private int selector;

    private String content;

    @ManyToOne
    private Topic topic;

    private Date posted = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    private UserDetails poster;

    public int getSelector() {
        return selector;
    }

    public void setSelector(int selector) {
        this.selector = selector;
    }

    public UserDetails getPoster() {
	return poster;
    }

    public void setPoster(UserDetails poster) {
	this.poster = poster;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Topic getTopic() {
	return topic;
    }

    public void setTopic(Topic topic) {
	this.topic = topic;
    }

    public Date getPosted() {
	return posted;
    }

    public void setPosted(Date posted) {
	this.posted = posted;
    }

}
