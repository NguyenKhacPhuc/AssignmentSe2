package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import dbconnect.DbConnect;
import model.VietNamProvinces;

public class VietNamProvinceDao {
	
	
	public void insertProvincesStatistic(){
		Connection conn = DbConnect.getConnection();
		String url = "https://ncov.moh.gov.vn/";
		ArrayList<String> nameOfProvince = new ArrayList<String>();
		ArrayList<Double> confirmedsLst = new ArrayList<Double>();
		ArrayList<Double> underTreatmentLst = new ArrayList<Double>();
		ArrayList<Double> recoveredLst = new ArrayList<Double>();
		ArrayList<Double> deathsLst = new ArrayList<Double>();
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		String insertValue = "Insert into provinces (name,confirmed,undertreatment,recovered,deaths,date) values (?,?,?,?,?,?);";
		try {
			
			 Document connect =  SSLHelper.getConnection(url).userAgent("HTTPS").get();
			 Element table1 = connect.select("table.table-striped.table-covid19").get(0);
			 for(int i = 0; i<table1.select("td:nth-of-type(1)").size();i++) {
				 nameOfProvince.add(table1.select("td:nth-of-type(1)").get(i).text());
				 confirmedsLst.add(Double.parseDouble(table1.select("td:nth-of-type(2)").get(i).text()));
				 underTreatmentLst.add(Double.parseDouble(table1.select("td:nth-of-type(3)").get(i).text()));
				 recoveredLst.add(Double.parseDouble(table1.select("td:nth-of-type(4)").get(i).text()));
				 deathsLst.add(Double.parseDouble(table1.select("td:nth-of-type(5)").get(i).text()));
			 }
			 for(int i = 0; i<table1.select("td:nth-of-type(1)").size();i++) {
				 PreparedStatement preparedStatement = conn.prepareStatement(insertValue);
				 preparedStatement.setString(1, nameOfProvince.get(i));
				 preparedStatement.setDouble(2, confirmedsLst.get(i));
				 preparedStatement.setDouble(3,underTreatmentLst.get(i));
				 preparedStatement.setDouble(4,recoveredLst.get(i));
				 preparedStatement.setDouble(5, deathsLst.get(i));
				 preparedStatement.setString(6, date);
				 preparedStatement.execute();
		
			 }
			 
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
	public ArrayList<VietNamProvinces> selectAllProvinces() throws SQLException {
		Connection conn = DbConnect.getConnection();
		ArrayList<VietNamProvinces> v = new ArrayList<VietNamProvinces>();
		String getAll = "SELECT* from provinces";
		PreparedStatement preparedStatement = conn.prepareStatement(getAll);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			String name = rs.getString("name");
			double confirmed = rs.getDouble("confirmed");
			double underTreatment = rs.getDouble("undertreatment");
			double recovered = rs.getDouble("recovered");
			double deaths = rs.getDouble("deaths");
			String date = rs.getString("date");
			v.add(new VietNamProvinces(name, confirmed, underTreatment, recovered, deaths, date));
			
		}
		return v;
	}
	public void automaticUpdateProvinces() throws IOException, SQLException {
		Connection conn = DbConnect.getConnection();
		String url = "https://ncov.moh.gov.vn/";
		ArrayList<String> nameOfProvince = new ArrayList<String>();
		ArrayList<Double> confirmedsLst = new ArrayList<Double>();
		ArrayList<Double> underTreatmentLst = new ArrayList<Double>();
		ArrayList<Double> recoveredLst = new ArrayList<Double>();
		ArrayList<Double> deathsLst = new ArrayList<Double>();
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		String insertValue = "Insert into provinces (name,confirmed,undertreatment,recovered,deaths,date) values (?,?,?,?,?,?);";
		String updateValue = "Update provinces Set "
				+"confirmed=?"
				+ ",undertreatment=?"
				+ ",recovered=?"
				+ "deaths=?"
				+ ",date=,"
				+ " where name=?";
		String check = "SELECT EXISTS(SELECT * from provinces WHERE name=? ";
		
		 Document connect =  SSLHelper.getConnection(url).userAgent("HTTPS").get();
		 Element table1 = connect.select("table.table-striped.table-covid19").get(0);
		 for(int i = 0; i<table1.select("td:nth-of-type(1)").size();i++) {
			 nameOfProvince.add(table1.select("td:nth-of-type(1)").get(i).text());
			 confirmedsLst.add(Double.parseDouble(table1.select("td:nth-of-type(2)").get(i).text()));
			 underTreatmentLst.add(Double.parseDouble(table1.select("td:nth-of-type(3)").get(i).text()));
			 recoveredLst.add(Double.parseDouble(table1.select("td:nth-of-type(4)").get(i).text()));
			 deathsLst.add(Double.parseDouble(table1.select("td:nth-of-type(5)").get(i).text()));
		 }
		 for(int i = 0; i<nameOfProvince.size() ;i++) {
		 PreparedStatement preparedStatement = conn.prepareStatement(check);
		 preparedStatement.setString(1, nameOfProvince.get(i));
		 int rs = preparedStatement.executeUpdate();
		 System.out.println(rs);
		 if(rs == 1) {
			 PreparedStatement preparedStatement1 = conn.prepareStatement(updateValue);
			 preparedStatement1.setDouble(1, confirmedsLst.get(i));
			 preparedStatement1.setDouble(2, underTreatmentLst.get(i));
			 preparedStatement1.setDouble(3, recoveredLst.get(i));
			 preparedStatement1.setDouble(4, deathsLst.get(i));
			 preparedStatement1.setString(5, date);
			 preparedStatement1.setString(6, nameOfProvince.get(i));
			 preparedStatement1.execute();
		 }else if(rs == 0) {
			 PreparedStatement preparedStatement1 = conn.prepareStatement(insertValue);
			 preparedStatement1.setString(1, nameOfProvince.get(i));
			 preparedStatement1.setDouble(2, confirmedsLst.get(i));
			 preparedStatement1.setDouble(3, underTreatmentLst.get(i));
			 preparedStatement1.setDouble(4, recoveredLst.get(i));
			 preparedStatement1.setDouble(5, deathsLst.get(i));
			 preparedStatement1.setString(6, date);
			 preparedStatement1.execute();
		 }
		 
		 
	}
}
	public void insertAProvince(VietNamProvinces vnD) throws SQLException {
		//INSERT TO DATABASE
		Connection conn = DbConnect.getConnection();
		String insertValue = "Insert into provinces (name,confirmed,undertreatment,recovered,deaths,date) values (?,?,?,?,?,?);";
		PreparedStatement preparedStatement1 = conn.prepareStatement(insertValue);
		 preparedStatement1.setString(1, vnD.getName());
		 preparedStatement1.setDouble(2, vnD.getConfirmed());
		 preparedStatement1.setDouble(3, vnD.getUnderTreatment());
		 preparedStatement1.setDouble(4, vnD.getRecovered());
		 preparedStatement1.setDouble(5, vnD.getDeaths());
		 preparedStatement1.setString(6, vnD.getDate());
		 preparedStatement1.execute();
	}
	public void updateAProvince(VietNamProvinces vnD) throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		Connection conn = DbConnect.getConnection();
		String updateValue = "Update provinces Set "
				+"confirmed=?"
				+ ",undertreatment=?"
				+ ",recovered=?"
				+ "deaths=?"
				+ ",date=,"
				+ " where name=?";
		PreparedStatement preparedStatement1 = conn.prepareStatement(updateValue);
		 preparedStatement1.setDouble(1, vnD.getConfirmed());
		 preparedStatement1.setDouble(2, vnD.getUnderTreatment());
		 preparedStatement1.setDouble(3, vnD.getRecovered());
		 preparedStatement1.setDouble(4, vnD.getDeaths());
		 preparedStatement1.setString(5, date);
		 preparedStatement1.setString(6, vnD.getName());
		 preparedStatement1.execute();
	}
	public void deleteAprovince(String name) throws SQLException {
		Connection conn = DbConnect.getConnection();
		 String deletion = "DELETE FROM provinces WHERE name=" +  "\"" + name + "\"";
		 PreparedStatement preparedStatement = conn.prepareStatement(deletion);
		preparedStatement.executeUpdate();
	}

}