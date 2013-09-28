package org.test.dao;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class UserDaoTest {
    @Autowired
    private SessionFactory factory;

    @Autowired
    private UserService userService;

    public SessionFactory getFactory() {
	return factory;
    }

    public void setFactory(SessionFactory factory) {
	this.factory = factory;
    }

    @Test
    public void testCount() {
	Session session = getFactory().getCurrentSession();
	UserDetails user1 = new UserDetails();
	user1.setName("Chris");
	user1.setPassword("testpassword");

	UserDetails user2 = new UserDetails();
	user2.setName("Chris");
	user2.setPassword("testpassword");

	session.save(user1);
	session.save(user2);

	assertEquals(2, userService.getUserCount());
    }
}
