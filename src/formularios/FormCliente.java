package formularios;

import javax.swing.JFrame;
import clases.Cliente;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpCliente;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormCliente {

	private JFrame frameCliente;
	private JTextField tci;
	private JTextField tnombre;
	private JTextField tdireccion;
	private JTextField ttelefono;
	private JTextField tcelular;
	private JTextField tnit;
	private JTextField tfechaderegistro;
	private JTextField tsearch;
	private JTable tablacliente;
	private int idcliente_ = 0;
	private int ci_ = -1;
	private JLabel testado;
	private FormMenu formmenu;
	private Usuario usuario;
	

	public JFrame getFrameCliente() {
		return frameCliente;
	}

	public void setFrameCliente(JFrame frameCliente) {
		this.frameCliente = frameCliente;
	}

	public int getIdcliente_() {
		return idcliente_;
	}

	public void setIdcliente_(int idcliente_) {
		this.idcliente_ = idcliente_;
	}

	public int getCi_() {
		return ci_;
	}

	public void setCi_(int ci_) {
		this.ci_ = ci_;
	}

	public void clienteSeleccionado(Cliente c) {
		setIdcliente_(c.getIdcliente());
		setCi_(c.getCi());
		tci.setText("" + c.getCi());
		tnombre.setText(c.getNombre());
		tcelular.setText(c.getCelular());
		ttelefono.setText(c.getTelefono());
		tdireccion.setText(c.getDireccion());
		tnit.setText(c.getNit());
		tfechaderegistro.setText(c.getFecharegistro());
		if (c.getEstado() == 1) {
			testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
			testado.setToolTipText("Activo");
		} else {
			testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
			testado.setToolTipText("Inactivo");
		}

	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablacliente.setModel(new DefaultTableModel(OpCliente.ListarCliente(val),
				new String[] { "ID", "CI", "NOMBRE", "TELÉFONO", "CELULAR", "NIT", "FECHA REGISTRO" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablacliente.getColumnModel().getColumn(0).setResizable(false);
		tablacliente.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablacliente.getColumnModel().getColumn(0).setMinWidth(40);
		tablacliente.getColumnModel().getColumn(0).setMaxWidth(40);
		tablacliente.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(1).setResizable(false);
		tablacliente.getColumnModel().getColumn(1).setPreferredWidth(80);
		tablacliente.getColumnModel().getColumn(1).setMinWidth(80);
		tablacliente.getColumnModel().getColumn(1).setMaxWidth(80);
		tablacliente.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(2).setResizable(false);
		tablacliente.getColumnModel().getColumn(2).setPreferredWidth(215);
		tablacliente.getColumnModel().getColumn(2).setMinWidth(215);
		tablacliente.getColumnModel().getColumn(2).setMaxWidth(215);

		tablacliente.getColumnModel().getColumn(3).setResizable(false);
		tablacliente.getColumnModel().getColumn(3).setPreferredWidth(95);
		tablacliente.getColumnModel().getColumn(3).setMinWidth(95);
		tablacliente.getColumnModel().getColumn(3).setMaxWidth(95);
		tablacliente.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(4).setResizable(false);
		tablacliente.getColumnModel().getColumn(4).setPreferredWidth(95);
		tablacliente.getColumnModel().getColumn(4).setMinWidth(95);
		tablacliente.getColumnModel().getColumn(4).setMaxWidth(95);
		tablacliente.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(5).setResizable(false);
		tablacliente.getColumnModel().getColumn(5).setPreferredWidth(95);
		tablacliente.getColumnModel().getColumn(5).setMinWidth(95);
		tablacliente.getColumnModel().getColumn(5).setMaxWidth(95);
		tablacliente.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(6).setResizable(false);
		tablacliente.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
	}

	public void limpiarFormulario() {
		setIdcliente_(0);
		setCi_(-1);
		tci.setText("");
		tnombre.setText("");
		ttelefono.setText("");
		tcelular.setText("");
		tdireccion.setText("");
		tnit.setText("");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tfechaderegistro.setText(dtf.format(now));
		testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
	}

	public int buscarClienteTabla(int idcliente) {
		if (idcliente != 0) {
			for (int i = 0; i < tablacliente.getRowCount(); i++) {
				if (tablacliente.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablacliente.getValueAt(i, 0)) == idcliente) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int buscarClienteTablaCi(int ci) {
		if (ci != 0) {
			for (int i = 0; i < tablacliente.getRowCount(); i++) {
				if (tablacliente.getValueAt(i, 1) != null) {
					if (Integer.parseInt((String) tablacliente.getValueAt(i, 1)) == ci) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public FormCliente(Usuario u, FormMenu fm) {
		formmenu = fm;
		usuario = u;
		initialize();
		this.frameCliente.setLocationRelativeTo(null);

	}

	private void initialize() {
		frameCliente = new JFrame();
		frameCliente.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameCliente.getContentPane().setLocale(new Locale("es", "BO"));
		frameCliente.getContentPane().setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		frameCliente.getContentPane().setBackground(Color.WHITE);
		frameCliente.getContentPane().setLayout(null);
		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameCliente.getContentPane().add(labelusuarioregistro);

		JLabel labelcliente = new JLabel("REGISTRO DE CLIENTES");
		labelcliente.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelcliente.setBackground(Color.WHITE);
		labelcliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelcliente.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelcliente.setBounds(0, 20, 800, 30);
		frameCliente.getContentPane().add(labelcliente);

		JLabel lci = new JLabel("CI *");
		lci.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lci.setBounds(20, 70, 140, 20);
		frameCliente.getContentPane().add(lci);

		tci = new JTextField();
		tci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tci);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tci);
			}
		});
		CajasTexto.soloNumeros(tci);
		tci.setToolTipText("");
		tci.setHorizontalAlignment(SwingConstants.LEFT);
		tci.setForeground(Color.BLACK);
		tci.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tci.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tci);
		tci.setAlignmentX(1.0f);
		tci.setBounds(20, 90, 140, 25);
		frameCliente.getContentPane().add(tci);

		JLabel lnombre = new JLabel("NOMBRE *");
		lnombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnombre.setBounds(180, 70, 300, 20);
		frameCliente.getContentPane().add(lnombre);

		tnombre = new JTextField();
		tnombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tnombre);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tnombre);
			}
		});
		tnombre.setToolTipText("");
		tnombre.setHorizontalAlignment(SwingConstants.LEFT);
		tnombre.setForeground(Color.BLACK);
		tnombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tnombre.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tnombre);
		tnombre.setAlignmentX(1.0f);
		tnombre.setBounds(180, 90, 300, 25);
		frameCliente.getContentPane().add(tnombre);

		JLabel ldireccion = new JLabel("DIRECCIÓN");
		ldireccion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldireccion.setBounds(180, 130, 300, 20);
		frameCliente.getContentPane().add(ldireccion);

		tdireccion = new JTextField();
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
		CajasTexto.TxtFueraDeFoco(tdireccion);
		tdireccion.setAlignmentX(1.0f);
		tdireccion.setBounds(180, 150, 300, 25);
		frameCliente.getContentPane().add(tdireccion);

		ttelefono = new JTextField();
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
		CajasTexto.TxtFueraDeFoco(ttelefono);
		ttelefono.setAlignmentX(1.0f);
		ttelefono.setBounds(500, 90, 140, 25);
		frameCliente.getContentPane().add(ttelefono);

		JLabel ltelefono = new JLabel("TELÉFONO");
		ltelefono.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltelefono.setBounds(500, 70, 140, 20);
		frameCliente.getContentPane().add(ltelefono);

		JLabel lcelular = new JLabel("CELULAR");
		lcelular.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcelular.setBounds(500, 130, 140, 20);
		frameCliente.getContentPane().add(lcelular);

		tcelular = new JTextField();
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
		CajasTexto.TxtFueraDeFoco(tcelular);
		tcelular.setAlignmentX(1.0f);
		tcelular.setBounds(500, 150, 140, 25);
		frameCliente.getContentPane().add(tcelular);

		JLabel lnit = new JLabel("NIT");
		lnit.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnit.setBounds(20, 130, 140, 20);
		frameCliente.getContentPane().add(lnit);

		tnit = new JTextField();
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
		CajasTexto.TxtFueraDeFoco(tnit);
		tnit.setAlignmentX(1.0f);
		tnit.setBounds(20, 150, 140, 25);
		frameCliente.getContentPane().add(tnit);

		JLabel lestado = new JLabel("ESTADO");
		lestado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lestado.setBounds(20, 190, 64, 20);
		frameCliente.getContentPane().add(lestado);

		JLabel lfechaderegistro = new JLabel("FECHA DE REGISTRO");
		lfechaderegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfechaderegistro.setBounds(180, 190, 300, 20);
		frameCliente.getContentPane().add(lfechaderegistro);

		JLabel ds = new JLabel("NUEVO");
		ds.setHorizontalTextPosition(SwingConstants.RIGHT);
		ds.setHorizontalAlignment(SwingConstants.RIGHT);
		ds.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ds.setBounds(705, 70, 75, 45);
		frameCliente.getContentPane().add(ds);

		JLabel lblActualizar = new JLabel("ACTUALIZAR");
		lblActualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblActualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblActualizar.setBounds(705, 130, 75, 45);
		frameCliente.getContentPane().add(lblActualizar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblEliminar.setBounds(705, 190, 75, 45);
		frameCliente.getContentPane().add(lblEliminar);

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
					if (tci.getText().equals("") || tnombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje", 1);
					} else {
						int ci = Integer.parseInt(tci.getText());
						if (OpCliente.BuscarClienteCi(ci, getCi_())) {
							JOptionPane.showMessageDialog(null,
									"Hay un cliente registrado con ese número de cédula de identidad.\nPor favor ingrese otra número de cédula de identidad.",
									"Error", 2);
						} else {
							String nombre = tnombre.getText().toUpperCase();
							String celular = tcelular.getText().toUpperCase();
							String telefono = ttelefono.getText().toUpperCase();
							String nit = tnit.getText().toUpperCase();
							String direccion = tdireccion.getText().toUpperCase();
							String fecharegistro = tfechaderegistro.getText();
							int estado = 0;
							if (testado.getToolTipText().equals("Activo")) {
								estado = 1;
							}
							Cliente cl = new Cliente(0, ci, estado, nombre, telefono, celular, fecharegistro, direccion,
									nit);
							if (OpCliente.RegistraCliente(cl)) {
								JOptionPane.showMessageDialog(null, "Se ha registrado al cliente correctamente.",
										"Registro exitoso", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								limpiarFormulario();
								int r = buscarClienteTablaCi(cl.getCi());
								if (r != -1) {
									// tablacliente.setRowSelectionInterval(r, r);
									tablacliente.requestFocus();
									tablacliente.changeSelection(r, 0, true, true);
								} else {
									btnregistrar.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido registrar al cliente.", "Error", 0);
							}

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
		btnregistrar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameCliente.getContentPane().add(btnregistrar);

		tfechaderegistro = new JTextField();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tfechaderegistro.setText(dtf.format(now));
		tfechaderegistro.setBackground(Color.WHITE);
		tfechaderegistro.setEditable(false);
		tfechaderegistro.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tfechaderegistro);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tfechaderegistro);
			}
		});
		tfechaderegistro.setToolTipText("");
		tfechaderegistro.setHorizontalAlignment(SwingConstants.LEFT);
		tfechaderegistro.setForeground(Color.BLACK);
		tfechaderegistro.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tfechaderegistro.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tfechaderegistro);
		tfechaderegistro.setAlignmentX(1.0f);
		tfechaderegistro.setBounds(180, 210, 300, 25);
		frameCliente.getContentPane().add(tfechaderegistro);

		JLabel btnactualizar = new JLabel("");
		btnactualizar.setEnabled(false);
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
					if (getCi_() != -1 && getIdcliente_() != 0) {
						if (tci.getText().equals("") || tnombre.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje",
									1);
						} else {
							int ci = Integer.parseInt(tci.getText());
							if (OpCliente.BuscarClienteCi(ci, getCi_())) {
								JOptionPane.showMessageDialog(null,
										"Hay un cliente registrado con ese número de cédula de identidad.\nPor favor ingrese otra número de cédula de identidad.",
										"Error", 2);
							} else {
								int idcliente = getIdcliente_();
								String nombre = tnombre.getText().toUpperCase();
								String celular = tcelular.getText().toUpperCase();
								String telefono = ttelefono.getText().toUpperCase();
								String nit = tnit.getText().toUpperCase();
								String direccion = tdireccion.getText().toUpperCase();
								String fecharegistro = tfechaderegistro.getText();
								int estado = 0;
								if (testado.getToolTipText().equals("Activo")) {
									estado = 1;
								}
								Cliente cl = new Cliente(idcliente, ci, estado, nombre, telefono, celular,
										fecharegistro, direccion, nit);
								if (OpCliente.ActualizarCliente(cl)) {

									JOptionPane.showMessageDialog(null,
											"Se ha actualizado los datos del cliente correctamente.",
											"Actualización exitosa", 1);
									if (tsearch.getText() != null) {
										cargarTabla(tsearch.getText());
									} else {
										cargarTabla("");
									}
									int r = buscarClienteTablaCi(cl.getCi());
									if (r != -1) {
										tablacliente.setRowSelectionInterval(r, r);
										tablacliente.requestFocus();
										tablacliente.changeSelection(r, 0, false, false);

									} else {
										btnactualizar.requestFocus();
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Ha ocurrido un error, no se ha podido actualizar los datos del cliente.",
											"Error", 0);
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente a actualizar.", "Mensaje", 1);

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
		btnactualizar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconClientUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setBounds(660, 130, 45, 45);
		frameCliente.getContentPane().add(btnactualizar);

		JLabel btneliminar = new JLabel("");
		btneliminar.setEnabled(false);
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

					if (getCi_() != -1 && getIdcliente_() != 0) {

						int idcliente = getIdcliente_();
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar a este usuario?",
								"Eliminar usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (OpCliente.EliminarCliente(idcliente)) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado al cliente correctamente.",
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
										"Ha ocurrido un error, no se ha podido eliminar al cliente.", "Error", 0);
								btneliminar.setEnabled(true);
							}

						} else {

							btneliminar.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente a actualizar.", "Mensaje", 1);
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
		btneliminar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setBounds(660, 190, 45, 45);
		frameCliente.getContentPane().add(btneliminar);

		JLabel btnnuevo = new JLabel("");
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
		btnnuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixed.png")));
		btnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnuevo.setHorizontalAlignment(SwingConstants.CENTER);
		btnnuevo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnnuevo.setBounds(660, 70, 45, 45);
		frameCliente.getContentPane().add(btnnuevo);

		JLabel lblRegistrar = new JLabel("REGISTRAR      ");
		lblRegistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRegistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRegistrar.setBounds(545, 190, 95, 45);
		frameCliente.getContentPane().add(lblRegistrar);

		testado = new JLabel("");
		testado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		testado.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
		testado.setIconTextGap(10);
		testado.setHorizontalTextPosition(SwingConstants.CENTER);
		testado.setHorizontalAlignment(SwingConstants.CENTER);
		testado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		testado.setBounds(20, 210, 64, 25);
		frameCliente.getContentPane().add(testado);

		JLabel labelsearch = new JLabel("");
		labelsearch.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSearch_.png")));
		labelsearch.setToolTipText("Buscar usuario");
		labelsearch.setIconTextGap(10);
		labelsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		labelsearch.setHorizontalAlignment(SwingConstants.CENTER);
		labelsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelsearch.setBounds(500, 265, 25, 25);
		frameCliente.getContentPane().add(labelsearch);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarClienteTabla(getIdcliente_());
					if (sel != -1) {
						tablacliente.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablacliente.changeSelection(sel, 0, false, false);
						btnregistrar.setEnabled(false);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdcliente_() != 0) {
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
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setHorizontalAlignment(SwingConstants.LEFT);
		tsearch.setForeground(Color.BLACK);
		tsearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch.setColumns(10);
		tsearch.setBorder(new LineBorder(Color.GRAY, 1, false));
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(525, 265, 255, 25);
		frameCliente.getContentPane().add(tsearch);

		JLabel labelseparado = new JLabel("");
		labelseparado.setOpaque(true);
		labelseparado.setBackground(Color.GRAY);
		labelseparado.setBounds(0, 249, 800, 1);
		frameCliente.getContentPane().add(labelseparado);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameCliente.getContentPane().add(scrollpanetabla);

		tablacliente = new JTable();
		tablacliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!tci.getText().equals("") || !tnombre.getText().equals("") || !ttelefono.getText().equals("")
						|| !tcelular.getText().equals("") || !tnit.getText().equals("")
						|| !tdireccion.getText().equals("")) && getIdcliente_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del cliente que intenta registrar.\n¿Está de acuerdo?",
							"Seleccionar cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							options, null);
					if (seleccion == 0) {
						int r = tablacliente.getSelectedRow();
						if (tablacliente.getValueAt(r, 0) != null) {
							Cliente c = OpCliente
									.BuscarCliente(Integer.parseInt((String) tablacliente.getValueAt(r, 0)));
							String nombre = c.getNombre();
							if (nombre != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdcliente_(c.getIdcliente());
								clienteSeleccionado(c);
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
						tablacliente.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablacliente.getSelectedRow();
					if (tablacliente.getValueAt(r, 0) != null) {
						Cliente c = OpCliente.BuscarCliente(Integer.parseInt((String) tablacliente.getValueAt(r, 0)));
						String nombre = c.getNombre();
						if (nombre != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdcliente_(c.getIdcliente());
							clienteSeleccionado(c);
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
				// tablacliente.getSelectionModel().clearSelection();
			}
		});

		tablacliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!tci.getText().equals("") || !tnombre.getText().equals("") || !ttelefono.getText().equals("")
						|| !tcelular.getText().equals("") || !tnit.getText().equals("")
						|| !tdireccion.getText().equals("")) && getIdcliente_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\t¿Está de acuerdo?", "Seleccionar cliente",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablacliente.getSelectedRow();
						if (tablacliente.getValueAt(r, 0) != null) {
							Cliente c = OpCliente
									.BuscarCliente(Integer.parseInt((String) tablacliente.getValueAt(r, 0)));
							String nombre = c.getNombre();
							if (nombre != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdcliente_(c.getIdcliente());
								clienteSeleccionado(c);
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
						tablacliente.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablacliente.getSelectedRow();
					if (tablacliente.getValueAt(r, 0) != null) {
						Cliente c = OpCliente.BuscarCliente(Integer.parseInt((String) tablacliente.getValueAt(r, 0)));
						String nombre = c.getNombre();
						if (nombre != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdcliente_(c.getIdcliente());
							clienteSeleccionado(c);
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
				// tablacliente.getSelectionModel().clearSelection();
			}
		});
		
		tablacliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablacliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablacliente.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablacliente.setSelectionForeground(new Color(26, 115, 232));
		tablacliente.setSelectionBackground(new Color(232, 240, 254));

		tablacliente.setBorder(null);
		tablacliente.setRowHeight(20);
		JTableHeader th = tablacliente.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablacliente.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablacliente);

		JLabel labelseparado_1 = new JLabel("");
		labelseparado_1.setOpaque(true);
		labelseparado_1.setBackground(Color.GRAY);
		labelseparado_1.setBounds(0, 0, 800, 1);
		frameCliente.getContentPane().add(labelseparado_1);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameCliente.dispose();
			}
		});
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameCliente.getContentPane().add(btnvolver);

		frameCliente.setForeground(Color.BLACK);
		frameCliente.setResizable(false);
		frameCliente.setBackground(Color.WHITE);
		frameCliente.setTitle("MODULO CLIENTE");
		frameCliente.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormCliente.class.getResource("/iconos/iconClient.png")));
		frameCliente.setBounds(100, 100, 816, 618);
		frameCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
