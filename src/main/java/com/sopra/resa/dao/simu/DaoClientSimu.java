package com.sopra.resa.dao.simu;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sopra.resa.dao.DaoClient;
import com.sopra.resa.model.Client;

/*
 * DaoClientSimu = Simulation sans base de donnees
 */
//@Component
//@Repository("daoClientSimu") //composant de type dao
@Repository // id par defaut = Nom de la classe avec minuscule au début
public class DaoClientSimu implements DaoClient {

	@Override
	public Client findByKey(Long idClient) {
		return new Client(idClient,"nomxx","prenomyy");
	}

	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client insert(Client client) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Client update(Client client) {
		// TODO Auto-generated method stub
        return null;
	}

	@Override
	public void delete(Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Client> findClientByName(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

}
