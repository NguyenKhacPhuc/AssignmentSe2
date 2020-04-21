package model;


public class Country {

	public Country(String country, double newConfirmed, double totalConfirmed, double newDeaths, double totalDeaths,
			double newRecovered, double totalRecovered, String date,String countryCode) {
		super();
		this.country = country;
		this.newConfirmed = newConfirmed;
		this.totalConfirmed = totalConfirmed;
		this.newDeaths = newDeaths;
		this.totalDeaths = totalDeaths;
		this.newRecovered = newRecovered;
		this.totalRecovered = totalRecovered;
		this.Date = date;
		this.countryCode = countryCode;
	}
	public Country() {
		
	}
	private String country;
	private double newConfirmed;
	private double totalConfirmed;
	private double newDeaths;
	private double totalDeaths;
	private double newRecovered;
	private double totalRecovered;
	private String  Date;
	private String countryCode;
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getNewConfirmed() {
		return newConfirmed;
	}
	public void setNewConfirmed(double newConfirmed) {
		this.newConfirmed = newConfirmed;
	}
	public double getTotalConfirmed() {
		return totalConfirmed;
	}
	public void setTotalConfirmed(double totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}
	public double getNewDeaths() {
		return newDeaths;
	}
	public void setNewDeaths(double newDeaths) {
		this.newDeaths = newDeaths;
	}
	public double getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(double totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public double getNewRecovered() {
		return newRecovered;
	}
	public void setNewRecovered(double newRecovered) {
		this.newRecovered = newRecovered;
	}
	public double getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(double totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
