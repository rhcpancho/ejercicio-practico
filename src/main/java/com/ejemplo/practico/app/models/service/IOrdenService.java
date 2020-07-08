package com.ejemplo.practico.app.models.service;

import com.ejemplo.practico.app.models.entity.Orden;

public interface IOrdenService {

	public Orden findById(Long id);

	public void saveOrden(Orden orden);
}
