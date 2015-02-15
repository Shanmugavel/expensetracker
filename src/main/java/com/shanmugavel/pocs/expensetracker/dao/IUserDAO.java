/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.dao;

import java.math.BigInteger;

import com.shanmugavel.pocs.expensetracker.domain.User;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public interface IUserDAO {

		public String create(User user);
		public User findById(String id);
		public void update(User user);
		public int delete(String id);
		
}
