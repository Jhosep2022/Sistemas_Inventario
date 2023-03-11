package formularios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import decoradores.CajasTexto;
import operaciones.OpCliente;

public class FormAgregarCliente {

	private JFrame frameSeleccionarCliente;
	private JTextField tsearch;
	private JTable tablacliente;
	private int idcliente_ = 0;
	private FormAgregarVenta formag;

	public int getIdcliente_() {
		return idcliente_;
	}

	public void setIdcliente_(int idcliente_) {
		this.idcliente_ = idcliente_;
	}



	public JFrame getFrameSeleccionarCliente() {
		return frameSeleccionarCliente;
	}

	public void setFrameSeleccionarCliente(JFrame frameSeleccionarCliente) {
		this.frameSeleccionarCliente = frameSeleccionarCliente;
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

	public void cargarTabla(String val) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablacliente
				.setModel(new DefaultTableModel(OpCliente.ListarCliente(val), new String[] { "ID", "CI", "CLIENTE" }) {

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
		tablacliente.getColumnModel().getColumn(0).setResizable(false);
		tablacliente.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablacliente.getColumnModel().getColumn(0).setMinWidth(60);
		tablacliente.getColumnModel().getColumn(0).setMaxWidth(60);
		tablacliente.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(1).setResizable(false);
		tablacliente.getColumnModel().getColumn(1).setPreferredWidth(120);
		tablacliente.getColumnModel().getColumn(1).setMinWidth(120);
		tablacliente.getColumnModel().getColumn(1).setMaxWidth(120);
		tablacliente.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		tablacliente.getColumnModel().getColumn(2).setResizable(false);

	}

	public FormAgregarCliente(FormAgregarVenta ag) {
		initialize();
		frameSeleccionarCliente.setLocationRelativeTo(null);
		this.formag = ag;
	}

	private void initialize() {
		frameSeleccionarCliente = new JFrame();
		frameSeleccionarCliente.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formag.getFrameAgregarVenta().setVisible(true);
			}
		});

		frameSeleccionarCliente.setResizable(false);
		frameSeleccionarCliente.getContentPane().setBackground(Color.WHITE);
		frameSeleccionarCliente.getContentPane().setLayout(null);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 105, 560, 228);
		frameSeleccionarCliente.getContentPane().add(scrollpanetabla);

		JLabel btnagregar = new JLabel("");
		btnagregar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getIdcliente_() != 0) {
					for (int i = 1; i < formag.getTcliente().getItemCount(); i++) {
						if (formag.getTcliente().getItemAt(i).getIdcliente() == getIdcliente_()) {
							formag.getTcliente().setSelectedIndex(i);
							i = formag.getTcliente().getItemCount();
							formag.getFrameAgregarVenta().setVisible(true);

						}
					}
					frameSeleccionarCliente.dispose();
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
		frameSeleccionarCliente.getContentPane().add(btnagregar);

		JLabel lblSeleccionar = new JLabel("SELECCIONAR");
		tablacliente = new JTable();
		tablacliente.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int r = tablacliente.getSelectedRow();
				if (r != -1) {
					if (tablacliente.getValueAt(r, 0) != null) {
						Cliente cl = OpCliente.BuscarCliente(Integer.parseInt((String) tablacliente.getValueAt(r, 0)));
						String nombre = cl.getNombre();
						if (nombre != null) {
							btnagregar.setEnabled(true);
							setIdcliente_(cl.getIdcliente());
						} else {
							btnagregar.setEnabled(false);
							setIdcliente_(0);
						}
					} else {
						btnagregar.setEnabled(false);
						setIdcliente_(0);
					}
				}

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

		JLabel lblSeleccionarProveedor = new JLabel("SELECCIONAR CLIENTE");
		lblSeleccionarProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarProveedor.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblSeleccionarProveedor.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblSeleccionarProveedor.setBackground(Color.WHITE);
		lblSeleccionarProveedor.setBounds(0, 20, 600, 30);
		frameSeleccionarCliente.getContentPane().add(lblSeleccionarProveedor);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormAgregarProveedor.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(300, 70, 25, 25);
		frameSeleccionarCliente.getContentPane().add(lsearch);

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
		frameSeleccionarCliente.getContentPane().add(tsearch);

		JLabel btnvolver = new JLabel("VOLVER");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameSeleccionarCliente.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(20, 344, 140, 25);
		frameSeleccionarCliente.getContentPane().add(btnvolver);

		lblSeleccionar.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionar.setForeground(Color.BLACK);
		lblSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblSeleccionar.setBounds(405, 344, 140, 25);
		frameSeleccionarCliente.getContentPane().add(lblSeleccionar);

		JLabel separador = new JLabel("");
		separador.setBackground(Color.BLACK);
		separador.setOpaque(true);
		separador.setBounds(0, 0, 600, 1);
		frameSeleccionarCliente.getContentPane().add(separador);
		frameSeleccionarCliente.setTitle("SELECCIONAR CLIENTE");
		frameSeleccionarCliente.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormAgregarProveedor.class.getResource("/iconos/iconProvider.png")));
		frameSeleccionarCliente.setBackground(Color.WHITE);
		frameSeleccionarCliente.setBounds(100, 100, 616, 427);
		frameSeleccionarCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
