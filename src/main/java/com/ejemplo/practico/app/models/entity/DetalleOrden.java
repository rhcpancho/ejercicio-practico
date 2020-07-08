package com.ejemplo.practico.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "detalle_orden")
public class DetalleOrden implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle_orden", nullable = false)
	private Long idDetalleOrden;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	@ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_articulo", nullable = false, updatable = false)
	private Articulo articulo;

	public Long getIdDetalleOrden() {
		return idDetalleOrden;
	}

	public void setIdDetalleOrden(Long idDetalleOrden) {
		this.idDetalleOrden = idDetalleOrden;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double calcularImporte() {
		return cantidad.doubleValue() * articulo.getPrecioUnitario();
	}
}
