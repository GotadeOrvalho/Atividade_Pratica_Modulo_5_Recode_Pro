package com.modulo5.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.modulo5.enums.Assento;

@Entity
@Table(name = "passagem")
public class Passagem {

	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_passagem;

	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_cliente", nullable = false)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_destino", nullable = false)
	private Destino destino;

	
	@Column(nullable = false, length = 4) 
    @Enumerated(EnumType.STRING)
    private Assento assento;

	// MÉTODOS ESPECIAIS
	
	public Destino getDestino() {
		return destino;
	}

	public void setDestino(Destino destino) {
		this.destino = destino;
	}

	public Long getId_passagem() {
		return id_passagem;
	}

	public void setId_passagem(Long id_passagem) {
		this.id_passagem = id_passagem;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	

	public Assento getAssento() {
		return assento;
	}

	public void setAssento(Assento assento) {
		this.assento = assento;
	}

	

	public Passagem() {

	}

	public Passagem(Assento assento, Cliente cliente, Destino destino) {
		this.setAssento(assento);

		this.setCliente(cliente);


		this.setDestino(destino);
	}

}
