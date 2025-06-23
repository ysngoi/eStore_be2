package com.example.demo.model;

public class WeatherBean {
	
	private Long weatherId;  
	private String key;
	private String value;
	private String jasonResult;
	private String description;
	private String createdDate;
	
	public WeatherBean() {
	}
	
	public Long getWeatherId() {
		return weatherId;
	}
	
	public void setWeatherId(Long weatherId) {
		this.weatherId = weatherId;
	}
	
	public String getKey() {
		return key;
	}
	
	public void setkey(String key) {
		this.key = key;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getJasonresult() {
		return jasonResult;
	}
	
	public void setJasonresult(String jasonresult) {
		this.jasonResult = jasonresult;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
}
