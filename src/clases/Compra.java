package clases;

import java.util.ArrayList;

public class Compra {
	private int idcompra;
	private Usuario usuario;
	private Proveedor proveedor;
	private String fecharegistro, observacion;
	private float total;
	private ArrayList<DetalleCompra> listacompra;

	public Compra() {

	}

	public Compra(int idcompra, Usuario usuario, Proveedor proveedor, String fecharegistro, String observacion,
			float total, ArrayList<DetalleCompra> listacompra) {
		this.idcompra = idcompra;
		this.usuario = usuario;
		this.proveedor = proveedor;
		this.fecharegistro = fecharegistro;
		this.observacion = observacion;
		this.total = total;
		this.listacompra = listacompra;
	}

	public ArrayList<DetalleCompra> getListacompra() {
		return listacompra;
	}

	public void setListacompra(ArrayList<DetalleCompra> listacompra) {
		this.listacompra = listacompra;
	}

	public int getIdcompra() {
		return idcompra;
	}

	public void setIdcompra(int idcompra) {
		this.idcompra = idcompra;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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

}
