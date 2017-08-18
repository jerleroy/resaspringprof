package com.sopra.resa.service;

import java.util.List;

import com.sopra.resa.model.Client;
import com.sopra.resa.model.Login;

/**
 * 
 * ServiceClient = service métier (business service)
 * avec gestion des transactions (commit, rollback)
 *    + traitements spécifiques au métier (banque ou aviation ou ...)
 *    
 *    avec certains sous traitements qui seront délégués au(x) DAO(s)
 *
 */

public interface ServiceClient {
	public Client rechercherClient(Long id);
	public void majClient(Client client); //mettre à jour client
	public List<Client> findClientByName(String nom);
	//...
	public Client insertClientWithLogin(Client cli, Login login);
	void supprimerClientWithLogin(Long idClient);
	public Client rechercherClientAvecResa(Long id);
}
