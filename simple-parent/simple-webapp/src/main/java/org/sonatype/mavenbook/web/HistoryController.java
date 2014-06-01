package org.sonatype.mavenbook.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import com.sontype.mavenbook.multispring.persist.LocationDao;
import com.sontype.mavenbook.multispring.persist.WeatherDao;
import com.sontype.mavenbook.weather.model.Location;
import com.sontype.mavenbook.weather.model.Weather;

public class HistoryController implements Controller{
	private LocationDao locationDao;
	private WeatherDao weatherDao;

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zip = request.getParameter("zip");
		Location location = locationDao.getByZipCode(zip);
		List<Weather> weatherList = Collections.emptyList();
		if (location != null){
			weatherList = weatherDao.recentForLocation(location);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("location", location);
		model.put("weathers", weatherList);
		
		return new ModelAndView("history", model);
	}

	public LocationDao getLocationDao() {
		return locationDao;
	}

	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public WeatherDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDao weatherDao) {
		this.weatherDao = weatherDao;
	}
		
}
