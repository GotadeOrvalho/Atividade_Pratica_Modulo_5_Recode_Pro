package com.modulo5.model;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "passagem")
public class Passagem {
 

    //ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_passagem;
	
	@Column(nullable = false, unique= true)
    @NumberFormat(style = Style.CURRENCY)
    private int num_voo;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_cliente", nullable = false)
    private Cliente fk_cliente;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_destino", nullable = false)
    private Destino fk_destino;
    
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private float valor;
    
    @Column(nullable = false, length = 5)
    private String assento; 
    
    @Column(nullable = false, length = 30)
    private String data_hora;
    

    //MÉTODOS ESPECIAIS
    public Destino getFk_destino() {
        return fk_destino;
    }
    public void setFk_destino(Destino fk_destino) {
        this.fk_destino = fk_destino;
    }
    public Long getId_passagem() {
        return id_passagem;
    }
    public void setId_passagem(Long id_passagem) {
        this.id_passagem = id_passagem;
    }
    public Cliente getFk_cliente() {
        return fk_cliente;
    }
    public void setFk_cliente(Cliente fk_cliente) {
        this.fk_cliente = fk_cliente;
    }
    public int getNum_voo() {
        return num_voo;
    }
    public void setNum_voo(int num_voo) {
        this.num_voo = num_voo;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {

        this.valor = valor;
    }
    public String getAssento() {
        return assento;
    }
    public void setAssento(String assento) {
        this.assento = assento;
    }
    public String getData_hora() {
        return data_hora;
    }
    public void setData_hora(String data_hora) {
        this.data_hora = data_hora;
    }

    public Passagem(){

    }
   
    public Passagem(String assento, String data_hora, int num_voo, Cliente fk_cliente, float valor, Destino fk_destino){
        this.setAssento(assento);
        this.setData_hora(data_hora);
        this.setFk_cliente(fk_cliente);
        this.setNum_voo(num_voo);
        this.setValor(valor);
        this.setFk_destino(fk_destino);
    }

    private float calcularValorComPromocao(Destino destino) {
    
        float valorOriginal = getValor();
    
      
        float porcentagemPromocao = destino.getPromocao();
    
      
        float valorComPromocao = valorOriginal - (valorOriginal * porcentagemPromocao / 100);
    
       
        if (valorComPromocao < 0) {
            valorComPromocao = 0;
        }
        return valorComPromocao;
    }

    public void aplicarPromocao(Destino destino) {
    	float valorComPromocao = calcularValorComPromocao(destino);
        setValor(valorComPromocao);
    }
}
