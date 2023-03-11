package clases;

import java.util.ArrayList;

public class Venta {
	private int idventa;
	private Usuario usuario;
	private Cliente cliente;
	private String fecharegistro, observacion;
	private float total;
	private ArrayList<DetalleVenta> listaventa;
	
	public Venta() {}

	

	public Venta(int idventa, Usuario usuario, Cliente cliente, String fecharegistro, String observacion, float total,
			ArrayList<DetalleVenta> listaventa) {
		this.idventa = idventa;
		this.usuario = usuario;
		this.cliente = cliente;
		this.fecharegistro = fecharegistro;
		this.observacion = observacion;
		this.total = total;
		this.listaventa = listaventa;
	}



	public int getIdventa() {
		return idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public ArrayList<DetalleVenta> getListaventa() {
		return listaventa;
	}

	public void setListaventa(ArrayList<DetalleVenta> listaventa) {
		this.listaventa = listaventa;
	}


	
}