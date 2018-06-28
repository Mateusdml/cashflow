package br.edu.ifpb.pweb2.cashflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USUARIO")
public class Usuario 
{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String email;
	private String login;
	private String senha;
	private double saldo;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Movimentacao> movimentacoes;
	
	public Usuario(int id, String email, String login, String senha) {
		this.id = id;
		this.email = email;
		this.login = login;
		this.senha = senha;
		this.saldo = 0.0;
		this.movimentacoes = new ArrayList<Movimentacao>();
	}

	public Usuario() {
//	PARA PERSISTENCIA 
	}	
	
	public int getId(){
		return id;
	}
	
	public void setId_usuario(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	
	public void adicionarMovimentacao(Movimentacao m) {
		m.opera(this);
		this.movimentacoes.add(m);
	}
	
	@Override
	public String toString() {
		return "Usuario [Email=" + this.email + ", saldo=" + saldo + "]";
	}
}