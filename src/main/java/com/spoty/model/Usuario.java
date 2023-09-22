package com.spoty.model;

import java.util.Objects;

public class Usuario {
	
	private int id_usuario;
	private String nombre;
	private String username;
	private String contrasena;
	private int activo;
	private String mail;
	private Permisos permisos;
	public Usuario() {
		super();
	}
	public Usuario(int id_usuario, String nombre, String username, String contrasena, int activo, String mail,
			Permisos permisos) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.username = username;
		this.contrasena = contrasena;
		this.activo = activo;
		this.mail = mail;
		this.permisos = permisos;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getActivo() {
		return activo;
	}
	public void setActivo(int activo) {
		this.activo = activo;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Permisos getPermisos() {
		return permisos;
	}
	public void setPermisos(Permisos permisos) {
		this.permisos = permisos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(activo, contrasena, id_usuario, mail, nombre, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return activo == other.activo && Objects.equals(contrasena, other.contrasena) && id_usuario == other.id_usuario
				&& Objects.equals(mail, other.mail) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", nombre=" + nombre + ", username=" + username + ", contrasena="
				+ contrasena + ", activo=" + activo + ", mail=" + mail + "]";
	}
	
	

}
