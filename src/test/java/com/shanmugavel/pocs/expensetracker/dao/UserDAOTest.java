/**
 * 
 */
package com.shanmugavel.pocs.expensetracker.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.reflect.Whitebox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.mongodb.WriteResult;
import com.shanmugavel.pocs.expensetracker.domain.Expense;
import com.shanmugavel.pocs.expensetracker.domain.Phone;
import com.shanmugavel.pocs.expensetracker.domain.PhoneType;
import com.shanmugavel.pocs.expensetracker.domain.Status;
import com.shanmugavel.pocs.expensetracker.domain.User;





/**
 * @author shanmugavelsundaramoorthy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class UserDAOTest {
	Logger LOGGER = LoggerFactory.getLogger(UserDAOTest.class);
	
	
	IUserDAO usrDAO;
	MongoOperations oprs;
	
	@Before
	public void setup() {
		usrDAO = new UserDAO();
		
		//Mock WriteResult
		WriteResult result = createMock(WriteResult.class);
		expect(result.getN()).andStubReturn(1);
		
		//Mock Mongo operations
		oprs = createMock(MongoOperations.class);
	
		//Create
		oprs.insert( isA (User.class) );
		expectLastCall();
		
		//Read
		expect(oprs.findOne(isA (Query.class), isA (Class.class))).andStubReturn(createUserObj());

		//Update
		oprs.save( isA (User.class));
		expectLastCall();
		
		//Delete
		expect(oprs.remove(isA (Query.class), isA (Class.class))).andStubReturn(result);

		//replay
		replay(oprs);
		
		//Set private field
		Whitebox.setInternalState(usrDAO, MongoOperations.class, oprs);
	}
	
	@Test
	public void testcreate() {
		String id = usrDAO.create(createUserObj());
		assertNotNull("User ID is NULL", id);
		LOGGER.debug("User ID::" + id);
	}
	
	@Test
	public void testfindById(){
		User usr = usrDAO.findById("54e01137c2e614776bd2e20d");
		assertNotNull("User object is null", usr);
	}
	
	@Test
	public void testdelete(){
		Integer no = usrDAO.delete("54e01137c2e614776bd2e20d");
		assertNotNull("No is null", no);
	}
	
	@Test
	public void testupdate() {
		usrDAO.update(createUserObj());
	}
	
	private  User createUserObj() {
		User usr = new User();
		usr.setId("54e01137c2e614776bd2e20d");
		
		usr.setEmailAddress("dummy@gmail.com");
		usr.setFirstName("Aarthi");
		usr.setLastName("Gnanasekar");
		usr.setStatus(Status.ACTIVE);
		
		Phone phn = new Phone();
		phn.setNumber("801 317 1882");
		phn.setType(PhoneType.HOME);
		phn.setDefaultPhone(true);
		usr.addPhone(phn);
		
		Expense expense = new Expense();
		expense.setAmount(12.45d);
		expense.setCurrency(Currency.getInstance(Locale.US));
		expense.setDesc("Dinner...");
		expense.setDt(Calendar.getInstance().getTime());
		List<String> tags = new ArrayList<String>();
		tags.add("Restaurant");
		expense.setTags(tags);
		usr.addExpense(expense);
		return usr;
	}
}
