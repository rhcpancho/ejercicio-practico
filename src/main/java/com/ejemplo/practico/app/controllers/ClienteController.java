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

import com.ejemplo.practico.app.models.entity.Cliente;
import com.ejemplo.practico.app.models.service.IClienteService;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public String ver(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "/clientes/lista";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Crear Cliente");
		return "/clientes/form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear Cliente");
			return "/clientes/form";
		}
		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/clientes/lista";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findById(id);
		} else {
			return "redirect:/clientes/lista";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Editar Cliente");
		return "/clientes/form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id) {
		if (id > 0) {
			clienteService.deleteById(id);
		}
		return "redirect:/clientes/lista";
	}

	@RequestMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findById(id);
		} else {
			return "redirect:/clientes/lista";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Datos del Cliente");
		return "/clientes/ver";
	}
}
