package com.modulo5.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensagem")
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_mensagem;

	@Column(nullable = false, length = 80)
	private String nome;

	@Column(nullable = false, length = 80, unique = false)
	private String email;

	@Column(nullable = true, columnDefinition = "TEXT")
	private String msg;

	public Long getId_mensagem() {
		return id_mensagem;
	}

	public void setId_mensagem(Long id_mensagem) {
		this.id_mensagem = id_mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Mensagem() {
		
	}
	
	public Mensagem(Long id_mensagem, String nome, String email, String msg) {
	
		this.id_mensagem = id_mensagem;
		this.nome = nome;
		this.email = email;
		this.msg = msg;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id_mensagem, msg, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mensagem other = (Mensagem) obj;
		return Objects.equals(email, other.email) && Objects.equals(id_mensagem, other.id_mensagem)
				&& Objects.equals(msg, other.msg) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Mensagem [id_mensagem=" + id_mensagem + ", nome=" + nome + ", email=" + email + ", mensagem=" + msg
				+ "]";
	}
	
	
}
