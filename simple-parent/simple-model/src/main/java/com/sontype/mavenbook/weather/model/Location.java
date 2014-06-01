package com.sontype.mavenbook.weather.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;

@Entity
public class Location {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Index(name="location_zip_index") @Column(length=6, unique=true)
	private String zip;
	@Column(length=50)
	private String city;
	@Column(length=50)
	private String region;
	@Column(length=10)
	private String country;
	@OneToMany(mappedBy="location", targetEntity=Weather.class)
	private List<Weather> weathers;
	
	
	public Location() {
		// TODO Auto-generated constructor stub
	}
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Weather> getWeathers() {
		return weathers;
	}
	
	public void setWeathers(List<Weather> weathers) {
		this.weathers = weathers;
	}
	
	public void addWeather(Weather weather){
		if (this.weathers == null){
			this.weathers = new ArrayList<Weather>();
		}
		this.weathers.add(weather);
	}
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}
