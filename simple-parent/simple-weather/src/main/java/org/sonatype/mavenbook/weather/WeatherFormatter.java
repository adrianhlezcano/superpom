package org.sonatype.mavenbook.weather;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.sontype.mavenbook.weather.model.Location;
import com.sontype.mavenbook.weather.model.Weather;

public class WeatherFormatter{
	private static Logger log = Logger.getLogger(WeatherFormatter.class);
	
	public String format(Weather weather) throws Exception{
		log.info("Formatting Weather Data");
		Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("output.vm"));
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("weather", weather);
		StringWriter sw = new StringWriter();
		Velocity.evaluate(velocityContext, sw, "", reader);
		return sw.toString();
	}
	
	public String format(List<Weather> weathers, Location location) throws Exception{
		log.info("Formatting Weather Data");
		Reader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("history.vm"));
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("location", location);
		velocityContext.put("weathers", weathers);
		StringWriter sw = new StringWriter();
		Velocity.evaluate(velocityContext, sw, "", reader);
		return sw.toString();
	}
}