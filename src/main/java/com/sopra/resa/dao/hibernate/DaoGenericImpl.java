package com.sopra.resa.dao.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.sopra.resa.dao.DaoGeneric;

@Transactional //de Spring
public class DaoGenericImpl<T,K extends Serializable> 
                  implements DaoGeneric<T, K > {
	
	@PersistenceContext() 
	//initialisation automatique tenant compte de config spring 
    //et persistence.xml
	protected EntityManager em;
	
	private Class<T> persistentClass; // équivalent de T.class à construire
	
	DaoGenericImpl(){
		try {
	    	   ParameterizedType parameterizedType = 
	    			         (ParameterizedType) getClass().getGenericSuperclass();  
	    	   Type typeT = parameterizedType.getActualTypeArguments()[0];
	    	   if(!typeT.toString().equals("T")){
	    		   this.persistentClass = (Class<T>) typeT;
	    	 }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Override
	public T findByKey(K key) {
		 return em.find(persistentClass, key);
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("SELECT o FROM " + persistentClass.getSimpleName()+
				             " o", persistentClass).getResultList();
	}

	@Override
	public T insert(T obj) {
		em.persist(obj);
		return obj;
	}

	@Override
	public T update(T obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(T obj) {
		em.remove(obj);
	}

	

}
