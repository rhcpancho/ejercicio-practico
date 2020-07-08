package com.ejemplo.practico.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplo.practico.app.models.entity.DetalleOrden;

public interface IDetalleOrdenDao extends CrudRepository<DetalleOrden, Long> {

}
