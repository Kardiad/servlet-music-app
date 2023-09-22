package com.spoty.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spoty.enviroment.Enviroment;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends MainController implements Enviroment {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//validar usuario para luego redirigir y meter todo en sesion
		this.init(request, response);
		this.parameterHandle();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("1", (String)this.parameters.get("1"));
		Map<String, Object> usuario = null;
		String redi = RED_INICIO;
		String permiso = "";
		boolean existe = false;
		usuario = this.getUser(param);
		if(usuario !=null) {
			existe = true;
			permiso = this.userType((String)usuario.get("clave"));
			redi = this.getURIRedirect(permiso);
		}
		if((int)this.getSession().get("intentos")>2) {
			redi = "https://www.google.es/?gws_rd=ssl";	
			this.session.setAttribute("intentos", 0);
		}
		if(!existe && !this.sessionDone()) {
			this.session.setAttribute("intentos", (int)this.session.getAttribute("intentos")+1);
		}
		if(!existe) {
			this.redirect(redi);
		}
		if(existe && this.verify((String)usuario.get("contrasena"),(String)this.parameters.get("2"))) {
			this.session.setAttribute("intentos", 0);
			this.addToSession(usuario);
			Map<String, Object> id_usuario = new HashMap<String, Object>();
			param.put("1", this.getSession().get("id_usuario"));
			this.request.setAttribute("usuario", usuario);
			this.request.setAttribute("categorias", this.db.procedure(GET_GENEROS, null, null, true));
			this.request.setAttribute("canciones", this.db.procedure(GET_CANCIONES, null, null, true));
			this.request.setAttribute("favs", this.db.procedure(GET_FAVS, id_usuario, null, true));
			this.sessionStatus();
			this.settingView(permiso);
			this.viewLoader( this.getViewLoad(permiso));	
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
