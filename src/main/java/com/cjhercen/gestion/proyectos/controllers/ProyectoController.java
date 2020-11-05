package com.cjhercen.gestion.proyectos.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosAltas;
import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosBorrado;
import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosConsultas;
import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosModificaciones;
import com.cjhercen.gestion.proyectos.models.Proyecto;
import com.cjhercen.gestion.proyectos.utils.FechaUtils;

@Controller
public class ProyectoController {

	private static Logger myLog = LoggerFactory.getLogger(ProyectoController.class);
	
	FechaUtils fechaUtils = new FechaUtils();
	
	@RequestMapping(value = "/proyectos/eliminar")
	public String eliminarProyecto(
			@RequestParam(value = "nombre_proyecto") String proyecto) {

		if(proyecto != null && proyecto.length() >=16) {
			proyecto = proyecto.substring(0, proyecto.length()-4);
			String proyectoPartes[] = proyecto.split(">");
			proyecto = proyectoPartes[1];
		}
		ProyectosBorrado borrados = new ProyectosBorrado();
		borrados.borrarProyectoPorNombre(proyecto);
		
		myLog.info("Se ha borrado correctamente la incidencia " + proyecto);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/proyectos/insertar", method = RequestMethod.POST)
	public String insertarProyecto(@Valid Proyecto proyecto, BindingResult result, Model model,
			SessionStatus status) {
	
		
		ProyectosAltas altas = new ProyectosAltas();
		Proyecto proyectoNuevo = new Proyecto();
		
		if(proyecto != null) {
			proyectoNuevo.setNombre_proyecto(proyecto.getNombre_proyecto());
			proyectoNuevo.setCreateAt(fechaUtils.obtenerFechaActual());
			proyectoNuevo.setUltima_modificacion(fechaUtils.obtenerFechaActual());
			proyectoNuevo.setDescripcion(proyecto.getDescripcion());
			altas.insertarProyecto(proyectoNuevo);
		}
		
		myLog.info("Se ha insertado correctamente el nuevo proyecto: " + 
				"Nombre: " + proyecto.getNombre_proyecto() + ", Descripcion: " + proyecto.getDescripcion());

		return "redirect:/";
	}
	
	@RequestMapping(value = "/proyectos/modificar/{nombre_proyecto}")
	public String modificarProyecto(Model modelo, @PathVariable(value = "nombre_proyecto") String nombre_proyecto) {

		//Se obtienen los datos del proyecto que se quiere modificar
		ProyectosConsultas consultas = new ProyectosConsultas();
		Proyecto proyectoCarga = new Proyecto();
		proyectoCarga = consultas.consultarProyectoPorNombre(nombre_proyecto);

		if(proyectoCarga != null) {
			//Se cargan los datos en la pantalla
			modelo.addAttribute("proyecto", proyectoCarga);
			return "modificarproyecto";
		} else {
			return "redirect:/";
		}	

	}
	
	@RequestMapping(value = "/proyectos/modificacion",  method = RequestMethod.POST)
	public String realizarModificacion(@Valid Proyecto proyecto, BindingResult result,
			SessionStatus status, RedirectAttributes flash) {
		
		//Se obtienen los datos actuales del proyecto en BD
		int idProyectoBD = proyecto.getId_proyecto();
		ProyectosConsultas consultas = new ProyectosConsultas();
		Proyecto proyectoBD = consultas.consultarProyectoPorId(idProyectoBD);
		
		ProyectosModificaciones modificaciones = new ProyectosModificaciones();
		
		if(proyectoBD != null) {
			Proyecto proyectoNuevo = new Proyecto();

			//DATOS BD del proyecto
			String nombreBD = proyectoBD.getNombre_proyecto();
			String descripcionBD = proyectoBD.getDescripcion();
			//DATOS que se pasan por el formulario
			String nombreNuevo = proyecto.getNombre_proyecto();
			String descripcionNueva = proyecto.getDescripcion();
			
			if(!nombreBD.equalsIgnoreCase(nombreNuevo) || !descripcionBD.equalsIgnoreCase(descripcionNueva)) {
				proyectoNuevo.setNombre_proyecto(nombreNuevo);
				proyectoNuevo.setCreateAt(proyectoBD.getCreateAt());
				proyectoNuevo.setUltima_modificacion(fechaUtils.obtenerFechaActual());
				proyectoNuevo.setDescripcion(descripcionNueva);
				
				modificaciones.modificarProyecto(proyectoNuevo, idProyectoBD);
				myLog.info("Hay cambios, se ha modificado el proyecto");
				flash.addFlashAttribute("tipo", "Información");
				flash.addFlashAttribute("message", "Se ha modificado el proyecto correctamente");
				return "redirect:/proyectos/modificar/" + proyectoNuevo.getNombre_proyecto();

			} else {
				myLog.info("No hay cambios, no se ha modificado el proyecto");
				flash.addFlashAttribute("tipo", "Información");
				flash.addFlashAttribute("message", "No hay cambios en los datos del proyecto");
				return "redirect:/proyectos/modificar/" + proyectoBD.getNombre_proyecto();
			}
				
		}
		
		return "modificarproyecto";

	}
	
	
}
