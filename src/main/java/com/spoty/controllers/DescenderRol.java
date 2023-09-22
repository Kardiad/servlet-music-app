package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DescenderRol
 */
@WebServlet("/DescenderRol")
public class DescenderRol extends MainController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DescenderRol() {
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
		this.paramsStatus();
		boolean levelInt = this.typeOf(this.parameters.get("2").toString())=="Integer";
		boolean idInt = this.typeOf(this.parameters.get("1").toString())=="Integer";
		if(levelInt && idInt && this.canIDoIt(PER_CAMBIAR_PERMISOS)) {
			this.parameters.put("2", (int)this.parameters.get("2")-1);			
			this.db.procedure(GET_CAMBIAR_NIVEL, this.parameters, null, false);
		}
		this.redirect(RED_INICIO_ADMIN);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
