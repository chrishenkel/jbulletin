package org.jbulletin.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.jbulletin.dao.PostDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernatePostDao extends HibernateDao implements PostDao {

    @Override
    @Transactional
    public void savePost(Post post) {
	getFactory().getCurrentSession().save(post);
    }

    @Override
    @Transactional
    public Post getPost(int id) {
	return (Post) getFactory().getCurrentSession().get(Post.class, id);
    }

    @Override
    @Transactional
    public Post mostRecentPostBySubSection(int subSectionId) {
	Session session = getFactory().getCurrentSession();
	Query query = session
		.createQuery("FROM Post as p where p.topic.subSection.id = :id ORDER BY p.posted DESC");
	query.setParameter("id", subSectionId);
	return (Post) query.uniqueResult();
    }

}
