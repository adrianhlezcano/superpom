package org.sonatype.mavenbook.weather;

import java.io.InputStream;

import org.apache.log4j.PropertyConfigurator;

import com.sontype.mavenbook.weather.model.Weather;

public class Main{
	public static void main(String[] arg) throws Exception{
		PropertyConfigurator.configure(Main.class.getClassLoader().getResource("log4j.properties"));
		String zipcode = "75001";
		if (arg != null && arg.length == 1){
			zipcode = arg[0];
		}		
		new Main(zipcode).start();
	}
	
	private String zipcode;
	
	public Main(String zipcode) {
		this.zipcode = zipcode;
	}
	
	public void start() throws Exception{
		// Retrieve Data 
		InputStream dataIn = new YahooRetriever().retrieve(zipcode);
		
		// Parse data
		Weather weather = new YahooParser().parse(zipcode, dataIn);
		
		// Format print data
		System.out.println(new WeatherFormatter().format(weather));
	}
	
}