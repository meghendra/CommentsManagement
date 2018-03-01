package com.blog.comments.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blog.comments.models.Comment;
import com.blog.comments.dao.*;

@Path("/CommentsService")
public class CommentsService 
{
	private CommentDAO commentDaoObject;
	
	@GET
	@Path("/allcomments")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Comment> getTrackInJSON() {
		commentDaoObject = new CommentDAO();
		ArrayList<Comment> allComments = new ArrayList<Comment>();
		Comment c = new Comment(null, 1, null, null, null, null, null, null, null);
		allComments = commentDaoObject.getAllCommentsForArticleId(c);
		return allComments;
	}
	
	@POST
	@Path("/submitcomment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment submitComment(Comment c) {
		commentDaoObject = new CommentDAO();
		c = commentDaoObject.insertComment(c);
		return c;
	}
	
	@POST
	@Path("/submitlike")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment submitLike(Comment c) {
		commentDaoObject = new CommentDAO();
		commentDaoObject.addLikeToComment(c);
		return c;
	}
	
	@POST
	@Path("/submitreport")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment submitReport(Comment c) {
		commentDaoObject = new CommentDAO();
		commentDaoObject.addReportToComment(c);
		return c;
	}
	
	@POST
	@Path("/deletecomment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment deleteComment(Comment c) {
		commentDaoObject = new CommentDAO();
		commentDaoObject.hardDeleteComment(c);
		return c;
	}
	
	@POST
	@Path("/editcomment")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Comment editComment(Comment c) {
		commentDaoObject = new CommentDAO();
		c = commentDaoObject.updateComment(c);
		return c;
	}
}