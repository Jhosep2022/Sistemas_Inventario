package clases;

public class Usuario extends TipoUsuario {
	private int idusuario, ci, estado;
	private String nombre, telefono, celular, fecharegistro, clave, direccion;

	public Usuario() {
		super();
	}

	public Usuario(int idtipo, String tipo, int idusuario, int ci, int estado, String nombre, String telefono,
			String celular, String fecharegistro, String clave, String direccion) {
		super(idtipo, tipo);
		this.idusuario = idusuario;
		this.ci = ci;
		this.nombre = nombre;
		this.telefono = telefono;
		this.celular = celular;
		this.fecharegistro = fecharegistro;
		this.clave = clave;
		this.direccion = direccion;
		this.estado = estado;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getFecharegistro() {
		return fecharegistro;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public int getCi() {
		return ci;
	}

	public void setCi(int ci) {
		this.ci = ci;
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

	public String Fecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

}
