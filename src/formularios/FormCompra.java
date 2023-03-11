package formularios;

import javax.swing.JFrame;
import clases.Compra;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpCompra;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormCompra {

	private JFrame frameCompra;
	private JTextField tsearch;
	private JTable tablacompra;
	private int idcompra_ = 0;
	private JLabel tidcompra;
	private JLabel tobservacion;
	private JLabel tfecharegistro;
	private JLabel tusuario;
	private JLabel ttotal;
	private JLabel tproveedor;
	private JLabel tnro;
	private Usuario usuario;
	private FormCompra formcompra = this;
	private FormMenu formmenu;

	public JFrame getFrameCompra() {
		return frameCompra;
	}

	public void setFrameCompra(JFrame frameCompra) {
		this.frameCompra = frameCompra;
	}

	public int getIdcompra_() {
		return idcompra_;
	}

	public void setIdcompra_(int idcompra_) {
		this.idcompra_ = idcompra_;
	}

	public void limpiarFormulario() {
		setIdcompra_(0);
		tidcompra.setText("-------------------------");
		tobservacion.setText("---------------------------------------------------------------------------");
		tfecharegistro.setText("-------------------------");
		tusuario.setText("------------------------------------------------");
		tproveedor.setText("------------------------------------------------");
		ttotal.setText("-------------------------");
		tnro.setText("-----------------------");
	}

	public int buscarCompraTabla(int idcompra) {
		if (idcompra != 0) {
			for (int i = 0; i < tablacompra.getRowCount(); i++) {
				if (tablacompra.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablacompra.getValueAt(i, 0)) == idcompra) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void compraSeleccionada(Compra c) {
		setIdcompra_(c.getIdcompra());
		tidcompra.setText(c.getIdcompra() + "");
		tobservacion.setText(c.getObservacion());
		tfecharegistro.setText(c.getFecharegistro());
		tusuario.setText(c.getUsuario().getNombre());
		tproveedor.setText(c.getProveedor().getProveedor());
		ttotal.setText("Bs. " + String.format("%.2f", c.getTotal()));
		tnro.setText(c.getListacompra().size() + "");
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablacompra.setModel(new DefaultTableModel(OpCompra.ListarCompras(val),
				new String[] { "ID", "PROVEEDOR", "USUARIO", "FECHA", "TOTAL" }) {

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
		tablacompra.getColumnModel().getColumn(0).setResizable(false);
		tablacompra.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablacompra.getColumnModel().getColumn(0).setMinWidth(40);
		tablacompra.getColumnModel().getColumn(0).setMaxWidth(40);
		tablacompra.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablacompra.getColumnModel().getColumn(1).setResizable(false);
		tablacompra.getColumnModel().getColumn(1).setPreferredWidth(220);
		tablacompra.getColumnModel().getColumn(1).setMinWidth(220);
		tablacompra.getColumnModel().getColumn(1).setMaxWidth(220);

		tablacompra.getColumnModel().getColumn(2).setResizable(false);
		tablacompra.getColumnModel().getColumn(2).setPreferredWidth(220);
		tablacompra.getColumnModel().getColumn(2).setMinWidth(220);
		tablacompra.getColumnModel().getColumn(2).setMaxWidth(220);

		tablacompra.getColumnModel().getColumn(3).setResizable(false);
		tablacompra.getColumnModel().getColumn(3).setPreferredWidth(140);
		tablacompra.getColumnModel().getColumn(3).setMinWidth(140);
		tablacompra.getColumnModel().getColumn(3).setMaxWidth(140);
		tablacompra.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablacompra.getColumnModel().getColumn(4).setResizable(false);

	}

	public void setActualizarRegistra(int idcompra) {
		limpiarFormulario();
		int sel = buscarCompraTabla(idcompra);
		if (sel != -1) {
			tablacompra.setRowSelectionInterval(sel, sel);
			tablacompra.changeSelection(sel, 0, false, false);
		} else {
			limpiarFormulario();
		}
	}

	public FormCompra(Usuario u, FormMenu fm) {
		usuario = u;
		formmenu = fm;
		initialize();
		frameCompra.setLocationRelativeTo(null);

	}

	private void initialize() {
		frameCompra = new JFrame();
		frameCompra.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameCompra.setResizable(false);
		frameCompra.getContentPane().setBackground(Color.WHITE);
		frameCompra.getContentPane().setLayout(null);

		JLabel separadorTop = new JLabel("New label");
		separadorTop.setBackground(Color.BLACK);
		separadorTop.setOpaque(true);
		separadorTop.setBounds(0, 0, 800, 1);
		frameCompra.getContentPane().add(separadorTop);

		JLabel separadorTop_1 = new JLabel("New label");
		separadorTop_1.setOpaque(true);
		separadorTop_1.setBackground(Color.BLACK);
		separadorTop_1.setBounds(0, 249, 800, 1);
		frameCompra.getContentPane().add(separadorTop_1);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 800, 20);
		frameCompra.getContentPane().add(labelusuarioregistro);

		JLabel labelmodulocompra = new JLabel("LISTA DE COMPRAS");
		labelmodulocompra.setHorizontalAlignment(SwingConstants.CENTER);
		labelmodulocompra.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelmodulocompra.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelmodulocompra.setBackground(Color.WHITE);
		labelmodulocompra.setBounds(0, 20, 800, 30);
		frameCompra.getContentPane().add(labelmodulocompra);

		JLabel lidcompra = new JLabel("ID COMPRA");
		lidcompra.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lidcompra.setBounds(20, 70, 150, 20);
		frameCompra.getContentPane().add(lidcompra);

		JLabel lproveedor = new JLabel("PROVEEDOR");
		lproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproveedor.setBounds(190, 190, 290, 20);
		frameCompra.getContentPane().add(lproveedor);

		tidcompra = new JLabel("-------------------------");
		tidcompra.setForeground(Color.GRAY);
		tidcompra.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tidcompra.setBounds(20, 90, 150, 20);
		frameCompra.getContentPane().add(tidcompra);

		tfecharegistro = new JLabel("-------------------------");
		tfecharegistro.setForeground(Color.GRAY);
		tfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tfecharegistro.setBounds(20, 150, 150, 20);
		frameCompra.getContentPane().add(tfecharegistro);

		JLabel lfecharegistro = new JLabel("FECHA HORA");
		lfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecharegistro.setBounds(20, 130, 150, 20);
		frameCompra.getContentPane().add(lfecharegistro);

		ttotal = new JLabel("-------------------------");
		ttotal.setForeground(Color.GRAY);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(20, 210, 150, 20);
		frameCompra.getContentPane().add(ttotal);

		JLabel ltotal = new JLabel("TOTAL");
		ltotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltotal.setBounds(20, 190, 150, 20);
		frameCompra.getContentPane().add(ltotal);

		tusuario = new JLabel("------------------------------------------------");
		tusuario.setForeground(Color.GRAY);
		tusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tusuario.setBounds(190, 150, 290, 20);
		frameCompra.getContentPane().add(tusuario);

		tproveedor = new JLabel("------------------------------------------------");
		tproveedor.setForeground(Color.GRAY);
		tproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tproveedor.setBounds(190, 210, 290, 20);
		frameCompra.getContentPane().add(tproveedor);

		JLabel lusuario = new JLabel("REG. USUARIO");
		lusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lusuario.setBounds(190, 130, 290, 20);
		frameCompra.getContentPane().add(lusuario);

		tobservacion = new JLabel("---------------------------------------------------------------------------");
		tobservacion.setForeground(Color.GRAY);
		tobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tobservacion.setBounds(190, 90, 450, 20);
		frameCompra.getContentPane().add(tobservacion);

		JLabel lobservacion = new JLabel("OBSERVACIÓN");
		lobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lobservacion.setBounds(190, 70, 450, 20);
		frameCompra.getContentPane().add(lobservacion);

		JLabel btnver = new JLabel("");
		btnver.setEnabled(false);
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
					if (getIdcompra_() != 0) {
						formcompra.getFrameCompra().setVisible(false);
						new FormVerCompra(OpCompra.BuscarCompra(getIdcompra_()), formcompra).getFrameDetalle()
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
		btnver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnver.setIcon(new ImageIcon(FormCompra.class.getResource("/iconos/iconViewFixed.png")));
		btnver.setHorizontalTextPosition(SwingConstants.CENTER);
		btnver.setHorizontalAlignment(SwingConstants.CENTER);
		btnver.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnver.setBounds(656, 70, 45, 45);
		frameCompra.getContentPane().add(btnver);

		JLabel labelver = new JLabel("VER");
		labelver.setHorizontalTextPosition(SwingConstants.RIGHT);
		labelver.setHorizontalAlignment(SwingConstants.RIGHT);
		labelver.setFont(new Font("Segoe UI", Font.BOLD, 12));
		labelver.setBounds(701, 70, 75, 45);
		frameCompra.getContentPane().add(labelver);

		JLabel lactualizar = new JLabel("ACTUALIZAR");
		lactualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lactualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lactualizar.setBounds(701, 130, 75, 45);
		frameCompra.getContentPane().add(lactualizar);

		JLabel btnactualizar = new JLabel("");
		btnactualizar.setEnabled(false);
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
					frameCompra.setVisible(false);
					new FormAgregarCompra(OpCompra.BuscarCompra(getIdcompra_()), 2, usuario, formcompra)
							.getFrameAgregarCompra().setVisible(true);

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
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.setIcon(new ImageIcon(FormCompra.class.getResource("/iconos/iconUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setBounds(656, 130, 45, 45);
		frameCompra.getContentPane().add(btnactualizar);

		JLabel btneliminar = new JLabel("");
		btneliminar.setEnabled(false);
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
					if (getIdcompra_() != 0) {
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null,
								"¿Quiere quitar este producto de la lista de compras?", "Quitar compra",
								JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
						if (seleccion == 0) {
							if (OpCompra.EliminarCompra(getIdcompra_())) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado la compra correctamente.",
										"Eliminación exitosa", 1);
								setIdcompra_(0);
								limpiarFormulario();
								String a = tsearch.getText();
								if (a != null) {
									cargarTabla(a);
								} else {
									cargarTabla("");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido eliminar la compra.", "Error", 0);
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
		btneliminar.setIcon(new ImageIcon(FormCompra.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setBounds(656, 190, 45, 45);
		frameCompra.getContentPane().add(btneliminar);

		JLabel leliminar = new JLabel("ELIMINAR");
		leliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		leliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		leliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		leliminar.setBounds(701, 190, 75, 45);
		frameCompra.getContentPane().add(leliminar);

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
					frameCompra.setVisible(false);
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewBuyFixedHover.png")));
					new FormAgregarCompra(new Compra(), 1, usuario, formcompra).getFrameAgregarCompra()
							.setVisible(true);

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
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.setIcon(new ImageIcon(FormCompra.class.getResource("/iconos/iconNewBuyFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(500, 190, 45, 45);
		frameCompra.getContentPane().add(btnregistrar);

		JLabel lblNuevaCompra = new JLabel("+ COMPRA");
		lblNuevaCompra.setForeground(Color.BLACK);
		lblNuevaCompra.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNuevaCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevaCompra.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNuevaCompra.setBounds(545, 190, 95, 45);
		frameCompra.getContentPane().add(lblNuevaCompra);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormCompra.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(500, 265, 25, 25);
		frameCompra.getContentPane().add(lsearch);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarCompraTabla(getIdcompra_());
					if (sel != -1) {
						tablacompra.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablacompra.changeSelection(sel, 0, false, false);
						btnver.setEnabled(true);
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnver.setEnabled(false);
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdcompra_() != 0) {
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
		frameCompra.getContentPane().add(tsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 760, 228);
		frameCompra.getContentPane().add(scrollpanetabla);

		tablacompra = new JTable();
		tablacompra.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tablacompra.getSelectedRow();
				if (r != -1) {
					if (tablacompra.getValueAt(r, 0) != null) {
						Compra co = OpCompra.BuscarCompra(Integer.parseInt((String) tablacompra.getValueAt(r, 0)));
						System.out.println(Integer.parseInt((String) tablacompra.getValueAt(r, 0)) + " manda");
						System.out.println(co.getIdcompra() + " envia");
						String fecharegistro = co.getFecharegistro();
						if (fecharegistro != null) {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							btnver.setEnabled(true);
							setIdcompra_(co.getIdcompra());
							compraSeleccionada(co);
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

		tablacompra.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablacompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablacompra.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablacompra.setSelectionForeground(new Color(26, 115, 232));
		tablacompra.setSelectionBackground(new Color(232, 240, 254));

		tablacompra.setBorder(null);
		tablacompra.setRowHeight(20);
		JTableHeader th = tablacompra.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablacompra.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablacompra);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameCompra.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(640, 534, 140, 25);
		frameCompra.getContentPane().add(btnvolver);

		tnro = new JLabel("-----------------------");
		tnro.setForeground(Color.GRAY);
		tnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tnro.setBounds(500, 150, 140, 20);
		frameCompra.getContentPane().add(tnro);

		JLabel lnro = new JLabel("NRO. PROD.");
		lnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnro.setBounds(500, 130, 140, 20);
		frameCompra.getContentPane().add(lnro);
		frameCompra.setTitle("MODULO COMPRA");
		frameCompra.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormCompra.class.getResource("/iconos/iconBuy.png")));
		frameCompra.setBackground(Color.WHITE);
		frameCompra.setBounds(100, 100, 816, 618);
		frameCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
