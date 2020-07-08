package com.ejemplo.practico.app.models.service;

import java.util.List;

import com.ejemplo.practico.app.models.entity.Articulo;

public interface IArticuloService {

	public List<Articulo> findAll();

	public Articulo findById(Long id);

	public void save(Articulo articulo);

	public void deleteById(Long id);
}
