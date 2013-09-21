package org.jbulletin.dao.jpa.impl;

import java.util.Collection;

import javax.persistence.Query;

import org.jbulletin.dao.TopicDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.Topic;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
public class JpaTopicDaoImpl extends JpaDao implements TopicDao {

    @Override
    @Transactional
    public Topic getTopic(int topicId) {
	return (Topic) manager.find(Topic.class, topicId);
    }

    @Override
    @Transactional
    public void saveTopic(Topic topic) {
	manager.merge(topic);
    }

    @Override
    @Transactional
    public Collection<Topic> getTopicsFromSubSection(int subSectionId,
	    int start, int length) {
	Query query = manager.createQuery("FROM Topic as t where t.subSection.id= :id ORDER BY t.mostRecentPost.posted DESC");
	query.setParameter("id", subSectionId);
	query.setFirstResult(start);
	query.setMaxResults(length);
	return query.getResultList();
    }
    
    @Override
    @Transactional
    public Collection<Post> getPostsFromTopic(int topicId, int start, int length) {
	Query query = manager.createQuery("FROM Post as p where p.topic.id = :id");
	query.setParameter("id", topicId);
	query.setFirstResult(start);
	query.setMaxResults(length);
	return query.getResultList();
    }

}
