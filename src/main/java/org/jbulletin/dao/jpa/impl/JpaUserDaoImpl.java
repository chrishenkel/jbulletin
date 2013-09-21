package org.jbulletin.dao.jpa.impl;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbulletin.dao.UserDao;
import org.jbulletin.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
public class JpaUserDaoImpl extends JpaDao implements UserDao {

    @Transactional(propagation = Propagation.REQUIRED)
    public int getUserCount() {
	return (manager.createQuery("from UserDetails").getResultList()).size();
    }

    @Override
    @Transactional
    public UserDetails getUserByName(String userName) {
	Query query = manager
		.createQuery("select details from UserDetails details where details.name = :userName");
	query.setParameter("userName", userName);
	return ((UserDetails) query.getSingleResult());
    }

    @Override
    @Transactional
    public void saveUser(UserDetails user) {
	manager.persist(user);
    }

    @Override
    @Transactional
    public UserDetails findUser(String userName, String password) {
	Query query = manager
		.createQuery("select details from UserDetails details where details.name = :userName AND details.password = :password");
	query.setParameter("userName", userName);
	query.setParameter("password", password);
	return ((UserDetails) query.getSingleResult());
    }

}
