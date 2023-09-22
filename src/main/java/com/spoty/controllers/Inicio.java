package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Inicio")
public class Inicio extends MainController implements Enviroment {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.init(request, response);
		this.request.setAttribute("canciones", this.db.procedure(GET_CANCIONES, null, null, true));
		if(this.sessionDone() && this.getSession().get("rol").equals(ADMIN)) {
			this.request.setAttribute("usuario", this.getSession());
			this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
			this.request.setAttribute("usuarios", this.db.procedure(GET_USUARIOS, null, null, true));
			this.viewLoader(ADMIN_VIEW);
		}else if(this.sessionDone() && this.getSession().get("rol").equals(USER)){
			this.request.setAttribute("usuario", this.getSession());
			this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
			this.viewLoader(LOGIN_VIEW);
		}else {
			this.viewLoader(LOGIN_VIEW);			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
