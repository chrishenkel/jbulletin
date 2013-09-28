package org.test.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jbulletin.model.SubSection;
import org.jbulletin.service.SubSectionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class SubSectionDaoTest {
    @PersistenceContext
    private EntityManager factory;

    @Autowired
    private SubSectionService subSectionService;

    public EntityManager getFactory() {
	return factory;
    }

    public void setFactory(EntityManager factory) {
	this.factory = factory;
    }

    @Test
    public void testGet() {

	EntityManager session = getFactory();

	SubSection section1 = new SubSection();
	section1.setName("Section name 1");

	SubSection section2 = new SubSection();
	section2.setName("Section name 2");

	session.persist(section1);
	session.persist(section2);

	assertEquals("Section name 1",
		session.find(SubSection.class, section1.getId()));
    }

}
