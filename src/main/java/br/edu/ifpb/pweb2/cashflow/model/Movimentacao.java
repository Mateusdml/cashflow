package br.edu.ifpb.pweb2.cashflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="MOVIMENTACAO")
public class Movimentacao 
{
	@Id
	@Column(name="ID_MOVIMENTACAO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_movimentacao;
	
	private String descricao;
	private double valor;
	private boolean operacao; // FALSE SE SAIDA - TRUE SE ENTRADA
	
	@ManyToOne()
	private Usuario usuario;
		
	public Movimentacao(String descricao, double valor, boolean tipo) {
		this.descricao = descricao;
		this.valor = valor;
		this.operacao = tipo;
	}
	
	public Movimentacao() {
//		PARA PRESISTENCIA
	}
	
//	PARA QUANDO VIER DO BANCO DE DADOS
	public Movimentacao(int id, Usuario usuario, String descricao, double valor, boolean tipo) {
		this.id_movimentacao = id;
		this.usuario = usuario;
		this.descricao = descricao;
		this.valor = valor;
		this.operacao = tipo;
	}
	
	public int getId() {
		return this.id_movimentacao;
	}
	
	public Usuario getIdUsuario() {
		return this.usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public boolean getOperacao() {
		return this.operacao;
	}
	
	public void setOperacao(boolean tipo) {
		this.operacao = tipo;
	}

	public void opera(Usuario u) {
		if(this.operacao) { // ENTRADA
			u.setSaldo(u.getSaldo() + this.valor);
			
		} else { // SAIDA
			u.setSaldo(u.getSaldo() - this.valor);
		}
	}
	
	@Override
	public String toString() {
		if(this.operacao){
			return "Entrada [descricao=" + descricao + ", valor=" + valor + "]";
		}else{
			return "Saida [descricao=" + descricao + ", valor=" + valor + "]";
		}
	}		
}