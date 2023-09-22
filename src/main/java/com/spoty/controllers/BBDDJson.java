package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class BBDDJson
 */
@WebServlet("/BBDDJson")
public class BBDDJson extends MainController implements Enviroment {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BBDDJson() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.init(request, response);
		if(this.getSession().get("nombre")!=null) {
			this.generateFile(BASE_PATH+ADMIN_PATH+"Canciones.json", this.utf8_conversion(this.procedureToJson(this.db.procedure(GET_CANCIONES, null, null, true))));
			this.generateFile(BASE_PATH+ADMIN_PATH+"Usuarios.json", this.utf8_conversion(this.procedureToJson(this.db.procedure(GET_USUARIOS, null, null, true))));
			this.redirect(RED_INICIO_ADMIN);
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
