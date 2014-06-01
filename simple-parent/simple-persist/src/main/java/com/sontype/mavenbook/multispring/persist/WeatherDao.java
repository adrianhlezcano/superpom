package com.sontype.mavenbook.multispring.persist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.sontype.mavenbook.weather.model.Location;
import com.sontype.mavenbook.weather.model.Weather;

public class WeatherDao extends HibernateDaoSupport{
	
	public WeatherDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void save(Weather weather){
		getHibernateTemplate().save(weather);
	}
	
	public void load(Integer id){
		getHibernateTemplate().load(Weather.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Weather> recentForLocation(final Location location){
		return (List<Weather>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery("WEATHER.byLocation");
						query.setParameter("location", location);
						return new ArrayList<Weather>(query.list());
					}
				});
	}

}
