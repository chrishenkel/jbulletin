package org.jbulletin.dao.jpa.impl;

import javax.persistence.Query;

import org.jbulletin.dao.PostDao;
import org.jbulletin.model.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
public class JpaPostDao extends JpaDao implements PostDao {

    @Override
    @Transactional
    public void savePost(Post post) {
	manager.persist(post);
    }

    @Override
    @Transactional
    public Post getPost(int id) {
	return manager.find(Post.class, id);
    }

    @Override
    @Transactional
    public Post mostRecentPostBySubSection(int subSectionId) {
	Query query = manager.createQuery("FROM Post as p where p.topic.subSection.id = :id ORDER BY p.posted DESC");
	query.setParameter("id", subSectionId);
	return (Post) query.getSingleResult();
    }

}
