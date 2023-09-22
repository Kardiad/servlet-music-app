package com.spoty.model;

import java.util.Objects;

public class Permisos {

	private int id;
	private String clave;
	private String rol;
	private String permisos;
	
	
	
	public Permisos() {
		super();
	}

	public Permisos(int id, String clave, String rol, String permisos) {
		super();
		this.id = id;
		this.clave = clave;
		this.rol = rol;
		this.permisos = permisos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getPermisos() {
		return permisos;
	}

	public void setPermisos(String permisos) {
		this.permisos = permisos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clave, id, permisos, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permisos other = (Permisos) obj;
		return Objects.equals(clave, other.clave) && id == other.id && Objects.equals(permisos, other.permisos)
				&& Objects.equals(rol, other.rol);
	}

	@Override
	public String toString() {
		return "Permisos [id=" + id + ", clave=" + clave + ", rol=" + rol + ", permisos=" + permisos + "]";
	}
	
	
	
}
