package com.poa.tp.services;

import java.util.List;

import com.poa.tp.dto.UsuarioDTO;

public interface IService <T, K> {

	void save( T entityDto) ;
	void saveAll(List<UsuarioDTO> lista);
	void delete( K id);
	void update(T entityDto);
	List<T> getAll( );
	T getOne( K id) ;
	
}



