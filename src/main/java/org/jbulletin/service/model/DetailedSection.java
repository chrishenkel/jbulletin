package org.jbulletin.service.model;

import java.util.Collection;

public class DetailedSection {
    private String name;
    private Collection<DetailedSubSection> subSections;

    public DetailedSection(String name,
	    Collection<DetailedSubSection> subSections) {
	this.name = name;
	this.subSections = subSections;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Collection<DetailedSubSection> getSubSections() {
	return subSections;
    }

    public void setSubSections(Collection<DetailedSubSection> subSections) {
	this.subSections = subSections;
    }

}
