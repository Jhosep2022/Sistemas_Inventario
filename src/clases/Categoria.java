package clases;

public class Categoria {
	int idcategoria;
	String categoria, descripcion;
	
	public Categoria() {
		
	}
	
	public Categoria(int idcategoria, String categoria, String descripcion) {
		this.idcategoria = idcategoria;
		this.categoria = categoria;
		this.descripcion = descripcion;
	}

	public int getIdcategoria() {
		return idcategoria;
	}

	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return this.categoria;
	}
	
}
