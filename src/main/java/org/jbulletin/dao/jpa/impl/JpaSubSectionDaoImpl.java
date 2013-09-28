package org.jbulletin.dao.jpa.impl;

import java.util.List;

import javax.persistence.Query;

import org.jbulletin.dao.SubSectionDao;
import org.jbulletin.model.Post;
import org.jbulletin.model.SubSection;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
public class JpaSubSectionDaoImpl extends JpaDao implements
	SubSectionDao {

    @Override
    @Transactional
    public SubSection getSubSection(int id) {
	return manager.find(SubSection.class, id);
    }

    @Override
    @Transactional
    public int topicsPerSubSection(int subSectionId) {
	Query query = manager.createQuery("select count(topic) from Topic topic where topic.subSection.id = :id");
	query .setParameter("id", subSectionId);
	return ((Long) query.getSingleResult()).intValue();
    }
    
    @Override
    @Transactional
    public int repliesPerSubSection(int subSectionId) {
	Query query = manager
		.createQuery("select count(post) from Post post where post.topic.subSection.id = :id");
	query.setParameter("id", subSectionId);
	return ((Long) query.getSingleResult()).intValue();
    }    

    @Override
    @Transactional
    public void saveSection(SubSection subSection) {
	manager.persist(subSection);
    }

    @Override
    @Transactional
    public Post mostRecentPost(SubSection subSection) {	
	Query query = manager.createQuery("FROM Post as p where p.topic.subSection.id= :id ORDER BY p.posted DESC");
	query.setParameter("id", subSection.getId());
	query.setFirstResult(0);
	query.setMaxResults(1);
	@SuppressWarnings("unchecked")
	List<Post> list = query.getResultList();
	if(list.isEmpty()) return null;
	return list.get(0);
    }

}
