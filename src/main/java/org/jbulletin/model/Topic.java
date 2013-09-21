package org.jbulletin.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class Topic {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "topic")
    private Collection<Post> posts = new ArrayList<Post>();

    @ManyToOne
    private SubSection subSection;

    @OneToOne
    private Post mostRecentPost;

    private int postCount;

    private int viewCount;

    @OneToOne
    private UserDetails poster;

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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Collection<Post> getPosts() {
	return posts;
    }

    public void setPosts(Collection<Post> posts) {
	this.posts = posts;
    }

    public SubSection getSubSection() {
	return subSection;
    }

    public void setSubSection(SubSection section) {
	this.subSection = section;
    }

    public int getPostCount() {
	return postCount;
    }

    public void setPostCount(int postCount) {
	this.postCount = postCount;
    }

    public void addPost(Post post) {
	posts.add(post);
	post.setSelector(postCount++);
	post.setTopic(this);
	mostRecentPost = post;
    }

    public int getViewCount() {
	return viewCount;
    }

    public void setViewCount(int viewCount) {
	this.viewCount = viewCount;
    }

    public Post getMostRecentPost() {
	return mostRecentPost;
    }

    public void setMostRecentPost(Post mostRecentPost) {
	this.mostRecentPost = mostRecentPost;
    }

}
