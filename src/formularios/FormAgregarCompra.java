package formularios;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import clases.Compra;
import clases.DetalleCompra;
import clases.Proveedor;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpCompra;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormAgregarCompra {

	private JFrame frameAgregarCompra;
	private JTextField tobservacion;
	private JTextField tfecha;
	private JTextField tcantidad;
	private FormAgregarCompra form = this;
	private JComboBox<Proveedor> tproveedor;
	private JFormattedTextField tprecio;
	private JTable tabladetallecompras;
	private Usuario usuario;
	private Proveedor proveedor;
	private ArrayList<DetalleCompra> detallecompra = new ArrayList<DetalleCompra>();
	private Compra compra = new Compra();
	private JLabel ttotal;
	private int idproducto_ = 0;
	private boolean b = true;
	private FormCompra formcompra;
	private int tipodeaccion;

	public FormCompra getFormcompra() {
		return formcompra;
	}

	public void setFormcompra(FormCompra formcompra) {
		this.formcompra = formcompra;
	}

	public int getIdproducto_() {
		return idproducto_;
	}

	public void setIdproducto_(int idproducto_) {
		this.idproducto_ = idproducto_;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public ArrayList<DetalleCompra> getDetallecompra() {
		return detallecompra;
	}

	public void setDetallecompra(ArrayList<DetalleCompra> detallecompra) {
		this.detallecompra = detallecompra;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public JFrame getFrameAgregarCompra() {
		return frameAgregarCompra;
	}

	public void setFrameAgregarCompra(JFrame frameAgregarCompra) {
		this.frameAgregarCompra = frameAgregarCompra;
	}

	public JComboBox<Proveedor> getTproveedor() {
		return tproveedor;
	}

	public void setTproveedor(JComboBox<Proveedor> tproveedor) {
		this.tproveedor = tproveedor;
	}

	public void mostrarDetalles(int idproducto, boolean bandera) {
		setIdproducto_(idproducto);
		if (idproducto != 0 || bandera == false) {
			String datos[][] = new String[detallecompra.size()][6];
			float total = 0f;
			for (int i = 0; i < detallecompra.size(); i++) {
				datos[i][0] = (i + 1) + "";
				datos[i][1] = "  " + detallecompra.get(i).getProducto().getIdproducto() + "-"
						+ detallecompra.get(i).getProducto().getProducto();
				datos[i][2] = detallecompra.get(i).getProducto().getStock() + "";
				datos[i][3] = detallecompra.get(i).getCantidad() + "";
				datos[i][4] = String.format("%.2f", detallecompra.get(i).getPreciocompra()) + "  ";
				datos[i][5] = String.format("%.2f",
						(detallecompra.get(i).getCantidad() * detallecompra.get(i).getPreciocompra())) + "  ";
				total = total + (detallecompra.get(i).getCantidad() * detallecompra.get(i).getPreciocompra());
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

		tabladetallecompras.setModel(new DefaultTableModel(datos,
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
		tabladetallecompras.getColumnModel().getColumn(0).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabladetallecompras.getColumnModel().getColumn(0).setMinWidth(40);
		tabladetallecompras.getColumnModel().getColumn(0).setMaxWidth(40);
		tabladetallecompras.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tabladetallecompras.getColumnModel().getColumn(1).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(1).setPreferredWidth(230);
		tabladetallecompras.getColumnModel().getColumn(1).setMinWidth(230);
		tabladetallecompras.getColumnModel().getColumn(1).setMaxWidth(230);

		tabladetallecompras.getColumnModel().getColumn(2).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(2).setPreferredWidth(50);
		tabladetallecompras.getColumnModel().getColumn(2).setMinWidth(50);
		tabladetallecompras.getColumnModel().getColumn(2).setMaxWidth(50);
		tabladetallecompras.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tabladetallecompras.getColumnModel().getColumn(3).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(3).setPreferredWidth(50);
		tabladetallecompras.getColumnModel().getColumn(3).setMinWidth(50);
		tabladetallecompras.getColumnModel().getColumn(3).setMaxWidth(50);
		tabladetallecompras.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tabladetallecompras.getColumnModel().getColumn(4).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(4).setPreferredWidth(110);
		tabladetallecompras.getColumnModel().getColumn(4).setMinWidth(110);
		tabladetallecompras.getColumnModel().getColumn(4).setMaxWidth(110);
		tabladetallecompras.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		tabladetallecompras.getColumnModel().getColumn(5).setResizable(false);
		tabladetallecompras.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		if (bandera) {
			for (int i = 0; i < tabladetallecompras.getRowCount(); i++) {
				int idp = Integer.parseInt(
						(String.valueOf(tabladetallecompras.getValueAt(i, 1)).split("-"))[0].replace(" ", ""));
				if (idproducto == idp) {
					tabladetallecompras.setRowSelectionInterval(i, i);
					i = tabladetallecompras.getRowCount();
				}
			}
		}
	}

	public FormAgregarCompra(Compra c, int tipo, Usuario usuariocompra, FormCompra form) {
		tipodeaccion = tipo;
		usuario = usuariocompra;
		initialize(c, tipo);
		frameAgregarCompra.setLocationRelativeTo(null);
		formcompra = form;
		// 1 = REGISTRAR
		// 2 = ACTUALIZAR
		if (tipo == 2) {
			compra.setFecharegistro(c.getFecharegistro());
			compra.setIdcompra(c.getIdcompra());
			compra.setListacompra(c.getListacompra());
			compra.setObservacion(c.getObservacion());
			compra.setProveedor(c.getProveedor());
			compra.setTotal(c.getTotal());
			compra.setUsuario(c.getUsuario());
			for (int i = 0; i < tproveedor.getItemCount(); i++) {
				if (tproveedor.getItemAt(i).getIdproveedor() == compra.getProveedor().getIdproveedor()) {
					tproveedor.setSelectedIndex(i);
					i = tproveedor.getItemCount();
				}
			}
			detallecompra = compra.getListacompra();
			tfecha.setText(compra.getFecharegistro());
			tobservacion.setText(compra.getObservacion());
			tprecio.setText("");
			tcantidad.setText("");
			mostrarDetalles(0, false);
		}
	}

	private void initialize(Compra c, int tipo) {
		frameAgregarCompra = new JFrame();
		frameAgregarCompra.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formcompra.getFrameCompra().setVisible(true);
			}
		});
		String titulo = "";
		if (tipo == 1) {
			titulo = "REGISTRAR";
		} else {
			titulo = "ACTUALIZAR";
		}

		frameAgregarCompra.setResizable(false);
		frameAgregarCompra.setTitle("AGREGAR COMPRA");
		frameAgregarCompra.getContentPane().setBackground(Color.WHITE);
		frameAgregarCompra.getContentPane().setLayout(null);

		ttotal = new JLabel("");
		ttotal.setOpaque(true);
		ttotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(490, 534, 140, 25);
		frameAgregarCompra.getContentPane().add(ttotal);

		JLabel labeltitulo = new JLabel(titulo + " COMPRA");
		labeltitulo.setHorizontalAlignment(SwingConstants.CENTER);
		labeltitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labeltitulo.setBorder(new EmptyBorder(0, 0, 0, 0));
		labeltitulo.setBackground(Color.WHITE);
		labeltitulo.setBounds(0, 20, 650, 30);
		frameAgregarCompra.getContentPane().add(labeltitulo);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 650, 20);
		frameAgregarCompra.getContentPane().add(labelusuarioregistro);

		JLabel top = new JLabel("");
		top.setBackground(Color.BLACK);
		top.setOpaque(true);
		top.setBounds(0, 0, 650, 1);
		frameAgregarCompra.getContentPane().add(top);

		JLabel lproveedor = new JLabel("PROVEEDOR *");
		lproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproveedor.setBounds(20, 70, 295, 20);
		frameAgregarCompra.getContentPane().add(lproveedor);

		JLabel lobservacion = new JLabel("OBSERVACIÓN");
		lobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lobservacion.setBounds(20, 130, 610, 20);
		frameAgregarCompra.getContentPane().add(lobservacion);

		tproveedor = OpCompra.listarProveedor();

		tproveedor.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco__(tproveedor);
				tproveedor.setBackground(Color.WHITE);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco__(tproveedor);
			}
		});
		tproveedor.setForeground(Color.BLACK);
		tproveedor.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		CajasTexto.ComboFueraDeFoco__(tproveedor);
		tproveedor.setBackground(Color.WHITE);
		tproveedor.setBounds(20, 90, 270, 25);
		frameAgregarCompra.getContentPane().add(tproveedor);

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
		frameAgregarCompra.getContentPane().add(tobservacion);

		JLabel lsearch = new JLabel("");
		lsearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarCompra.setVisible(false);
				new FormAgregarProveedor(form).getFrameSeleccionarProveedor().setVisible(true);
			}
		});
		lsearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsearch.setIcon(new ImageIcon(FormAgregarCompra.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(290, 90, 25, 25);
		frameAgregarCompra.getContentPane().add(lsearch);
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
		frameAgregarCompra.getContentPane().add(tfecha);

		JLabel lfecha = new JLabel("FECHA HORA");
		lfecha.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecha.setBounds(335, 70, 295, 20);
		frameAgregarCompra.getContentPane().add(lfecha);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setEnabled(false);
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 236, 610, 288);
		frameAgregarCompra.getContentPane().add(scrollpanetabla);

		JLabel btneliminar = new JLabel("");
		btneliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btneliminar.isEnabled() && getIdproducto_() != 0) {
					btneliminar.setEnabled(false);
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"¿Quiere quitar este producto de la lista de compras?", "Quitar producto",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						for (int i = 0; i < detallecompra.size(); i++) {
							if (detallecompra.get(i).getProducto().getIdproducto() == getIdproducto_()) {
								detallecompra.remove(i);
								mostrarDetalles(0, false);
								btneliminar.setEnabled(false);
								tabladetallecompras.requestFocus();
								i = detallecompra.size();
								tcantidad.setText("");
								tprecio.setText("");
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
		btneliminar.setIcon(new ImageIcon(FormAgregarCompra.class.getResource("/iconos/iconBuyDelete.png")));
		btneliminar.setToolTipText("Eliminar");
		btneliminar.setIconTextGap(10);
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btneliminar.setBounds(490, 200, 25, 25);
		frameAgregarCompra.getContentPane().add(btneliminar);

		tabladetallecompras = new JTable();
		tabladetallecompras.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tabladetallecompras.getSelectedRow();
				if (r != -1 && tabladetallecompras.getValueAt(r, 1) != null) {
					int idp = Integer.parseInt(
							(String.valueOf(tabladetallecompras.getValueAt(r, 1)).split("-"))[0].replace(" ", ""));
					setIdproducto_(idp);
					for (int i = 0; i < detallecompra.size(); i++) {
						if (detallecompra.get(i).getProducto().getIdproducto() == idp) {
							if (b) {
								tprecio.setText(detallecompra.get(i).getPreciocompra() + "");
								tcantidad.setText(detallecompra.get(i).getCantidad() + "");
							}

							i = detallecompra.size();
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

		tabladetallecompras.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabladetallecompras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabladetallecompras.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabladetallecompras.setSelectionForeground(new Color(26, 115, 232));
		tabladetallecompras.setSelectionBackground(new Color(232, 240, 254));
		tabladetallecompras.setBorder(null);
		tabladetallecompras.setRowHeight(20);

		mostrarDetalles(0, false);

		JTableHeader th = tabladetallecompras.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		tabladetallecompras.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tabladetallecompras);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarCompra.dispose();
			}
		});
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(181, 534, 140, 25);
		frameAgregarCompra.getContentPane().add(lblVolver);

		JLabel middle = new JLabel("");
		middle.setBackground(Color.BLACK);
		middle.setOpaque(true);
		middle.setBounds(0, 190, 650, 1);
		frameAgregarCompra.getContentPane().add(middle);

		JLabel lsearch_1 = new JLabel("");
		lsearch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameAgregarCompra.setVisible(false);
				new FormAgregarProducto(form).getFrmAgregarProductos().setVisible(true);
			}
		});
		lsearch_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lsearch_1.setIcon(new ImageIcon(FormAgregarCompra.class.getResource("/iconos/iconAddProduct.png")));
		lsearch_1.setToolTipText("Agregar productos");
		lsearch_1.setIconTextGap(10);
		lsearch_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch_1.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch_1.setBounds(575, 200, 25, 25);
		frameAgregarCompra.getContentPane().add(lsearch_1);

		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPrecio.setBounds(210, 200, 60, 25);
		frameAgregarCompra.getContentPane().add(lblPrecio);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCantidad.setBounds(20, 200, 90, 25);
		frameAgregarCompra.getContentPane().add(lblCantidad);

		tcantidad = new JTextField();
		tcantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (getIdproducto_() != 0) {
					try {
						int f = Integer.parseInt(tcantidad.getText());
						for (int i = 0; i < detallecompra.size(); i++) {
							if (getIdproducto_() == detallecompra.get(i).getProducto().getIdproducto()) {
								detallecompra.get(i).setCantidad(f);
								i = detallecompra.size();
							}
						}
						mostrarDetalles(getIdproducto_(), true);

					} catch (NumberFormatException ex) {
						System.out.println(ex);
					}
				}
			}
		});

		CajasTexto.soloNumeros(tcantidad);
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
		tcantidad.setToolTipText("");
		tcantidad.setHorizontalAlignment(SwingConstants.LEFT);
		tcantidad.setForeground(Color.BLACK);
		tcantidad.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcantidad.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tcantidad);
		tcantidad.setBackground(Color.WHITE);
		tcantidad.setAlignmentX(1.0f);
		tcantidad.setBounds(110, 200, 80, 25);
		frameAgregarCompra.getContentPane().add(tcantidad);
		DecimalFormatSymbols dfs;
		DecimalFormat dFormat;

		dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		dfs.setGroupingSeparator(',');
		dFormat = new DecimalFormat("#0.##", dfs);
		tprecio = new JFormattedTextField(dFormat);
		tprecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (getIdproducto_() != 0) {
					try {
						float f = Float.parseFloat(tprecio.getText());
						for (int i = 0; i < detallecompra.size(); i++) {
							if (getIdproducto_() == detallecompra.get(i).getProducto().getIdproducto()) {
								detallecompra.get(i).setPreciocompra(f);
								i = detallecompra.size();
							}
						}
						mostrarDetalles(getIdproducto_(), true);

					} catch (NumberFormatException ex) {
						System.out.println(ex);
					}
				}
			}
		});
		tprecio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tprecio);
				b = false;

			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tprecio);
				b = true;
			}
		});
		CajasTexto.TxtFueraDeFoco(tprecio);
		tprecio.setForeground(Color.BLACK);
		tprecio.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		CajasTexto.TxtFueraDeFoco(tprecio);
		tprecio.setBackground(Color.WHITE);
		tprecio.setBounds(270, 200, 140, 25);
		frameAgregarCompra.getContentPane().add(tprecio);

		JButton btnregistrar = new JButton(titulo);
		btnregistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnregistrar.setEnabled(false);
				if (tproveedor.getSelectedIndex() <= 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor.", "Campos obligatorios", 1);
				} else if (detallecompra.size() < 1) {
					JOptionPane.showMessageDialog(null, "Debe agregar productos a comprar.", "Campos obligatorios", 1);
				} else {
					boolean bandera = true;
					for (int i = 0; i < detallecompra.size(); i++) {
						if (detallecompra.get(i).getCantidad() <= 0) {
							bandera = false;
							JOptionPane.showMessageDialog(null, "Debe ingresar cantidades mayores cero.", "Error", 0);
							i = detallecompra.size();
						} else if (detallecompra.get(i).getPreciocompra() < 0) {
							bandera = false;
							JOptionPane.showMessageDialog(null,
									"Debe ingresar precios unitarios mayores iguales a cero.", "Error", 0);
							i = detallecompra.size();
						}
					}
					if (bandera) {
						if (tipodeaccion == 1) {

							Compra cpra = new Compra(0, usuario, (Proveedor) tproveedor.getSelectedItem(), "",
									tobservacion.getText(), 0, detallecompra);
							int res = OpCompra.RegistrarCompra(cpra);
							if (res != 0) {
								JOptionPane.showMessageDialog(null, "Se ha registrado con exito la compra.",
										"Registro exitoso", 1);

								frameAgregarCompra.setVisible(false);
								new FormVerCompra(OpCompra.BuscarCompra(res), formcompra).getFrameDetalle()
										.setVisible(true);

							} else {
								JOptionPane.showMessageDialog(null, "Ha ocurrido un error en el registro.", "Error", 0);
							}
						} else {
							Compra cpra = new Compra(compra.getIdcompra(), compra.getUsuario(),
									(Proveedor) tproveedor.getSelectedItem(), "", tobservacion.getText(), 0,
									detallecompra);
							int res = OpCompra.RegistrarCompra(cpra);
							if (res != 0) {
								JOptionPane.showMessageDialog(null, "Se ha actualizado con exito la compra.",
										"Registro exitoso", 1);
								frameAgregarCompra.setVisible(false);
								new FormVerCompra(OpCompra.BuscarCompra(res), formcompra).getFrameDetalle()
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
		frameAgregarCompra.getContentPane().add(btnregistrar);

		JLabel lblTotalBs = new JLabel("TOTAL Bs. ");
		lblTotalBs.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTotalBs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBs.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalBs.setBounds(370, 534, 110, 25);
		frameAgregarCompra.getContentPane().add(lblTotalBs);
		frameAgregarCompra.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormAgregarCompra.class.getResource("/iconos/icoBuy_.png")));
		frameAgregarCompra.setBounds(100, 100,666, 618);
		frameAgregarCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
