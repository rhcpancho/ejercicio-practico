package com.ejemplo.practico.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplo.practico.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
