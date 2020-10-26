package com.cjhercen.gestion.proyectos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosBorrado;

@Controller
public class ProyectoController {

	private static Logger myLog = LoggerFactory.getLogger(ProyectoController.class);
	
	@RequestMapping(value = "/proyectos/eliminar/{proyecto}")
	public String eliminarIncidencia(
			@PathVariable(value = "proyecto") String proyecto) {

		ProyectosBorrado borrados = new ProyectosBorrado();
		borrados.borrarProyectoPorNombre(proyecto);
		
		myLog.info("Se ha borrado correctamente la incidencia " + proyecto);

		return "redirect:/";
	}
	
	
}
