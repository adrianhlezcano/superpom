package com.sontype.mavenbook.multispring.persist;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sontype.mavenbook.weather.model.Location;

public class LocationDao extends HibernateDaoSupport{
	
	public LocationDao() {
		
	}
	
	public Location getByZipCode(final String zipCode){
		return (Location) getHibernateTemplate().execute(new HibernateCallback() {			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				return session.createCriteria(Location.class)
						.add(Restrictions.eq("zip", zipCode))
						.uniqueResult();
				
			}
		});
	}
	


}
