package com.modulo5.model;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import com.modulo5.enums.UF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "destino")
public class Destino {


    //ATRIBUTOS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_destino;
    
    @Column(nullable = false, length = 30)
    private String cidade; 
    
    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private UF uf;
    
    @Column(nullable = false, length = 30, unique = true)
    private String pais; 
    
    @Column(nullable = false, length = 30, unique = true)
    private String aeroporto;
    
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY)
    private float promocao;

    //MÉTODOS ESPECIAIS
    public float getPromocao() {
        return promocao;
    }
    public void setPromocao(float promocao) {
        this.promocao = promocao;    
    }
    public Long getId_destino() {
        return id_destino;
    }
    public void setId_destino(Long id_destino) {
        this.id_destino = id_destino;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public UF getUf() {
        return uf;
    }
    public void setUf(UF uf) {
        this.uf = uf;
    }
    public String getPais() {
        return pais;
    }
    public void setPais(String pais) {
        this.pais = pais;
    }
    public String getAeroporto() {
        return aeroporto;
    }
    public void setAeroporto(String aeroporto) {
        this.aeroporto = aeroporto;
    }

    public Destino(){

    }

    public Destino(String cidade, UF uf, String pais, String aeroporto, float promocao){
        this.setCidade(cidade);
        this.setUf(uf);
        this.setPais(pais);
        this.setAeroporto(aeroporto);
        this.setPromocao(promocao);
    }

   
}
