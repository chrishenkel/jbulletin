package org.jbulletin.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jbulletin.dao.SubSectionDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.SubSection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateSubSectionDaoImpl extends HibernateDao implements
	SubSectionDao {

    @Override
    @Transactional
    public SubSection getSubSection(int id) {
	return (SubSection) (getFactory().getCurrentSession().get(
		SubSection.class, id));
    }

    @Override
    @Transactional
    public int topicsPerSubSection(int subSectionId) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("select count(topic) from Topic topic where topic.subSection.id = :id");
	query.setParameter("id", subSectionId);
	return ((Long) query.uniqueResult()).intValue();
    }
    
    @Override
    @Transactional
    public int repliesPerSubSection(int subSectionId) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("select count(post) from Post post where post.topic.subSection.id = :id");
	query.setParameter("id", subSectionId);
	return ((Long) query.uniqueResult()).intValue();
    }    

    @Override
    @Transactional
    public void saveSection(SubSection subSection) {
	getFactory().getCurrentSession().save(subSection);
    }

    @Override
    public Post mostRecentPost(SubSection subSection) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("FROM Post as p where p.topic.subSection.id= :id ORDER BY p.posted DESC");
	query.setParameter("id", subSection.getId());
	query.setFirstResult(0);
	query.setMaxResults(1);
	return (Post) query.uniqueResult();
    }

}
