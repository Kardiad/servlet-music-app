package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class BloquearUsuario
 */
@WebServlet("/BloquearUsuario")
public class BloquearUsuario extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BloquearUsuario() {
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
		String red = RED_INICIO;
		if(this.getSession().get("username")!=null && this.canIDoIt(PER_BLOQUEO_USUARIO)) {
			this.db.procedure(POST_BLOQUEAR, this.parameters, null, false);
			red = RED_INICIO_ADMIN;
		}
		this.redirect(red);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
