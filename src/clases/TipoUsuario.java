package clases;

public class TipoUsuario {

	private int idtipo;
	private String tipo;

	public TipoUsuario() {

	}

	public TipoUsuario(int idtipo, String tipo) {
		this.idtipo = idtipo;
		this.tipo = tipo;
	}

	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return this.tipo;
	}

}
