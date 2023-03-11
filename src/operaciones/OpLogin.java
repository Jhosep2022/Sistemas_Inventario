package operaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import clases.Conexion;
import clases.Usuario;

public class OpLogin {
	public static Usuario IniciarSesion(int ci, String clave) {
		Usuario u = new Usuario();
		Connection conn = (new Conexion()).getConn();
		String sql = "SELECT * FROM USUARIO AS U, TIPOUSUARIO AS TU WHERE U.IDTIPO = TU.IDTIPO AND U.CI ='" + ci
				+ "' AND U.CLAVE = '" + clave + "' AND U.ESTADO = 1";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				int idtipo = result.getInt("IDTIPO");
				String tipo = result.getString("TIPO");
				int idusuario = result.getInt("IDUSUARIO");
				int ci_ = result.getInt("CI");
				int estado = result.getInt("ESTADO");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String fecharegistro = result.getString("FECHAREGISTRO");
				String celular = result.getString("CELULAR");
				String clave_ = result.getString("CLAVE");
				String direccion = result.getString("DIRECCION");

				u.setIdtipo(idtipo);
				u.setTipo(tipo);
				u.setIdusuario(idusuario);
				u.setCi(ci_);
				u.setNombre(nombre);
				u.setTelefono(telefono);
				u.setFecharegistro(fecharegistro);
				u.setCelular(celular);
				u.setClave(clave_);
				u.setDireccion(direccion);
				u.setEstado(estado);
			}
			return u;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e, "Error", 0);
			return u;
		}

	}
}
