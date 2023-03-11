package clases;

public class DetalleVenta {
	private Producto producto;
	private int cantidad;
	private float precioventa;

	public DetalleVenta() {
	}

	public Producto getProducto() {
		return producto;
	}

	public float getPrecioventa() {
		return precioventa;
	}

	public void setPrecioventa(float precioventa) {
		this.precioventa = precioventa;
	}

	public DetalleVenta(Producto producto, int cantidad, float precioventa) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.precioventa = precioventa;
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

}
