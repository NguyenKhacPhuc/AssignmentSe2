package dbconnect;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.SQLException;


import org.json.JSONArray;
import org.json.JSONObject;

public class DbConnect {
	private static String DB_URL = "jdbc:mysql://localhost:3306/coronavirus?useSSL=false";
    private static String USER_NAME = "root";
    private static String PASSWORD = "phuc1213";
    private static String WORLD="https://api.covid19api.com/summary";
    private static String COUNTRIES = "https://api.covid19api.com/summary";
    private static String days = "https://api.covid19api.com/dayone/country/vietnam";
    
	public static Connection getConnection()  {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	public static  JSONObject retrieveWorldStatistic() throws IOException, SQLException {
		URL url = new URL(WORLD);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		if (connection.getResponseCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : "
		            + connection.getResponseCode());
		}
		InputStream in = new BufferedInputStream(
			    (connection.getInputStream()));
		
		String output = convertToString(in);
		JSONObject all = new JSONObject(output);
		return all;
		
	}
	private static String convertToString(InputStream in) {
		// TODO Auto-generated method stub
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	      StringBuilder sb = new StringBuilder();

	      String line;
	      try {
	         while ((line = reader.readLine()) != null) {
	            sb.append(line).append('\n');
	         }
	      } catch (IOException e) {
	         e.printStackTrace();
	      } finally {
	         try {
	        	 in.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	        
	      return sb.toString();
	   }
	public static JSONArray getAllCountriesStatic() throws IOException  {
		URL url = new URL(COUNTRIES);
		
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		if (connection.getResponseCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : "
		            + connection.getResponseCode());
		}
		InputStream in = new BufferedInputStream(
			    (connection.getInputStream()));
		
		String output = convertToString(in);
		JSONObject total = new JSONObject(output);
		JSONArray worldStatistic = total.getJSONArray("Countries");
		return worldStatistic;
	}
	public static JSONArray getUpdateDays() throws IOException {
		URL url = new URL(days);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", "application/json");
		if (connection.getResponseCode() != 200) {
		    throw new RuntimeException("Failed : HTTP error code : "
		            + connection.getResponseCode());
		}
		InputStream in = new BufferedInputStream(
			    (connection.getInputStream()));
		String output = convertToString(in);
		JSONArray vnDaysStatistic = new JSONArray(output);
		return vnDaysStatistic;
	}

}

