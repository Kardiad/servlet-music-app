package com.spoty.model;

import java.util.Objects;

public class Genero {

	public int id_genero;
	public String nombre;
	public Genero(int id_genero, String nombre) {
		super();
		this.id_genero = id_genero;
		this.nombre = nombre;
	}
	public Genero() {
		super();
	}
	public int getId_genero() {
		return id_genero;
	}
	public void setId_genero(int id_genero) {
		this.id_genero = id_genero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id_genero, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return id_genero == other.id_genero && Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Genero [id_genero=" + id_genero + ", nombre=" + nombre + "]";
	}
	
	
	
}
