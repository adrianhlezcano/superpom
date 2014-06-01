package org.sonatype.mavenbook.weather;

import java.io.InputStream;

import com.sontype.mavenbook.weather.model.Weather;

public class WeatherService {
	private YahooParser yahooParser;
	private YahooRetriever yahooRetriever;
	
	public WeatherService() {
	}

	public Weather retrieveForecast(String zip) throws Exception {
		// Retrieve Data
		InputStream data = yahooRetriever.retrieve(zip);

		// Parse Data
		Weather weather = yahooParser.parse(zip, data);

		return weather;
		// Format data
		// return new WeatherFormatter().format(weather);
	}

	public YahooParser getYahooParser() {
		return yahooParser;
	}

	public void setYahooParser(YahooParser yahooParser) {
		this.yahooParser = yahooParser;
	}

	public YahooRetriever getYahooRetriever() {
		return yahooRetriever;
	}

	public void setYahooRetriever(YahooRetriever yahooRetriever) {
		this.yahooRetriever = yahooRetriever;
	}
			
}
