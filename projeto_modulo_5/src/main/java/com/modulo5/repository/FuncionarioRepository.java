package com.modulo5.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import com.modulo5.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @EntityGraph(attributePaths = {"endereco"})
    List<Funcionario> findAll();   
    
    Optional<Funcionario> findByEmail(String email);
}
