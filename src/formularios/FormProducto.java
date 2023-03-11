package formularios;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import clases.Categoria;
import clases.Producto;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpProducto;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormProducto {

	private JFrame frameProducto;
	private JTextField tproducto;
	private JFormattedTextField tprecioventa;
	private JTextField tdescripcion;
	private JTextField tstock;
	private JTextField tsearch;
	private JTable tablaproducto;
	private JComboBox<Categoria> tcategoria;
	private int idproducto_ = 0;
	private JLabel testado;
	private FormMenu formmenu;
	private Usuario usuario;

	public JFrame getFrameProducto() {
		return frameProducto;
	}

	public void setFrameProducto(JFrame frameProducto) {
		this.frameProducto = frameProducto;
	}

	public int getIdproducto_() {
		return idproducto_;
	}

	public void setIdproducto_(int idproducto_) {
		this.idproducto_ = idproducto_;
	}

	public int buscarProductoTabla(int idproducto) {
		if (idproducto != 0) {
			for (int i = 0; i < tablaproducto.getRowCount(); i++) {
				if (tablaproducto.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablaproducto.getValueAt(i, 0)) == idproducto) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaproducto.setModel(new DefaultTableModel(OpProducto.ListarProducto(val),
				new String[] { "ID", "PRODUCTO", "CATEGORÍA", "STOCK", "PRECIO" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class, };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablaproducto.getColumnModel().getColumn(0).setResizable(false);
		tablaproducto.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaproducto.getColumnModel().getColumn(0).setMinWidth(40);
		tablaproducto.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaproducto.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablaproducto.getColumnModel().getColumn(1).setResizable(false);
		tablaproducto.getColumnModel().getColumn(1).setPreferredWidth(250);
		tablaproducto.getColumnModel().getColumn(1).setMinWidth(250);
		tablaproducto.getColumnModel().getColumn(1).setMaxWidth(250);

		tablaproducto.getColumnModel().getColumn(2).setResizable(false);
		tablaproducto.getColumnModel().getColumn(2).setPreferredWidth(250);
		tablaproducto.getColumnModel().getColumn(2).setMinWidth(250);
		tablaproducto.getColumnModel().getColumn(2).setMaxWidth(250);

		tablaproducto.getColumnModel().getColumn(3).setResizable(false);
		tablaproducto.getColumnModel().getColumn(3).setPreferredWidth(80);
		tablaproducto.getColumnModel().getColumn(3).setMinWidth(80);
		tablaproducto.getColumnModel().getColumn(3).setMaxWidth(80);
		tablaproducto.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablaproducto.getColumnModel().getColumn(4).setResizable(false);

	}

	public JComboBox<Categoria> cargarCombo(JComboBox<Categoria> cat) {
		cat = new JComboBox<Categoria>();
		ArrayList<Categoria> listcat = OpProducto.ListarCategoria();
		for (int i = 0; i < listcat.size(); i++) {
			cat.addItem(listcat.get(i));
		}
		return cat;

	}

	public int buscarProductoTablaProducto(String producto) {
		if (producto != null) {
			for (int i = 0; i < tablaproducto.getRowCount(); i++) {
				if (tablaproducto.getValueAt(i, 1) != null) {
					if (((String) tablaproducto.getValueAt(i, 1)).equals("  " + producto)) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void productoSeleccionado(Producto p) {
		setIdproducto_(p.getIdproducto());
		tproducto.setText(p.getProducto());
		tdescripcion.setText(p.getDescripcionproducto());
		tprecioventa.setText(p.getPrecioventa() + "");
		tstock.setText(p.getStock() + "");
		int j = tcategoria.getItemCount();
		for (int i = 0; i < j; i++) {
			Categoria pr = (Categoria) tcategoria.getItemAt(i);
			if (pr.getIdcategoria() == p.getIdcategoria()) {
				tcategoria.setSelectedIndex(i);
				i = j;
			}
		}

		if (p.getEstado() == 1) {
			testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
			testado.setToolTipText("Activo");
		} else {
			testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
			testado.setToolTipText("Inactivo");
		}
	}

	public void limpiarFormulario() {
		setIdproducto_(0);
		tproducto.setText("");
		tdescripcion.setText("");
		tprecioventa.setText("");
		tstock.setText("");
		tcategoria.setSelectedIndex(0);
		testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
	}

	public FormProducto(Usuario u, FormMenu fm) {
		usuario = u;
		formmenu = fm;
		initialize();
		frameProducto.setLocationRelativeTo(null);
	}

	private void initialize() {
		frameProducto = new JFrame();
		frameProducto.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameProducto.setTitle("MODULO PRODUCTO");
		frameProducto.getContentPane().setBackground(Color.WHITE);
		frameProducto.getContentPane().setForeground(new Color(255, 255, 255));
		frameProducto.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 800, 1);
		frameProducto.getContentPane().add(lblNewLabel);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameProducto.getContentPane().add(labelusuarioregistro);

		JLabel labeltitulomoduloproveedores = new JLabel("REGISTRO DE PRODUCTOS");
		labeltitulomoduloproveedores.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitulomoduloproveedores.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labeltitulomoduloproveedores.setBorder(new EmptyBorder(0, 0, 0, 0));
		labeltitulomoduloproveedores.setBackground(Color.WHITE);
		labeltitulomoduloproveedores.setBounds(0, 20, 800, 30);
		frameProducto.getContentPane().add(labeltitulomoduloproveedores);

		tproducto = new JTextField();
		tproducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tproducto);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tproducto);
			}
		});
		tproducto.setBackground(Color.WHITE);
		tproducto.setToolTipText("");
		tproducto.setHorizontalAlignment(SwingConstants.LEFT);
		tproducto.setForeground(Color.BLACK);
		CajasTexto.TxtFueraDeFoco(tproducto);
		tproducto.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tproducto.setColumns(10);
		tproducto.setAlignmentX(1.0f);
		tproducto.setBounds(20, 90, 220, 25);
		frameProducto.getContentPane().add(tproducto);

		JLabel lproducto = new JLabel("PRODUCTO *");
		lproducto.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproducto.setBounds(20, 70, 220, 20);
		frameProducto.getContentPane().add(lproducto);

		JLabel lcategoria = new JLabel("CATEGORÍA *");
		lcategoria.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcategoria.setBounds(260, 70, 220, 20);
		frameProducto.getContentPane().add(lcategoria);

		tcategoria = cargarCombo(tcategoria);

		tcategoria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco_(tcategoria);
				tcategoria.setBackground(Color.WHITE);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco_(tcategoria);
			}
		});
		tcategoria.setBackground(Color.WHITE);
		tcategoria.setForeground(Color.BLACK);
		CajasTexto.ComboFueraDeFoco_(tcategoria);
		tcategoria.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcategoria.setBounds(260, 90, 220, 25);
		frameProducto.getContentPane().add(tcategoria);

		JLabel lprecioventa = new JLabel("PRECIO VENTA *");
		lprecioventa.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lprecioventa.setBounds(500, 70, 140, 20);
		frameProducto.getContentPane().add(lprecioventa);

		DecimalFormatSymbols dfs;
		DecimalFormat dFormat;

		dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		dfs.setGroupingSeparator(',');
		dFormat = new DecimalFormat("#0.##", dfs);
		tprecioventa = new JFormattedTextField(dFormat);
		tprecioventa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tprecioventa);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tprecioventa);
			}
		});

		tprecioventa.setBackground(Color.WHITE);
		tprecioventa.setForeground(Color.BLACK);
		CajasTexto.TxtFueraDeFoco(tprecioventa);
		tprecioventa.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tprecioventa.setBounds(500, 90, 140, 25);
		frameProducto.getContentPane().add(tprecioventa);

		tdescripcion = new JTextField();
		tdescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tdescripcion);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tdescripcion);
			}
		});
		tdescripcion.setBackground(Color.WHITE);
		tdescripcion.setToolTipText("");
		tdescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		tdescripcion.setForeground(Color.BLACK);
		CajasTexto.TxtFueraDeFoco(tdescripcion);
		tdescripcion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdescripcion.setColumns(10);
		tdescripcion.setAlignmentX(1.0f);
		tdescripcion.setBounds(20, 150, 460, 25);
		frameProducto.getContentPane().add(tdescripcion);

		JLabel ldescripcion = new JLabel("DESCRIPCIÓN");
		ldescripcion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldescripcion.setBounds(20, 130, 460, 20);
		frameProducto.getContentPane().add(ldescripcion);

		JLabel lstock = new JLabel("STOCK *");
		lstock.setBackground(Color.WHITE);
		lstock.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lstock.setBounds(500, 130, 140, 20);
		frameProducto.getContentPane().add(lstock);

		tstock = new JTextField();
		CajasTexto.soloNumeros(tstock);
		tstock.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tstock);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tstock);
			}
		});
		tstock.setToolTipText("");
		tstock.setHorizontalAlignment(SwingConstants.LEFT);
		tstock.setForeground(Color.BLACK);
		CajasTexto.TxtFueraDeFoco(tstock);
		tstock.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tstock.setColumns(10);
		tstock.setAlignmentX(1.0f);
		tstock.setBounds(500, 150, 140, 25);
		frameProducto.getContentPane().add(tstock);

		JLabel lestado = new JLabel("ESTADO");
		lestado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lestado.setBounds(20, 190, 64, 20);
		frameProducto.getContentPane().add(lestado);

		testado = new JLabel("");
		testado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (testado.getToolTipText().equals("Inactivo")) {
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
					testado.setToolTipText("Activo");
				} else {
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
					testado.setToolTipText("Inactivo");
				}
			}
		});
		testado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		testado.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
		testado.setIconTextGap(10);
		testado.setHorizontalTextPosition(SwingConstants.CENTER);
		testado.setHorizontalAlignment(SwingConstants.CENTER);
		testado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		testado.setBounds(20, 210, 64, 25);
		frameProducto.getContentPane().add(testado);

		JLabel lnuevo = new JLabel("NUEVO");
		lnuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lnuevo.setHorizontalAlignment(SwingConstants.RIGHT);
		lnuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lnuevo.setBounds(705, 70, 75, 45);
		frameProducto.getContentPane().add(lnuevo);

		JLabel btnactualizar = new JLabel("");
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
					btnactualizar.setEnabled(false);
					if (getIdproducto_() != 0) {
						if (tproducto.getText().equals("") || tcategoria.getSelectedIndex() <= 0
								|| tprecioventa.getText().equals("") || tstock.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje",
									1);
						} else {

							Categoria cat = (Categoria) tcategoria.getSelectedItem();

							String producto = tproducto.getText().toUpperCase();
							String descripcion = tdescripcion.getText().toUpperCase();
							int stock = Integer.parseInt(tstock.getText());
							float precioventa = Float.parseFloat(tprecioventa.getText());
							int estado = 0;
							if (testado.getToolTipText().equals("Activo")) {
								estado = 1;
							}
							Producto pr = new Producto(cat.getIdcategoria(), cat.getCategoria(), cat.getDescripcion(),
									getIdproducto_(), stock, estado, precioventa, producto, descripcion);
							if (OpProducto.ActualizarProducto(pr)) {

								JOptionPane.showMessageDialog(null,
										"Se ha actualizado los datos del producto correctamente.",
										"Actualización exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								int r = buscarProductoTablaProducto(pr.getProducto());
								if (r != -1) {
									tablaproducto.setRowSelectionInterval(r, r);
									tablaproducto.requestFocus();
									tablaproducto.changeSelection(r, 0, false, false);

								} else {
									btnactualizar.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido actualizar los datos del producto.",
										"Error", 0);
							}

						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un producto a actualizar.", "Mensaje", 1);

					}
					btnactualizar.setEnabled(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
				}
			}
		});
		btnactualizar.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setEnabled(false);
		btnactualizar.setBounds(660, 130, 45, 45);
		frameProducto.getContentPane().add(btnactualizar);

		JLabel lactualizar = new JLabel("ACTUALIZAR");
		lactualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lactualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lactualizar.setBounds(705, 130, 75, 45);
		frameProducto.getContentPane().add(lactualizar);

		JLabel leliminar = new JLabel("ELIMINAR");
		leliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		leliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		leliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		leliminar.setBounds(705, 190, 75, 45);
		frameProducto.getContentPane().add(leliminar);

		JLabel lregistrar = new JLabel("REGISTRAR      ");
		lregistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lregistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lregistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lregistrar.setBounds(545, 190, 95, 45);
		frameProducto.getContentPane().add(lregistrar);

		JLabel btnregistrar = new JLabel("");
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover_.png")));

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnregistrar.isEnabled()) {

					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));

					btnregistrar.setEnabled(false);
					if (tproducto.getText().equals("") || tcategoria.getSelectedIndex() <= 0
							|| tprecioventa.getText().equals("") || tstock.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje", 1);
					} else {

						Categoria cat = (Categoria) tcategoria.getSelectedItem();

						String producto = tproducto.getText().toUpperCase();
						String descripcion = tdescripcion.getText().toUpperCase();
						int stock = Integer.parseInt(tstock.getText());
						float precioventa = Float.parseFloat(tprecioventa.getText());
						int estado = 0;
						if (testado.getToolTipText().equals("Activo")) {
							estado = 1;
						}
						Producto pr = new Producto(cat.getIdcategoria(), cat.getCategoria(), cat.getDescripcion(), 0,
								stock, estado, precioventa, producto, descripcion);

						if (OpProducto.RegistrarProducto(pr)) {
							JOptionPane.showMessageDialog(null, "Se ha registrado el producto correctamente.",
									"Registro exitoso", 1);
							if (tsearch.getText() != null) {
								cargarTabla(tsearch.getText());
							} else {
								cargarTabla("");
							}
							limpiarFormulario();
							int r = buscarProductoTablaProducto(pr.getProducto());
							if (r != -1) {
								// tablacliente.setRowSelectionInterval(r, r);
								tablaproducto.requestFocus();
								tablaproducto.changeSelection(r, 0, true, true);
							} else {
								btnregistrar.requestFocus();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Ha ocurrido un error, no se ha podido registrar el producto.", "Error", 0);
						}

					}
					btnregistrar.setEnabled(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));

				}
			}
		});
		btnregistrar.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconSaveFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameProducto.getContentPane().add(btnregistrar);
		JLabel btneliminar = new JLabel("");
		btneliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btneliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));

					btneliminar.setEnabled(false);

					if (getIdproducto_() != 0) {

						int idproducto = getIdproducto_();
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar este producto?",
								"Eliminar producto", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (OpProducto.EliminarProducto(idproducto)) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado el producto correctamente.",
										"Eliminación exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								limpiarFormulario();
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								btnregistrar.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido eliminar el producto.", "Error", 0);
								btneliminar.setEnabled(true);
							}

						} else {

							btneliminar.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un producto a eliminar.", "Mensaje", 1);
						btneliminar.setEnabled(true);
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));
				}
			}
		});
		btneliminar.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setEnabled(false);
		btneliminar.setBounds(660, 190, 45, 45);
		frameProducto.getContentPane().add(btneliminar);

		JLabel btnnuevo = new JLabel("");
		btnnuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixed.png")));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover_.png")));

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

				limpiarFormulario();
				btnregistrar.setEnabled(true);
				btneliminar.setEnabled(false);
				btnactualizar.setEnabled(false);
				if (tsearch.getText() != null) {
					cargarTabla(tsearch.getText());
				} else {
					cargarTabla("");
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

			}
		});
		btnnuevo.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconNewFixed.png")));
		btnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnuevo.setHorizontalAlignment(SwingConstants.CENTER);
		btnnuevo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnnuevo.setBounds(660, 70, 45, 45);
		frameProducto.getContentPane().add(btnnuevo);

		JLabel labelseparadormiddle = new JLabel("");
		labelseparadormiddle.setOpaque(true);
		labelseparadormiddle.setBackground(Color.BLACK);
		labelseparadormiddle.setBounds(0, 249, 800, 1);
		frameProducto.getContentPane().add(labelseparadormiddle);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarProductoTabla(getIdproducto_());
					if (sel != -1) {
						tablaproducto.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablaproducto.changeSelection(sel, 0, false, false);
						btnregistrar.setEnabled(false);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdproducto_() != 0) {
							limpiarFormulario();
						}
					}

				}
			}
		});
		tsearch.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tsearch);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tsearch);
			}
		});
		tsearch.setToolTipText("");
		tsearch.setHorizontalAlignment(SwingConstants.LEFT);
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setForeground(Color.BLACK);
		tsearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch.setColumns(10);
		tsearch.setBorder(new LineBorder(Color.GRAY, 1, false));
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(525, 265, 255, 25);
		frameProducto.getContentPane().add(tsearch);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormProducto.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(500, 265, 25, 25);
		frameProducto.getContentPane().add(lsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameProducto.getContentPane().add(scrollpanetabla);

		tablaproducto = new JTable();
		tablaproducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!tproducto.getText().equals("") || tcategoria.getSelectedIndex() > 0
						|| !tstock.getText().equals("") || !tdescripcion.getText().equals("")
						|| !tprecioventa.getText().equals("")) && getIdproducto_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar producto",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablaproducto.getSelectedRow();
						if (tablaproducto.getValueAt(r, 0) != null) {
							Producto p = OpProducto
									.BuscarProducto(Integer.parseInt((String) tablaproducto.getValueAt(r, 0)));
							String producto = p.getProducto();
							if (producto != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdproducto_(p.getIdproducto());
								productoSeleccionado(p);
							} else {
								btnregistrar.setEnabled(true);
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								limpiarFormulario();
							}
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						tablaproducto.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablaproducto.getSelectedRow();
					if (tablaproducto.getValueAt(r, 0) != null) {
						Producto p = OpProducto
								.BuscarProducto(Integer.parseInt((String) tablaproducto.getValueAt(r, 0)));
						String producto = p.getProducto();
						if (producto != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdproducto_(p.getIdproducto());
							productoSeleccionado(p);
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						limpiarFormulario();
					}
				}
			}
		});
		tablaproducto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!tproducto.getText().equals("") || tcategoria.getSelectedIndex() > 0
						|| !tstock.getText().equals("") || !tdescripcion.getText().equals("")
						|| !tprecioventa.getText().equals("")) && getIdproducto_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar producto",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablaproducto.getSelectedRow();
						if (tablaproducto.getValueAt(r, 0) != null) {
							Producto p = OpProducto
									.BuscarProducto(Integer.parseInt((String) tablaproducto.getValueAt(r, 0)));
							String producto = p.getProducto();
							if (producto != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdproducto_(p.getIdproducto());
								productoSeleccionado(p);
							} else {
								btnregistrar.setEnabled(true);
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								limpiarFormulario();
							}
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						tablaproducto.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablaproducto.getSelectedRow();
					if (tablaproducto.getValueAt(r, 0) != null) {
						Producto p = OpProducto
								.BuscarProducto(Integer.parseInt((String) tablaproducto.getValueAt(r, 0)));
						String producto = p.getProducto();
						if (producto != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdproducto_(p.getIdproducto());
							productoSeleccionado(p);
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						limpiarFormulario();
					}
				}
			}
		});

		tablaproducto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaproducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaproducto.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablaproducto.setSelectionForeground(new Color(26, 115, 232));
		tablaproducto.setSelectionBackground(new Color(232, 240, 254));

		tablaproducto.setBorder(null);
		tablaproducto.setRowHeight(20);
		JTableHeader th = tablaproducto.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablaproducto.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablaproducto);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameProducto.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameProducto.getContentPane().add(btnvolver);
		frameProducto.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormProducto.class.getResource("/iconos/iconProduct.png")));
		frameProducto.setForeground(Color.WHITE);
		frameProducto.setResizable(false);
		frameProducto.setBounds(100, 100, 816, 618);
		frameProducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
