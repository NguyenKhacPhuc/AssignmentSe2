package model;

public class VietNamProvinces {
	
	public VietNamProvinces(String name, double confirmed, double underTreatment, double recovered, double deaths,String date) {
		super();
		this.name = name;
		this.confirmed = confirmed;
		this.underTreatment = underTreatment;
		this.recovered = recovered;
		this.deaths = deaths;
		this.date = date;
	}
	private String name;
	private double confirmed;
	private double underTreatment;
	private double recovered;
	private double deaths;
	private String date;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(double confirmed) {
		this.confirmed = confirmed;
	}
	public double getUnderTreatment() {
		return underTreatment;
	}
	public void setUnderTreatment(double underTreatment) {
		this.underTreatment = underTreatment;
	}
	public double getRecovered() {
		return recovered;
	}
	public void setRecovered(double recovered) {
		this.recovered = recovered;
	}
	public double getDeaths() {
		return deaths;
	}
	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
