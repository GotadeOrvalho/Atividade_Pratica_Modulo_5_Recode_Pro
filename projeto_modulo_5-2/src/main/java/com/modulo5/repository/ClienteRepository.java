package com.modulo5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo5.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	    @EntityGraph(attributePaths = "endereco")
	    List<Cliente> findAll();
	    
	    Optional<Cliente> findByEmail(String email);
	    
}

