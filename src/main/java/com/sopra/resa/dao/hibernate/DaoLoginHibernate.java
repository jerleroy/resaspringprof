package com.sopra.resa.dao.hibernate;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.resa.dao.DaoLogin;
import com.sopra.resa.model.Login;


//@Component
@Repository // id par defaut = Nom de la classe avec minuscule au début
@Transactional //en version spring
public class DaoLoginHibernate extends DaoGenericImpl<Login,Long> implements DaoLogin {
	
	public Login findLoginByUsername(String username){
	 
	 return em.createNamedQuery("Login.findByUsername",Login.class)
			 .setParameter("pusername", username)
			 .getSingleResult();
    }


}
