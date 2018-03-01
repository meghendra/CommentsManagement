package com.blog.comments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.blog.comments.models.User;

public class UserDAO {

	public User getUser(User user) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE user_ID=" + user.getUserId());
			if (rs.next()) {
				User u = new User();
				u.setEmailId(rs.getString("email"));
				u.setUserName(rs.getString("user_name"));
				u.setPassword(rs.getString("password"));
				ConnectionFactory.closeConnection(connection);
				return u;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return null;
	}

	public User getUserByEmailId(User user) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE email= '" + user.getEmailId()+"'");
			if (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("user_ID"));
				u.setUserName(rs.getString("user_name"));
				u.setPassword(rs.getString("password"));
				ConnectionFactory.closeConnection(connection);
				return u;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return null;
	}

	public boolean insertUser(User u) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO Users (user_name, email, password) VALUES (?, ?, ?)");
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getEmailId());
			ps.setString(3, u.getPassword());
			int i = ps.executeUpdate();
			if (i == 1) {
				ConnectionFactory.closeConnection(connection);
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return false;
	}
	
//	public static void main(String[] args) {
//		UserDAO ud = new UserDAO();
//		User u = new User(1, "", "", "");
//		u = ud.getUser(u);
//		System.out.println(u.getUserName()+", "+u.getEmailId()+", ");
//	}

}
