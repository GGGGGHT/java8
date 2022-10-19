package com.ggggght.learningjava8.spel;

public class PlaceOfBirth {

	private String city;
	private String country;
	
	public PlaceOfBirth(String city) {
		this.city=city;
	}
	public PlaceOfBirth(String city, String country)
	{
		this(city);
		this.country = country;
	}
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String s) {
		this.city = s;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	@Override public String toString() {
		return "PlaceOfBirth{" +
			"city='" + city + '\'' +
			", country='" + country + '\'' +
			'}';
	}
}