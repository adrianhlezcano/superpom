package com.sontype.mavenbook.weather.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Condition {	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String text;
	private Integer code;
	private Integer temp;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@OneToOne
	@JoinColumn(name="id_weather", nullable=false)
	private Weather weather;
	
	
	public Condition() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getTemp() {
		return temp;
	}

	public void setTemp(Integer temp) {
		this.temp = temp;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
		
	public Weather getWeather() {
		return weather;
	}
	
	public void setWeather(Weather weather) {
		this.weather = weather;
	}
	
}
