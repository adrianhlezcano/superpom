package org.sonatype.mavenbook.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.mavenbook.weather.WeatherService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.sontype.mavenbook.multispring.persist.WeatherDao;
import com.sontype.mavenbook.weather.model.Weather;

public class WeatherController implements Controller{
	private WeatherService weatherService;
	private WeatherDao weatherDao;
	
	public WeatherController() {
		// TODO Auto-generated constructor stub
	}
		
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String zip = request.getParameter("zip");
		Weather weather = weatherService.retrieveForecast(zip);
		weatherDao.save(weather);
		return new ModelAndView("weather", "weather", weather);
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public WeatherDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDao weatherDao) {
		this.weatherDao = weatherDao;
	}
	
}
