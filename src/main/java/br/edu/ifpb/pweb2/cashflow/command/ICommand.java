package br.edu.ifpb.pweb2.cashflow.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.pweb2.cashflow.controller.Resultado;

public interface ICommand {

	Resultado execute(HttpServletRequest request, HttpServletResponse response);
}
