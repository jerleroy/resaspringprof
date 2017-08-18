package com.sopra.resa.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.resa.dao.DaoClient;
import com.sopra.resa.dao.DaoLogin;
import com.sopra.resa.model.Client;
import com.sopra.resa.model.Login;

//@Component
@Service //id par defaut =  serviceClientImpl
public class ServiceClientImpl implements ServiceClient {
	
	private Logger logger = LoggerFactory.getLogger(ServiceClientImpl.class);
	
	//à ajouter en TP : référence "private" sur DaoClient 
	@Autowired
	private DaoClient daoClient=null;
	
	@Autowired
	private DaoLogin daoLogin=null;
	
	public ServiceClientImpl(){
		System.out.println("dans constructeur, daoClient="+daoClient);
		//daoClient.setXyz("..."); declencherait ici nullPointerException
	}
	//@PostConstruct ou bien init-method="initAfterInject" dans <bean ...>
	public void initAfterInject(){
		System.out.println("dans initAfterInject, daoClient="+daoClient);
		//daoClient.setXyz("..."); possible ici
	}
	
	//à ajouter en TP : setter public pour injection de dépendances 
	//@Autowired
	public void setDaoClient(DaoClient daoClient) {
		this.daoClient = daoClient;
		System.out.println("dans setDaoClient, daoClient="+daoClient);
	}
	//à faire en TP : coder les méthodes en déléguant au dao: 
	
	@Override
	public Client rechercherClient(Long id) {
		return daoClient.findByKey(id);
	}
	
	@Override
	@Transactional
	public Client rechercherClientAvecResa(Long id) {
		Client client=  daoClient.findByKey(id);
		loadLazyCollection(client.getListeResa());
		return client;
	}
	
	public static void loadLazyCollection(Collection col) {
		col.size();
	}

	@Override
	@Transactional
    public Client insertClientWithLogin(Client cli,Login login){
		Client savedClient = daoClient.insert(cli);
		try {
			login.setIdClient(savedClient.getIdClient());
			Login savedLogin = daoLogin.insert(login);
			savedClient.setLogin(savedLogin);
		} catch (Exception e) {
			
			//e.printStackTrace(); 
			logger.error("echec insertClientWithLogin",e);
			throw new RuntimeException("echec insertClientWithLogin",e);
		}
    	return savedClient;
    }
	
	@Override
	@Transactional //obligatoire ici pour que client et login restent persistants
	               //avant de les retransmettre à daoXyz.delete()
	public void supprimerClientWithLogin(Long idClient){
		Client client = daoClient.findByKey(idClient);
		Login login = daoLogin.findByKey(idClient);
		if(login!=null) 
			 daoLogin.delete(login);//ordre selon contrainte du schema
		if(client!=null) 
			daoClient.delete(client);
		
	}

	@Override
	public void majClient(Client client) {
		daoClient.update(client);
	}
	
	@Override
	public List<Client> findClientByName(String nom) {
		return daoClient.findClientByName(nom);
	}

}
