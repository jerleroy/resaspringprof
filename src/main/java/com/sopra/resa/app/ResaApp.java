package com.sopra.resa.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sopra.resa.service.ServiceClient;

public class ResaApp {

	public static void main(String[] args) {
		System.out.println("resaApp");
		try {
			ClassPathXmlApplicationContext context =
					new ClassPathXmlApplicationContext("configSpring.xml");
			
			ServiceClient serviceClient = (ServiceClient)
					context.getBean("serviceClientImpl"/*id*/);
			
			System.out.println(serviceClient.rechercherClient(1L));
			context.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
