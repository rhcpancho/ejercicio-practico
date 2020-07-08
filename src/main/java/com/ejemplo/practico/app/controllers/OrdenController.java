package com.ejemplo.practico.app.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.practico.app.models.entity.Articulo;
import com.ejemplo.practico.app.models.entity.Cliente;
import com.ejemplo.practico.app.models.entity.DetalleOrden;
import com.ejemplo.practico.app.models.entity.Orden;
import com.ejemplo.practico.app.models.service.IArticuloService;
import com.ejemplo.practico.app.models.service.IClienteService;
import com.ejemplo.practico.app.models.service.IOrdenService;

@Controller
@RequestMapping("/ordenes")
@SessionAttributes("orden")
public class OrdenController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IOrdenService ordenService;

	@Autowired
	private IArticuloService articuloService;

	@GetMapping("/form/{idCliente}")
	public String crear(@PathVariable(value = "idCliente") Long idCliente, Map<String, Object> model,
			RedirectAttributes flash) {
		Cliente cliente = clienteService.findById(idCliente);
		if (cliente == null) {
			flash.addAttribute("error", "El cliente no existe.");
			return "redirect:/clientes/ver/" + idCliente;
		}
		Orden orden = new Orden();
		orden.setCliente(cliente);

		model.put("titulo", "Crear Orden");
		model.put("orden", orden);
		return "/ordenes/form";
	}

	@GetMapping(value = "/cargar-productos/{term}", produces = { "application/json" })
	public @ResponseBody List<Articulo> cargarArticulos(@PathVariable String term) {
		return clienteService.findByNombre(term);
	}

	@PostMapping("/form")
	public String guardar(Orden orden, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
			@RequestParam(name = "cantidad[]", required = false) Integer[] cantidad, RedirectAttributes flash,
			SessionStatus status) {

		for (int i = 0; i < itemId.length; i++) {
			Articulo articulo = articuloService.findById(itemId[i]);
			DetalleOrden detalle = new DetalleOrden();
			detalle.setArticulo(articulo);
			detalle.setCantidad(cantidad[i]);
			orden.addDetalleOrden(detalle);
			orden.setTotal(orden.getDetallesOrden().stream().map(DetalleOrden::calcularImporte)
					.collect(Collectors.summingDouble(Double::doubleValue)));
			log.info("ID: " + itemId[i].toString() + ", cantidad: " + cantidad[i].toString());
		}
		ordenService.saveOrden(orden);
		status.setComplete();
		flash.addFlashAttribute("success", "Orden creada con éxito");
		return "redirect:/clientes/ver/" + orden.getCliente().getIdCliente();
	}

	@RequestMapping(value = "/orden/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Orden orden = null;
		if (id > 0) {
			orden = ordenService.findById(id);
		} else {
			return "redirect:/clientes/lista";
		}
		model.put("orden", orden);
		model.put("titulo", "Datos de la Orden");
		return "/ordenes/orden";
	}
}
