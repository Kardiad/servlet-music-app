package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class Buscar
 */
@WebServlet("/Buscar")
public class Buscar extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buscar() {
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
		this.request.setAttribute("canciones", this.db.procedure(GET_BUSQUEDA_CANCIONES, this.parameters, null, true));
		this.request.setAttribute("usuario", this.getSession());
		this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, this.parameters, null, true));
		if(this.getSession().get("rol").toString().equals(ADMIN)) {
			this.request.setAttribute("usuarios", this.db.procedure(GET_USUARIOS, null, null, true));
		}
		this.viewLoader(this.getViewLoad(this.getSession().get("rol").toString()));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
