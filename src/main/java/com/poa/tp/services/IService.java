package com.poa.tp.services;

import java.util.List;

import com.poa.tp.services.exceptions.ObjectAlreadyExistException;
import com.poa.tp.services.exceptions.ObjectNotFoundException;

public interface IService <T, K> {

	List<T> getAll( );
	T getOne(K dni) throws ObjectNotFoundException ;
	void save( T entityDto) throws ObjectAlreadyExistException;
	void saveAll(List<T> lista) throws ObjectAlreadyExistException;
	void delete( K dni) throws ObjectNotFoundException;
	void update(T entityDto) throws ObjectNotFoundException;
	
}



