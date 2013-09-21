package org.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbulletin.model.Section;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.SectionService;
import org.jbulletin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class SectionDaoTest {
	@Autowired
	private SessionFactory factory;
	
	@Autowired
	private SectionService sectionService;

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

	@Test
	public void testCount() 
	{
		
		Session session = getFactory().getCurrentSession();
		
		Section section1 = new Section();
		section1.setName("Section name 1");
		
		Section section2 = new Section();
		section2.setName("Section name 2");

		session.save(section1);
		session.save(section2);
		
		assertEquals(2, sectionService.getSections().size());
	}
}
