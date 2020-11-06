package com.cjhercen.gestion.proyectos.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhercen.gestion.proyectos.utils.FechaUtils;

@Controller
public class AdministracionController {

	private static Logger myLog = LoggerFactory.getLogger(AdministracionController.class);
	
	FechaUtils fechaUtils = new FechaUtils();
	
	@RequestMapping(value = "/administracion")
	public String administracion(Model modelo, HttpSession sesion) {

		String nombreProyecto = (String) sesion.getAttribute("proyectoActivo");
		myLog.info("Proyecto activo en administracion: " + nombreProyecto);
		myLog.info("Entrada en administracion... ");
		
		//Se añade el proyecto activo que hay en sesion para poder volver a la pantalla del proyecto
		modelo.addAttribute("proyectoActivo", nombreProyecto);
		
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
