package com.ejemplo.practico.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplo.practico.app.models.entity.Articulo;
import com.ejemplo.practico.app.models.service.IArticuloService;

@Controller
@SessionAttributes("articulo")
@RequestMapping("/articulos")
public class ArticuloController {

	@Autowired
	private IArticuloService articuloService;

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String ver(Model model) {
		model.addAttribute("titulo", "Listado de artículos");
		model.addAttribute("articulos", articuloService.findAll());
		return "/articulos/lista";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Articulo articulo = new Articulo();
		model.put("articulo", articulo);
		model.put("titulo", "Crear Artículo");
		return "/articulos/form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Articulo articulo, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Artículo");
			return "/articulo/form";
		}
		articuloService.save(articulo);
		status.setComplete();
		return "redirect:/articulos/lista";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Articulo articulo = null;
		if (id > 0) {
			articulo = articuloService.findById(id);
		} else {
			return "redirect:/articulos/lista";
		}
		model.put("articulo", articulo);
		model.put("titulo", "Editar Artículo");
		return "/articulos/form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			articuloService.deleteById(id);
		}
		return "redirect:/articulos/lista";
	}
}
