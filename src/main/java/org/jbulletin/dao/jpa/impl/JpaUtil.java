package org.jbulletin.dao.jpa.impl;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.EntityManager;

import org.apache.commons.beanutils.PropertyUtils;

public class JpaUtil {
    public static void saveOrUpdate(EntityManager manager, Object object)
    {
	try {
	    if(manager.find(object.getClass(), PropertyUtils.getSimpleProperty(object, "id")) == null)
	    {
		manager.persist(object);    
	    }
	    else
	    {
		manager.merge(object);
	    }
	} catch (IllegalAccessException e) {
	    throw new RuntimeException(e);
	} catch (InvocationTargetException e) {
	    throw new RuntimeException(e);
	} catch (NoSuchMethodException e) {
	    throw new RuntimeException(e);
	}
    }
}
