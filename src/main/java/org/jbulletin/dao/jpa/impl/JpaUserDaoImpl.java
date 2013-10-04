package org.jbulletin.dao.jpa.impl;

import static org.jbulletin.dao.jpa.impl.JpaUtil.saveOrUpdate;

import java.util.List;

import javax.persistence.Query;

import org.jbulletin.dao.UserDao;
import org.jbulletin.model.UserDetails;
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
    public UserDetails getUserByName(String userName) {
	Query query = manager
		.createQuery("select details from UserDetails details where details.name = :userName");
	query.setParameter("userName", userName);
	@SuppressWarnings("unchecked")
	List<UserDetails> list = query.getResultList();
	if(list.isEmpty()) return null;
	return (UserDetails) list.get(0);
    }

    @Override
    public void saveUser(UserDetails user) {
	saveOrUpdate(manager, user);
    }

    @Override
    public UserDetails findUser(String userName, String password) {
	Query query = manager
		.createQuery("select details from UserDetails details where details.name = :userName AND details.password = :password");
	query.setParameter("userName", userName);
	query.setParameter("password", password);
	@SuppressWarnings("unchecked")
	List<UserDetails> list = query.getResultList();
	if(list.isEmpty()) return null;
	return (UserDetails) list.get(0);
    }

    @Override
    public UserDetails getUser(int id) {
	return manager.find(UserDetails.class, id);
    }

}
