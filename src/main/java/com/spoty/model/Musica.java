package com.spoty.model;

import java.util.Objects;

public class Musica {

	private int id;
	private String titulo;
	private String grupo;
	private String url_path;
	private int duracion;
	private int id_usuario;
	private Genero genero;
	
	public Musica() {}

	public Musica(int id, String titulo, String grupo, String url_path, int duracion, int id_usuario, Genero genero) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.grupo = grupo;
		this.url_path = url_path;
		this.duracion = duracion;
		this.id_usuario = id_usuario;
		this.genero = genero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getUrl_path() {
		return url_path;
	}

	public void setUrl_path(String url_path) {
		this.url_path = url_path;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, grupo, id, id_usuario, titulo, url_path);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Musica other = (Musica) obj;
		return duracion == other.duracion && Objects.equals(grupo, other.grupo) && id == other.id
				&& id_usuario == other.id_usuario && Objects.equals(titulo, other.titulo)
				&& Objects.equals(url_path, other.url_path);
	}

	@Override
	public String toString() {
		return "Musica [id=" + id + ", titulo=" + titulo + ", grupo=" + grupo + ", url_path=" + url_path + ", duracion="
				+ duracion + ", id_usuario=" + id_usuario + "]";
	}
	
	
	
	
	
}
