package com.modulo5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.modulo5.model.Destino;


public interface DestinoRepository extends JpaRepository<Destino, Long> {
	
	List<Destino> findAll();
	@Query("SELECT d.imagem FROM Destino d WHERE d.id = :id")
    byte[] getImagemById(@Param("id") Long id);
	
}
