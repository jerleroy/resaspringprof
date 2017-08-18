package com.sopra.resa.dao;

import com.sopra.resa.model.Login;

/*
 * DaoLogin = Data Access Object
 *         alias "Data Service" alias "repository spring"
 *   avec methodes CRUD
 *   et throws RuntimeException implicites
 */
public interface DaoLogin extends DaoGeneric<Login,Long>{
    
    public Login findLoginByUsername(String username);
	
}    
