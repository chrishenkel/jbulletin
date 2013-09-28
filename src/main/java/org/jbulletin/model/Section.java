package org.jbulletin.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Section {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
    private Collection<SubSection> subSections = new ArrayList<SubSection>();

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

    public Collection<SubSection> getSubSections() {
	return subSections;
    }

    public void setSubSections(Collection<SubSection> subSections) {
	this.subSections = subSections;
    }
}
