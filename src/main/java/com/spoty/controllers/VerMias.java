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
 * Servlet implementation class VerMias
 */
@WebServlet("/VerMias")
public class VerMias extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerMias() {
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
		Map<String, Object> s = this.getSession();
		if(s.get("nombre")!=null) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("1", s.get("id_usuario"));
			this.request.setAttribute("usuario", this.getSession());
			this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
			this.request.setAttribute("canciones", this.db.procedure(GET_MIS_CANCIONES, p, null, true));
		}
		this.viewLoader(LOGIN_VIEW);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
