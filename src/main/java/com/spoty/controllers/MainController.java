package com.spoty.controllers;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.spoty.dao.DBHelper;
import com.spoty.enviroment.Enviroment;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public abstract class MainController extends HttpServlet implements Enviroment {


	private static final long serialVersionUID = 1L;
	
	protected List<Map<String, Object>> permisson;
	protected Map<String, Object> parameters;
	protected DBHelper db;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Argon2 argon;
	protected URL asker;
	protected Part file;
	
	public MainController() {
		super();
		this.db = new DBHelper();
		this.parameters = new HashMap<String, Object>();
		this.permisson = db.procedure(GET_PERMISOS, null, null, true);
		this.argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
	}
	
	protected void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		if(this.session.getAttribute("intentos")==null) {
			this.session.setAttribute("intentos", 0);			
		}
		//this.response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
	}
	
	protected boolean validFields(int expectedData) {
		int total = 0;
		for(String o : this.parameters.keySet()) {
			if(this.parameters.get(o)!=null) {
				total ++;
			}
		}
		return total == expectedData;
	}
	
	protected void parameterHandle() throws IOException, ServletException{
		int i = 1;
		for(String a : this.request.getParameterMap().keySet()) {
			String param = (String)this.request.getParameter(a);
			String typeOf = this.typeOf(param);
			if(typeOf.equals("String")){
				//System.out.println("añadiendo como String");
				this.parameters.put(""+i, new String(param.getBytes(Charset.forName("ISO-8859-1")), CHARSET));
			}
			if(typeOf.equals("Integer")) {
				//System.out.println("añadiendo como Integer");
				this.parameters.put(""+i, Integer.parseInt(param));
			}
			if(typeOf.equals("")) {
				//System.out.println("No se ha detectado tipo para "+param);
			}
			i++;
		}
	}
	
	protected void fileHandle(String field) {
		try {
			this.file = this.request.getPart(field);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void fileGarbage() {
		this.file = null;
	}
	
	protected String utf8_conversion(String string) throws UnsupportedEncodingException {
		String sanitized = (new String(string.getBytes(Charset.forName("ISO-8859-1")), CHARSET));
		return sanitized.replace('?', ' ');
	}
	
	protected Map<String,Object> conversionToDBHelper(Map<String, Object> object) throws UnsupportedEncodingException{
		int i = 1;
		Map<String,Object> l = new HashMap<String,Object>();
		for(String a : object.keySet()) {
			String param = object.get(a).toString();
			String typeOf = this.typeOf(param);
			if(typeOf.equals("String")){
				//System.out.println("añadiendo como String");
				l.put(""+i, new String(param.getBytes(Charset.forName("ISO-8859-1")), CHARSET));
			}
			if(typeOf.equals("Integer")) {
				//System.out.println("añadiendo como Integer");
				l.put(""+i, Integer.parseInt(param));
			}
			if(typeOf.equals("Double")) {
				l.put(""+i, Integer.parseInt((String) param.subSequence(0, param.indexOf('.'))));
			}
			if(typeOf.equals("")) {
				//System.out.println("No se ha detectado tipo para "+param);
			}
			i++;
		}
		return l;
	}
	
	protected String typeOf(String b) {
		if(b.matches("^\\d+$")) return "Integer";
		if(b.matches("^\\d+\\.\\d+$")) return "Double";
		return "String";
	}

	
	protected void viewLoader(String path) throws ServletException, IOException {
		this.request.getRequestDispatcher(path).forward(this.request, this.response);
	}
	
	protected void redirect(String path) throws IOException {
		this.response.sendRedirect(path);
	}
	
	protected String hash (String pass) {
		return this.argon.hash(1, 1024, 1, pass);
	}
	
	protected boolean verify(String hashedPass, String nohass) {
		return this.argon.verify(hashedPass, nohass);
	}
	
	protected void addToSession(Map<String, Object> map) {
		for(String o : map.keySet()) {
			this.session.setAttribute(o, map.get(o));
		}
	}
	//esto se puede hacer con invalidate
	protected void removeToSession(Map<String, Object> map) {
		for(String o : map.keySet()) {
			this.session.removeAttribute(o);
		}
	}
	
	protected boolean sessionDone() {
		int i = 0;
		for(Object o : this.getSession().keySet()) {
			i++;
		}
		return i>1;
	}
	
	protected Map<String, Object> getSession() {
		Enumeration<String> attributes = this.session.getAttributeNames();
		Map<String, Object> session = new HashMap<String, Object>();
		while(attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			session.put(attribute, this.session.getAttribute(attribute));
		}
		return session;
	}
	
	protected void sessionStatus() {
		Enumeration<String> attributes = this.session.getAttributeNames();
		while(attributes.hasMoreElements()) {
			String attribute = attributes.nextElement();
			System.out.println("["+attribute+"]="+this.session.getAttribute(attribute));
		}
	}
	
	protected boolean canIDoIt(String requestPermission) {
		String permisssion = requestPermission.toUpperCase();
		boolean permiso = false;
		for(String option : ((String) this.getSession().get("permisos")).split(",")) {
			if(permisssion.equals(option.trim())) {
				permiso = true;
			}
		}
		return permiso;
	}
	
	protected String getURIRedirect(String rol) {
		if(rol.equals(ADMIN)) return RED_INICIO_ADMIN;
		return RED_INICIO;
	}
	
	protected String getViewLoad(String rol) {
		if(rol.equals(ADMIN)) return ADMIN_VIEW;
		return LOGIN_VIEW;
	}
	
	protected void settingView(String rol) {
		if(rol.equals(ADMIN)) {
			this.request.setAttribute("usuarios", this.db.procedure(GET_USUARIOS, null, null, true));
		}
		if(rol.equals(USER)) {
			this.request.setAttribute("canciones", this.db.procedure(GET_CANCIONES, null, null, true));
		}
	}
	
	protected String userType(String key) {
		String type = "";
		for(Map<String, Object> p : this.permisson) {
			if(key.equals(p.get("clave"))) {
				type = ""+p.get("rol");
			}
		}
		return type;
	}
	
	protected void generateFile(String path, String content) {
		File file = new File(path);
		try {
			FileWriter f = new FileWriter(file);
			f.write(content);
			f.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void paramsStatus() {
		for(String a : this.parameters.keySet()) {
			System.out.println("["+a+"]="+this.parameters.get(a));
		}
	}
	
	protected Map<String,Object> getUser(Map<String, Object> searchField){
		List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
		if(searchField.get("1")!=null) {
			user = this.db.procedure(GET_USUARIO, searchField, null, true);			
		}
		if(user.size()==0) {
			return null;
		}else {
			try {
				return user.get(0);				
			}catch(Exception e) {
				return null;
			}
		}
	}
	
	protected void downloadData(String path, String filename) {
		this.response.addHeader("Content-Type", "application/octet-strem");
		this.response.addHeader("Content-Type", "application/json");
		this.response.addHeader("Content-Transfer-Encoding", "binary");
		this.response.addHeader("Content-Disposition", "attachment; filename="+filename);
		try {
			this.response.flushBuffer();
			String content = "";
			Scanner r = new Scanner(new File(path));
			while(r.hasNext()) {
				content+= r.nextLine();
			}
			PrintWriter o = this.response.getWriter();
			o.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String procedureToJson(List<Map<String, Object>> list) {
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	protected List<Map<String, Object>> deserialized(String j){
		Gson gson = new Gson();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		return gson.fromJson(j, list.getClass()); 
	}
	
	
	
	protected void dbClose() {
		this.db.close();
	}
}
