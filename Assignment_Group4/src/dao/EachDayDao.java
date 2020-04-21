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
import model.EachDay;

public class EachDayDao {
	
	public ArrayList<EachDay> getAllDays() throws SQLException {
		Connection conn = DbConnect.getConnection();
		ArrayList<EachDay> e = new ArrayList<EachDay>();
		String getAll = "SELECT* from eachday";
		PreparedStatement preparedStatement = conn.prepareStatement(getAll);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			String date = rs.getString("date");
			double cases = rs.getDouble("cases");
			double recovered = rs.getDouble("recovered");
			double deaths = rs.getDouble("deaths");
			e.add(new EachDay(date,cases,recovered,deaths));
			
		}
		return e;
	}

	public void updateASpecificDay(EachDay day) throws SQLException {
		Connection conn = DbConnect.getConnection();
//	
		String updateDay = "UPDATE eachday SET "
				+ "date = ?"
				+ ", cases = ?"
				+ ", recovered=?"
				+ ", deaths = ?"
				+" Where date=?";
		PreparedStatement preparedStatement = conn.prepareStatement(updateDay);
		preparedStatement.setString(1, day.getDate());
		preparedStatement.setDouble(2, day.getCases());
		preparedStatement.setDouble(3, day.getRecovered());
		preparedStatement.setDouble(4, day.getDeaths());
		preparedStatement.setString(5, day.getDate());
		preparedStatement.execute();
	}
	public void insertAday(EachDay day) throws SQLException {
		Connection conn = DbConnect.getConnection();
		String insertAllCountryName = "INSERT INTO eachday "
				+ "(date,cases, recovered, deaths)"+" VALUES (?,?,?,?);";
		String country = day.getDate();
		double cases = day.getCases();
		double recovered = day.getRecovered();
		double deaths = day.getDeaths();
		PreparedStatement preparedStatement = conn.prepareStatement(insertAllCountryName);
		preparedStatement.setString(1, country);
		preparedStatement.setDouble(2, cases);
		preparedStatement.setDouble(3, recovered);
		preparedStatement.setDouble(4, deaths);
		preparedStatement.execute();
	}
	public void updateDataInEachDayTable() throws IOException, SQLException {
		Connection conn = DbConnect.getConnection();
		String updateVnDaysData = "UPDATE eachday SET "
				+"cases=?"
				+",recovered=?"
				+",date=?"
				+",deaths=? "
				+"Where ID=?; ";
		JSONArray vnDaysData = DbConnect.getUpdateDays();
		for(int i = 0;i<vnDaysData.length();i++) {
			int count = 0;
			count = i+1;
			JSONObject day = vnDaysData.getJSONObject(i);
			System.out.println(day.toString());
			double cases = day.getDouble("Confirmed");
			double recovered = day.getDouble("Recovered");
			String date = day.getString("Date");
			double deaths = day.getDouble("Deaths");
			
			PreparedStatement preparedStatement = conn.prepareStatement(updateVnDaysData);
			preparedStatement.setDouble(1, cases);
			preparedStatement.setDouble(2, recovered);
			preparedStatement.setString(3, date);
			preparedStatement.setDouble(4, deaths);
			preparedStatement.setInt(5, count);
			preparedStatement.execute();
	}
		
}

}