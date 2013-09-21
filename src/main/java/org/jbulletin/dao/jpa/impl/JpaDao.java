package org.jbulletin.dao.jpa.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaDao {

    @PersistenceContext
    EntityManager manager;
}
