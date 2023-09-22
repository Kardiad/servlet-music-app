package com.spoty.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.spoty.enviroment.Enviroment;

public class DBHelper implements Enviroment{
	private Connection db;
	private PreparedStatement stmt;
	private CallableStatement cstmt;
	
	public DBHelper() {
		try {
			Class.forName(DRIVER);
			this.db = DriverManager.getConnection(CONEXION, USUARIO, CONTRASENA);
			System.out.println("DB conectada");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problemas de SQL");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra el driver");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> procedure(String sql, Map<String, Object> params, String[] paramsOut, boolean select){
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			this.cstmt = this.db.prepareCall(sql);
			int base = 0;
			// Hacer setters de ? : se tiene porque estará en el procedimento
			if(sql.contains("?") && !params.isEmpty() && params!=null) {
				Object[] keys = params.keySet().toArray();
				Object[] values = params.values().toArray();	
				for(int i = 0, j=1; i<keys.length; i++, j++) {
					//System.out.println("El parámetro bindeado en "+j+" es tipo "+((Object) values[i]).getClass().getSimpleName()+" con valor "+values[i]);
					this.cstmtTypeIn(j, ((Object)values[i]).getClass().getSimpleName() ,values[i]);
					base++;
				}				
			}
			// Hacer getters de info : se tiene porque está en el map
			if(paramsOut!=null && paramsOut.length>0) {
				for(int i = 0, j=1; i<paramsOut.length; i++, j++) {
					this.cstmtTypeOut(base+j, paramsOut[i]);
				}
			}
			if(select) {
				list = this.launchSelect(paramsOut, base);
			}else {
				this.cstmt.execute();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error del procedimiento");
			e.printStackTrace();
		}
		return list;
	}
	
	private List<Map<String, Object>> launchSelect(String[] paramsOut, int base) throws SQLException {
		/*if(this.cstmt.execute()) {
			System.out.println("Todo correcto, falta lo último");
			
		}*/
		List<Map<String, Object>> list = new ArrayList<>();
		boolean hasResults = this.cstmt.execute();
		  do {
              if (hasResults) {
                  ResultSet rs = this.cstmt.getResultSet();
                  ResultSetMetaData meta = rs.getMetaData();
                  int columns = meta.getColumnCount();
                  // Trabajar con el resultado actual (rs)
                  while (rs.next()) {
                	  Map<String, Object> row = new HashMap<>();
	      				for(int i = 1; i<=columns; i++) {	      			
	      					if(meta.getColumnTypeName(i).contains("VARCHAR") || meta.getColumnTypeName(i).contains("TEXT")) {
	      						row.put(meta.getColumnName(i), rs.getString(i));
	      						//System.out.println(meta.getColumnName(i)+" : "+rs.getString(i));
	      					}
	      					if(meta.getColumnTypeName(i).contains("INT")) {
	      						row.put(meta.getColumnName(i), rs.getInt(i));
	      						//System.out.println(meta.getColumnName(i)+" : "+rs.getInt(i));
	      					}
	      					if(meta.getColumnTypeName(i).contains("DOUBLE")) {
	      						row.put(meta.getColumnName(i), rs.getDouble(i));
	      						//System.out.println(meta.getColumnName(i)+" : "+rs.getDouble(i));
	      					}
	      				}
      					list.add(row);
                  }
                  rs.close();
              }
              hasResults = cstmt.getMoreResults();
          } while (hasResults);
		  return list; 
	}
	
	private void cstmtTypeOut(int i, String type) throws SQLException {
		if(type.contains("String")) {
			this.cstmt.registerOutParameter(i, Types.VARCHAR);
		}
		if(type.contains("Integer")) {
			this.cstmt.registerOutParameter(i, Types.INTEGER);
		}
		if(type.contains("Double")) {
			this.cstmt.registerOutParameter(i, Types.DOUBLE);
		}
	}
	
	private void cstmtTypeIn(int i, String type, Object val) throws SQLException {
		if(type.contains("String")) {
			this.cstmt.setString(i, val.toString());
		}
		if(type.contains("Integer")) {
			this.cstmt.setInt(i, Integer.parseInt(val.toString()));
		}
		if(type.contains("Double")) {
			this.cstmt.setDouble(i, Double.parseDouble(val.toString()));
		}
		//Faltaría añadir movida aquí
	}
	
	public List<Map<String, Object>> select(String sql){
		
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			Statement stmt = this.db.createStatement();
			ResultSet data = stmt.executeQuery(sql);
			ResultSetMetaData meta = data.getMetaData();
			int columns = meta.getColumnCount();
			while(data.next()) {
				Map<String, Object> row = new HashMap<>();
				for(int i = 1; i<=columns; i++) {
					row.put(meta.getColumnName(i), data.getObject(i));
				}
				list.add(row);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Consulta fallida");
			e.printStackTrace();
		}
		return list;
	}
	
	private String sqlSELECTBuilder() {
		return "";
	}
	
	public void insert(String tabla, Map<String, Object> params) {
		//crear el insert
		String sql = this.sqlINSERTBuilder(tabla, params);
		System.out.println(sql);
		Object[] valores = params.values().toArray();
		try {
			this.stmt = this.db.prepareStatement(sql);
			for(int i=0; i<valores.length; i++) {
				this.typeAsing(i+1, ((Object)valores[i]).getClass().getSimpleName(), valores[i]);
			}
			int files = this.stmt.executeUpdate();
			System.out.println("Se ha insertado : "+files+" filas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private String sqlINSERTBuilder(String tabla, Map<String, Object> params) {
		String sql = "INSERT INTO "+tabla+"( @fields ) values ( @values );";
		String fields = "";
		String values = "";
		for(String field : params.keySet()) {
			fields += field+",";
			values += "? ,";
		}
		fields = fields.substring(0, fields.length()-1);
		values = values.substring(0, values.length()-1);
		sql = sql.replace("@fields", fields).replace("@values", values);
		return sql;
	}
	
	public void update(String tabla, Map<String, Object>params, String[] where, Object[] whereValues) {
		String sql = this.sqlUPDATEBuilder(tabla, params, where);
		System.out.println(sql);
		Object[] values = params.values().toArray();
		try {
			this.stmt = this.db.prepareStatement(sql);
			//anadir valores al set
			int cont = 1;
			for(int i = 0; i<values.length; i++) {
				this.typeAsing(cont, ((Object)values[i]).getClass().getSimpleName(), values[i]);
				cont++;
			}
			for(int i = 0; i<whereValues.length; i++) {
				this.typeAsing(cont+i, ((Object)whereValues[i]).getClass().getSimpleName(), whereValues[i]);
			}
			//anadir valores al where
			int files = this.stmt.executeUpdate();
			System.out.println("Se han actualizado: "+files+" filas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("La consulta falló");
			e.printStackTrace();
		}
	}

	private String sqlUPDATEBuilder(String tabla, Map<String, Object> params, String[]where) {
		String sql = "UPDATE "+tabla+" SET @updateable WHERE @whereable;";
		String updateable = "";
		Object[] values = params.values().toArray();
		Object[] keys = params.keySet().toArray();
		for(int i=0; i<values.length; i++) {
			updateable += keys[i]+" = ?,";
		}
		updateable = updateable.substring(0, updateable.length()-1);
		sql = sql.replace("@updateable", updateable).replace("@whereable", String.join(",", where));
		// Estructura del
		return sql;
	}
	
	public void delete(String tabla, String where, Map<String, Object> fields) {
		String sql = "DELETE FROM "+tabla+" WHERE @whereable ";
		sql = sql.replace("@whereable", where);
		try {
			this.stmt = this.db.prepareStatement(sql);
			Object[] values = fields.values().toArray();
			Object[] keys = fields.values().toArray();
			for(int i = 0, j=1; i<values.length; i++, j++) {
				this.typeAsing(j, ((Object)values[i]).getClass().getSimpleName(), values[i]);
			}
			int afectedFiles = this.stmt.executeUpdate();
			System.out.println("Se han borrado "+afectedFiles+" filas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("La consulta falló");
			e.printStackTrace();
		}
	}
	
	private CallableStatement typeAsing(int index, String type, Object value, CallableStatement stmt) throws SQLException {
		if(type.equals("String")) {
			stmt.setString(index, value.toString());
		}
		if(type.equals("Integer")) {
			stmt.setInt(index, Integer.parseInt(value.toString()));
		}
		return stmt;
	}
	
	
	private void typeAsing(int index, String type, Object value) throws SQLException {
		if(type.equals("String")) {
			this.stmt.setString(index, value.toString());
		}
		if(type.equals("Integer")) {
			this.stmt.setInt(index, Integer.parseInt(value.toString()));
		}
		//faltaría añadir algunos tipos más
	}
	

	public void close() {
		try {
			this.db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
