package com.shanmugavel.pocs.expensetracker.dao;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.FactoryBean;

import com.shanmugavel.pocs.expensetracker.domain.Expense;
import com.shanmugavel.pocs.expensetracker.domain.Phone;
import com.shanmugavel.pocs.expensetracker.domain.PhoneType;
import com.shanmugavel.pocs.expensetracker.domain.Status;
import com.shanmugavel.pocs.expensetracker.domain.User;



public class MockUserDAOFactoryBean implements FactoryBean<IUserDAO> {

	@Override
	public IUserDAO getObject() throws Exception {
		IUserDAO userDAOMock = createMock(IUserDAO.class);
		expect(userDAOMock.findById("54e01137c2e614776bd2e20d")).andStubReturn(createUserObj());
		expect(userDAOMock.delete("54e01137c2e614776bd2e20d")).andStubReturn(1);
		expect(userDAOMock.create( isA (User.class) )).andStubReturn("54e01137c2e614776bd2e20d");
		userDAOMock.update( isA (User.class) );
		expectLastCall();

		replay(userDAOMock);
		return userDAOMock;
	}

	@Override
	public Class<?> getObjectType() {
		return IUserDAO.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
	
	private  User createUserObj() {
		User usr = new User();
		usr.setId("54e231e8c2e6b7f3ea34e0a4");
		usr.setEmailAddress("aarthig21@gmail.com");
		usr.setFirstName("Aarthi");
		usr.setLastName("Gnanasekar");
		usr.setStatus(Status.ACTIVE);
		
		com.shanmugavel.pocs.expensetracker.domain.Phone phn = new Phone();
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
