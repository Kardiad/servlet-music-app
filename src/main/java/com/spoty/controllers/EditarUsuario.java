package com.spoty.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class EditarUsuario
 */
@WebServlet("/EditarUsuario")
public class EditarUsuario extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarUsuario() {
        super();
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.init(request, response);
		this.parameterHandle();
		if(this.getSession().get("nombre")!=null && this.canIDoIt(PER_EDITAR_USUARIO)) {
			this.parameters.put("6", this.getSession().get("id_usuario"));
			this.db.procedure(POST_EDITAR_USUARIO, this.parameters, null, false);
			Map<String, Object> finder = new HashMap<String, Object>();
			finder.put("1", this.getSession().get("username"));
			Map<String, Object> user = (this.db.procedure(GET_USUARIO, finder, null, true)).get(0);
			this.addToSession(user);
		}
		this.redirect(RED_INICIO);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
