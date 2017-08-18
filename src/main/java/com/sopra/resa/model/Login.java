package com.sopra.resa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="Login")
@NamedQuery(name="Login.findByUsername",
            query="SELECT l FROM Login l WHERE l.username = :pusername")
public class Login {
	
	@Id
	@Column(name="id")
	private Long idClient;
	
	@Column(name="username",unique=true)
	private String username;
	
	@Column(name="password")
	private String password;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private Client client;


	public Login() {
		super();
	}


	public Login(Long idClient, String username, String password) {
		super();
		this.idClient = idClient;
		this.username = username;
		this.password = password;
	}
	
	

	@Override
	public String toString() {
		return "Login [idClient=" + idClient + ", username=" + username + ", password=" + password + "]";
	}


	public Long getIdClient() {
		return idClient;
	}


	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
	
	
		
	
}
