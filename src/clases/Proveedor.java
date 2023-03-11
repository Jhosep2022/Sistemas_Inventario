package clases;

public class Proveedor {
	private int idproveedor;
	private String proveedor, direccion, telefono, celular, descripcion, nit;

	public Proveedor() {

	}

	public Proveedor(int idproveedor, String proveedor, String direccion, String telefono, String celular,
			String descripcion, String nit) {
		this.idproveedor = idproveedor;
		this.proveedor = proveedor;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.descripcion = descripcion;
		this.nit = nit;
	}

	public int getIdproveedor() {
		return idproveedor;
	}

	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Override
	public String toString() {
		return this.proveedor;
	}

}
