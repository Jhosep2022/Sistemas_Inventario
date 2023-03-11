package formularios;

import javax.swing.JFrame;
import clases.Usuario;
import clases.Venta;
import decoradores.CajasTexto;
import operaciones.OpVenta;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormVenta {

	private JFrame frameventa;
	private final JLabel separadortop = new JLabel("");
	private JTextField tsearch;
	private Usuario usuario;
	private JTable tablaventa;
	private int idventa_ = 0;
	private JLabel tidventa;
	private JLabel tobservacion;
	private JLabel tfecharegistro;
	private JLabel tusuario;
	private JLabel tnro;
	private JLabel ttotal;
	private JLabel tcliente;
	private FormVenta formventa = this;
	private FormMenu formmenu;

	public JFrame getFrameventa() {
		return frameventa;
	}

	public void setFrameventa(JFrame frameventa) {
		this.frameventa = frameventa;
	}

	public int getIdventa_() {
		return idventa_;
	}

	public void setIdventa_(int idventa_) {
		this.idventa_ = idventa_;
	}

	public void setActualizarRegistra(int idventa) {
		limpiarFormulario();
		int sel = buscarVentaTabla(idventa);
		if (sel != -1) {
			tablaventa.setRowSelectionInterval(sel, sel);
			tablaventa.changeSelection(sel, 0, false, false);
		} else {
			limpiarFormulario();
		}
	}

	public int buscarVentaTabla(int idventa) {
		if (idventa != 0) {
			for (int i = 0; i < tablaventa.getRowCount(); i++) {
				if (tablaventa.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablaventa.getValueAt(i, 0)) == idventa) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void limpiarFormulario() {
		setIdventa_(0);
		tidventa.setText("-------------------------");
		tobservacion.setText("---------------------------------------------------------------------------");
		tfecharegistro.setText("-------------------------");
		tusuario.setText("------------------------------------------------");
		tcliente.setText("------------------------------------------------");
		ttotal.setText("-------------------------");
		tnro.setText("-----------------------");
	}

	public void ventaseleccionada(Venta v) {
		setIdventa_(v.getIdventa());
		tidventa.setText(v.getIdventa() + "");
		tobservacion.setText(v.getObservacion());
		tfecharegistro.setText(v.getFecharegistro());
		tusuario.setText(v.getUsuario().getNombre());
		tcliente.setText(v.getCliente().getNombre());
		ttotal.setText("Bs. " + String.format("%.2f", v.getTotal()));
		tnro.setText(v.getListaventa().size() + "");
	}

	public FormVenta(Usuario u, FormMenu fm) {
		formmenu = fm;
		usuario = u;
		initialize();
		frameventa.setLocationRelativeTo(null);
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaventa.setModel(new DefaultTableModel(OpVenta.ListarVentas(val),
				new String[] { "ID", "CLIENTE", "USUARIO", "FECHA", "TOTAL" }) {
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
		tablaventa.getColumnModel().getColumn(0).setResizable(false);
		tablaventa.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablaventa.getColumnModel().getColumn(0).setMinWidth(40);
		tablaventa.getColumnModel().getColumn(0).setMaxWidth(40);
		tablaventa.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablaventa.getColumnModel().getColumn(1).setResizable(false);
		tablaventa.getColumnModel().getColumn(1).setPreferredWidth(220);
		tablaventa.getColumnModel().getColumn(1).setMinWidth(220);
		tablaventa.getColumnModel().getColumn(1).setMaxWidth(220);

		tablaventa.getColumnModel().getColumn(2).setResizable(false);
		tablaventa.getColumnModel().getColumn(2).setPreferredWidth(220);
		tablaventa.getColumnModel().getColumn(2).setMinWidth(220);
		tablaventa.getColumnModel().getColumn(2).setMaxWidth(220);

		tablaventa.getColumnModel().getColumn(3).setResizable(false);
		tablaventa.getColumnModel().getColumn(3).setPreferredWidth(140);
		tablaventa.getColumnModel().getColumn(3).setMinWidth(140);
		tablaventa.getColumnModel().getColumn(3).setMaxWidth(140);
		tablaventa.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablaventa.getColumnModel().getColumn(4).setResizable(false);

	}

	private void initialize() {
		frameventa = new JFrame();
		frameventa.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameventa.setResizable(false);
		frameventa.getContentPane().setBackground(Color.WHITE);
		frameventa.getContentPane().setLayout(null);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameventa.getContentPane().add(labelusuarioregistro);

		JLabel labelmoduloventa = new JLabel("LISTA DE VENTAS");
		labelmoduloventa.setHorizontalAlignment(SwingConstants.CENTER);
		labelmoduloventa.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelmoduloventa.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelmoduloventa.setBackground(Color.WHITE);
		labelmoduloventa.setBounds(0, 20, 800, 30);
		frameventa.getContentPane().add(labelmoduloventa);

		tidventa = new JLabel("-------------------------");
		tidventa.setForeground(Color.GRAY);
		tidventa.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tidventa.setBounds(20, 90, 150, 20);
		frameventa.getContentPane().add(tidventa);

		JLabel lblIdVenta = new JLabel("ID VENTA");
		lblIdVenta.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblIdVenta.setBounds(20, 70, 150, 20);
		frameventa.getContentPane().add(lblIdVenta);

		JLabel lobservacion = new JLabel("OBSERVACIÓN");
		lobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lobservacion.setBounds(190, 70, 450, 20);
		frameventa.getContentPane().add(lobservacion);

		tobservacion = new JLabel("---------------------------------------------------------------------------");
		tobservacion.setForeground(Color.GRAY);
		tobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tobservacion.setBounds(190, 90, 450, 20);
		frameventa.getContentPane().add(tobservacion);

		JLabel lfecharegistro = new JLabel("FECHA HORA");
		lfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecharegistro.setBounds(20, 130, 150, 20);
		frameventa.getContentPane().add(lfecharegistro);

		tfecharegistro = new JLabel("-------------------------");
		tfecharegistro.setForeground(Color.GRAY);
		tfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tfecharegistro.setBounds(20, 150, 150, 20);
		frameventa.getContentPane().add(tfecharegistro);

		JLabel lusuario = new JLabel("REG. USUARIO");
		lusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lusuario.setBounds(190, 130, 290, 20);
		frameventa.getContentPane().add(lusuario);

		tusuario = new JLabel("------------------------------------------------");
		tusuario.setForeground(Color.GRAY);
		tusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tusuario.setBounds(190, 150, 290, 20);
		frameventa.getContentPane().add(tusuario);

		JLabel lnro = new JLabel("NRO. PROD.");
		lnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnro.setBounds(500, 130, 140, 20);
		frameventa.getContentPane().add(lnro);

		tnro = new JLabel("-----------------------");
		tnro.setForeground(Color.GRAY);
		tnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tnro.setBounds(500, 150, 140, 20);
		frameventa.getContentPane().add(tnro);

		ttotal = new JLabel("-------------------------");
		ttotal.setForeground(Color.GRAY);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(20, 210, 150, 20);
		frameventa.getContentPane().add(ttotal);

		JLabel ltotal = new JLabel("TOTAL");
		ltotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltotal.setBounds(20, 190, 150, 20);
		frameventa.getContentPane().add(ltotal);

		JLabel lcliente = new JLabel("CLIENTE");
		lcliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcliente.setBounds(190, 190, 290, 20);
		frameventa.getContentPane().add(lcliente);

		tcliente = new JLabel("------------------------------------------------");
		tcliente.setForeground(Color.GRAY);
		tcliente.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tcliente.setBounds(190, 210, 290, 20);
		frameventa.getContentPane().add(tcliente);
		separadortop.setBackground(Color.BLACK);
		separadortop.setOpaque(true);
		separadortop.setBounds(0, 0, 800, 1);
		frameventa.getContentPane().add(separadortop);

		JLabel separadortop_1 = new JLabel("");
		separadortop_1.setOpaque(true);
		separadortop_1.setBackground(Color.BLACK);
		separadortop_1.setBounds(0, 249, 800, 1);
		frameventa.getContentPane().add(separadortop_1);

		JLabel labelver = new JLabel("VER");
		labelver.setHorizontalTextPosition(SwingConstants.RIGHT);
		labelver.setHorizontalAlignment(SwingConstants.RIGHT);
		labelver.setFont(new Font("Segoe UI", Font.BOLD, 12));
		labelver.setBounds(705, 70, 75, 45);
		frameventa.getContentPane().add(labelver);

		JLabel lactualizar = new JLabel("ACTUALIZAR");
		lactualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lactualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lactualizar.setBounds(705, 130, 75, 45);
		frameventa.getContentPane().add(lactualizar);

		JLabel leliminar = new JLabel("ELIMINAR");
		leliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		leliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		leliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		leliminar.setBounds(705, 190, 75, 45);
		frameventa.getContentPane().add(leliminar);

		JLabel lblNuevaCompra = new JLabel("+ VENTA");
		lblNuevaCompra.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNuevaCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaCompra.setForeground(Color.BLACK);
		lblNuevaCompra.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevaCompra.setBounds(545, 190, 95, 45);
		frameventa.getContentPane().add(lblNuevaCompra);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormVenta.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(500, 265, 25, 25);
		frameventa.getContentPane().add(lsearch);

		JLabel btnver = new JLabel("");
		btnver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnver.isEnabled()) {

					btnver.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconViewFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnver.isEnabled()) {
					btnver.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconViewFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnver.isEnabled()) {
					btnver.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconViewFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnver.isEnabled()) {
					btnver.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconViewFixedHover.png")));
					if (getIdventa_() != 0) {
						formventa.getFrameventa().setVisible(false);
						new FormVerVenta(OpVenta.BuscarVenta(getIdventa_()), formventa).getFrameDetalle()
								.setVisible(true);
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnver.isEnabled()) {
					btnver.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconViewFixedHover.png")));
				}
			}
		});
		btnver.setIcon(new ImageIcon(FormVenta.class.getResource("/iconos/iconViewFixed.png")));
		btnver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnver.setHorizontalTextPosition(SwingConstants.CENTER);
		btnver.setHorizontalAlignment(SwingConstants.CENTER);
		btnver.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnver.setEnabled(false);
		btnver.setBounds(656, 70, 45, 45);
		frameventa.getContentPane().add(btnver);

		JLabel btnactualizar = new JLabel("");
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
					frameventa.setVisible(false);
					new FormAgregarVenta(OpVenta.BuscarVenta(getIdventa_()), 2, usuario, formventa)
							.getFrameAgregarVenta().setVisible(true);

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
		btnactualizar.setIcon(new ImageIcon(FormVenta.class.getResource("/iconos/iconUpdateFixed.png")));
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setEnabled(false);
		btnactualizar.setBounds(656, 130, 45, 45);
		frameventa.getContentPane().add(btnactualizar);

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
					if (getIdventa_() != 0) {
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null,
								"¿Quiere quitar este producto de la lista de ventas?", "Quitar venta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
						if (seleccion == 0) {
							if (OpVenta.EliminarVenta(getIdventa_())) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado la venta correctamente.",
										"Eliminación exitosa", 1);
								setIdventa_(0);
								limpiarFormulario();
								String a = tsearch.getText();
								if (a != null) {
									cargarTabla(a);
								} else {
									cargarTabla("");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido eliminar la venta.", "Error", 0);
								btneliminar.setEnabled(true);
							}
						}
					}
					btneliminar.setEnabled(true);
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
		btneliminar.setIcon(new ImageIcon(FormVenta.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setEnabled(false);
		btneliminar.setBounds(656, 190, 45, 45);
		frameventa.getContentPane().add(btneliminar);

		JLabel btnregistrar = new JLabel("");
		btnregistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixedHover_.png")));

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					frameventa.setVisible(false);
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixedHover.png")));
					new FormAgregarVenta(new Venta(), 1, usuario, formventa).getFrameAgregarVenta().setVisible(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixedHover.png")));

				}
			}
		});
		btnregistrar.setIcon(new ImageIcon(FormVenta.class.getResource("/iconos/iconNewBuyFixed.png")));
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameventa.getContentPane().add(btnregistrar);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameventa.getContentPane().add(scrollpanetabla);

		tablaventa = new JTable();
		tablaventa.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tablaventa.getSelectedRow();
				if (r != -1) {
					if (tablaventa.getValueAt(r, 0) != null) {
						Venta ve = OpVenta.BuscarVenta(Integer.parseInt((String) tablaventa.getValueAt(r, 0)));
						String fecharegistro = ve.getFecharegistro();
						if (fecharegistro != null) {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							btnver.setEnabled(true);
							setIdventa_(ve.getIdventa());
							ventaseleccionada(ve);
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							btnver.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						btnregistrar.setEnabled(true);
						btnver.setEnabled(false);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						limpiarFormulario();
					}
				}

			}
		});

		tablaventa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaventa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablaventa.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablaventa.setSelectionForeground(new Color(26, 115, 232));
		tablaventa.setSelectionBackground(new Color(232, 240, 254));

		tablaventa.setBorder(null);
		tablaventa.setRowHeight(20);
		JTableHeader th = tablaventa.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablaventa.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablaventa);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameventa.dispose();
			}
		});
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameventa.getContentPane().add(btnvolver);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarVentaTabla(getIdventa_());
					if (sel != -1) {
						tablaventa.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablaventa.changeSelection(sel, 0, false, false);
						btnver.setEnabled(true);
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnver.setEnabled(false);
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdventa_() != 0) {
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
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(525, 265, 255, 25);
		frameventa.getContentPane().add(tsearch);

		frameventa.setTitle("MODULO DE VENTA");
		frameventa.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormVenta.class.getResource("/iconos/iconSAle.png")));
		frameventa.setBounds(100, 100, 816, 618);
		frameventa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
