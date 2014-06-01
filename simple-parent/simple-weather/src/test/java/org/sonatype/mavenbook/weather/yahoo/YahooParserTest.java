package org.sonatype.mavenbook.weather.yahoo;

import java.io.InputStream;

import org.sonatype.mavenbook.weather.YahooParser;

import com.sontype.mavenbook.weather.model.Weather;

import junit.framework.TestCase;

public class YahooParserTest extends TestCase{
	
	public YahooParserTest(String name){
		super(name);
	}
	
	public void testParser() throws Exception {
		InputStream nyData = getClass().getClassLoader().getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse("10007", nyData);
		assertEquals("New York", weather.getLocation().getCity());
		assertEquals("NY", weather.getLocation().getRegion());
		assertEquals("US", weather.getLocation().getCountry());
		assertEquals(Integer.valueOf(68), weather.getCondition().getTemp());
		assertEquals("Cloudy", weather.getCondition().getText());
		assertEquals(Integer.valueOf(68), weather.getWind().getChill());
		assertEquals(Integer.valueOf(45), weather.getAtmosphere().getHumidity());
		
	}
	
	
}
