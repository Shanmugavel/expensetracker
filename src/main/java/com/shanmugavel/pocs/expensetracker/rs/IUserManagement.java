/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.rs;

import javax.ws.rs.core.Response;

import com.shanmugavel.pocs.expensetracker.domain.User;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public interface IUserManagement {

	public User getUser(String id);
	public Response deleteUser(String id);
	public Response createUser(User user);
	public Response updateUser(String id, User user);
}
