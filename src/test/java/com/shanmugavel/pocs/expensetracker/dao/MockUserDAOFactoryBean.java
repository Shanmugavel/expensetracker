package com.shanmugavel.pocs.expensetracker.dao;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.expectLastCall;

import org.springframework.beans.factory.FactoryBean;

import com.shanmugavel.pocs.expensetracker.domain.User;

public class MockUserDAOFactoryBean implements FactoryBean<IUserDAO> {

	@Override
	public IUserDAO getObject() throws Exception {
		IUserDAO userDAOMock = createMock(IUserDAO.class);
		expect(userDAOMock.findById("54e01137c2e614776bd2e20d")).andStubReturn(new User());
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

}
