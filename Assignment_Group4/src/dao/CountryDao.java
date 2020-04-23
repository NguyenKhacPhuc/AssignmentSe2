package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import dbconnect.DbConnect;
import model.Country;

public class CountryDao {
	private Connection conn;
	public CountryDao() throws IOException, SQLException {
		conn = DbConnect.getConnection();
	}
	public void updateAllCountry() throws IOException, SQLException {
		
		JSONArray worldStatistic = DbConnect.getAllCountriesStatic();
//		String insertAllCountryName = "INSERT INTO country "
//				+ "(country,newConfirmed, totalConfirmed, newDeaths,totalDeaths,newRecovered"
//				+ ",totalRecovered,date,countryCode)"+" VALUES (?,?,?,?,?,?,?,?,?);";
		String updateAllCountry = "UPDATE country SET "
				+ "country = ?"
				+ ", newConfirmed = ?"
				+ ", totalConfirmed=?"
				+ ", newDeaths = ?"
				+ ", totalDeaths = ?"
				+ ", newRecovered = ?"
				+ ", totalRecovered = ?"
				+ ",date = ? "
				+",countryCode=? "
				+ "WHERE ID = ?;";
		for(int i = 0; i<worldStatistic.length();i++) {
			JSONObject o = worldStatistic.getJSONObject(i);
			System.out.println(o.toString());
			String country = o.getString("Country");
			double newConfirmed = o.getDouble("NewConfirmed");
			double totalConfirmed = o.getDouble("TotalConfirmed");
			double newDeaths = o.getDouble("NewDeaths");
			double totalDeaths = o.getDouble("TotalDeaths");
			double newRecovered = o.getDouble("NewRecovered");
			double totalRecovered = o.getDouble("TotalRecovered");
			String date = o.getString("Date");
			String countryCode = o.getString("CountryCode");
			PreparedStatement preparedStatement = conn.prepareStatement(updateAllCountry);
			preparedStatement.setString(1, country);
			preparedStatement.setDouble(2, newConfirmed);
			preparedStatement.setDouble(3, totalConfirmed);
			preparedStatement.setDouble(4, newDeaths);
			preparedStatement.setDouble(5, totalDeaths);
			preparedStatement.setDouble(6, newRecovered);
			preparedStatement.setDouble(7,totalRecovered);
			preparedStatement.setString(8, date);
			preparedStatement.setString(9, countryCode);
			preparedStatement.setInt(10, i++);
			preparedStatement.executeUpdate();
		}
	}
	public ArrayList<Country> selectAllCountry() throws SQLException{
		ArrayList<Country> countryLst = new ArrayList<Country>();
		String selectAllCountry= "SELECT * FROM country";
		PreparedStatement preparedStatement = conn.prepareStatement(selectAllCountry);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			String name = rs.getString("country");
			double newConfirmed = rs.getDouble("newConfirmed");
			double totalConfirmed = rs.getDouble("totalConfirmed");
			double newDeaths = rs.getDouble("newDeaths");
			double totalDeaths = rs.getDouble("totalDeaths");
			double newRecovered = rs.getDouble("newRecovered");
			double totalRecoverd = rs.getDouble("totalRecovered");
			String date = rs.getString("date");
			String countryCode = rs.getString("countryCode");
			countryLst.add(new Country(name, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecoverd, date,countryCode));
		}
		return countryLst;
	}
	
	public Country selectVietNamCurrent() throws SQLException {
		Country vn = null;
		String selectVietnam= "SELECT * FROM country where countryCode = 'VN' ";
		PreparedStatement preparedStatement = conn.prepareStatement(selectVietnam);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
		String name = rs.getString("country");
		double newConfirmed = rs.getDouble("newConfirmed");
		double totalConfirmed = rs.getDouble("totalConfirmed");
		double newDeaths = rs.getDouble("newDeaths");
		double totalDeaths = rs.getDouble("totalDeaths");
		double newRecovered = rs.getDouble("newRecovered");
		double totalRecoverd = rs.getDouble("totalRecovered");
		String date = rs.getString("date");
		String countryCode = rs.getString("countryCode");
		vn = new Country(name, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecoverd, date,countryCode);
		}
		return vn;
	}
	public Country selectCustomCountry(String cD) throws SQLException {
		Country vn = null;
		String selectVietnam= "SELECT * FROM country where countryCode = " + "\"" + cD + "\"";
		PreparedStatement preparedStatement = conn.prepareStatement(selectVietnam);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
		String name = rs.getString("country");
		double newConfirmed = rs.getDouble("newConfirmed");
		double totalConfirmed = rs.getDouble("totalConfirmed");
		double newDeaths = rs.getDouble("newDeaths");
		double totalDeaths = rs.getDouble("totalDeaths");
		double newRecovered = rs.getDouble("newRecovered");
		double totalRecoverd = rs.getDouble("totalRecovered");
		String date = rs.getString("date");
		String countryCode = rs.getString("countryCode");
		vn = new Country(name, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecoverd, date,countryCode);
		}
		return vn;
	}
	public void createCountry(Country c) throws SQLException {
		Connection conn = DbConnect.getConnection();
		String insert = "INSERT INTO country "
				+ "(country,newConfirmed, totalConfirmed, newDeaths,totalDeaths,newRecovered"
				+ ",totalRecovered,date,countryCode)"+" VALUES (?,?,?,?,?,?,?,?,?);";
		PreparedStatement preparedStatement = conn.prepareStatement(insert);
		preparedStatement.setString(1,c.getCountry());
		preparedStatement.setDouble(2,c.getNewConfirmed());
		preparedStatement.setDouble(3,c.getTotalConfirmed());
		preparedStatement.setDouble(4, c.getNewDeaths());
		preparedStatement.setDouble(5, c.getTotalDeaths());
		preparedStatement.setDouble(6, c.getNewRecovered());
		preparedStatement.setDouble(7, c.getTotalRecovered());
		preparedStatement.setString(8, c.getDate());
		preparedStatement.setString(9, c.getCountryCode());
		preparedStatement.execute();
	}
	
	public void deleteCountry(String cD) throws SQLException {
		 String deletion = "DELETE FROM country WHERE countryCode=" +  "\"" + cD + "\"";
		 PreparedStatement preparedStatement = conn.prepareStatement(deletion);
		preparedStatement.executeUpdate();
	}
	public void updateSpecificCountry(Country c) throws SQLException {
		String updateCountry = "UPDATE country SET "
				+ "country = ?"
				+ ", newConfirmed = ?"
				+ ", totalConfirmed=?"
				+ ", newDeaths = ?"
				+ ", totalDeaths = ?"
				+ ", newRecovered = ?"
				+ ", totalRecovered = ?"
				+ ",date = ? "
				+",countryCode=? "
				+ "WHERE countryCode = ?;";
		PreparedStatement preparedStatement = conn.prepareStatement(updateCountry);
		preparedStatement.setString(1, c.getCountry());
		preparedStatement.setDouble(2, c.getNewConfirmed());
		preparedStatement.setDouble(3, c.getTotalConfirmed());
		preparedStatement.setDouble(4, c.getNewDeaths());
		preparedStatement.setDouble(5, c.getTotalDeaths());
		preparedStatement.setDouble(6, c.getNewRecovered());
		preparedStatement.setDouble(7,c.getTotalRecovered());
		preparedStatement.setString(8, c.getDate());
		preparedStatement.setString(9, c.getCountryCode());
		preparedStatement.setString(10, c.getCountryCode());
		preparedStatement.execute();
	}
	public boolean checkExist(String b) throws SQLException {
		Connection conn = DbConnect.getConnection();
		boolean check = false;
		ArrayList<String> allNameOfCountries = new ArrayList<>();
		String select = "select * from country";
		PreparedStatement preparedStatement = conn.prepareStatement(select);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			String countryCode = rs.getString("countryCode");
			allNameOfCountries.add(countryCode);
		}
		for(String s: allNameOfCountries) {
			if(s.equals(b)) {
				check= true;
				break;
			}
		}
		return check;
	}
	// only use when country in the table is zero
	public void insertAllCountry() throws IOException, SQLException {
		
		JSONArray worldStatistic = DbConnect.getAllCountriesStatic();
		String insertAllCountryName = "INSERT INTO country "
				+ "(country,newConfirmed, totalConfirmed, newDeaths,totalDeaths,newRecovered"
				+ ",totalRecovered,date,countryCode)"+" VALUES (?,?,?,?,?,?,?,?,?);";
		
		for(int i = 0; i<worldStatistic.length();i++) {
			JSONObject o = worldStatistic.getJSONObject(i);
			String country = o.getString("Country");
			double newConfirmed = o.getDouble("NewConfirmed");
			double totalConfirmed = o.getDouble("TotalConfirmed");
			double newDeaths = o.getDouble("NewDeaths");
			double totalDeaths = o.getDouble("TotalDeaths");
			double newRecovered = o.getDouble("NewRecovered");
			double totalRecoverd = o.getDouble("TotalRecovered");
			String date = o.getString("Date");
			String countryCode = o.getString("CountryCode");
			PreparedStatement preparedStatement = conn.prepareStatement(insertAllCountryName);
			preparedStatement.setString(1, country);
			preparedStatement.setDouble(2, newConfirmed);
			preparedStatement.setDouble(3, totalConfirmed);
			preparedStatement.setDouble(4, newDeaths);
			preparedStatement.setDouble(5, totalDeaths);
			preparedStatement.setDouble(6, newRecovered);
			preparedStatement.setDouble(7,totalRecoverd);
			preparedStatement.setString(8, date);
			preparedStatement.setString(9, countryCode);
			preparedStatement.execute();
	}
}
	public void insertACountry(Country c) throws SQLException {
		
		String insertAllCountryName = "INSERT INTO country "
				+ "(country,newConfirmed, totalConfirmed, newDeaths,totalDeaths,newRecovered"
				+ ",totalRecovered,date,countryCode)"+" VALUES (?,?,?,?,?,?,?,?,?);";
		String country = c.getCountry();
		double newConfirmed = c.getNewConfirmed();
		double totalConfirmed = c.getTotalConfirmed();
		double newDeaths = c.getNewDeaths();
		double totalDeaths = c.getTotalDeaths();
		double newRecovered = c.getNewRecovered();
		double totalRecoverd = c.getTotalRecovered();
		String date = c.getDate();
		String countryCode = c.getCountryCode();
		PreparedStatement preparedStatement = conn.prepareStatement(insertAllCountryName);
		preparedStatement.setString(1, country);
		preparedStatement.setDouble(2, newConfirmed);
		preparedStatement.setDouble(3, totalConfirmed);
		preparedStatement.setDouble(4, newDeaths);
		preparedStatement.setDouble(5, totalDeaths);
		preparedStatement.setDouble(6, newRecovered);
		preparedStatement.setDouble(7,totalRecoverd);
		preparedStatement.setString(8, date);
		preparedStatement.setString(9, countryCode);
		preparedStatement.execute();
	}
}