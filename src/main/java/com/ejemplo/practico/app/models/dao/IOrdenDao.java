package com.ejemplo.practico.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplo.practico.app.models.entity.Orden;

public interface IOrdenDao extends CrudRepository<Orden, Long> {

}
