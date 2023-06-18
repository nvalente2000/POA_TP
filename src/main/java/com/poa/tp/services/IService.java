package com.poa.tp.services;

import java.util.List;

import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

public interface IService <T, K> {

	List<T> getAll( );
	T getOne(K dni) throws ObjectNotFoundException ;
	void save( T entity) throws ObjectAlreadyExistException;
	void saveAll(List<T> lista) throws ObjectAlreadyExistException;
	void delete( K key) throws ObjectNotFoundException;
	void update(T entity) throws ObjectNotFoundException;
	
}



