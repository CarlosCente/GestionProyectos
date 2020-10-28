package com.cjhercen.gestion.proyectos.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosBorrado;

@Controller
public class ProyectoController {

	private static Logger myLog = LoggerFactory.getLogger(ProyectoController.class);
	
	@RequestMapping(value = "/proyectos/eliminar")
	public String eliminarIncidencia(
			@RequestParam(value = "nombre_proyecto") String proyecto) {

		if(proyecto != null && proyecto.length() >=16) {
			proyecto = proyecto.substring(0, proyecto.length()-4);
			proyecto = proyecto.substring(12);
		}
		
		ProyectosBorrado borrados = new ProyectosBorrado();
		borrados.borrarProyectoPorNombre(proyecto);
		
		myLog.info("Se ha borrado correctamente la incidencia " + proyecto);

		return "redirect:/";
	}
	
	
}
