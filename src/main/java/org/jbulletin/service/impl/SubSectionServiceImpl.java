package org.jbulletin.service.impl;

import java.util.List;

import org.jbulletin.dao.SubSectionDao;
import org.jbulletin.model.SubSection;
import org.jbulletin.service.SubSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubSectionServiceImpl implements SubSectionService {
	
	@Autowired
	private SubSectionDao subSectionDao;
	
	public SubSectionDao getSubSectionDao() {
		return subSectionDao;
	}

	public void setSubSectionDao(SubSectionDao subSectionDao) {
		this.subSectionDao = subSectionDao;
	}

	@Override
	public SubSection get(int id) {
		return subSectionDao.getSubSection(id);
	}

	@Override
	public void save(SubSection subSection) {
		subSectionDao.saveSection(subSection);
	}

	@Override
	public List<SubSection> findBySectionId(int sectionId) {
	    if(true) throw new RuntimeException("Method must be implemented");
		// TODO Auto-generated method stub
		return null;
	}

}
