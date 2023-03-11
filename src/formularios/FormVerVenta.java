package formularios;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.DetalleVenta;
import clases.Venta;

public class FormVerVenta {

	private JFrame frameDetalle;
	private JTable tabladetalle;
	private JLabel lblCompra;
	private JLabel tusuario;
	private JLabel tproveedor;
	private JLabel tobservacion;
	private JLabel tfecharegistro;
	private JLabel tnro;
	private JLabel lblTotalBs;
	private JLabel ttotal;
	private FormVenta formventa;
	private Venta venta;

	public JFrame getFrameDetalle() {
		return frameDetalle;
	}

	public void setFrameDetalle(JFrame frameDetalle) {
		this.frameDetalle = frameDetalle;
	}

	public void cargarTabla(String datos[][]) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

		tabladetalle.setModel(new DefaultTableModel(datos,
				new String[] { "NRO.", "PRODUCTO", "CANT.", "PRECIO UNIT. Bs.", "SUBTOTAL Bs." }) {

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
		tabladetalle.getColumnModel().getColumn(0).setResizable(false);
		tabladetalle.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabladetalle.getColumnModel().getColumn(0).setMinWidth(40);
		tabladetalle.getColumnModel().getColumn(0).setMaxWidth(40);
		tabladetalle.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tabladetalle.getColumnModel().getColumn(1).setResizable(false);
		tabladetalle.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabladetalle.getColumnModel().getColumn(1).setMinWidth(220);
		tabladetalle.getColumnModel().getColumn(1).setMaxWidth(220);

		tabladetalle.getColumnModel().getColumn(2).setResizable(false);
		tabladetalle.getColumnModel().getColumn(2).setPreferredWidth(40);
		tabladetalle.getColumnModel().getColumn(2).setMinWidth(40);
		tabladetalle.getColumnModel().getColumn(2).setMaxWidth(40);
		tabladetalle.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tabladetalle.getColumnModel().getColumn(3).setResizable(false);
		tabladetalle.getColumnModel().getColumn(3).setPreferredWidth(110);
		tabladetalle.getColumnModel().getColumn(3).setMinWidth(110);
		tabladetalle.getColumnModel().getColumn(3).setMaxWidth(110);
		tabladetalle.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

		tabladetalle.getColumnModel().getColumn(4).setResizable(false);
		tabladetalle.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

	}

	public void mostrarCompra(Venta v) {
		lblCompra.setText("NRO. DE COMPRA 000" + v.getIdventa());
		tusuario.setText(v.getUsuario().getNombre());
		tproveedor.setText(v.getCliente().getNombre());
		tfecharegistro.setText(v.getFecharegistro());
		tobservacion.setText(v.getObservacion());
		tnro.setText(v.getListaventa().size() + "");
		ArrayList<DetalleVenta> det = v.getListaventa();
		String datos[][] = new String[det.size()][5];
		for (int i = 0; i < det.size(); i++) {
			datos[i][0] = (i + 1) + "";
			datos[i][1] = "  " + det.get(i).getProducto().getProducto();
			datos[i][2] = det.get(i).getCantidad() + "";
			datos[i][3] = String.format("%.2f", det.get(i).getProducto().getPrecioventa()) + "  ";
			datos[i][4] = String.format("%.2f", (det.get(i).getCantidad() * det.get(i).getProducto().getPrecioventa()))
					+ "  ";
		}
		cargarTabla(datos);
	}

	public void mostrarVenta(Venta v) {
		lblCompra.setText("NRO. DE VENTA 000" + v.getIdventa());
		tusuario.setText(v.getUsuario().getNombre());
		tproveedor.setText(v.getCliente().getNombre());
		tfecharegistro.setText(v.getFecharegistro());
		tobservacion.setText(v.getObservacion());
		tnro.setText(v.getListaventa().size() + "");
		ArrayList<DetalleVenta> det = v.getListaventa();
		String datos[][] = new String[det.size()][5];
		for (int i = 0; i < det.size(); i++) {
			datos[i][0] = (i + 1) + "";
			datos[i][1] = "  " + det.get(i).getProducto().getProducto();
			datos[i][2] = det.get(i).getCantidad() + "";
			datos[i][3] = String.format("%.2f", det.get(i).getProducto().getPrecioventa()) + "  ";
			datos[i][4] = String.format("%.2f", (det.get(i).getCantidad() * det.get(i).getProducto().getPrecioventa()))
					+ "  ";
		}
		ttotal.setText(String.format("%.2f", v.getTotal()) + "     ");
		cargarTabla(datos);
	}

	public FormVerVenta(Venta v, FormVenta fv) {
		formventa = fv;
		initialize();
		frameDetalle.setLocationRelativeTo(null);
		mostrarVenta(v);
		venta = v;
	}

	private void initialize() {
		frameDetalle = new JFrame();
		frameDetalle.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formventa.cargarTabla("");
				formventa.setActualizarRegistra(venta.getIdventa());
				formventa.getFrameventa().setVisible(true);
				formventa.getFrameventa().setLocationRelativeTo(null);
			}
		});
		frameDetalle.setTitle("VER DETALLE DE LA VENTA");
		frameDetalle.setBackground(Color.WHITE);
		frameDetalle.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormVerCompra.class.getResource("/iconos/iconView.png")));
		frameDetalle.getContentPane().setBackground(Color.WHITE);
		frameDetalle.getContentPane().setLayout(null);

		lblCompra = new JLabel("NRO. DE VENTA XXXXX");
		lblCompra.setHorizontalAlignment(SwingConstants.CENTER);
		lblCompra.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblCompra.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblCompra.setBackground(Color.WHITE);
		lblCompra.setBounds(0, 20, 600, 30);
		frameDetalle.getContentPane().add(lblCompra);

		JLabel labeltopseparador = new JLabel("");
		labeltopseparador.setBackground(Color.BLACK);
		labeltopseparador.setOpaque(true);
		labeltopseparador.setBounds(0, 0, 600, 1);
		frameDetalle.getContentPane().add(labeltopseparador);

		JLabel lfecharegistro = new JLabel("FECHA HORA");
		lfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecharegistro.setBounds(20, 170, 270, 20);
		frameDetalle.getContentPane().add(lfecharegistro);

		tfecharegistro = new JLabel("---------------------------------------------");
		tfecharegistro.setForeground(Color.GRAY);
		tfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tfecharegistro.setBounds(20, 190, 270, 20);
		frameDetalle.getContentPane().add(tfecharegistro);

		JLabel lobservacion = new JLabel("OBSERVACIÓN");
		lobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lobservacion.setBounds(20, 120, 560, 20);
		frameDetalle.getContentPane().add(lobservacion);

		tusuario = new JLabel(
				"---------------------------------------------------------------------------------------------");
		tusuario.setForeground(Color.GRAY);
		tusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tusuario.setBounds(20, 90, 270, 20);
		frameDetalle.getContentPane().add(tusuario);

		JLabel lusuario = new JLabel("USUARIO");
		lusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lusuario.setBounds(20, 70, 270, 20);
		frameDetalle.getContentPane().add(lusuario);

		tobservacion = new JLabel(
				"---------------------------------------------------------------------------------------------");
		tobservacion.setForeground(Color.GRAY);
		tobservacion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tobservacion.setBounds(20, 140, 560, 20);
		frameDetalle.getContentPane().add(tobservacion);

		JLabel lproveedor = new JLabel("CLIENTE");
		lproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lproveedor.setBounds(310, 70, 270, 20);
		frameDetalle.getContentPane().add(lproveedor);

		tproveedor = new JLabel(
				"---------------------------------------------------------------------------------------------");
		tproveedor.setForeground(Color.GRAY);
		tproveedor.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tproveedor.setBounds(310, 90, 270, 20);
		frameDetalle.getContentPane().add(tproveedor);

		JLabel lnro = new JLabel("NRO. PRODUCTOS");
		lnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnro.setBounds(310, 170, 270, 20);
		frameDetalle.getContentPane().add(lnro);

		tnro = new JLabel("---------------------------------------------");
		tnro.setForeground(Color.GRAY);
		tnro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		tnro.setBounds(310, 190, 270, 20);
		frameDetalle.getContentPane().add(tnro);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setEnabled(false);
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 236, 560, 288);
		frameDetalle.getContentPane().add(scrollpanetabla);

		tabladetalle = new JTable();
		tabladetalle.setEnabled(false);

		tabladetalle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabladetalle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tabladetalle.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tabladetalle.setSelectionForeground(new Color(26, 115, 232));
		tabladetalle.setSelectionBackground(new Color(232, 240, 254));
		tabladetalle.setBorder(null);
		tabladetalle.setRowHeight(20);
		JTableHeader th = tabladetalle.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		tabladetalle.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tabladetalle);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameDetalle.dispose();
			}
		});
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(20, 534, 140, 25);
		frameDetalle.getContentPane().add(lblVolver);

		lblTotalBs = new JLabel("TOTAL Bs. ");
		lblTotalBs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBs.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTotalBs.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalBs.setBounds(320, 534, 110, 25);
		frameDetalle.getContentPane().add(lblTotalBs);

		ttotal = new JLabel("-----------------------");
		ttotal.setOpaque(true);
		ttotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(440, 534, 140, 25);
		frameDetalle.getContentPane().add(ttotal);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setBounds(20, 55, 560, 1);
		frameDetalle.getContentPane().add(lblNewLabel);
		frameDetalle.setBounds(100, 100, 616, 618);
		frameDetalle.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
