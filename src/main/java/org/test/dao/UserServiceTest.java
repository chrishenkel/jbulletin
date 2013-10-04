package org.test.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.PersistenceException;

import org.jbulletin.model.Post;
import org.jbulletin.model.Topic;
import org.jbulletin.model.UserDetails;
import org.jbulletin.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;

    private UserDetails user;

    @Before
    public void initObjects() {
	user = new UserDetails();
	user.setName("Chris");
	user.setPassword("Password");
	
	assertEquals(user.getName(), "Chris");
	assertEquals(user.getPassword(), "Password");	
    }

    @Test
    public void testCount() {
	UserDetails user2 = new UserDetails();
	user2.setName("Chris2");
	user2.setPassword("testpassword");

	userService.saveUser(user);
	userService.saveUser(user2);

	assertEquals(2, userService.getUserCount());
    }

    @Test(expected = PersistenceException.class)
    public void testUniqueConstraint() throws Exception {
	UserDetails user2 = new UserDetails();
	user2.setName(user.getName());
	user2.setPassword(user.getPassword());

	userService.saveUser(user);
	userService.saveUser(user2);
    }

    @Test
    public void testSaveUser() {
	userService.saveUser(user);
	assertEquals(user.getName(), userService.getUserById(user.getId()).getName());
    }

    @Test
    public void testUserExists() {
	userService.saveUser(user);
	assertEquals(true, userService.userExists(user.getName()));
    }

    @Test
    public void testUserMatch() {
	userService.saveUser(user);
	UserDetails details = userService.userMatch(user.getName(), user.getPassword());
	assertEquals(user.getName(), details.getName());
	assertEquals(user.getPassword(), details.getPassword());
    }
    
    @Test
    public void saveUserImage() {
	user.setAvatar(new byte[] { 1, 2, 3});
	userService.saveUser(user);
	UserDetails retrieved = userService.getUserById(user.getId());
	assertEquals(1, retrieved.getAvatar()[0]);
	assertEquals(2, retrieved.getAvatar()[1]);
	assertEquals(3, retrieved.getAvatar()[2]);
	assertEquals(3, retrieved.getAvatar().length);
    }
        
    @Test
    public void getUserByName() {
	userService.saveUser(user);
	assertEquals(user.getName(), userService.getUserByName(user.getName()).getName());
    }    

    @Test
    public void getUserById() {
	userService.saveUser(user);
	assertEquals(user.getName(), userService.getUserById(user.getId()).getName());
    }    
    
    @Test
    public void incrementPostCount() {
	userService.saveUser(user);
	userService.incrementPostCountForUser(user);
	assertEquals(1, userService.getUserById(user.getId()).getPostCount());
    }        
}
