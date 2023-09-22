package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MostrarFavoritos
 */
@WebServlet("/MostrarFavoritos")
public class MostrarFavoritos extends MainController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarFavoritos() {
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
		this.parameters.put("1", this.getSession().get("id_usuario"));
		this.request.setAttribute("canciones", this.db.procedure(GET_FAVS, this.parameters, null, true));
		this.request.setAttribute("usuario", this.getSession());
		this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
		this.request.setAttribute("fav_mode", this.request.getRequestURI().split("/")[1]);
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
