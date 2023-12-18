package com.modulo5.repository;

import java.util.List;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo5.model.Passagem;

public interface PassagemRepository extends JpaRepository<Passagem, Long> {

    @EntityGraph(attributePaths = {"destino", "cliente"})
    List<Passagem> findAll();

    
    
}