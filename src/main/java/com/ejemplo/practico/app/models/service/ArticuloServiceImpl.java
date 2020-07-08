package com.ejemplo.practico.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.practico.app.models.dao.IArticuloDao;
import com.ejemplo.practico.app.models.entity.Articulo;

@Service
public class ArticuloServiceImpl implements IArticuloService {

	@Autowired
	private IArticuloDao articuloDao;

	@Override
	@Transactional(readOnly = true)
	public List<Articulo> findAll() {
		return (List<Articulo>) articuloDao.findAll();
	}

	@Override
	@Transactional
	public void save(Articulo articulo) {
		articuloDao.save(articulo);
	}

	@Override
	@Transactional(readOnly = true)
	public Articulo findById(Long id) {
		return articuloDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		articuloDao.deleteById(id);
	}

}
