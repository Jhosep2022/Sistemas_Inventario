package clases;

public class Producto extends Categoria {

	private int idproducto, stock, estado;
	private float precioventa;
	private String producto, descripcionproducto;

	public Producto() {

	}

	public Producto(int idcategoria, String categoria, String descripcion, int idproducto, int stock, int estado,
			float precioventa, String producto, String descripcionproducto) {
		super(idcategoria, categoria, descripcion);
		this.idproducto = idproducto;
		this.stock = stock;
		this.estado = estado;
		this.precioventa = precioventa;
		this.producto = producto;
		this.descripcionproducto = descripcionproducto;
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public float getPrecioventa() {
		return precioventa;
	}

	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getDescripcionproducto() {
		return descripcionproducto;
	}

	public void setDescripcionproducto(String descripcionproducto) {
		this.descripcionproducto = descripcionproducto;
	}

	@Override
	public String toString() {
		if (this.producto != null) {
			return (this.idproducto + "-" + this.producto + "-" + this.categoria).toUpperCase();
		} else {
			return "TODOS LOS PRODUCTOS";
		}
	}

}
