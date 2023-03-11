package clases;

public class DetalleCompra {

	private Producto producto;
	private int cantidad;
	private float preciocompra;

	public DetalleCompra() {

	}

	public DetalleCompra(Producto producto, int cantidad, float preciocompra) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.preciocompra = preciocompra;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPreciocompra() {
		return preciocompra;
	}

	public void setPreciocompra(float preciocompra) {
		this.preciocompra = preciocompra;
	}

}