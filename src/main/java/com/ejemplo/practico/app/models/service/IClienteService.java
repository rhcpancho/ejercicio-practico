package com.ejemplo.practico.app.models.service;

import java.util.List;

import com.ejemplo.practico.app.models.entity.Articulo;
import com.ejemplo.practico.app.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();

	public Cliente findById(Long id);

	public void save(Cliente cliente);

	public void deleteById(Long id);

	public List<Articulo> findByNombre(String term);

}
