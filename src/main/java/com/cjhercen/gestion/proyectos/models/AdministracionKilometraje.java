package com.cjhercen.gestion.proyectos.models;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AdministracionKilometraje {

	private int id_reg;
	
	@NotNull
	@Length(min=3, max=150)
	private String localidad;
	
	@NotNull
	@Length(min=3, max=150)
	private String provincia;
	
	@NotNull
	private double kilometros;

	public int getId_reg() {
		return id_reg;
	}

	public void setId_reg(int id_reg) {
		this.id_reg = id_reg;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public double getKilometros() {
		return kilometros;
	}

	public void setKilometros(double kilometraje) {
		this.kilometros = kilometraje;
	}
	
	
}
