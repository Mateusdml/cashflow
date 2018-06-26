package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Resultado;

public interface ICommand {

	Resultado execute(HttpServletRequest request, HttpServletResponse response);
}
