/**
 * 
 */
package com.shanmugavel.pocs.expensetracker;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;

import javax.ws.rs.core.UriInfo;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public class MockUriInfoFactoryBean implements FactoryBean<UriInfo> {

	@Override
	public UriInfo getObject() throws Exception {
		UriInfo uriInfoMock = createMock(UriInfo.class);
		expect(uriInfoMock.getAbsolutePath()).andStubReturn(new URIBuilder("http://localhost/user").build());
		return uriInfoMock;
	}

	@Override
	public Class<?> getObjectType() {
		return UriInfo.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
