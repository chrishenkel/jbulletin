package org.jbulletin.dao.jpa.impl;

import static org.jbulletin.dao.jpa.impl.JpaUtil.saveOrUpdate;

import java.util.List;

import javax.persistence.Query;

import org.jbulletin.dao.PostDao;
import org.jbulletin.model.Post;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class JpaPostDao extends JpaDao implements PostDao {

    @Override
    public void savePost(Post post) {
	saveOrUpdate(manager, post);
    }

    @Override
    public Post getPost(int id) {
	return manager.find(Post.class, id);
    }

    @Override
    public Post mostRecentPostBySubSection(int subSectionId) {
	Query query = manager.createQuery("FROM Post as p where p.topic.subSection.id = :id ORDER BY p.posted DESC");
	query.setParameter("id", subSectionId);
	query.setMaxResults(1);
	@SuppressWarnings("unchecked")
	List<Post> list = query.getResultList();
	if(list.isEmpty()) return null;
	return list.get(0);
    }

}
