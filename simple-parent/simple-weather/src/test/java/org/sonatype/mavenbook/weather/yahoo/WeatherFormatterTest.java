package org.sonatype.mavenbook.weather.yahoo;

import java.io.InputStream;

import org.sonatype.mavenbook.weather.WeatherFormatter;
import org.sonatype.mavenbook.weather.YahooParser;
import org.apache.commons.io.IOUtils;

import com.sontype.mavenbook.weather.model.Weather;

import junit.framework.TestCase;

public class WeatherFormatterTest extends TestCase {
	
	public WeatherFormatterTest(String name){
		super(name);
	}
	
	public void testFormat() throws Exception{
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse("10007", nyData);
		String formattedResult = new WeatherFormatter().format(weather);
		InputStream expected = getClass().getClassLoader().getResourceAsStream("format-expected.dat");
		assertEquals( IOUtils.toString(expected), formattedResult.trim());
		
	}

}
