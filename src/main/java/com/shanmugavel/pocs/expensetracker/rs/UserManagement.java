/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.rs;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shanmugavel.pocs.expensetracker.dao.IUserDAO;
import com.shanmugavel.pocs.expensetracker.domain.User;


/**
 * @author shanmugavelsundaramoorthy
 *
 */
@Component
@Path("/user")
public class UserManagement implements IUserManagement{

	@Context
	private UriInfo uriInfo;
	
	Logger LOGGER = LoggerFactory.getLogger(UserManagement.class);
	
	@Autowired
	IUserDAO userDAO;
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("id") String id) {
		LOGGER.debug("id..." + id);
		User user = userDAO.findById(id);
		return user;
	}
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") String id){
		LOGGER.debug("id..." + id);
		userDAO.delete(id);
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(User user){
		String id = userDAO.create(user);
		URI uri = URI.create(uriInfo.getAbsolutePath() + "/" + id);
		//URI uri = null;
		return Response.created(uri).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser( @PathParam("id") String id, User user) {
		LOGGER.debug("id..." + id);
		userDAO.update(user);
		return Response.ok().build();
	}
}
