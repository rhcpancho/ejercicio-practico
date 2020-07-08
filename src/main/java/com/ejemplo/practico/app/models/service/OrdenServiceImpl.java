package com.ejemplo.practico.app.models.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplo.practico.app.models.dao.IOrdenDao;
import com.ejemplo.practico.app.models.entity.Orden;

@Service
public class OrdenServiceImpl implements IOrdenService {

	@Autowired
	private IOrdenDao ordenDao;

	@Override
	@Transactional
	public void saveOrden(Orden orden) {
		ordenDao.save(orden);
	}

	@Override
	public Orden findById(Long id) {
		return ordenDao.findById(id).orElse(null);
	}

}
