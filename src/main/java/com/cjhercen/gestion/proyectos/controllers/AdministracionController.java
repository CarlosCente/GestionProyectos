package com.cjhercen.gestion.proyectos.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhercen.gestion.proyectos.dao.administracion.AdministracionConsultas;
import com.cjhercen.gestion.proyectos.models.AdministracionKilometraje;
import com.cjhercen.gestion.proyectos.utils.FechaUtils;

@Controller
public class AdministracionController {

	private static Logger myLog = LoggerFactory.getLogger(AdministracionController.class);
	
	AdministracionConsultas consultasAdm = new AdministracionConsultas();
	
	FechaUtils fechaUtils = new FechaUtils();
	
	@RequestMapping(value = "/administracion")
	public String administracion(Model modelo, HttpSession sesion) {

		String nombreProyecto = (String) sesion.getAttribute("proyectoActivo");
		myLog.info("Proyecto activo en administracion: " + nombreProyecto);
		myLog.info("Entrada en administracion... ");
		
		myLog.info("Entrando en AdministracionController... inicio");
		
		//Se obtiene la lista de los proyectos existentes
		List<AdministracionKilometraje> listaKilom = consultasAdm.consultarKilometrajes();
				
		//Se añade el objeto para poder crear proyectos
		AdministracionKilometraje kilometraje = new AdministracionKilometraje();
		
		modelo.addAttribute("kilometraje", kilometraje);
		modelo.addAttribute("listakm", listaKilom);		
		//Se añade el proyecto activo que hay en sesion para poder volver a la pantalla del proyecto
		modelo.addAttribute("proyectoActivo", nombreProyecto);
		
		myLog.info("DATOS ENVIADOS A LA PLANTILLA: " + modelo.toString());

		myLog.info("Saliendo de Administracion Controller... inicio");
		
		return "administracion";

	}
	
	@RequestMapping(value = "/administracionparametros")
	public String administracionParametros(Model modelo, HttpSession sesion) {

		String nombreProyecto = (String) sesion.getAttribute("proyectoActivo");
		myLog.info("Proyecto activo en administracion: " + nombreProyecto);
		myLog.info("Entrada en administracion... ");
		
		//Se añade el proyecto activo que hay en sesion para poder volver a la pantalla del proyecto
		modelo.addAttribute("proyectoActivo", nombreProyecto);
		
		return "administracionparametros";

	}
	
}
