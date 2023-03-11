package formularios;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Proveedor;
import clases.Usuario;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import decoradores.CajasTexto;
import operaciones.OpProveedor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormProveedor {

	private JFrame frameProveedor;
	private JTextField tproveedor;
	private JTextField tdireccion;
	private JTextField ttelefono;
	private JTextField tcelular;
	private JTextField tdescripcion;
	private JTextField tnit;
	private JTextField tsearch;
	private JTable tablaproveedor;
	private int idproveedor_ = 0;
	private Usuario usuario;
	private FormMenu formmenu;

	
	public JFrame getFrameProveedor() {
		return frameProveedor;
	}

	public void setFrameProveedor(JFrame frameProveedor) {
		this.frameProveedor = frameProveedor;
	}

	public int getIdproveedor_() {
		return idproveedor_;
	}

	public void setIdproveedor_(int idproveedor_) {
		this.idproveedor_ = idproveedor_;
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaproveedor.setModel(new DefaultTableModel(OpProveedor.ListarProveedor(val),
				new String[] { "ID", "PROVEEDOR", "DIRECCIÓN", "TELÉFONO", "CELULAR", "NIT" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablaproveedor.getColumnModel().getColumn(0).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaproveedor.getColumnModel().getColumn(0).setMinWidth(40);
		tablaproveedor.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaproveedor.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablaproveedor.getColumnModel().getColumn(1).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(1).setPreferredWidth(150);
		tablaproveedor.getColumnModel().getColumn(1).setMinWidth(150);
		tablaproveedor.getColumnModel().getColumn(1).setMaxWidth(150);

		tablaproveedor.getColumnModel().getColumn(2).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(2).setPreferredWidth(260);
		tablaproveedor.getColumnModel().getColumn(2).setMinWidth(260);
		tablaproveedor.getColumnModel().getColumn(2).setMaxWidth(260);

		tablaproveedor.getColumnModel().getColumn(3).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(3).setPreferredWidth(95);
		tablaproveedor.getColumnModel().getColumn(3).setMinWidth(95);
		tablaproveedor.getColumnModel().getColumn(3).setMaxWidth(95);
		tablaproveedor.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablaproveedor.getColumnModel().getColumn(4).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(4).setPreferredWidth(95);
		tablaproveedor.getColumnModel().getColumn(4).setMinWidth(95);
		tablaproveedor.getColumnModel().getColumn(4).setMaxWidth(95);
		tablaproveedor.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		tablaproveedor.getColumnModel().getColumn(5).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	}

	public void limpiarFormulario() {
		setIdproveedor_(0);
		tproveedor.setText("");
		tdireccion.setText("");
		ttelefono.setText("");
		tcelular.setText("");
		tnit.setText("");
		tdescripcion.setText("");
	}

	public void proveedorSeleccionado(Proveedor p) {
		setIdproveedor_(p.getIdproveedor());
		tproveedor.setText(p.getProveedor());
		tdireccion.setText(p.getDireccion());
		ttelefono.setText(p.getTelefono());
		tcelular.setText(p.getCelular());
		tdescripcion.setText(p.getDescripcion());
		tnit.setText(p.getNit());
	}

	public int buscarProveedorTabla(int idproveedor) {
		if (idproveedor != 0) {
			for (int i = 0; i < tablaproveedor.getRowCount(); i++) {
				if (tablaproveedor.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablaproveedor.getValueAt(i, 0)) == idproveedor) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int buscarProveedorTablaProveedor(String proveedor) {
		if (proveedor != null) {
			for (int i = 0; i < tablaproveedor.getRowCount(); i++) {
				if (tablaproveedor.getValueAt(i, 1) != null) {
					if (((String) tablaproveedor.getValueAt(i, 1)).equals("  " + proveedor)) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public FormProveedor(Usuario u, FormMenu fm) {
		formmenu = fm;
		usuario = u;
		initialize();
		frameProveedor.setLocationRelativeTo(null);
	}

	private void initialize() {
		frameProveedor = new JFrame();
		frameProveedor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameProveedor.setResizable(false);
		frameProveedor.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormProveedor.class.getResource("/iconos/iconProvider.png")));
		frameProveedor.setTitle("MODULO PROVEEDOR");
		frameProveedor.getContentPane().setBackground(Color.WHITE);
		frameProveedor.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 12));
		frameProveedor.setBounds(100, 100, 816, 618);
		frameProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameProveedor.getContentPane().setLayout(null);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameProveedor.getContentPane().add(labelusuarioregistro);

		JLabel labeltitulomoduloproveedores = new JLabel("REGISTRO DE PROVEEDORES");
		labeltitulomoduloproveedores.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitulomoduloproveedores.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labeltitulomoduloproveedores.setBorder(new EmptyBorder(0, 0, 0, 0));
		labeltitulomoduloproveedores.setBackground(Color.WHITE);
		labeltitulomoduloproveedores.setBounds(0, 20, 800, 30);
		frameProveedor.getContentPane().add(labeltitulomoduloproveedores);

		tproveedor = new JTextField();
		CajasTexto.TxtFueraDeFoco(tproveedor);
		tproveedor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tproveedor);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tproveedor);
			}
		});
		tproveedor.setToolTipText("");
		tproveedor.setHorizontalAlignment(SwingConstants.LEFT);
		tproveedor.setForeground(Color.BLACK);
		tproveedor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tproveedor.setColumns(10);
		tproveedor.setAlignmentX(1.0f);
		tproveedor.setBounds(20, 90, 300, 25);
		frameProveedor.getContentPane().add(tproveedor);

		JLabel lproveedor = new JLabel("PROVEEDOR *");
		lproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproveedor.setBounds(20, 70, 300, 20);
		frameProveedor.getContentPane().add(lproveedor);

		tdireccion = new JTextField();
		CajasTexto.TxtFueraDeFoco(tdireccion);
		tdireccion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tdireccion);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tdireccion);
			}
		});
		tdireccion.setToolTipText("");
		tdireccion.setHorizontalAlignment(SwingConstants.LEFT);
		tdireccion.setForeground(Color.BLACK);
		tdireccion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdireccion.setColumns(10);
		tdireccion.setAlignmentX(1.0f);
		tdireccion.setBounds(20, 210, 460, 25);
		frameProveedor.getContentPane().add(tdireccion);

		JLabel ldireccion = new JLabel("DIRECCIÓN");
		ldireccion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldireccion.setBounds(20, 190, 460, 20);
		frameProveedor.getContentPane().add(ldireccion);

		ttelefono = new JTextField();
		CajasTexto.TxtFueraDeFoco(ttelefono);
		ttelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(ttelefono);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(ttelefono);
			}
		});
		ttelefono.setToolTipText("");
		ttelefono.setHorizontalAlignment(SwingConstants.LEFT);
		ttelefono.setForeground(Color.BLACK);
		ttelefono.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		ttelefono.setColumns(10);
		ttelefono.setAlignmentX(1.0f);
		ttelefono.setBounds(340, 90, 140, 25);
		frameProveedor.getContentPane().add(ttelefono);

		JLabel ltelefono = new JLabel("TELÉFONO");
		ltelefono.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltelefono.setBounds(340, 70, 140, 20);
		frameProveedor.getContentPane().add(ltelefono);

		tcelular = new JTextField();
		CajasTexto.TxtFueraDeFoco(tcelular);
		tcelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tcelular);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tcelular);
			}
		});
		tcelular.setToolTipText("");
		tcelular.setHorizontalAlignment(SwingConstants.LEFT);
		tcelular.setForeground(Color.BLACK);
		tcelular.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcelular.setColumns(10);
		tcelular.setAlignmentX(1.0f);
		tcelular.setBounds(500, 90, 140, 25);
		frameProveedor.getContentPane().add(tcelular);

		JLabel lcelular = new JLabel("CELULAR");
		lcelular.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcelular.setBounds(500, 70, 140, 20);
		frameProveedor.getContentPane().add(lcelular);

		tdescripcion = new JTextField();
		CajasTexto.TxtFueraDeFoco(tdescripcion);
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
		tdescripcion.setToolTipText("");
		tdescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		tdescripcion.setForeground(Color.BLACK);
		tdescripcion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdescripcion.setColumns(10);
		tdescripcion.setAlignmentX(1.0f);
		tdescripcion.setBounds(20, 150, 460, 25);
		frameProveedor.getContentPane().add(tdescripcion);

		JLabel ldescripcion = new JLabel("DESCRIPCIÓN");
		ldescripcion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldescripcion.setBounds(20, 130, 460, 20);
		frameProveedor.getContentPane().add(ldescripcion);

		tnit = new JTextField();
		CajasTexto.TxtFueraDeFoco(tnit);
		tnit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tnit);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tnit);
			}
		});
		tnit.setToolTipText("");
		tnit.setHorizontalAlignment(SwingConstants.LEFT);
		tnit.setForeground(Color.BLACK);
		tnit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tnit.setColumns(10);
		tnit.setAlignmentX(1.0f);
		tnit.setBounds(500, 150, 140, 25);
		frameProveedor.getContentPane().add(tnit);

		JLabel lnit = new JLabel("NIT");
		lnit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnit.setBounds(500, 130, 140, 20);
		frameProveedor.getContentPane().add(lnit);

		JLabel labelseparadortop = new JLabel("");
		labelseparadortop.setBackground(Color.BLACK);
		labelseparadortop.setOpaque(true);
		labelseparadortop.setBounds(0, 0, 800, 1);
		frameProveedor.getContentPane().add(labelseparadortop);

		JLabel labelseparadormiddle = new JLabel("");
		labelseparadormiddle.setOpaque(true);
		labelseparadormiddle.setBackground(Color.BLACK);
		labelseparadormiddle.setBounds(0, 249, 800, 1);
		frameProveedor.getContentPane().add(labelseparadormiddle);

		JLabel lactualizar = new JLabel("ACTUALIZAR");
		lactualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lactualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lactualizar.setBounds(705, 130, 75, 45);
		frameProveedor.getContentPane().add(lactualizar);

		JLabel btnactualizar = new JLabel("");
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(
							new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(
							new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(
							new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixedHover.png")));
					btnactualizar.setEnabled(false);
					if (getIdproveedor_() != 0) {
						if (tproveedor.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje",
									1);
						} else {

							String proveedor = tproveedor.getText().toUpperCase();
							String direccion = tdireccion.getText().toUpperCase();
							String celular = tcelular.getText().toUpperCase();
							String telefono = ttelefono.getText().toUpperCase();
							String descripcion = tdescripcion.getText().toUpperCase();
							String nit = tnit.getText().toUpperCase();
							Proveedor pr = new Proveedor(getIdproveedor_(), proveedor, direccion, telefono, celular,
									descripcion, nit);
							if (OpProveedor.ActualizarProveedor(pr)) {

								JOptionPane.showMessageDialog(null,
										"Se ha actualizado los datos del proveedor correctamente.",
										"Actualización exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								int r = buscarProveedorTablaProveedor(pr.getProveedor());
								if (r != -1) {
									tablaproveedor.setRowSelectionInterval(r, r);
									tablaproveedor.requestFocus();
									tablaproveedor.changeSelection(r, 0, false, false);

								} else {
									btnactualizar.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido actualizar los datos del proveedor.",
										"Error", 0);
							}

						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor a actualizar.", "Mensaje",
								1);

					}
					btnactualizar.setEnabled(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(
							new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixedHover.png")));
				}
			}
		});
		btnactualizar.setIcon(new ImageIcon(FormProveedor.class.getResource("/iconos/iconClientUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setEnabled(false);
		btnactualizar.setBounds(660, 130, 45, 45);
		frameProveedor.getContentPane().add(btnactualizar);

		JLabel leliminar = new JLabel("ELIMINAR");
		leliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		leliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		leliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		leliminar.setBounds(705, 190, 75, 45);
		frameProveedor.getContentPane().add(leliminar);

		JLabel lregistrar = new JLabel("REGISTRAR      ");
		lregistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lregistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lregistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lregistrar.setBounds(545, 190, 95, 45);
		frameProveedor.getContentPane().add(lregistrar);

		JLabel btnregistrar = new JLabel("");
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixedHover_.png")));

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnregistrar.isEnabled()) {

					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixedHover.png")));
					btnregistrar.setEnabled(false);
					if (tproveedor.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje", 1);
					} else {

						String proveedor = tproveedor.getText().toUpperCase();
						String direccion = tdireccion.getText().toUpperCase();
						String celular = tcelular.getText().toUpperCase();
						String telefono = ttelefono.getText().toUpperCase();
						String descripcion = tdescripcion.getText().toUpperCase();
						String nit = tnit.getText().toUpperCase();
						Proveedor pr = new Proveedor(0, proveedor, direccion, telefono, celular, descripcion, nit);
						if (OpProveedor.RegistrarProveedor(pr)) {
							JOptionPane.showMessageDialog(null, "Se ha registrado al proveedor correctamente.",
									"Registro exitoso", 1);
							if (tsearch.getText() != null) {
								cargarTabla(tsearch.getText());
							} else {
								cargarTabla("");
							}
							limpiarFormulario();
							int r = buscarProveedorTablaProveedor(pr.getProveedor());
							if (r != -1) {
								// tablacliente.setRowSelectionInterval(r, r);
								tablaproveedor.requestFocus();
								tablaproveedor.changeSelection(r, 0, true, true);
							} else {
								btnregistrar.requestFocus();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Ha ocurrido un error, no se ha podido registrar al proveedor.", "Error", 0);
						}

					}
					btnregistrar.setEnabled(true);
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixedHover.png")));

				}
			}
		});
		btnregistrar.setIcon(new ImageIcon(FormProveedor.class.getResource("/iconos/iconClientFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameProveedor.getContentPane().add(btnregistrar);

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

					if (getIdproveedor_() != 0) {

						int idproveedor = getIdproveedor_();
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar a este proveedor?",
								"Eliminar proveedor", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (OpProveedor.EliminarProveedor(idproveedor)) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado al proveedor correctamente.",
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
										"Ha ocurrido un error, no se ha podido eliminar al proveedor.", "Error", 0);
								btneliminar.setEnabled(true);
							}

						} else {

							btneliminar.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor a eliminar.", "Mensaje", 1);
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
		btneliminar.setIcon(new ImageIcon(FormProveedor.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setEnabled(false);
		btneliminar.setBounds(660, 190, 45, 45);
		frameProveedor.getContentPane().add(btneliminar);

		tsearch = new JTextField();
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarProveedorTabla(getIdproveedor_());
					if (sel != -1) {
						tablaproveedor.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablaproveedor.changeSelection(sel, 0, false, false);
						btnregistrar.setEnabled(false);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdproveedor_() != 0) {
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
		btnnuevo.setIcon(new ImageIcon(FormProveedor.class.getResource("/iconos/iconNewFixed.png")));
		btnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnuevo.setHorizontalAlignment(SwingConstants.CENTER);
		btnnuevo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnnuevo.setBounds(660, 70, 45, 45);
		frameProveedor.getContentPane().add(btnnuevo);

		JLabel lnuevo = new JLabel("NUEVO");
		lnuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lnuevo.setHorizontalAlignment(SwingConstants.RIGHT);
		lnuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lnuevo.setBounds(705, 70, 75, 45);
		frameProveedor.getContentPane().add(lnuevo);

		tsearch.setToolTipText("");
		tsearch.setHorizontalAlignment(SwingConstants.LEFT);
		tsearch.setForeground(Color.BLACK);
		tsearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch.setColumns(10);
		tsearch.setBorder(new LineBorder(Color.GRAY, 1, false));
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(525, 265, 255, 25);
		frameProveedor.getContentPane().add(tsearch);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormProveedor.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(500, 265, 25, 25);
		frameProveedor.getContentPane().add(lsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameProveedor.getContentPane().add(scrollpanetabla);

		tablaproveedor = new JTable();
		tablaproveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!tproveedor.getText().equals("") || !tdireccion.getText().equals("")
						|| !ttelefono.getText().equals("") || !tcelular.getText().equals("")
						|| !tnit.getText().equals("") || !tdescripcion.getText().equals(""))
						&& getIdproveedor_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar proveedor",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablaproveedor.getSelectedRow();
						if (tablaproveedor.getValueAt(r, 0) != null) {
							Proveedor p = OpProveedor
									.BuscarProveedor(Integer.parseInt((String) tablaproveedor.getValueAt(r, 0)));
							String proveedor = p.getProveedor();
							if (proveedor != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdproveedor_(p.getIdproveedor());
								proveedorSeleccionado(p);
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
						tablaproveedor.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablaproveedor.getSelectedRow();
					if (tablaproveedor.getValueAt(r, 0) != null) {
						Proveedor p = OpProveedor
								.BuscarProveedor(Integer.parseInt((String) tablaproveedor.getValueAt(r, 0)));
						String proveedor = p.getProveedor();
						if (proveedor != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdproveedor_(p.getIdproveedor());
							proveedorSeleccionado(p);
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
		tablaproveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!tproveedor.getText().equals("") || !tdireccion.getText().equals("")
						|| !ttelefono.getText().equals("") || !tcelular.getText().equals("")
						|| !tnit.getText().equals("") || !tdescripcion.getText().equals(""))
						&& getIdproveedor_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar proveedor",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablaproveedor.getSelectedRow();
						if (tablaproveedor.getValueAt(r, 0) != null) {
							Proveedor p = OpProveedor
									.BuscarProveedor(Integer.parseInt((String) tablaproveedor.getValueAt(r, 0)));
							String proveedor = p.getProveedor();
							if (proveedor != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdproveedor_(p.getIdproveedor());
								proveedorSeleccionado(p);
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
						tablaproveedor.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablaproveedor.getSelectedRow();
					if (tablaproveedor.getValueAt(r, 0) != null) {
						Proveedor p = OpProveedor
								.BuscarProveedor(Integer.parseInt((String) tablaproveedor.getValueAt(r, 0)));
						String proveedor = p.getProveedor();
						if (proveedor != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdproveedor_(p.getIdproveedor());
							proveedorSeleccionado(p);
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

		tablaproveedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaproveedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaproveedor.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablaproveedor.setSelectionForeground(new Color(26, 115, 232));
		tablaproveedor.setSelectionBackground(new Color(232, 240, 254));

		tablaproveedor.setBorder(null);
		tablaproveedor.setRowHeight(20);
		JTableHeader th = tablaproveedor.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablaproveedor.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablaproveedor);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameProveedor.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameProveedor.getContentPane().add(btnvolver);
	}
}
