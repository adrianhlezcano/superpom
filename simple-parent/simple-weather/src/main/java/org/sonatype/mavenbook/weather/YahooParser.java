package org.sonatype.mavenbook.weather;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sontype.mavenbook.weather.model.Atmosphere;
import com.sontype.mavenbook.weather.model.Condition;
import com.sontype.mavenbook.weather.model.Location;
import com.sontype.mavenbook.weather.model.Weather;
import com.sontype.mavenbook.weather.model.Wind;


public class YahooParser{
	private static Logger log = Logger.getLogger(YahooParser.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm a z");
	
	public Weather parse(String zip, InputStream data) throws Exception{
		SAXReader xmlReader = createXMLReader();
		Document document = xmlReader.read(data);
		Weather weather = new Weather();		
		log.info("Parsing XML Response");
		
//		<lastBuildDate>Wed, 21 May 2014 8:49 pm EDT</lastBuildDate>
		Node node = document.selectSingleNode("//rss/channel/lastBuildDate");
		weather.setDate(parseDate(node.getText()));
		
//		 <yweather:location city="New York" region="NY" country="US" />
		node = document.selectSingleNode("//rss/channel/yweather:location");
		Location location = new Location();
		location.setCity(node.valueOf("@city"));
		location.setRegion(node.valueOf("@region"));
		location.setCountry(node.valueOf("@country"));
		location.setZip(zip);
		weather.setLocation(location);
		
//		<yweather:condition text="Cloudy" code="26" temp="68" date="Wed, 21 May 2014 8:49 pm EDT" />
		node = document.selectSingleNode("//rss/channel/item/yweather:condition");
		Condition condition = new Condition();		
		condition.setCode(parseInteger(node.valueOf("@code")));
		condition.setDate(parseDate(node.valueOf("@date")));
		condition.setText(node.valueOf("@text"));
	    condition.setTemp(parseInteger(node.valueOf("@temp")));
	    weather.setCondition(condition);
	    
//	    <yweather:wind chill="68" direction="0" speed="0" />
	    node = document.selectSingleNode("//rss/channel/yweather:wind");
	    Wind wind = new Wind();
	    wind.setChill(parseInteger(node.valueOf("@chill")));
	    wind.setDirection(parseInteger(node.valueOf("@direction")));
	    wind.setSpeed(parseInteger(node.valueOf("@speed")));
	    weather.setWind(wind);
	    
//	    <yweather:atmosphere humidity="45" visibility="10" pressure="29.93" rising="2" />
	    node = document.selectSingleNode("//rss/channel/yweather:atmosphere");
	    Atmosphere atmosphere = new Atmosphere();
	    atmosphere.setHumidity(parseInteger(node.valueOf("@humidity")));
	    atmosphere.setPressure(parseDouble(node.valueOf("@pressure")));
	    atmosphere.setVisibility(parseInteger(node.valueOf("@visibility")));
	    atmosphere.setRising(parseInteger(node.valueOf("@rising")));
	    weather.setAtmosphere(atmosphere);
	    
		log.info("Weather Parsed Successfully");		
		return weather;
	}
	
	private SAXReader createXMLReader(){
		Map<String, String> uris = new HashMap<String, String>();
		uris.put("yweather", "http://xml.weather.yahoo.com/ns/rss/1.0");
		DocumentFactory df = DocumentFactory.getInstance();
		df.setXPathNamespaceURIs(uris);
		
		SAXReader reader = new SAXReader();
		reader.setDocumentFactory(df);
		return reader;
		
	}
	
	private Integer parseInteger(String value){
		Integer result = null;
		try {
			result = Integer.parseInt(value);
		} catch (Exception e) {			
		}
		return result;
	}
	
	private Double parseDouble(String value){
		Double result = null;
		try {
			result = Double.parseDouble(value);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	private Date parseDate(String value){
		try {
			return sdf.parse(value);
		} catch(Exception e){
			return null;
		}		
	}
	
}