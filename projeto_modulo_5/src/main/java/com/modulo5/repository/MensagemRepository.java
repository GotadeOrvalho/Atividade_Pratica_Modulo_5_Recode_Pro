package com.modulo5.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.modulo5.model.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
	
	
	
}
