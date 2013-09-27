package org.jbulletin.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbulletin.dao.UserDao;
import org.jbulletin.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateUserDaoImpl extends HibernateDao implements UserDao {

    @Transactional(propagation = Propagation.REQUIRED)
    public int getUserCount() {
	return (getFactory().getCurrentSession()
		.createQuery("from UserDetails").list()).size();
    }

    @Override
    @Transactional
    public UserDetails getUserByName(String userName) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("select details from UserDetails details where details.name = :userName");
	query.setParameter("userName", userName);
	return ((UserDetails) query.uniqueResult());
    }

    @Override
    @Transactional
    public void saveUser(UserDetails user) {
	getFactory().getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public UserDetails findUser(String userName, String password) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("select details from UserDetails details where details.name = :userName AND details.password = :password");
	query.setParameter("userName", userName);
	query.setParameter("password", password);
	return ((UserDetails) query.uniqueResult());
    }

    @Override
    public UserDetails getUser(int id) {
	// TODO Auto-generated method stub
	return null;
    }

}
