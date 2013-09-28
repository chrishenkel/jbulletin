package org.jbulletin.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SubSection {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "subSection")
    private Collection<Topic> topics = new ArrayList<Topic>();
    
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Collection<Topic> getTopics() {
	return topics;
    }

    public void setTopics(Collection<Topic> Topics) {
	this.topics = Topics;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void addTopic(Topic topic) {
	getTopics().add(topic);
	topic.setSubSection(this);
    }
}
