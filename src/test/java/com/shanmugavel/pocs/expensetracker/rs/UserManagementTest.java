package com.shanmugavel.pocs.expensetracker.rs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.plugins.server.tjws.TJWSEmbeddedJaxrsServer;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.plugins.spring.SpringResourceFactory;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class})
public class UserManagementTest {

	Logger LOGGER = LoggerFactory.getLogger(UserManagementTest.class);
    
    @Autowired
    ConfigurableApplicationContext applicationContext;
    
    private static final int PORT = 8081;
    private static final String BASE_URL = "http://localhost:" + PORT;
    
    protected TJWSEmbeddedJaxrsServer server;
    
    protected HttpClient client;
         
    @Before
    public void setup() {
    	server = new TJWSEmbeddedJaxrsServer();
    	server.setPort(PORT);
    	server.start();
    	
    	ResteasyDeployment deployment = server.getDeployment();
    	Dispatcher dispatcher = deployment.getDispatcher();
    	
    	SpringBeanProcessor processor = new SpringBeanProcessor(dispatcher, deployment.getRegistry()
    			, deployment.getProviderFactory());
    	applicationContext.addBeanFactoryPostProcessor(processor);
    	
    	SpringResourceFactory noDefaults = new SpringResourceFactory("userManagement", applicationContext, UserManagement.class);
    	//noDefaults.setContext("/test");
    	dispatcher.getRegistry().addResourceFactory(noDefaults);
    	
    	
    	//client
    	client = new DefaultHttpClient();
    }
    
    @After
    public void end() {
    	server.stop();
    }
    
    @Test
    public void testgetUser() throws ClientProtocolException, IOException {
        String getUrl = BASE_URL + "/user/54e01137c2e614776bd2e20d";

    	HttpResponse response = null;
        HttpGet getMethod = new HttpGet(getUrl);
        getMethod.addHeader(new Header() {
			
			@Override
			public String getValue() {
				return MediaType.APPLICATION_JSON;
			}
			
			@Override
			public String getName() {
				return HttpHeaders.ACCEPT;
			}
			
			@Override
			public HeaderElement[] getElements() throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}
		});
        response = client.execute(getMethod);
        
        // CONVERT RESPONSE TO STRING
        String result = EntityUtils.toString(response.getEntity());
        LOGGER.debug("Result::" + result);
        assertNotNull("Response is NULL", result);
        assertEquals("HttpStatus is not correct", response.getStatusLine().getStatusCode(), 200);
    }
    
    @Test
    public void testdeleteUser() throws ClientProtocolException, IOException {
        String deleteUrl = BASE_URL + "/user/54e01137c2e614776bd2e20d";

    	HttpResponse response = null;
        HttpDelete deleteMethod = new HttpDelete(deleteUrl);

        response = client.execute(deleteMethod);
        
        // CONVERT RESPONSE TO STRING
        String result = EntityUtils.toString(response.getEntity());
        LOGGER.debug("Result::" + result);
        assertNotNull("Response is NULL", result);
        assertEquals("HttpStatus is not correct", response.getStatusLine().getStatusCode(), 200);
    }
    
    @Test
    public void testcreateUser() throws ClientProtocolException, IOException, URISyntaxException {
    	/*UriInfo uri = EasyMock.createMock(UriInfo.class);
    	EasyMock.expect(uri.getAbsolutePath()).andStubReturn( new URIBuilder("http://localhost/user").build());
    	
    	Whitebox.setInternalState(UserManagement.class, uri);*/
    	
        String postUrl = BASE_URL + "/user";

    	HttpResponse response = null;
        HttpPost postMethod = new HttpPost(postUrl);
        postMethod.addHeader(new Header() {
			
			@Override
			public String getValue() {
				return MediaType.APPLICATION_JSON;
			}
			
			@Override
			public String getName() {
				return HttpHeaders.CONTENT_TYPE;
			}
			
			@Override
			public HeaderElement[] getElements() throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}
		});
        
        postMethod.setEntity(new StringEntity("{\"id\":null,\"firstName\":\"Aarthi\",\"lastName\":\"Gnanasekar\",\"middleName\":null,\"emailAddress\":\"aarthig21@gmail.com\",\"phones\":[{\"type\":\"HOME\",\"number\":\"801 317 1882\",\"defaultPhone\":true}],\"status\":\"ACTIVE\",\"expenses\":[{\"dt\":1424110056447,\"tags\":[\"Restaurant\"],\"amount\":12.45,\"currency\":\"USD\",\"desc\":\"Dinner...\"}]}"));
        response = client.execute(postMethod);
        
        // CONVERT RESPONSE TO STRING
        String result = EntityUtils.toString(response.getEntity());
        LOGGER.debug("Result::" + result);
        assertNotNull("Response is NULL", result);
        assertEquals("HttpStatus is not correct", 201, response.getStatusLine().getStatusCode());
    }

    @Test
    public void testupdateUser() throws ClientProtocolException, IOException, URISyntaxException {
        String putURL = BASE_URL + "/user/54e01137c2e614776bd2e20d";

    	HttpResponse response = null;
        HttpPut putMethod = new HttpPut(putURL);
        putMethod.addHeader(new Header() {
			
			@Override
			public String getValue() {
				return MediaType.APPLICATION_JSON;
			}
			
			@Override
			public String getName() {
				return HttpHeaders.CONTENT_TYPE;
			}
			
			@Override
			public HeaderElement[] getElements() throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}
		});
        putMethod.addHeader(new Header() {
			
			@Override
			public String getValue() {
				return MediaType.APPLICATION_JSON;
			}
			
			@Override
			public String getName() {
				return HttpHeaders.ACCEPT;
			}
			
			@Override
			public HeaderElement[] getElements() throws ParseException {
				// TODO Auto-generated method stub
				return null;
			}
		});
        putMethod.setEntity(new StringEntity("{\"id\":null,\"firstName\": \"Shan\",\"lastName\":null,\"middleName\":null,\"emailAddress\":null,\"phones\":null,\"status\":null,\"expenses\":null}"));
        response = client.execute(putMethod);
        
        // CONVERT RESPONSE TO STRING
        String result = EntityUtils.toString(response.getEntity());
        LOGGER.debug("Result::" + result);
        assertNotNull("Response is NULL", result);
        assertEquals("HttpStatus is not correct", 200, response.getStatusLine().getStatusCode());
    }
}
