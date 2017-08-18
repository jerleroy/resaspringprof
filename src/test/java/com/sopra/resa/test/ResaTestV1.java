package com.sopra.resa.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sopra.resa.model.Client;
import com.sopra.resa.service.ServiceClient;

import junit.framework.Assert;

public class ResaTestV1 {
	
	private static ServiceClient serviceClient; //à tester
	private static ClassPathXmlApplicationContext context = null;
	
	@BeforeClass
	public static void init(){
		context =	new ClassPathXmlApplicationContext("configSpring.xml");
		//serviceClient = (ServiceClient) context.getBean("serviceClientImpl"/*id*/);
		serviceClient = context.getBean(ServiceClient.class);
	}
	
	@Test
	public void testRechercherClient(){
		Client c = serviceClient.rechercherClient(1L);
		Assert.assertTrue(c.getIdClient()==1L);
		System.out.println(c);
	}
	
	@AfterClass
	public static void fin(){
		context.close();
	}
	
}
