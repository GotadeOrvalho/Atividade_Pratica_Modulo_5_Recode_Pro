package com.modulo5.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends Pessoa {
	
	
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Passagem> passagem;

    public List<Passagem> getPassagem() {
        return passagem;
    }

    public void setProjetos(List<Passagem> passagem) {
        this.passagem = passagem;
    }
    
}
