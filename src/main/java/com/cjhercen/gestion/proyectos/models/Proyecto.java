package com.cjhercen.gestion.proyectos.models;

import java.sql.Timestamp;

public class Proyecto {

	private int id_proyecto;
	private String nombre_proyecto;
	private String createAt;
	private String ultima_modificacion;
	private String descripcion;

	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getNombre_proyecto() {
		return nombre_proyecto;
	}

	public void setNombre_proyecto(String nombre_proyecto) {
		this.nombre_proyecto = nombre_proyecto;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUltima_modificacion() {
		return ultima_modificacion;
	}

	public void setUltima_modificacion(String ultima_modificacion) {
		this.ultima_modificacion = ultima_modificacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
