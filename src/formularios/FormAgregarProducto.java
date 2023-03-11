package formularios;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.DetalleCompra;
import clases.Producto;
import decoradores.CajasTexto;
import operaciones.OpCompra;
import operaciones.OpProducto;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormAgregarProducto {

	private JFrame frameagregarproducto;
	private JTextField tsearch;
	private JTable tablaproducto;
	private int idproducto_ = 0;
	private FormAgregarCompra formag;

	public FormAgregarCompra getFormag() {
		return formag;
	}

	public void setFormag(FormAgregarCompra formag) {
		this.formag = formag;
	}

	public int getIdproducto_() {
		return idproducto_;
	}

	public void setIdproducto_(int idproducto_) {
		this.idproducto_ = idproducto_;
	}

	public JFrame getFrmAgregarProductos() {
		return frameagregarproducto;
	}

	public void setFrmAgregarProductos(JFrame frmAgregarProductos) {
		this.frameagregarproducto = frmAgregarProductos;
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

	public FormAgregarProducto(FormAgregarCompra fmag) {
		initialize();
		frameagregarproducto.setLocationRelativeTo(null);
		this.formag = fmag;
		
	}

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		tablaproducto.setModel(new DefaultTableModel(OpCompra.ListarProducto(val),
				new String[] { "ID", "PRODUCTO", "STOCK", "PRECIO VENTA" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablaproducto.getColumnModel().getColumn(0).setResizable(false);
		tablaproducto.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablaproducto.getColumnModel().getColumn(0).setMinWidth(60);
		tablaproducto.getColumnModel().getColumn(0).setMaxWidth(60);
		tablaproducto.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablaproducto.getColumnModel().getColumn(1).setResizable(false);
		tablaproducto.getColumnModel().getColumn(1).setPreferredWidth(270);
		tablaproducto.getColumnModel().getColumn(1).setMinWidth(270);
		tablaproducto.getColumnModel().getColumn(1).setMaxWidth(270);

		tablaproducto.getColumnModel().getColumn(2).setResizable(false);
		tablaproducto.getColumnModel().getColumn(2).setPreferredWidth(100);
		tablaproducto.getColumnModel().getColumn(2).setMinWidth(100);
		tablaproducto.getColumnModel().getColumn(2).setMaxWidth(100);
		tablaproducto.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tablaproducto.getColumnModel().getColumn(3).setResizable(false);

	}

	private void initialize() {
		frameagregarproducto = new JFrame();
		frameagregarproducto.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formag.getFrameAgregarCompra().setVisible(true);
			}
		});
		frameagregarproducto.setResizable(false);
		frameagregarproducto.getContentPane().setBackground(Color.WHITE);
		frameagregarproducto.setTitle("AGREGAR PRODUCTOS");
		frameagregarproducto.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormAgregarProducto.class.getResource("/iconos/icoBuy_.png")));
		frameagregarproducto.setBounds(100, 100, 616, 427);
		frameagregarproducto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameagregarproducto.getContentPane().setLayout(null);

		JLabel lblSeleccionarProveedor = new JLabel("AGREGAR PRODUCTO");
		lblSeleccionarProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblSeleccionarProveedor.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblSeleccionarProveedor.setBackground(Color.WHITE);
		lblSeleccionarProveedor.setBounds(0, 20, 600, 30);
		frameagregarproducto.getContentPane().add(lblSeleccionarProveedor);

		JLabel labelseparador = new JLabel("");
		labelseparador.setBackground(Color.BLACK);
		labelseparador.setOpaque(true);
		labelseparador.setBounds(0, 0, 600, 1);
		frameagregarproducto.getContentPane().add(labelseparador);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormAgregarProducto.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(300, 70, 25, 25);
		frameagregarproducto.getContentPane().add(lsearch);

		JLabel btnagregar = new JLabel("");
		btnagregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getIdproducto_() != 0 && btnagregar.isEnabled()) {
					btnagregar.setEnabled(false);
					Producto prod = OpProducto.BuscarProducto(getIdproducto_());
					boolean b = true;
					for (int i = 0; i < formag.getDetallecompra().size(); i++) {
						if (formag.getDetallecompra().get(i).getProducto().getIdproducto() == prod.getIdproducto()) {
							b = false;
							i = formag.getDetallecompra().size();
						}
					}
					if (b) {
						DetalleCompra det = new DetalleCompra(prod, 0, 0f);
						formag.getDetallecompra().add(det);
					}
					btnagregar.setEnabled(true);
					formag.mostrarDetalles(getIdproducto_(), true);
					formag.getFrameAgregarCompra().setVisible(true);
					frameagregarproducto.dispose();
				}
			}
		});
		btnagregar.setIcon(new ImageIcon(FormAgregarProducto.class.getResource("/iconos/iconPlus.png")));
		btnagregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnagregar.setToolTipText("Agregar producto");
		btnagregar.setIconTextGap(10);
		btnagregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnagregar.setHorizontalAlignment(SwingConstants.CENTER);
		btnagregar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnagregar.setEnabled(false);
		btnagregar.setBounds(555, 344, 25, 25);
		frameagregarproducto.getContentPane().add(btnagregar);

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
						btnagregar.setEnabled(true);
					} else {
						setIdproducto_(0);
						btnagregar.setEnabled(false);
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
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setColumns(10);
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(325, 70, 255, 25);
		frameagregarproducto.getContentPane().add(tsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 105, 560, 228);
		frameagregarproducto.getContentPane().add(scrollpanetabla);

		tablaproducto = new JTable();
		tablaproducto.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tablaproducto.getSelectedRow();
				if (r != -1) {
					if (tablaproducto.getValueAt(r, 0) != null) {
						Producto pr = OpProducto
								.BuscarProducto(Integer.parseInt((String) tablaproducto.getValueAt(r, 0)));
						String producto = pr.getProducto();
						if (producto != null) {
							btnagregar.setEnabled(true);
							setIdproducto_(pr.getIdproducto());
						} else {
							btnagregar.setEnabled(false);
							setIdproducto_(0);
						}
					} else {
						btnagregar.setEnabled(false);
						setIdproducto_(0);
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

		JLabel btnvolver = new JLabel("VOLVER");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameagregarproducto.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(20, 344, 140, 25);
		frameagregarproducto.getContentPane().add(btnvolver);

		JLabel lblSeleccionar = new JLabel("AGREGAR");
		lblSeleccionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionar.setForeground(Color.BLACK);
		lblSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSeleccionar.setBounds(405, 344, 140, 25);
		frameagregarproducto.getContentPane().add(lblSeleccionar);

	}
}
