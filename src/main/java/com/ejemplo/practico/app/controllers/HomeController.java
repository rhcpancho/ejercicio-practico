package com.ejemplo.practico.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/index", "/", "", "home" })
	public String index(Model model) {
		model.addAttribute("titulo", "Ejercicio Práctico");
		return "index";
	}
}
