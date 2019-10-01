package com.example.demo.dto;

public class RestaurantDto {
	private String name;
	private Long contact;
	private double rating;
	private String resId;
	private String location;
	private String co_ordinates;
	

	public RestaurantDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RestaurantDto(String name, Long contact, double rating, String resId, String location, String co_ordinates) {
		super();
		this.name = name;
		this.contact = contact;
		this.rating = rating;
		this.resId = resId;
		this.location = location;
		this.co_ordinates = co_ordinates;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Long getContact() {
		return contact;
	}


	public void setContact(Long contact) {
		this.contact = contact;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getResId() {
		return resId;
	}


	public void setResId(String resId) {
		this.resId = resId;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCo_ordinates() {
		return co_ordinates;
	}


	public void setCo_ordinates(String co_ordinates) {
		this.co_ordinates = co_ordinates;
	}

}
