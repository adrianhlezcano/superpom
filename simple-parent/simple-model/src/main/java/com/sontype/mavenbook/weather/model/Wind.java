package com.sontype.mavenbook.weather.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Wind {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer chill;
	private Integer direction;
	private Integer speed;
	@OneToOne
	@JoinColumn(name="id_weather", nullable=false)
	private Weather weather;
	
	public Wind() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getChill() {
		return chill;
	}

	public void setChill(Integer chill) {
		this.chill = chill;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
