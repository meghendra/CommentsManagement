package com.blog.comments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.blog.comments.models.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDAO {

	private Comment extractCommentFromResultSetHelper(ResultSet rs) throws SQLException {
		Comment c = new Comment();
		c.setCommentId(rs.getInt("comment_ID"));
		c.setArticleId(rs.getInt("article_ID"));
		c.setUserName(rs.getString("user_name"));
		Date date = new Date();
		date.setTime(rs.getTimestamp("time_stamp").getTime());
		String formattedTimestamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").format(date);
		c.setTimeStamp(formattedTimestamp);
		c.setContent(rs.getString("content"));
		c.setParentId(rs.getInt("parent_ID"));
		c.setNumberOfLikes(rs.getInt("number_of_likes"));
		c.setIsDeleted(rs.getInt("is_deleted"));
		c.setTimesReported(rs.getInt("times_reported"));
		return c;
	}

	public Comment getCommentById(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Comments WHERE comment_ID=" + c.getCommentId());
			if (rs.next()) {
				c = extractCommentFromResultSetHelper(rs);
				ConnectionFactory.closeConnection(connection);
				return c;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return null;
	}

	public ArrayList<Comment> getAllCommentsForArticleId(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Comments WHERE article_ID=" + c.getArticleId());

			ArrayList<Comment> comments = new ArrayList<Comment>();
			while (rs.next()) {
				Comment comment = extractCommentFromResultSetHelper(rs);
				comments.add(comment);
			}
			ConnectionFactory.closeConnection(connection);
			return comments;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return null;
	}

	public Comment insertComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO Comments (article_ID, user_name, time_stamp, content) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, c.getArticleId());
			ps.setString(2, c.getUserName());
			Date timeStamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(c.getTimeStamp());
			ps.setTimestamp(3, new java.sql.Timestamp(timeStamp.getTime()));
			ps.setString(4, c.getContent());
			int i = ps.executeUpdate();
			if (i == 1) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					c.setCommentId(generatedKeys.getInt(1));
					ConnectionFactory.closeConnection(connection);
					return getCommentById(c);
				} else {
					ConnectionFactory.closeConnection(connection);
					throw new SQLException("Creating user failed, no ID obtained.");
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return c;
	}

	public Comment updateComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE Comments SET time_stamp=?, content=? WHERE comment_ID=?");
			Date timeStamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z").parse(c.getTimeStamp());
			ps.setTimestamp(1, new java.sql.Timestamp(timeStamp.getTime()));
			ps.setString(2, c.getContent());
			ps.setInt(3, c.getCommentId());
			int i = ps.executeUpdate();
			if (i == 1) {
				ConnectionFactory.closeConnection(connection);
				return getCommentById(c);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConnectionFactory.closeConnection(connection);
		return c;
	}

	public boolean deleteComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("UPDATE Comments SET is_deleted=? WHERE comment_ID=?");
			ps.setInt(1, c.getIsDeleted());
			ps.setInt(2, c.getCommentId());
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

	public boolean hardDeleteComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Comments WHERE comment_ID=?");
			ps.setInt(1, c.getCommentId());
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

	public boolean addLikeToComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE Comments SET number_of_likes=? WHERE comment_ID=?");
			ps.setInt(1, c.getNumberOfLikes());
			ps.setInt(2, c.getCommentId());
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

	public boolean addReportToComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("UPDATE Comments SET times_reported=? WHERE comment_ID=?");
			ps.setInt(1, c.getTimesReported());
			ps.setInt(2, c.getCommentId());
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

}
