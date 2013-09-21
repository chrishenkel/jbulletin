package org.jbulletin.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.jbulletin.dao.SectionDao;
import org.jbulletin.model.Section;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateSectionDaoImpl extends HibernateDao implements SectionDao {
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int getUserCount() {
		return getFactory().getCurrentSession().createQuery("from UserDetails").list().size();
	}

	@Override
	@Transactional
	public void saveSection(Section section) {
		getFactory().getCurrentSession().save(section);
	}

	@Transactional
	public Section getSection(int id) {
		Session session = (getFactory().getCurrentSession());
		return (Section) session.get(Section.class, id);
	}

	@Override
	@Transactional
	public List<Section> getSections() {
		return getFactory().getCurrentSession().createQuery("from Section").list();
	}
}
