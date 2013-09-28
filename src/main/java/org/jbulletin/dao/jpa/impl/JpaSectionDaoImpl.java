package org.jbulletin.dao.jpa.impl;

import java.util.List;

import org.jbulletin.dao.SectionDao;
import org.jbulletin.model.Section;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Repository
public class JpaSectionDaoImpl extends JpaDao implements SectionDao {

    @Transactional(propagation = Propagation.REQUIRED)
    public int getUserCount() {
	return manager.createQuery("from UserDetails").getResultList().size();
    }

    @Override
    @Transactional
    public void saveSection(Section section) {
	manager.persist(section);
    }

    @Transactional
    public Section getSection(int id) {
	return (Section) manager.find(Section.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public List<Section> getSections() {
	return manager.createQuery("from Section")
		.getResultList();
    }
}
