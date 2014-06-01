package com.sontype.mavenbook.weather.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Atmosphere {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer humidity;
	private Integer visibility;
	private Double pressure;
	private Integer rising;
	@OneToOne
	@JoinColumn(name="id_weather", nullable=false)
	private Weather weather;
	
	public Atmosphere() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Integer getVisibility() {
		return visibility;
	}

	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public Integer getRising() {
		return rising;
	}

	public void setRising(Integer rising) {
		this.rising = rising;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
}
