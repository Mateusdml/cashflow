package br.edu.ifpb.pweb2.cashflow.controller;

import java.util.List;

import javax.persistence.EntityManager;

public class UsuarioController {
	private List<String> mensagensErro;
	private EntityManager entityManager;
	
	public UsuarioController(EntityManager em) {
		this.entityManager = em;
	}
	
	public Resultado cadastre() {
		Resultado resultado = new Resultado();
		
		
		
		return resultado;
	}
	
	

}
