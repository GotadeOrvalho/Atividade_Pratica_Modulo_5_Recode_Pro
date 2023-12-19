package com.modulo5.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
	private List<Passagem> passagem;

	@Lob
	@Column(name = "imagem", length = Integer.MAX_VALUE, nullable = true)
	private byte[] imagem;
	
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	
    public List<Passagem> getPassagem() {
        return passagem;
    }

    public void setProjetos(List<Passagem> passagem) {
        this.passagem = passagem;
    }

}
