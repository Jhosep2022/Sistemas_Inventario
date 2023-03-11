package clases;

public class Cliente {

	private int idcliente, ci, estado;
	private String nombre, telefono, celular, fecharegistro, direccion, nit;

	public Cliente(int idcliente, int ci, int estado, String nombre, String telefono, String celular,
			String fecharegistro, String direccion, String nit) {
		this.idcliente = idcliente;
		this.ci = ci;
		this.estado = estado;
		this.nombre = nombre;
		this.telefono = telefono;
		this.celular = celular;
		this.fecharegistro = fecharegistro;
		this.direccion = direccion;
		this.nit = nit;
	}

	public Cliente() {
	}

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	@Override
	public String toString() {
		return this.nombre;
	}	
}
