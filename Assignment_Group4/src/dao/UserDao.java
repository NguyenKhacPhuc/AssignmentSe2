package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnect.DbConnect;
import model.User;

public class UserDao {
	
	public ArrayList<User> getAllUser() throws SQLException{
		Connection conn = dbconnect.DbConnect.getConnection();
		ArrayList<User> userLst = new ArrayList<User>();
		String selectAllCountry= "SELECT * FROM user";
		PreparedStatement preparedStatement = conn.prepareStatement(selectAllCountry);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int iD= rs.getInt("ID");
			String name = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			String dob = rs.getString("dob");
			User user = new User(name,password,email,age,dob);
			user.setiD(iD);
			userLst.add(user);
		}
		return userLst;
	}
	public void createUser(User user) throws SQLException {
		Connection conn = dbconnect.DbConnect.getConnection();
		String createUser = "INSERT INTO user (username,password,email,age,dob) values (?,?,?,?,?);";
		PreparedStatement preparedStatement = conn.prepareStatement(createUser);
		preparedStatement.setString(1,user.getUsername());
		preparedStatement.setString(2,user.getPassword());
		preparedStatement.setString(3,user.getEmail());
		preparedStatement.setInt(4, user.getAge());
		preparedStatement.setString(5, user.getDob());
		preparedStatement.execute();
		
	}
	public void deleteUser(int id) throws SQLException {
		Connection conn = dbconnect.DbConnect.getConnection();
		String createUser = "DELETE FROM user WHERE ID=" +  id;
		PreparedStatement preparedStatement = conn.prepareStatement(createUser);
		preparedStatement.executeUpdate();
	}
	public ArrayList<User> searchUser(String name) throws SQLException {
		ArrayList<User> userLst = new ArrayList<User>();
		Connection conn = dbconnect.DbConnect.getConnection();
		String searchUsers = "Select * FROM user WHERE username="  + "\"" + name + "\"";
		PreparedStatement preparedStatement = conn.prepareStatement(searchUsers);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int iD= rs.getInt("ID");
			String name1 = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			String dob = rs.getString("dob");
			User user = new User(name1,password,email,age,dob);
			user.setiD(iD);
			userLst.add(user);
		}
		return userLst;
	}
	public void updateUser(User user) throws SQLException {
		Connection conn = dbconnect.DbConnect.getConnection();
		String createUser = "Update user set "
				+"username=?,"
				+"password=?,"
				+"email=?,"
				+"age=?,"
				+"dob=? "
				+"Where ID=?;";
		if(checkIdExist(user.getiD())) {
		PreparedStatement preparedStatement = conn.prepareStatement(createUser);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.setInt(4, user.getAge());
		preparedStatement.setString(5, user.getDob());
		preparedStatement.setInt(6,user.getiD());
		preparedStatement.executeUpdate();
		}
	}
	private boolean checkIdExist(int iD) throws SQLException {
		ArrayList<Integer> allID = new ArrayList<Integer>();
		Connection conn = DbConnect.getConnection();
		String query = "Select * from user";
		PreparedStatement preparedStatement = conn.prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int ID = rs.getInt("ID");
			allID.add(ID);
		}
		for(int k : allID) {
			if(iD == k) {
				return true;
			}
		}
		return false;
	}
	public String LogIn(String username, String passwordLog) throws SQLException {
		Connection conn = dbconnect.DbConnect.getConnection();
		ArrayList<User> userLst = new ArrayList<User>();
		String selectAllCountry= "SELECT * FROM user";
		PreparedStatement preparedStatement = conn.prepareStatement(selectAllCountry);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int iD= rs.getInt("ID");
			String name = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			String dob = rs.getString("dob");
			User user = new User(name,password,email,age,dob);
			user.setiD(iD);
			userLst.add(user);
		}
		for(User u : userLst) {
			if(username.equals(u.getUsername())) {
				if(passwordLog.equals(u.getPassword())) {
					return "Success";
				}
				else {
					return "Wrong password";
				}
			}
		}
		return "username does not exist";
	}
	public User searchUserByID(int ID) throws SQLException {
		Connection conn = dbconnect.DbConnect.getConnection();
		User user = null;
		String searchUsers = "Select * FROM user WHERE ID="  + ID;
		PreparedStatement preparedStatement = conn.prepareStatement(searchUsers);
		ResultSet rs = preparedStatement.executeQuery();
		while(rs.next()) {
			int iD= rs.getInt("ID");
			String name1 = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			int age = rs.getInt("age");
			String dob = rs.getString("dob");
			user = new User(iD,name1,password,email,age,dob);
	}
		return user;

}
}