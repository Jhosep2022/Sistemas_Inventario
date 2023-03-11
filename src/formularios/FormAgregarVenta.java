package formularios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Cliente;
import clases.DetalleVenta;
import clases.Usuario;
import clases.Venta;
import decoradores.CajasTexto;
import operaciones.OpVenta;

public class FormAgregarVenta {

	private JFrame frameAgregarVenta;
	private JTextField tobservacion;
	private JTextField tfecha;
	private JTextField tcantidad;
	private FormAgregarVenta form = this;
	private JComboBox<Cliente> tcliente;
	private JTable tabladetalleventas;
	private Usuario usuario;
	private ArrayList<DetalleVenta> detalleventa = new ArrayList<DetalleVenta>();
	private Venta venta = new Venta();
	private JLabel ttotal;
	private int idproducto_ = 0;
	private boolean b = true;
	private FormVenta formventa;
	private int tipodeaccion;
	private int idcliente_ = 0;

	public JComboBox<Cliente> getTcliente() {
		return tcliente;
	}

	public void setTcliente(JComboBox<Cliente> tcliente) {
		this.tcliente = tcliente;
	}

	public int getIdcliente_() {
		return idcliente_;
	}

	public void setIdcliente_(int idcliente_) {
		this.idcliente_ = idcliente_;
	}

	public ArrayList<DetalleVenta> getDetalleventa() {
		return detalleventa;
	}

	public void setDetalleventa(ArrayList<DetalleVenta> detalleventa) {
		this.detalleventa = detalleventa;
	}

	public JFrame getFrameAgregarVenta() {
		return frameAgregarVenta;
	}

	public void setFrameAgregarVenta(JFrame frameAgregarVenta) {
		this.frameAgregarVenta = frameAgregarVenta;
	}

	public int getIdproducto_() {
		return idproducto_;
	}

	public void setIdproducto_(int idproducto_) {
		this.idproducto_ = idproducto_;
	}

	public void mostrarDetalles(int idproducto, boolean bandera) {
		setIdproducto_(idproducto);
		if (idproducto != 0 || bandera == false) {
			String datos[][] = new String[detalleventa.size()][6];
			float total = 0f;
			for (int i = 0; i < detalleventa.size(); i++) {
				datos[i][0] = (i + 1) + "";
				datos[i][1] = "  " + detalleventa.get(i).getProducto().getIdproducto() + "-"
						+ detalleventa.get(i).getProducto().getProducto();
				datos[i][2] = detalleventa.get(i).getProducto().getStock() + "";
				datos[i][3] = detalleventa.get(i).getCantidad() + "";
				datos[i][4] = String.format("%.2f", detalleventa.get(i).getProducto().getPrecioventa()) + "  ";
				datos[i][5] = String.format("%.2f",
						(detalleventa.get(i).getCantidad() * detalleventa.get(i).getProducto().getPrecioventa()))
						+ "  ";
				total = total
						+ (detalleventa.get(i).getCantidad() * detalleventa.get(i).getProducto().getPrecioventa());
			}
			ttotal.setText(String.format("%.2f", total) + "  ");
			cargarTabla(datos, idproducto, bandera);
		}
	}

	public void cargarTabla(String datos[][], int idproducto, boolean bandera) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		tabladetalleventas.setModel(new DefaultTableModel(datos,
				new String[] { "NRO.", "PRODUCTO", "STOCK", "CANT.", "PRECIO UNIT. Bs.", "SUBTOTAL Bs." }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tabladetalleventas.getColumnModel().getColumn(0).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(0).setPreferredWidth(45);
		tabladetalleventas.getColumnModel().getColumn(0).setMinWidth(45);
		tabladetalleventas.getColumnModel().getColumn(0).setMaxWidth(45);
		tabladetalleventas.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tabladetalleventas.getColumnModel().getColumn(1).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(1).setPreferredWidth(230);
		tabladetalleventas.getColumnModel().getColumn(1).setMinWidth(230);
		tabladetalleventas.getColumnModel().getColumn(1).setMaxWidth(230);

		tabladetalleventas.getColumnModel().getColumn(2).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(2).setPreferredWidth(45);
		tabladetalleventas.getColumnModel().getColumn(2).setMinWidth(45);
		tabladetalleventas.getColumnModel().getColumn(2).setMaxWidth(45);
		tabladetalleventas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tabladetalleventas.getColumnModel().getColumn(3).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(3).setPreferredWidth(45);
		tabladetalleventas.getColumnModel().getColumn(3).setMinWidth(45);
		tabladetalleventas.getColumnModel().getColumn(3).setMaxWidth(45);
		tabladetalleventas.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tabladetalleventas.getColumnModel().getColumn(4).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabladetalleventas.getColumnModel().getColumn(4).setMinWidth(110);
		tabladetalleventas.getColumnModel().getColumn(4).setMaxWidth(110);
		tabladetalleventas.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		tabladetalleventas.getColumnModel().getColumn(5).setResizable(false);
		tabladetalleventas.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		if (bandera) {
			for (int i = 0; i < tabladetalleventas.getRowCount(); i++) {
				int idp = Integer
						.parseInt((String.valueOf(tabladetalleventas.getValueAt(i, 1)).split("-"))[0].replace(" ", ""));
				if (idproducto == idp) {
					tabladetalleventas.setRowSelectionInterval(i, i);
					i = tabladetalleventas.getRowCount();
				}
			}
		}
	}

	public FormAgregarVenta(Venta v, int tipo, Usuario usuariocompra, FormVenta form) {
		tipodeaccion = tipo;
		usuario = usuariocompra;
		initialize(v, tipo);
		frameAgregarVenta.setLocationRelativeTo(null);
		formventa = form;
		// 1 = REGISTRAR
		// 2 = ACTUALIZAR
		if (tipo == 2) {
			venta.setFecharegistro(v.getFecharegistro());
			venta.setIdventa(v.getIdventa());
			venta.setListaventa(v.getListaventa());
			venta.setObservacion(v.getObservacion());
			venta.setCliente(v.getCliente());
			venta.setTotal(v.getTotal());
			venta.setUsuario(v.getUsuario());
			for (int i = 0; i < tcliente.getItemCount(); i++) {
				if (tcliente.getItemAt(i).getIdcliente() == venta.getCliente().getIdcliente()) {
					tcliente.setSelectedIndex(i);
					i = tcliente.getItemCount();
				}
			}
			detalleventa = venta.getListaventa();
			tfecha.setText(venta.getFecharegistro());
			tobservacion.setText(venta.getObservacion());
			tcantidad.setText("");
			mostrarDetalles(0, false);
		}
	}

	private void initialize(Venta v, int tipo) {
		frameAgregarVenta = new JFrame();
		frameAgregarVenta.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formventa.getFrameventa().setVisible(true);
			}
		});
		String titulo = "";
		if (tipo == 1) {
			titulo = "REGISTRAR";
		} else {
			titulo = "ACTUALIZAR";
		}

		frameAgregarVenta.setResizable(false);
		frameAgregarVenta.setTitle("AGREGAR VENTA");
		frameAgregarVenta.getContentPane().setBackground(Color.WHITE);
		frameAgregarVenta.getContentPane().setLayout(null);

		ttotal = new JLabel("");
		ttotal.setOpaque(true);
		ttotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(490, 534, 140, 25);
		frameAgregarVenta.getContentPane().add(ttotal);

		JLabel labeltitulo = new JLabel(titulo + " VENTA");
		labeltitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labeltitulo.setBorder(new EmptyBorder(0, 0, 0, 0));
		labeltitulo.setBackground(Color.WHITE);
		labeltitulo.setBounds(0, 20, 650, 30);
		frameAgregarVenta.getContentPane().add(labeltitulo);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 6550, 20);
		frameAgregarVenta.getContentPane().add(labelusuarioregistro);

		JLabel top = new JLabel("");
		top.setBackground(Color.BLACK);
		top.setOpaque(true);
		top.setBounds(0, 0, 650, 1);
		frameAgregarVenta.getContentPane().add(top);

		JLabel lproveedor = new JLabel("CLIENTE *");
		lproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproveedor.setBounds(20, 70, 295, 20);
		frameAgregarVenta.getContentPane().add(lproveedor);

		JLabel lobservacion = new JLabel("OBSERVACIÓN");
		lobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lobservacion.setBounds(20, 130, 610, 20);
		frameAgregarVenta.getContentPane().add(lobservacion);

		tcliente = OpVenta.ListarCliente();
		tcliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco__c(tcliente);
				tcliente.setBackground(Color.WHITE);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco__c(tcliente);
			}
		});
		tcliente.setForeground(Color.BLACK);
		tcliente.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		CajasTexto.ComboFueraDeFoco__c(tcliente);
		tcliente.setBackground(Color.WHITE);
		tcliente.setBounds(20, 90, 270, 25);
		frameAgregarVenta.getContentPane().add(tcliente);

		tobservacion = new JTextField();
		tobservacion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tobservacion);

			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tobservacion);
			}
		});
		tobservacion.setToolTipText("");
		tobservacion.setHorizontalAlignment(SwingConstants.LEFT);
		tobservacion.setForeground(Color.BLACK);
		tobservacion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tobservacion.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tobservacion);
		tobservacion.setBackground(Color.WHITE);
		tobservacion.setAlignmentX(1.0f);
		tobservacion.setBounds(20, 150, 610, 25);
		frameAgregarVenta.getContentPane().add(tobservacion);

		JLabel lsearch = new JLabel("");

		lsearch.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarVenta.setVisible(false);
				new FormAgregarCliente(form).getFrameSeleccionarCliente().setVisible(true);
			}
		});
		lsearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsearch.setIcon(new ImageIcon(FormAgregarVenta.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(290, 90, 25, 25);
		frameAgregarVenta.getContentPane().add(lsearch);

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
		tfecha.setEditable(false);
		tfecha.setToolTipText("");
		tfecha.setHorizontalAlignment(SwingConstants.LEFT);
		tfecha.setForeground(Color.BLACK);
		tfecha.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tfecha.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tfecha);
		tfecha.setBackground(Color.WHITE);
		tfecha.setAlignmentX(1.0f);
		tfecha.setBounds(335, 90, 295, 25);
		frameAgregarVenta.getContentPane().add(tfecha);

		JLabel lfecha = new JLabel("FECHA HORA");
		lfecha.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecha.setBounds(335, 70, 295, 20);
		frameAgregarVenta.getContentPane().add(lfecha);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setEnabled(false);
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 236, 610, 288);
		frameAgregarVenta.getContentPane().add(scrollpanetabla);

		JLabel btneliminar = new JLabel("");
		btneliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btneliminar.isEnabled() && getIdproducto_() != 0) {
					btneliminar.setEnabled(false);
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"¿Quiere quitar este producto de la lista de ventas?", "Quitar producto",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						for (int i = 0; i < detalleventa.size(); i++) {
							if (detalleventa.get(i).getProducto().getIdproducto() == getIdproducto_()) {
								detalleventa.remove(i);
								mostrarDetalles(0, false);
								btneliminar.setEnabled(false);
								tabladetalleventas.requestFocus();
								i = detalleventa.size();
								tcantidad.setText("");
							} else {
								btneliminar.setEnabled(true);
							}
						}
					} else {
						btneliminar.setEnabled(true);
						btneliminar.requestFocus();
					}
				}
			}
		});

		btneliminar.setEnabled(false);
		btneliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btneliminar.setIcon(new ImageIcon(FormAgregarVenta.class.getResource("/iconos/iconBuyDelete.png")));
		btneliminar.setToolTipText("Eliminar");
		btneliminar.setIconTextGap(10);
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btneliminar.setBounds(490, 200, 25, 25);
		frameAgregarVenta.getContentPane().add(btneliminar);

		tabladetalleventas = new JTable();
		tabladetalleventas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tabladetalleventas.getSelectedRow();
				if (r != -1 && tabladetalleventas.getValueAt(r, 1) != null) {
					int idp = Integer.parseInt(
							(String.valueOf(tabladetalleventas.getValueAt(r, 1)).split("-"))[0].replace(" ", ""));
					setIdproducto_(idp);
					for (int i = 0; i < detalleventa.size(); i++) {
						if (detalleventa.get(i).getProducto().getIdproducto() == idp) {
							if (b) {
								tcantidad.setText(detalleventa.get(i).getCantidad() + "");
							}

							i = detalleventa.size();
							btneliminar.setEnabled(true);
						}
					}
					if (!btneliminar.isEnabled()) {
						setIdproducto_(0);
					}
				} else {
					btneliminar.setEnabled(false);
					setIdproducto_(0);
				}
			}

		});

		tabladetalleventas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabladetalleventas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabladetalleventas.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabladetalleventas.setSelectionForeground(new Color(26, 115, 232));
		tabladetalleventas.setSelectionBackground(new Color(232, 240, 254));
		tabladetalleventas.setBorder(null);
		tabladetalleventas.setRowHeight(20);

		// mostrarDetalles(0, false);

		JTableHeader th = tabladetalleventas.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		tabladetalleventas.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tabladetalleventas);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarVenta.dispose();
			}
		});
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(181, 534, 140, 25);
		frameAgregarVenta.getContentPane().add(lblVolver);

		JLabel lbmiddle = new JLabel("");
		lbmiddle.setBackground(Color.BLACK);
		lbmiddle.setOpaque(true);
		lbmiddle.setBounds(0, 190, 650, 1);
		frameAgregarVenta.getContentPane().add(lbmiddle);

		JLabel btnagregar = new JLabel("");
		btnagregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarVenta.setVisible(false);
				new FormAgregarProducto_(form).getFrmAgregarProductos().setVisible(true);
			}
		});

		btnagregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnagregar.setIcon(new ImageIcon(FormAgregarVenta.class.getResource("/iconos/iconAddProduct.png")));
		btnagregar.setToolTipText("Agregar productos");
		btnagregar.setIconTextGap(10);
		btnagregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnagregar.setHorizontalAlignment(SwingConstants.CENTER);
		btnagregar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnagregar.setBounds(575, 200, 25, 25);
		frameAgregarVenta.getContentPane().add(btnagregar);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCantidad.setBounds(20, 200, 90, 25);
		frameAgregarVenta.getContentPane().add(lblCantidad);

		tcantidad = new JTextField();
		tcantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (getIdproducto_() != 0) {
					try {
						int f = Integer.parseInt(tcantidad.getText());
						for (int i = 0; i < detalleventa.size(); i++) {
							if (getIdproducto_() == detalleventa.get(i).getProducto().getIdproducto()) {
								if (f <= detalleventa.get(i).getProducto().getStock()) {
									detalleventa.get(i).setCantidad(f);
								} else {
									JOptionPane.showMessageDialog(null,
											"La cantidad no debe exceder el stock disponible.", "Error cantidad", 0);
									detalleventa.get(i).setCantidad(0);
									tcantidad.setText("0");
								}

								i = detalleventa.size();
							}
						}
						mostrarDetalles(getIdproducto_(), true);

					} catch (NumberFormatException ex) {
						System.out.println(ex);
					}
				}
			}
		});
		tcantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tcantidad);
				b = false;

			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tcantidad);
				b = true;
			}
		});

		CajasTexto.soloNumeros(tcantidad);

		tcantidad.setToolTipText("");
		tcantidad.setHorizontalAlignment(SwingConstants.LEFT);
		tcantidad.setForeground(Color.BLACK);
		tcantidad.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcantidad.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tcantidad);
		tcantidad.setBackground(Color.WHITE);
		tcantidad.setAlignmentX(1.0f);
		tcantidad.setBounds(110, 200, 80, 25);
		frameAgregarVenta.getContentPane().add(tcantidad);

		JButton btnregistrar = new JButton(titulo);
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnregistrar.setEnabled(false);
				if (tcliente.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente.", "Campos obligatorios", 1);
				} else if (detalleventa.size() < 1) {
					JOptionPane.showMessageDialog(null, "Debe agregar productos a vender.", "Campos obligatorios", 1);
				} else {
					boolean bandera = true;
					for (int i = 0; i < detalleventa.size(); i++) {
						if (detalleventa.get(i).getCantidad() <= 0) {
							bandera = false;
							JOptionPane.showMessageDialog(null, "Debe ingresar cantidades mayores cero.", "Error", 0);
							i = detalleventa.size();
						}
					}
					if (bandera) {
						if (tipodeaccion == 1) {

							Venta ven = new Venta(0, usuario, (Cliente) tcliente.getSelectedItem(), "",
									tobservacion.getText(), 0, detalleventa);
							int res = OpVenta.RegistrarVenta(ven);
							if (res != 0) {
								JOptionPane.showMessageDialog(null, "Se ha registrado con exito la venta.",
										"Registro exitoso", 1);

								frameAgregarVenta.setVisible(false);
								new FormVerVenta(OpVenta.BuscarVenta(res), formventa).getFrameDetalle()
										.setVisible(true);

							} else {
								JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el registro.", "Error", 0);
							}
						} else {
							Venta ven = new Venta(venta.getIdventa(), venta.getUsuario(),
									(Cliente) tcliente.getSelectedItem(), "", tobservacion.getText(), 0, detalleventa);
							int res = OpVenta.RegistrarVenta(ven);
							if (res != 0) {
								JOptionPane.showMessageDialog(null, "Se ha actualizado con exito la venta.",
										"Registro exitoso", 1);
								frameAgregarVenta.setVisible(false);
								new FormVerVenta(OpVenta.BuscarVenta(res), formventa).getFrameDetalle()
										.setVisible(true);
							} else {
								JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el registro.", "Error", 0);
							}
						}
					}

				}
				btnregistrar.setEnabled(true);
			}
		});

		btnregistrar.setFocusPainted(false);
		btnregistrar.setBorderPainted(false);
		btnregistrar.setBorder(null);
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.setBackground(new Color(26, 115, 232));
		btnregistrar.setForeground(Color.WHITE);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnregistrar.setBounds(20, 534, 130, 29);
		frameAgregarVenta.getContentPane().add(btnregistrar);

		JLabel lblTotalBs = new JLabel("TOTAL Bs. ");
		lblTotalBs.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTotalBs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBs.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalBs.setBounds(370, 534, 110, 25);
		frameAgregarVenta.getContentPane().add(lblTotalBs);
		frameAgregarVenta.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormAgregarVenta.class.getResource("/iconos/icoBuy_.png")));
		frameAgregarVenta.setBounds(100, 100, 666, 618);
		frameAgregarVenta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
