package org.jbulletin.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateDao {

    @Autowired
    private SessionFactory factory;

    public SessionFactory getFactory() {
	return factory;
    }

    public void setFactory(SessionFactory factory) {
	this.factory = factory;
    }
}
