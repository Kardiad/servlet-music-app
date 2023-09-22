package com.spoty.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ImportCanciones
 */
@MultipartConfig
@WebServlet("/ImportCanciones")
public class ImportCanciones extends MainController {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportCanciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.init(request, response);
		this.parameterHandle();
		Part json = this.request.getPart("json");
		String j = "";
		for(byte a : json.getInputStream().readAllBytes()) {
			j += (char)a;
		}
		List<Map<String, Object>> deserialized = this.deserialized(this.utf8_conversion(j));
		for(Map<String,Object> o : deserialized) {
			System.out.println(this.conversionToDBHelper(o));
			this.db.procedure(POST_IMPORT_CANCIONES, o, null, false);
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
