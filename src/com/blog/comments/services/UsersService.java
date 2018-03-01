package com.blog.comments.services;

import javax.ws.rs.core.Context;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.blog.comments.models.User;
import com.blog.comments.dao.*;

@Path("/UsersService")
public class UsersService 
{
	private UserDAO userDaoObject;
	
	@Context private HttpServletRequest request;
	@POST
	@Path("/registeruser")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(User u) {
		userDaoObject = new UserDAO();
		if (userDaoObject.insertUser(u))
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			return "Success";
		}
		return "Failure";
	}
	
	@POST
	@Path("/authenticateuser")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String authenticateUser(User u) {
		userDaoObject = new UserDAO();
		User dbUser = userDaoObject.getUserByEmailId(u);
		if(dbUser.getPassword().equals(u.getPassword()))
		{
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			return "Success";
		}
		return "Failure";
	}
	
}