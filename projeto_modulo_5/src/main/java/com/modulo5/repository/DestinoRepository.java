package com.modulo5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo5.model.Destino;


public interface DestinoRepository extends JpaRepository<Destino, Long> {
	
	List<Destino> findAll();
	
}
