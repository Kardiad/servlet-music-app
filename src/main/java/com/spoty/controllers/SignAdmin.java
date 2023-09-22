package com.spoty.controllers;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class SignAdmin
 */
@WebServlet("/SignAdmin")
public class SignAdmin extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignAdmin() {
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
		if(this.canIDoIt(PER_ANADIR_USUARIO) && this.validFields(4)) {
			this.parameters.put("5", this.permisson.get(1).get("clave"));
			this.parameters.put("3", this.hash((String)this.parameters.get("3")));
			this.paramsStatus();
			this.db.procedure(POST_USUARIO, this.parameters, null, false);
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
