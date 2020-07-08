package com.ejemplo.practico.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplo.practico.app.models.entity.Articulo;

public interface IArticuloDao extends CrudRepository<Articulo, Long> {

	@Query("select a from Articulo a where a.nombre like %?1%")
	public List<Articulo> findByNombre(String term);

	public List<Articulo> findByNombreLikeIgnoreCase(String term);
}
