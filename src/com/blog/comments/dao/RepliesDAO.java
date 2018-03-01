package com.blog.comments.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import com.blog.comments.models.Comment;
import com.blog.comments.models.Reply;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RepliesDAO {

	private Reply extractReplyFromResultSetHelper (ResultSet rs) throws SQLException {
		Reply r = new Reply();
		r.setReplyId(rs.getInt("reply_ID"));
		r.setParentCommentId(rs.getInt("parent_comment_ID"));
		r.setChildCommentId(rs.getInt("child_comment_ID"));
	    return r;
	}
	
	public ArrayList<Reply> getAllRepliesForAComment(Comment c) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Replies WHERE parent_comment_ID=" + c.getCommentId());
			
			ArrayList<Reply> replies = new ArrayList<Reply> ();
	        while(rs.next())
	        {
	        		Reply reply = extractReplyFromResultSetHelper(rs);
	        		replies.add(reply);
	        }
	        return replies;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public boolean insertReply(Reply r) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO Replies (parent_comment_ID, child_comment_ID) VALUES (?, ?)");
			ps.setInt(1, r.getParentCommentId());
			ps.setInt(2, r.getChildCommentId());
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
}
