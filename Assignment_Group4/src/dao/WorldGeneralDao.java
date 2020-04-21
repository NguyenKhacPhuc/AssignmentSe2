package dao;
import model.WorldGeneral;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.json.JSONObject;

import dbconnect.DbConnect;
public class WorldGeneralDao {
	private Connection conn;
	
	public WorldGeneralDao()  {
		conn = DbConnect.getConnection();
	}
	
	public void updateCurrentWorldSitutation() throws IOException, SQLException {
		
		JSONObject all = DbConnect.retrieveWorldStatistic();
		JSONObject object = all.getJSONObject("Global");
		int newConfirmed = object.getInt("NewConfirmed");
		int totalConfirmed = object.getInt("TotalConfirmed");
		int newDeaths = object.getInt("NewDeaths");
		int totalDeaths = object.getInt("TotalDeaths");
		int newRecovered = object.getInt("NewRecovered");
		int totalRecovered = object.getInt("TotalRecovered");
		
		LocalDateTime now = LocalDateTime.now();
		String date = now.toString();
		String updateCurrentWorldSituation = "INSERT INTO worldgeneral "
			+ "(date,newConfirmed, totalConfirmed, newDeaths,totalDeaths,newRecovered"
			+ ",totalRecovered)"+" VALUES (?,?,?,?,?,?,?);";
		PreparedStatement preparedStatement = conn.prepareStatement(updateCurrentWorldSituation);
		preparedStatement.setString(1, date);
		preparedStatement.setInt(2, newConfirmed);
		preparedStatement.setInt(3, totalConfirmed);
		preparedStatement.setInt(4, newDeaths);
		preparedStatement.setInt(5, totalDeaths);
		preparedStatement.setInt(6, newRecovered);
		preparedStatement.setInt(7, totalRecovered);
		preparedStatement.execute();
	}
	public ArrayList<WorldGeneral> getHistoricalWorldGeneral() {
		ArrayList<WorldGeneral> worldG = new ArrayList<WorldGeneral>();
		WorldGeneral world = null;
		try {
			// Step 2:Create a statement using connection object
			String SELECT_WORLD_SITUATION = "SELECT * FROM worldgeneral";
			PreparedStatement preparedStatement = conn.prepareStatement(SELECT_WORLD_SITUATION);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			// Step 4: Process the ResultSet object
			while (rs.next()) {
				world = new WorldGeneral(0,"",0,0,0,0,0,0);
				String date = rs.getString("date");
				int id = rs.getInt("ID");
				int newConfirmed = rs.getInt("newConfirmed");
				int totalConfirmed = rs.getInt("totalConfirmed");
				int newDeaths  = rs.getInt("newDeaths");
				int totalDeaths = rs.getInt("totalDeaths");
				int newRecovered = rs.getInt("newRecovered");
				int totalRecovered = rs.getInt("totalRecovered");
				
				world.setId(id);
				world.setDate(date);
				world.setNewConfirmed(newConfirmed);
				world.setTotalConfirmed(totalConfirmed);
				world.setNewDeaths(newDeaths);
				world.setTotalDeaths(totalDeaths);
				world.setNewRecovered(newRecovered);
				world.setTotalRecovered(totalRecovered);
				worldG.add(world);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return worldG;
	}
	public WorldGeneral getCurrentWorldGeneral() throws SQLException {
		String SELECT_CURRENT_WORLD_SITUATION = "SELECT * FROM worldgeneral ORDER BY id DESC LIMIT 1;";
		WorldGeneral world = null;
		PreparedStatement preparedStatement = conn.prepareStatement(SELECT_CURRENT_WORLD_SITUATION);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			world = new WorldGeneral(0,"",0,0,0,0,0,0);
			String date = rs.getString("date");
			int id = rs.getInt("ID");
			int newConfirmed = rs.getInt("newConfirmed");
			int totalConfirmed = rs.getInt("totalConfirmed");
			int newDeaths  = rs.getInt("newDeaths");
			int totalDeaths = rs.getInt("totalDeaths");
			int newRecovered = rs.getInt("newRecovered");
			int totalRecovered = rs.getInt("totalRecovered");
			
			world.setId(id);
			world.setDate(date);
			world.setNewConfirmed(newConfirmed);
			world.setTotalConfirmed(totalConfirmed);
			world.setNewDeaths(newDeaths);
			world.setTotalDeaths(totalDeaths);
			world.setNewRecovered(newRecovered);
			world.setTotalRecovered(totalRecovered);
	}
		return world;
}
	public void updateWorldGeneralManually(WorldGeneral w) throws SQLException {
		Connection conn = DbConnect.getConnection();
		String update = "UPDATE worldgeneral " + 
				"SET "
				+ "date=?," 
				+ "newConfirmed=?,"
				+ "totalConfirmed=?,"
				+ "newDeaths=?,"
				+ "totalDeaths=?,"
				+ "newRecovered=?,"
				+ "totalRecovered=? "
				+" ORDER BY ID DESC LIMIT 1"; 
		PreparedStatement preparedStatement = conn.prepareStatement(update);
		preparedStatement.setString(1, w.getDate());
		preparedStatement.setInt(2, w.getNewConfirmed());
		preparedStatement.setInt(3, w.getTotalConfirmed());
		preparedStatement.setInt(4, w.getNewDeaths());
		preparedStatement.setInt(5, w.getTotalDeaths());
		preparedStatement.setInt(6, w.getNewRecovered());
		preparedStatement.setInt(7, w.getTotalRecovered());
		preparedStatement.execute();
	}
}