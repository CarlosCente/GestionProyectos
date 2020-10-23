package com.cjhercen.gestion.proyectos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador Inicial que lleva a la página de inicio de la aplicación
 * @author carlosCente
 * @version 23-10-2020
 */
@Controller
public class InicioController {

	@RequestMapping(value = {"/","/inicio","/index"})
	public String inicio(Model modelo) {
		return "inicio";
	}
	
}
