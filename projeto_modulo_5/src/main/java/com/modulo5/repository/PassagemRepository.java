package com.modulo5.repository;

import java.util.List;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.modulo5.model.Passagem;

public interface PassagemRepository extends JpaRepository<Passagem, Long> {

    @EntityGraph(attributePaths = {"endereco", "destino", "cliente"})
    List<Passagem> findAll();

    @Query("select f from passagem f where f.cliente.nome = :clienteNome")
    List<Passagem> buscarPorCliente(String clienteNome);

    @Query("select f from passagem f where f.cliente.nome <> :clienteNome")
    List<Passagem> buscarPorClienteExceto(String clienteNome);

    List<Passagem> findByClienteNome(String clienteNome);

    List<Passagem> findByClienteNomeNot(String clienteNome);
    
    @Query("select f from passagem f where f.destino.cidade = :destinoCidade")
    List<Passagem> buscarPorDestino(String destinoCidade);

    @Query("select f from passagem f where f.destino.cidade <> :destinoCidade")
    List<Passagem> buscarPorDestinoExceto(String destinoCidade);

    List<Passagem> findByDestinoCidade(String destinoCidade);

    List<Passagem> findByDestinoCidadeNot(String destinoCidade);
    
}