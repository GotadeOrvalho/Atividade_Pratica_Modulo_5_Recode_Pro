package com.modulo5.model;

import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import com.modulo5.enums.UF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "destino")
public class Destino {

	// ATRIBUTOS

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_destino;

	@Column(nullable = false, length = 30)
	private String cidade;

	@Column(nullable = false, length = 2)
	@Enumerated(EnumType.STRING)
	private UF uf;

	@Column(nullable = false, length = 30)
	private String pais;

	@Column(nullable = false, length = 80)
	private String aeroporto;

	@Column(nullable = false)
	private int promocao;

	@Column(nullable = false)
	private double valor;
	
	@Column(nullable = false)
	private double valorFinal;

	
	@Column(nullable = false, unique = true)
	private int num_voo;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
	@Column(name = "data_hora", nullable = true, unique = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Calendar dataHora;
	
	@Lob
	@Column(name = "imagem", length = Integer.MAX_VALUE, nullable = true)
	private byte[] imagem;

	// MÉTODOS ESPECIAIS
	

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public int getNum_voo() {
		return num_voo;
	}

	public void setNum_voo(int num_voo) {
		this.num_voo = num_voo;
	}

	public Calendar getDataHora() {
		return dataHora;
	}

	public void setDataHora(Calendar dataHora) {
		this.dataHora = dataHora;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {

		this.valor = valor;
	}

	public int getPromocao() {
		return promocao;
	}

	public void setPromocao(int promocao) {
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

	public Destino() {

	}
	
	public double getValorFinal() {
		return valorFinal;
	}

	 public void setValorFinal() {
	        double valorComPromocao = calcularValorComPromocao(this.valor, this.promocao);
	        this.valorFinal = valorComPromocao;
	    }

	    private double calcularValorComPromocao(double valorOriginal, double porcentagemPromocao) {
	    	double valorComPromocao = valorOriginal - (valorOriginal * porcentagemPromocao / 100);
	        if (valorComPromocao < 0) {
	            valorComPromocao = 0;
	        }
	        return valorComPromocao;
	    }

	   

	public Destino(String cidade, UF uf, int num_voo, String pais, String aeroporto, int promocao, double valor,
		Calendar dataHora) {
		this.setCidade(cidade);
		this.setUf(uf);
		this.setNum_voo(num_voo);
		this.setPais(pais);
		this.setAeroporto(aeroporto);
		this.setPromocao(promocao);
		this.setValor(valor);
		this.setDataHora(dataHora);
		this.setValorFinal();
	}

	

}
