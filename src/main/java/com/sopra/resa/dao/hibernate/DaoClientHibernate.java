package com.sopra.resa.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.resa.dao.DaoClient;
import com.sopra.resa.model.Client;


//@Component
@Repository // id par defaut = Nom de la classe avec minuscule au début
@Transactional //en version spring
public class DaoClientHibernate extends DaoGenericImpl<Client,Long> implements DaoClient {
	
	public List<Client> findClientByName(String nom){
//	 return em.createQuery("SELECT c FROM Client c WHERE c.nom = :pnom",Client.class)
//			 .setParameter("pnom", nom)
//			 .getResultList();
	 
	 return em.createNamedQuery("Client.findByName",Client.class)
			 .setParameter("pnom", nom)
			 .getResultList();
    }


}
