package com.cjhercen.gestion.proyectos.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosConsultas;
import com.cjhercen.gestion.proyectos.models.Proyecto;
import com.cjhercen.gestion.proyectos.utils.FechaUtils;

@Controller
public class PrincipalController {

private static Logger myLog = LoggerFactory.getLogger(PrincipalController.class);
	
	FechaUtils fechaUtils = new FechaUtils();
	
	@RequestMapping(value = "/principal/{nombre_proyecto}")
	public String proyectoPrincipal(Model modelo, @PathVariable(value = "nombre_proyecto") String nombre_proyecto,
			HttpSession sesion) {

		myLog.info("Entrada en proyectoPrincipal... ");
		
		//Se obtienen los datos del proyecto que se quiere modificar
		ProyectosConsultas consultas = new ProyectosConsultas();
		Proyecto proyectoPrincipal = new Proyecto();
		proyectoPrincipal = consultas.consultarProyectoPorNombre(nombre_proyecto);

		if(proyectoPrincipal != null) {
			//Se añade el proyecto a la sesión para controlar el proyecto activo
			sesion.setAttribute("proyectoActivo", proyectoPrincipal.getNombre_proyecto());
			myLog.info("Proyecto activo: " + proyectoPrincipal.getNombre_proyecto());

			//Se cargan los datos en la pantalla
			modelo.addAttribute("proyecto", proyectoPrincipal);
			
			myLog.info("Salida de proyectoPrincipal... OK ");
			return "principal";
		} else {
			myLog.info("Salida de proyectoPrincipal... FAIL");
			return "redirect:/";
		}	

	}
	
	@RequestMapping(value = "/principal/logout")
	public String cerrarProyectoActivo(HttpSession sesion) {

		myLog.info("Proyecto activo en sesión: " + sesion.getAttribute("proyectoActivo"));
		myLog.info("Borrando sesion... ");
		
		sesion.removeAttribute("proyectoActivo");
				
		return "redirect:/";
	
	}
	
}
