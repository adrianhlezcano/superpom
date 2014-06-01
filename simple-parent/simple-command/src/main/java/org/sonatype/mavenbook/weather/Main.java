package org.sonatype.mavenbook.weather;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sontype.mavenbook.multispring.persist.LocationDao;
import com.sontype.mavenbook.multispring.persist.WeatherDao;
import com.sontype.mavenbook.weather.model.Location;
import com.sontype.mavenbook.weather.model.Weather;

/**
 * Hello world!
 *
 */
public class Main 
{
	private WeatherService weatherService;
	private WeatherDao weatherDao;
	private LocationDao locationDao;
	
    public static void main( String[] args ) throws Exception
    {
    	// Configure Log4j
        PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
        
        // Read the zip code fomr the command-line (if none supplied, use 60202)
        String zipCode = "60202";
        
        if (args != null && args.length > 0){
        	zipCode = args[0];
        }
        
        // Read the Operation from the Command-line (if none supplied use weather)
        String operation = "weather";
        if (args != null && args.length > 1){
        	operation = args[1];
        }
        
        // Start the program
        Main main = new Main(zipCode);
        
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
        		"classpath:applicationContext-persist.xml",
        		"classpath:applicationContext-weather.xml"
        });
        main.setLocationDao((LocationDao) context.getBean("locationDao"));
        main.setWeatherDao((WeatherDao) context.getBean("weatherDao"));
        main.setWeatherService((WeatherService) context.getBean("weatherService"));
        
        if ("weather".equalsIgnoreCase(operation)){
        	main.getWeather();
        } else if ("history".equalsIgnoreCase("history")){
        	main.getHistory();
        }
        
    }
    
    private String zipCode;
    public Main() {
	}
    public Main(String zipCode){
    	this.zipCode = zipCode;
    }
    public void getWeather() throws Exception{
    	Weather weather = weatherService.retrieveForecast(getZipCode());
    	weatherDao.save(weather);
    	System.out.println(new WeatherFormatter().format(weather));
    }
    public void getHistory() throws Exception{
    	Location location = locationDao.getByZipCode(getZipCode());
    	List<Weather> weathers = Collections.emptyList();
    	if (location != null){
    		weathers = weatherDao.recentForLocation(location);
    	}
    	System.out.println(new WeatherFormatter().format(weathers, location));    	
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
	public LocationDao getLocationDao() {
		return locationDao;
	}
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    
    
}
