package formularios;

import javax.swing.JFrame;
import clases.TipoUsuario;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpUsuario;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormUsuario_ {

	private JFrame frameUsuario;
	private Usuario usuario;
	private JTextField tci;
	private JTextField tnombre;
	private JTextField ttelefono;
	private JTextField tdireccion;
	private JTextField tcelular;
	private JTextField tfecha;
	private JTextField tclave;
	private JComboBox<TipoUsuario> ttipo;
	private JTextField tsearch;
	private JTable tablausuario;
	private int idusuario_ = 0;
	private JLabel testado;
	private int ci_ = 0;
	private FormMenu formmenu;

	public int getCi_() {
		return ci_;
	}

	public void setCi_(int ci_) {
		this.ci_ = ci_;
	}

	public int getIdusuario_() {
		return idusuario_;
	}

	public void setIdusuario_(int idusuario_) {
		this.idusuario_ = idusuario_;
	}

	
	
	public JFrame getFrameUsuario() {
		return frameUsuario;
	}

	public void setFrameUsuario(JFrame frameUsuario) {
		this.frameUsuario = frameUsuario;
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablausuario.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(val),
				new String[] { "ID", "CI", "NOMBRE", "TIPO", "FECHA REGISTRO" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablausuario.getColumnModel().getColumn(0).setResizable(false);
		tablausuario.getColumnModel().getColumn(0).setPreferredWidth(50);
		tablausuario.getColumnModel().getColumn(0).setMinWidth(50);
		tablausuario.getColumnModel().getColumn(0).setMaxWidth(50);
		tablausuario.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablausuario.getColumnModel().getColumn(1).setResizable(false);
		tablausuario.getColumnModel().getColumn(1).setPreferredWidth(110);
		tablausuario.getColumnModel().getColumn(1).setMinWidth(110);
		tablausuario.getColumnModel().getColumn(1).setMaxWidth(110);
		tablausuario.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		tablausuario.getColumnModel().getColumn(2).setResizable(false);
		tablausuario.getColumnModel().getColumn(2).setPreferredWidth(300);
		tablausuario.getColumnModel().getColumn(2).setMinWidth(300);
		tablausuario.getColumnModel().getColumn(2).setMaxWidth(300);

		tablausuario.getColumnModel().getColumn(3).setResizable(false);
		tablausuario.getColumnModel().getColumn(3).setPreferredWidth(140);
		tablausuario.getColumnModel().getColumn(3).setMinWidth(140);
		tablausuario.getColumnModel().getColumn(3).setMaxWidth(140);
		tablausuario.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablausuario.getColumnModel().getColumn(4).setResizable(false);
		tablausuario.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
	}

	public int buscarUsuarioTabla(int idusuario) {
		if (idusuario != 0) {
			for (int i = 0; i < tablausuario.getRowCount(); i++) {
				if (tablausuario.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablausuario.getValueAt(i, 0)) == idusuario) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int buscarUsuarioTablaCi(int ci) {
		if (ci != 0) {
			for (int i = 0; i < tablausuario.getRowCount(); i++) {
				if (tablausuario.getValueAt(i, 1) != null) {
					if (Integer.parseInt((String) tablausuario.getValueAt(i, 1)) == ci) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void usuarioseleccionado(Usuario u) {
		setIdusuario_(u.getIdusuario());
		setCi_(u.getCi());
		tci.setText(u.getCi() + "");
		tnombre.setText(u.getNombre());
		tcelular.setText(u.getCelular());
		ttelefono.setText(u.getTelefono());
		for (int i = 0; i < ttipo.getItemCount(); i++) {
			if (ttipo.getItemAt(i).getIdtipo() == u.getIdtipo()) {
				ttipo.setSelectedIndex(i);
				i = ttipo.getItemCount();
			}
		}
		tdireccion.setText(u.getDireccion());
		tclave.setText(u.getClave());
		if (u.getEstado() == 1) {
			testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconActivate.png")));
			testado.setToolTipText("Activo");
		} else {
			testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconUnActivate.png")));
			testado.setToolTipText("Inactivo");
		}
		tfecha.setText(u.getFecharegistro());

	}

	public void limpiarFormulario() {
		setIdusuario_(0);
		tci.setText("");
		tnombre.setText("");
		tcelular.setText("");
		ttelefono.setText("");
		ttipo.setSelectedIndex(0);
		tdireccion.setText("");
		tclave.setText("");
		testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tfecha.setText(dtf.format(now));

	}

	public FormUsuario_(Usuario u, FormMenu fm) {
		formmenu = fm;
		usuario = u;
		initialize();
		frameUsuario.setLocationRelativeTo(null);
	}

	private void initialize() {
		frameUsuario = new JFrame();
		frameUsuario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameUsuario.getContentPane().setBackground(Color.WHITE);
		frameUsuario.getContentPane().setLayout(null);

		JLabel labelusuarioregistro = new JLabel(
				"USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: " + usuario.getTipo() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameUsuario.getContentPane().add(labelusuarioregistro);

		JLabel labelcliente = new JLabel("REGISTRO DE USUARIOS");
		labelcliente.setHorizontalAlignment(SwingConstants.CENTER);
		labelcliente.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelcliente.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelcliente.setBackground(Color.WHITE);
		labelcliente.setBounds(0, 20, 800, 30);
		frameUsuario.getContentPane().add(labelcliente);

		ttipo = OpUsuario.ListarTipoUsuario();
		ttipo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco(ttipo);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco(ttipo);
			}
		});
		ttipo.setForeground(Color.BLACK);
		ttipo.setOpaque(false);
		ttipo.setBackground(Color.WHITE);
		CajasTexto.ComboFueraDeFoco(ttipo);
		ttipo.setBounds(20, 150, 140, 25);
		ttipo.setBorder(null);
		frameUsuario.getContentPane().add(ttipo);

		JLabel septop = new JLabel("");
		septop.setBackground(Color.BLACK);
		septop.setOpaque(true);
		septop.setBounds(0, 0, 800, 1);
		frameUsuario.getContentPane().add(septop);

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
		tci.setToolTipText("");
		tci.setHorizontalAlignment(SwingConstants.LEFT);
		tci.setForeground(Color.BLACK);
		tci.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tci.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tci);
		tci.setAlignmentX(1.0f);
		tci.setBounds(20, 90, 140, 25);
		frameUsuario.getContentPane().add(tci);

		JLabel lci = new JLabel("CI *");
		lci.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lci.setBounds(20, 70, 140, 20);
		frameUsuario.getContentPane().add(lci);

		JLabel lnombre = new JLabel("NOMBRE *");
		lnombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnombre.setBounds(180, 70, 300, 20);
		frameUsuario.getContentPane().add(lnombre);

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
		frameUsuario.getContentPane().add(tnombre);

		JLabel ltelefono = new JLabel("TELÉFONO");
		ltelefono.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltelefono.setBounds(500, 70, 140, 20);
		frameUsuario.getContentPane().add(ltelefono);

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
		frameUsuario.getContentPane().add(ttelefono);

		JLabel ldireccion = new JLabel("DIRECCIÓN");
		ldireccion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldireccion.setBounds(180, 130, 300, 20);
		frameUsuario.getContentPane().add(ldireccion);

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
		frameUsuario.getContentPane().add(tdireccion);

		JLabel lcelular = new JLabel("CELULAR");
		lcelular.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcelular.setBounds(500, 130, 140, 20);
		frameUsuario.getContentPane().add(lcelular);

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
		frameUsuario.getContentPane().add(tcelular);

		JLabel ltipo = new JLabel("TIPO USUARIO *");
		ltipo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltipo.setBounds(20, 130, 140, 20);
		frameUsuario.getContentPane().add(ltipo);

		JLabel lestado = new JLabel("ESTADO");
		lestado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lestado.setBounds(20, 190, 64, 20);
		frameUsuario.getContentPane().add(lestado);

		testado = new JLabel("");
		testado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		testado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (testado.getToolTipText().equals("Inactivo")) {
					testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconActivate.png")));
					testado.setToolTipText("Activo");
				} else {
					testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconUnActivate.png")));
					testado.setToolTipText("Inactivo");
				}
			}
		});
		testado.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
		testado.setIconTextGap(10);
		testado.setHorizontalTextPosition(SwingConstants.CENTER);
		testado.setHorizontalAlignment(SwingConstants.CENTER);
		testado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		testado.setBounds(20, 210, 64, 25);
		frameUsuario.getContentPane().add(testado);

		JLabel lblFechaRegistro = new JLabel("FECHA REGISTRO");
		lblFechaRegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaRegistro.setBounds(180, 190, 140, 20);
		frameUsuario.getContentPane().add(lblFechaRegistro);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		tfecha = new JTextField(dtf.format(now));
		tfecha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tfecha);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tfecha);
			}
		});
		tfecha.setToolTipText("");

		tfecha.setHorizontalAlignment(SwingConstants.LEFT);
		tfecha.setForeground(Color.BLACK);
		tfecha.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tfecha.setEditable(false);
		tfecha.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tfecha);
		tfecha.setBackground(Color.WHITE);
		tfecha.setAlignmentX(1.0f);
		tfecha.setBounds(180, 210, 140, 25);
		frameUsuario.getContentPane().add(tfecha);

		JLabel lclave = new JLabel("CLAVE *");
		lclave.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lclave.setBounds(340, 190, 140, 20);
		frameUsuario.getContentPane().add(lclave);

		tclave = new JTextField();
		tclave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tclave);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tclave);
			}
		});
		tclave.setToolTipText("");
		tclave.setHorizontalAlignment(SwingConstants.LEFT);
		tclave.setForeground(Color.BLACK);
		tclave.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tclave.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tclave);
		tclave.setAlignmentX(1.0f);
		tclave.setBounds(340, 210, 140, 25);
		frameUsuario.getContentPane().add(tclave);

		JLabel ds = new JLabel("NUEVO");
		ds.setHorizontalTextPosition(SwingConstants.RIGHT);
		ds.setHorizontalAlignment(SwingConstants.RIGHT);
		ds.setFont(new Font("Segoe UI", Font.BOLD, 12));
		ds.setBounds(705, 70, 75, 45);
		frameUsuario.getContentPane().add(ds);

		JLabel lblActualizar = new JLabel("ACTUALIZAR");
		lblActualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblActualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblActualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblActualizar.setBounds(705, 130, 75, 45);
		frameUsuario.getContentPane().add(lblActualizar);

		JLabel btnactualizar = new JLabel("");
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
					int r = tablausuario.getSelectedRow();
					if (tablausuario.getSelectedRow() < 0) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario.", "Mensaje", 1);
					} else {

						if (tci.getText().equals("") || tnombre.getText().equals("") || tclave.getText().equals("")
								|| ttipo.getSelectedIndex() < 1) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios.", "Mensaje", 2);
						} else if (OpUsuario.BuscarCiUsuarioActualizar(Integer.parseInt(tci.getText()),
								Integer.parseInt((String) tablausuario.getValueAt(r, 1)))) {
							JOptionPane.showMessageDialog(null,
									"Ya existe un usuario registrado con ese número de cédula de identidad.",
									"Cédula de identidad repetida", 0);
						} else {

							TipoUsuario tu = (TipoUsuario) ttipo.getSelectedItem();
							int estado = 0;
							if (testado.getToolTipText().equals("Activo")) {
								estado = 1;
							}

							Usuario us = new Usuario(tu.getIdtipo(), tu.getTipo(), getIdusuario_(),
									Integer.parseInt(tci.getText()), estado, tnombre.getText(), ttelefono.getText(),
									tcelular.getText(), "", tclave.getText(), tdireccion.getText());
							if (OpUsuario.ActualizarUsuario(us)) {
								JOptionPane.showMessageDialog(null,
										"Se ha actualizado los datos del cliente correctamente.",
										"Actualización exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								int rr = buscarUsuarioTablaCi(us.getCi());
								if (rr != -1) {
									tablausuario.setRowSelectionInterval(rr, rr);
									tablausuario.requestFocus();
									tablausuario.changeSelection(rr, 0, false, false);

								} else {
									btnactualizar.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(null, "No se ha podido actualizar al usuario.",
										"Actualización fállida", 0);
							}

						}

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
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconClientUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setEnabled(false);
		btnactualizar.setBounds(660, 130, 45, 45);
		frameUsuario.getContentPane().add(btnactualizar);

		JLabel lblEliminar = new JLabel("ELIMINAR");
		lblEliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblEliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblEliminar.setBounds(705, 190, 75, 45);
		frameUsuario.getContentPane().add(lblEliminar);

		JLabel btnregistrar = new JLabel("");
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
					if (btnregistrar.isEnabled()) {

						btnregistrar.setIcon(
								new ImageIcon(FormCliente.class.getResource("/iconos/iconClientFixedHover.png")));
						btnregistrar.setEnabled(false);
						if (tci.getText().equals("") || tnombre.getText().equals("") || tclave.getText().equals("")
								|| ttipo.getSelectedIndex() <= 0) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje",
									2);
						} else {
							int ci = Integer.parseInt(tci.getText());
							if (OpUsuario.BuscarCiUsuario(ci)) {
								JOptionPane.showMessageDialog(null,
										"Hay un usuario registrado con ese número de cédula de identidad.\nPor favor ingrese otra número de cédula de identidad.",
										"Error", 2);
							} else {
								TipoUsuario tp = (TipoUsuario) ttipo.getSelectedItem();
								String nombre = tnombre.getText().toUpperCase();
								String celular = tcelular.getText().toUpperCase();
								String telefono = ttelefono.getText().toUpperCase();
								String clave = tclave.getText().toUpperCase();
								String direccion = tdireccion.getText().toUpperCase();
								String fecharegistro = tfecha.getText();
								int estado = 0;
								if (testado.getToolTipText().equals("Activo")) {
									estado = 1;
								}
								Usuario us = new Usuario(tp.getIdtipo(), tp.getTipo(), 0, ci, estado, nombre, telefono,
										celular, fecharegistro, clave, direccion);
								if (OpUsuario.InsertarUsuario(us)) {
									JOptionPane.showMessageDialog(null, "Se ha registrado al usuario correctamente.",
											"Registro exitoso", 1);
									if (tsearch.getText() != null) {
										cargarTabla(tsearch.getText());
									} else {
										cargarTabla("");
									}
									limpiarFormulario();
									int r = buscarUsuarioTablaCi(us.getCi());
									if (r != -1) {
										// tablacliente.setRowSelectionInterval(r, r);
										tablausuario.requestFocus();
										tablausuario.changeSelection(r, 0, true, true);
									} else {
										btnregistrar.requestFocus();
									}
								} else {
									JOptionPane.showMessageDialog(null,
											"Ha ocurrido un error, no se ha podido registrar al usuario.", "Error", 0);
								}

							}
						}
						btnregistrar.setEnabled(true);
					}

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
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconClientFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameUsuario.getContentPane().add(btnregistrar);
		JLabel btneliminar = new JLabel("");
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
					int r = tablausuario.getSelectedRow();
					;
					if (r < 0 || tablausuario.getValueAt(r, 0) == null) {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario.", "Mensaje", 1);
						btneliminar.setEnabled(true);
					} else {
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar a este usuario?",
								"Limpiar usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (OpUsuario.EliminarUsuario(getIdusuario_())) {
								limpiarFormulario();
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								btnregistrar.setEnabled(true);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								JOptionPane.showMessageDialog(null, "Se ha eliminado al usuario correctamente.",
										"Mensaje", 1);
							} else {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								JOptionPane.showMessageDialog(null, "No se ha podido eliminar al usuario.", "Error", 1);
							}
						} else {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
						}
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
		btneliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btneliminar.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setEnabled(false);
		btneliminar.setBounds(660, 190, 45, 45);
		frameUsuario.getContentPane().add(btneliminar);
		JLabel lblRegistrar = new JLabel("REGISTRAR      ");
		lblRegistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblRegistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblRegistrar.setBounds(545, 190, 95, 45);
		frameUsuario.getContentPane().add(lblRegistrar);

		JLabel labelseparado = new JLabel("");
		labelseparado.setOpaque(true);
		labelseparado.setBackground(Color.GRAY);
		labelseparado.setBounds(0, 249, 800, 1);
		frameUsuario.getContentPane().add(labelseparado);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarUsuarioTabla(getIdusuario_());
					if (sel != -1) {
						tablausuario.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablausuario.changeSelection(sel, 0, false, false);
						btnregistrar.setEnabled(false);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdusuario_() != 0) {
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
		tsearch.setForeground(Color.BLACK);
		tsearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch.setColumns(10);
		tsearch.setBorder(new LineBorder(Color.GRAY, 1, false));
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(525, 265, 255, 25);
		frameUsuario.getContentPane().add(tsearch);

		JLabel labelsearch = new JLabel("");
		labelsearch.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconSearch_.png")));
		labelsearch.setToolTipText("Buscar usuario");
		labelsearch.setIconTextGap(10);
		labelsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		labelsearch.setHorizontalAlignment(SwingConstants.CENTER);
		labelsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelsearch.setBounds(500, 265, 25, 25);
		frameUsuario.getContentPane().add(labelsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameUsuario.getContentPane().add(scrollpanetabla);

		tablausuario = new JTable();
		tablausuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();

				if (lsm.isSelectionEmpty()) {
				} else {
					int r = lsm.getMinSelectionIndex();
					if ((!tci.getText().equals("") || !tnombre.getText().equals("") || ttipo.getSelectedIndex() > 0
							|| !tclave.getText().equals("")) && getIdusuario_() == 0
							&& tablausuario.getValueAt(r, 0) != null) {
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null,
								"Se borraran los datos del cliente que intenta registrar.\n¿Está de acuerdo?",
								"Seleccionar cliente", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (r != -1) {
								System.out.println(tablausuario.getValueAt(r, 0) + "yyy");
								if (tablausuario.getValueAt(r, 0) != null) {
									Usuario us = OpUsuario
											.Buscarusuario(Integer.parseInt((String) tablausuario.getValueAt(r, 0)));
									String nomb = us.getNombre();
									if (nomb != null) {
										btneliminar.setEnabled(true);
										btnactualizar.setEnabled(true);
										btnregistrar.setEnabled(false);
										setIdusuario_(us.getIdusuario());
										usuarioseleccionado(us);
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
						} else {
							tablausuario.getSelectionModel().clearSelection();
						}
					} else {
						System.out.println(tablausuario.getValueAt(r, 0) + "xx");
						if (r != -1) {
							if (tablausuario.getValueAt(r, 0) != null) {
								Usuario us = OpUsuario
										.Buscarusuario(Integer.parseInt((String) tablausuario.getValueAt(r, 0)));
								String nomb = us.getNombre();
								System.out.println(nomb);
								if (nomb != null) {
									btnregistrar.setEnabled(false);
									btneliminar.setEnabled(true);
									btnactualizar.setEnabled(true);
									setIdusuario_(us.getIdusuario());
									usuarioseleccionado(us);
								} else {
									btnregistrar.setEnabled(true);
									btneliminar.setEnabled(false);
									btnactualizar.setEnabled(false);
									limpiarFormulario();
								}
							} else {
								System.out.println("aqui");
								btnregistrar.setEnabled(true);
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								limpiarFormulario();
							}
						}
					}
				}
			}
		});
		tablausuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablausuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablausuario.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablausuario.setSelectionForeground(new Color(26, 115, 232));
		tablausuario.setSelectionBackground(new Color(232, 240, 254));

		tablausuario.setBorder(null);
		tablausuario.setRowHeight(20);
		JTableHeader th = tablausuario.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablausuario.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));

		scrollpanetabla.setViewportView(tablausuario);

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

			}

			@Override
			public void mouseReleased(MouseEvent e) {

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
		});
		btnnuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnuevo.setIcon(new ImageIcon(FormUsuario_.class.getResource("/iconos/iconNewFixed.png")));
		btnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnuevo.setHorizontalAlignment(SwingConstants.CENTER);
		btnnuevo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnnuevo.setBounds(660, 70, 45, 45);
		frameUsuario.getContentPane().add(btnnuevo);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameUsuario.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameUsuario.getContentPane().add(btnvolver);
		frameUsuario.setResizable(false);
		frameUsuario.setTitle("MODULO  USUARIO");
		frameUsuario.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormUsuario_.class.getResource("/iconos/iconUser.png")));
		frameUsuario.setBounds(100, 100, 816, 618);
		frameUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
