package model;


public class WorldGeneral {

	public WorldGeneral(int iD,String date,int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered,
			int totalRecovered) {
		super();
		this.date = date;
		this.newConfirmed = newConfirmed;
		this.totalConfirmed = totalConfirmed;
		this.newDeaths = newDeaths;
		this.totalDeaths = totalDeaths;
		this.newRecovered = newRecovered;
		this.totalRecovered = totalRecovered;
	}
	public WorldGeneral(String date,int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered,
			int totalRecovered) {
		super();
		this.date = date;
		this.newConfirmed = newConfirmed;
		this.totalConfirmed = totalConfirmed;
		this.newDeaths = newDeaths;
		this.totalDeaths = totalDeaths;
		this.newRecovered = newRecovered;
		this.totalRecovered = totalRecovered;
	}
	private String date;
	private int iD;
	private int newConfirmed;
	private int totalConfirmed;
	private int newDeaths;
	private int totalDeaths;
	private int newRecovered;
	private int totalRecovered;
	public int getNewConfirmed() {
		return newConfirmed;
	}
	public void setNewConfirmed(int newConfirmed) {
		this.newConfirmed = newConfirmed;
	}
	public int getTotalConfirmed() {
		return totalConfirmed;
	}
	public void setTotalConfirmed(int totalConfirmed) {
		this.totalConfirmed = totalConfirmed;
	}
	public int getNewDeaths() {
		return newDeaths;
	}
	public void setNewDeaths(int newDeaths) {
		this.newDeaths = newDeaths;
	}
	public int getTotalDeaths() {
		return totalDeaths;
	}
	public void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}
	public int getNewRecovered() {
		return newRecovered;
	}
	public void setNewRecovered(int newRecovered) {
		this.newRecovered = newRecovered;
	}
	public int getTotalRecovered() {
		return totalRecovered;
	}
	public void setTotalRecovered(int totalRecovered) {
		this.totalRecovered = totalRecovered;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
		return this.date;
	}
	public void setId(int id) {
		 this.iD = id;
	}
	public int getId() {
		return this.iD;
	}
}
