package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Servlet implementation class Sign
 */
@WebServlet("/Sign")
public class Sign extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sign() {
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
			this.parameters.put("5", this.permisson.get(0).get("clave"));
			this.parameters.put("3", this.hash((String)this.parameters.get("3")));
			this.paramsStatus();
			this.db.procedure(POST_USUARIO, this.parameters, null, false);
			this.request.setAttribute(GET_USUARIOS, response);
			this.db.close();
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
