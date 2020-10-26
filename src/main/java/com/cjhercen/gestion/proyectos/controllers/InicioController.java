package com.cjhercen.gestion.proyectos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjhercen.gestion.proyectos.dao.proyectos.ProyectosConsultas;
import com.cjhercen.gestion.proyectos.models.Proyecto;

/**
 * Controlador Inicial que lleva a la página de inicio de la aplicación
 * @author carlosCente
 * @version 23-10-2020
 */
@Controller
public class InicioController {
	
	private static Logger myLog = LoggerFactory.getLogger(InicioController.class);
	
	ProyectosConsultas proyectoConsultas = new ProyectosConsultas();
	
	@RequestMapping(value = {"/","/inicio","/index"})
	public String inicio(Model modelo) {

		myLog.info("Entrando en Inicio Controller... inicio");
		
		//Controlara si hay o no proyectos para modificar el comportamiento de la tabla
		Boolean mostrarMensajeNoProyectos = true;
		
		//Se obtiene la lista de los proyectos existentes
		List<Proyecto> resultadoConsulta = proyectoConsultas.consultarProyectos();

		if(resultadoConsulta.size()>0) {
			mostrarMensajeNoProyectos = false;
			myLog.info("Si existen proyectos, no se mostrará el mensaje en la tabla indicándolo");
		} else {
			myLog.info("No existen proyectos, se mostrará el mensaje en la tabla indicándolo");
		}
		//Se guardan los nombres de los proyectos para cargar la tabla
		List<String> nombresProyectos = new ArrayList<String>();
		
		for(int i=0; i < resultadoConsulta.size(); i++) {
			
			String nombreProyecto = resultadoConsulta.get(i).getNombre_proyecto();
			nombresProyectos.add(nombreProyecto);
			
		}
		
		/*Se obtienen los proyectos con su respectiva fecha de ultima modificacion para rellenar
		los datos de los que se han utilizado por ultima vez*/
		
		List<Proyecto> proyectosUtilizadosUlt = proyectoConsultas.consultarProyectosUltimaModif();
		if(proyectosUtilizadosUlt.size()>5) {
			proyectosUtilizadosUlt = proyectosUtilizadosUlt.subList(0, 6);
		}
		
		modelo.addAttribute("listaProyectosUtilizados", proyectosUtilizadosUlt);
		modelo.addAttribute("listaNombresProyectos", nombresProyectos);
		modelo.addAttribute("mostrarMensajeProyectos", mostrarMensajeNoProyectos);
		
		myLog.info("DATOS ENVIADOS A LA PLANTILLA: " + modelo.toString());

		myLog.info("Saliendo de Inicio Controller... inicio");
		
		return "inicio";
	}

}
