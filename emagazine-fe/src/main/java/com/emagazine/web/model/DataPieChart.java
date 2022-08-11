package com.emagazine.web.model;

public class DataPieChart {
	private String name;
	private Integer y;
	
	
	
	
	public DataPieChart(String name, Integer y) {
		super();
		this.name = name;
		this.y = y;
	}
	public DataPieChart() {
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	
	
}
