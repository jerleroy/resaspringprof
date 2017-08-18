package com.sopra.resa.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sopra.resa.model.Client;
import com.sopra.resa.model.Login;
import com.sopra.resa.model.Resa;
import com.sopra.resa.service.ServiceClient;

@RunWith(SpringJUnit4ClassRunner.class)//necessite spring-test dans pom.xml
@ContextConfiguration(locations={"/configSpring.xml"}) //chargé qu'une seule fois!!!
public class ResaTest {
	
	@Autowired //@Autowired est equivalent à context.getBean(ServiceClient.class);
	private  ServiceClient serviceClient; //à tester

	
	@Test
	public void testRechercherClient(){
		Client c = serviceClient.rechercherClient(1L);
		Assert.assertTrue(c.getIdClient()==1L);
		System.out.println(c);
	}
	
	@Test
	public void testRechercherClientAvecResa(){
		Client c = serviceClient.rechercherClientAvecResa(1L);
		Assert.assertTrue(c.getIdClient()==1L);
		System.out.println(c);
		for(Resa r : c.getListeResa()){
			System.out.println("\t" + r);
		}
	}
	
	@Test
	public void testRechercherClientByName(){
		List<Client> listeCli = serviceClient.findClientByName("Therieur");
		Assert.assertTrue(listeCli.size()>0);
	}
	
	@Test
	public void testValidInsertClientWithLogin(){
		Client nouveauClient = new Client(null,"nomXx", "prenomYy");
		Login nouveauLogin = new Login(null,"usernameXx","passwordYy");
		Client savedClientWithSavedLogin = serviceClient.insertClientWithLogin(nouveauClient, nouveauLogin);
		Assert.assertNotNull(savedClientWithSavedLogin);
		Long nouvelId = savedClientWithSavedLogin.getIdClient();
		Client client = serviceClient.rechercherClient(nouvelId);
		Assert.assertEquals(client.getLogin().getUsername(),"usernameXx");
		Assert.assertEquals(client.getLogin().getPassword(),"passwordYy");
		//affichage temporaire ou exceptionnel (tp):
		System.out.println("nouveau client: " + client + " avec login: " + client.getLogin());
		//suppression à la fin pour pouvoir relancer le test plusieurs fois:
		serviceClient.supprimerClientWithLogin(nouvelId);
	}
	
	@Test
	public void testInValidInsertClientWithLogin(){
		Client nouveauClient = new Client(null,"nomXx", "prenomYy");
		Login nouveauLogin = new Login(null,"alex-therieur","passwordYy"); //invalide car username dejà en base et devant être unique
		try{
		    Client savedClientWithSavedLogin = serviceClient.insertClientWithLogin(nouveauClient, nouveauLogin);
		    Assert.fail("une exception aurait du remonter");
		} catch(Exception ex){
			System.err.println("exception normale:" + ex.getMessage());   
		}
		
		Long nouvelId = nouveauClient.getIdClient(); //savedClientWithSavedLogin.getIdClient();
		Client client = serviceClient.rechercherClient(nouvelId);
		if(client !=null){
			//affichage temporaire ou exceptionnel (tp):
			System.out.println("nouveau client: " + client + " avec login: " + client.getLogin());
			//suppression à la fin pour pouvoir relancer le test plusieurs fois:
			serviceClient.supprimerClientWithLogin(nouvelId);
			Assert.fail("comportement non transactionnel (action partielle enregistree en base)");
		}
	}

	
	
}
