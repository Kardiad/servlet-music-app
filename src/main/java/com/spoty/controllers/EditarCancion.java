package com.spoty.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

/**
 * Servlet implementation class EditarCancion
 */
@WebServlet("/EditarCancion")
public class EditarCancion extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarCancion() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.init(request, response);
		this.parameterHandle();
		this.paramsStatus();
		if(this.canIDoIt(PER_EDITAR_CANCION)) {
			this.db.procedure(POST_EDITAR_CANCION, this.parameters, null, false);
		}
		this.redirect(RED_INICIO);
	}

}
