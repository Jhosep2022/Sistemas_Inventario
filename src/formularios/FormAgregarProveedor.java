package formularios;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Proveedor;
import decoradores.CajasTexto;
import operaciones.OpCompra;
import operaciones.OpProveedor;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class FormAgregarProveedor {

	private JFrame frameSeleccionarProveedor;
	private JTextField tsearch;
	private JTable tablaproveedor;
	private int idproveedor_ = 0;
	private FormAgregarCompra formag;

	public JFrame getFrameSeleccionarProveedor() {
		return frameSeleccionarProveedor;
	}

	public void setFrameSeleccionarProveedor(JFrame frameSeleccionarProveedor) {
		this.frameSeleccionarProveedor = frameSeleccionarProveedor;
	}

	public int getIdproveedor_() {
		return idproveedor_;
	}

	public void setIdproveedor_(int idproveedor_) {
		this.idproveedor_ = idproveedor_;
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

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablaproveedor.setModel(
				new DefaultTableModel(OpCompra.ListarProveedor(val), new String[] { "ID", "PROVEEDOR", "NIT" }) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { String.class, String.class, String.class };

					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		tablaproveedor.getColumnModel().getColumn(0).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablaproveedor.getColumnModel().getColumn(0).setMinWidth(60);
		tablaproveedor.getColumnModel().getColumn(0).setMaxWidth(60);
		tablaproveedor.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablaproveedor.getColumnModel().getColumn(1).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(1).setPreferredWidth(350);
		tablaproveedor.getColumnModel().getColumn(1).setMinWidth(350);
		tablaproveedor.getColumnModel().getColumn(1).setMaxWidth(350);

		tablaproveedor.getColumnModel().getColumn(2).setResizable(false);
		tablaproveedor.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

	}

	public FormAgregarProveedor(FormAgregarCompra ag) {
		initialize();
		frameSeleccionarProveedor.setLocationRelativeTo(null);
		this.formag = ag;
	}

	private void initialize() {
		frameSeleccionarProveedor = new JFrame();
		frameSeleccionarProveedor.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formag.getFrameAgregarCompra().setVisible(true);
			}
		});

		frameSeleccionarProveedor.setResizable(false);
		frameSeleccionarProveedor.getContentPane().setBackground(Color.WHITE);
		frameSeleccionarProveedor.getContentPane().setLayout(null);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 105, 560, 228);
		frameSeleccionarProveedor.getContentPane().add(scrollpanetabla);

		JLabel btnagregar = new JLabel("");
		btnagregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getIdproveedor_() != 0) {
					for (int i = 1; i < formag.getTproveedor().getItemCount(); i++) {
						if (formag.getTproveedor().getItemAt(i).getIdproveedor() == getIdproveedor_()) {
							formag.getTproveedor().setSelectedIndex(i);
							i = formag.getTproveedor().getItemCount();
							formag.getFrameAgregarCompra().setVisible(true);

						}
					}
					frameSeleccionarProveedor.dispose();
				}
			}
		});
		btnagregar.setEnabled(false);
		btnagregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnagregar.setIcon(new ImageIcon(FormAgregarProveedor.class.getResource("/iconos/iconActivate.png")));
		btnagregar.setToolTipText("Buscar usuario");
		btnagregar.setIconTextGap(10);
		btnagregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnagregar.setHorizontalAlignment(SwingConstants.CENTER);
		btnagregar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnagregar.setBounds(555, 344, 25, 25);
		frameSeleccionarProveedor.getContentPane().add(btnagregar);

		JLabel lblSeleccionar = new JLabel("SELECCIONAR");
		tablaproveedor = new JTable();
		tablaproveedor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tablaproveedor.getSelectedRow();
				if (r != -1) {
					if (tablaproveedor.getValueAt(r, 0) != null) {
						Proveedor pr = OpProveedor
								.BuscarProveedor(Integer.parseInt((String) tablaproveedor.getValueAt(r, 0)));
						String proveedor = pr.getProveedor();
						if (proveedor != null) {
							btnagregar.setEnabled(true);
							setIdproveedor_(pr.getIdproveedor());
						} else {
							btnagregar.setEnabled(false);
							setIdproveedor_(0);
						}
					} else {
						btnagregar.setEnabled(false);
						setIdproveedor_(0);
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

		JLabel lblSeleccionarProveedor = new JLabel("SELECCIONAR PROVEEDOR");
		lblSeleccionarProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblSeleccionarProveedor.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblSeleccionarProveedor.setBackground(Color.WHITE);
		lblSeleccionarProveedor.setBounds(0, 20, 600, 30);
		frameSeleccionarProveedor.getContentPane().add(lblSeleccionarProveedor);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormAgregarProveedor.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(300, 70, 25, 25);
		frameSeleccionarProveedor.getContentPane().add(lsearch);

		tsearch = new JTextField();
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
						btnagregar.setEnabled(true);
					} else {
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
		tsearch.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(325, 70, 255, 25);
		frameSeleccionarProveedor.getContentPane().add(tsearch);

		JLabel btnvolver = new JLabel("VOLVER");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameSeleccionarProveedor.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(20, 344, 140, 25);
		frameSeleccionarProveedor.getContentPane().add(btnvolver);

		lblSeleccionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionar.setForeground(Color.BLACK);
		lblSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSeleccionar.setBounds(405, 344, 140, 25);
		frameSeleccionarProveedor.getContentPane().add(lblSeleccionar);

		JLabel separador = new JLabel("");
		separador.setBackground(Color.BLACK);
		separador.setOpaque(true);
		separador.setBounds(0, 0, 600, 1);
		frameSeleccionarProveedor.getContentPane().add(separador);
		frameSeleccionarProveedor.setTitle("SELECCIONAR PROVEEDOR");
		frameSeleccionarProveedor.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormAgregarProveedor.class.getResource("/iconos/iconProvider.png")));
		frameSeleccionarProveedor.setBackground(Color.WHITE);
		frameSeleccionarProveedor.setBounds(100, 100, 616, 427);
		frameSeleccionarProveedor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
