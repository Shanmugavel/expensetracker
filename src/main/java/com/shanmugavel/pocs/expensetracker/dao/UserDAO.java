/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.WriteResult;
import com.shanmugavel.pocs.expensetracker.domain.User;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
@Repository
public class UserDAO implements IUserDAO {

	@Autowired
	private MongoOperations mongoOprs;
	
	@Override
	public String create(User user) {
		this.mongoOprs.insert(user);
		return user.getId();
	}


	@Override
	public User findById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOprs.findOne(query, User.class);
	}


	@Override
	public void update(User user) {
		this.mongoOprs.save(user);
	}


	@Override
	public int delete(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOprs.remove(query, User.class);
		return result.getN();
	}

}
