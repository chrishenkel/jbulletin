package org.jbulletin.dao.impl;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jbulletin.dao.TopicDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateTopicDaoImpl extends HibernateDao implements TopicDao {

    @Override
    @Transactional
    public Topic getTopic(int topicId) {
	Session session = getFactory().getCurrentSession();
	return (Topic) session.get(Topic.class, topicId);
    }

    @Override
    @Transactional
    public void saveTopic(Topic topic) {
	getFactory().getCurrentSession().saveOrUpdate(topic);
    }

    @Override
    @Transactional
    public Collection<Topic> getTopicsFromSubSection(int subSectionId,
	    int start, int length) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("FROM Topic as t where t.subSection.id= :id ORDER BY t.mostRecentPost.posted DESC");
	query.setParameter("id", subSectionId);
	query.setFirstResult(start);
	query.setMaxResults(length);
	return query.list();
    }
    
    @Override
    @Transactional
    public Collection<Post> getPostsFromTopic(int topicId, int start, int length) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("FROM Post as p where p.topic.id = :id");
	query.setParameter("id", topicId);
	query.setFirstResult(start);
	query.setMaxResults(length);
	return query.list();
    }

}
