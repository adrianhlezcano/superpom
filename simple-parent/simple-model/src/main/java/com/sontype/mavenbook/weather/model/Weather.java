package com.sontype.mavenbook.weather.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
	@NamedQuery(name="WEATHER.byLocation", 
				 query="from Weather w where w.location = :location")
})
public class Weather {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_location", nullable=false)
	private Location location;
	@OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
	private Condition condition;
	@OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
	private Wind wind;
	@OneToOne(mappedBy="weather", cascade=CascadeType.ALL)
	private Atmosphere atmosphere;
	private Date date;
	
	public Weather() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
		this.location.addWeather(this);
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
		this.condition.setWeather(this);
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
		this.wind.setWeather(this);
	}

	public Atmosphere getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(Atmosphere atmosphere) {
		this.atmosphere = atmosphere;
		this.atmosphere.setWeather(this);
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
}
