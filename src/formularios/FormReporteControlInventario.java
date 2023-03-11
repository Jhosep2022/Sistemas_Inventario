package formularios;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Producto;
import clases.Usuario;
import operaciones.OpReporte;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormReporteControlInventario {

	private JFrame frameReporteInventario;
	private JTable tablareporte;
	private Usuario usuario;
	private FormMenu form;
	private String datatable[][];
	private JLabel ttotal, lblTotalBs, lblCantTotal, ttotal_1;

	
	
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormReporteControlInventario window =
	 * new FormReporteControlInventario();
	 * window.frameReporteInventario.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public JFrame getFrameReporteInventario() {
		return frameReporteInventario;
	}

	public void setFrameReporteInventario(JFrame frameReporteInventario) {
		this.frameReporteInventario = frameReporteInventario;
	}

	public FormReporteControlInventario(FormMenu f, Usuario u) {
		usuario = u;
		form = f;
		initialize();
		frameReporteInventario.setLocationRelativeTo(null);

	}

	public String[][] CargarDatos() {
		String datosventa[][] = OpReporte.ListarVentasTotales(),
				datosproductos[][] = OpReporte.ListarVentasProductosTotales();
		for (int i = 0; i < datosventa.length; i++) {
			int idproducto = Integer.parseInt(datosventa[i][0]);
			int cantidad = Integer.parseInt(datosventa[i][1]);
			for (int j = 0; j < datosproductos.length; j++) {

				int idproducto2 = Integer.parseInt(datosproductos[j][2]);
				int cantidad2 = Integer.parseInt(datosproductos[j][5]);
				if (idproducto2 == idproducto) {
					if (cantidad == 0) {
						j = datosproductos.length - 1;
					} else {
						if (cantidad >= cantidad2) {
							cantidad = cantidad - cantidad2;
							datosproductos[j][5] = "0";
						} else {
							cantidad2 = cantidad2 - cantidad;
							cantidad = 0;
							datosproductos[j][5] = cantidad2 + "";
						}
					}
				}
			}

		}
		return datosproductos;
	}

	public void DatosTabla(int id) {
		String data[][];
		String datos[][] = CargarDatos();
		if (id == 0) {
			int nro = 0;
			for (int j = 0; j < datos.length; j++) {
				int cant = Integer.parseInt(datos[j][5]);
				if (cant > 0) {
					nro++;
				}
			}
			data = new String[nro][7];
			int i = 0;
			float total = 0;
			for (int j = 0; j < datos.length; j++) {
				int cant = Integer.parseInt(datos[j][5]);
				if (cant > 0) {
					data[i][0] = datos[j][0];
					data[i][1] = datos[j][1];
					data[i][2] = datos[j][3];
					data[i][3] = datos[j][4];
					data[i][4] = datos[j][5] + "  ";
					data[i][5] = String.format("%.2f  ", Float.parseFloat(datos[j][6]));
					data[i][6] = String.format("%.2f  ",
							(Float.parseFloat(datos[j][6]) * Integer.parseInt(datos[j][5])));
					total = total + (Float.parseFloat(datos[j][6]) * Integer.parseInt(datos[j][5]));
					i++;
				}
			}
			ttotal.setText(String.format("%.2f  ", total));
			lblCantTotal.setVisible(false);
			ttotal_1.setVisible(false);

		} else {
			int nro = 0;
			for (int j = 0; j < datos.length; j++) {
				int cant = Integer.parseInt(datos[j][5]);
				if (cant > 0 && datos[j][2].equals(String.valueOf(id))) {
					nro++;
				}
			}
			data = new String[nro][7];
			int i = 0;
			float total = 0;
			int cantidad = 0;
			for (int j = 0; j < datos.length; j++) {
				int cant = Integer.parseInt(datos[j][5]);
				if (cant > 0 && datos[j][2].equals(String.valueOf(id))) {
					data[i][0] = datos[j][0];
					data[i][1] = datos[j][1];
					data[i][2] = datos[j][3];
					data[i][3] = datos[j][4];
					data[i][4] = datos[j][5] + "  ";
					data[i][5] = String.format("%.2f  ", Float.parseFloat(datos[j][6]));
					data[i][6] = String.format("%.2f  ",
							(Float.parseFloat(datos[j][6]) * Integer.parseInt(datos[j][5])));
					total = total + (Float.parseFloat(datos[j][6]) * Integer.parseInt(datos[j][5]));
					cantidad = cantidad + Integer.parseInt(datos[j][5]);
					i++;
				}
			}
			ttotal.setText(String.format("%.2f  ", total));
			lblCantTotal.setVisible(true);
			ttotal_1.setVisible(true);
			ttotal_1.setText(cantidad + "  ");
		}
		datatable = data;
	}

	public void CargarTabla(int id) {

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		DatosTabla(id);
		tablareporte.setModel(new DefaultTableModel(datatable,
				new String[] { "ID COMPRA", "FECHA", "PRODUCTO", "CATEGORIA", "CANTIDAD", "PRECIO U.", "SUBTOTAL" }) {

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
		tablareporte.getColumnModel().getColumn(0).setResizable(false);
		tablareporte.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablareporte.getColumnModel().getColumn(0).setMinWidth(100);
		tablareporte.getColumnModel().getColumn(0).setMaxWidth(100);
		tablareporte.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(1).setResizable(false);
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(140);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(140);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(140);
		tablareporte.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(4).setResizable(false);
		tablareporte.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablareporte.getColumnModel().getColumn(4).setMinWidth(80);
		tablareporte.getColumnModel().getColumn(4).setMaxWidth(80);
		tablareporte.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(5).setResizable(false);
		tablareporte.getColumnModel().getColumn(5).setPreferredWidth(120);
		tablareporte.getColumnModel().getColumn(5).setMinWidth(120);
		tablareporte.getColumnModel().getColumn(5).setMaxWidth(120);
		tablareporte.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(6).setResizable(false);
		tablareporte.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
	}

	private void initialize() {
		frameReporteInventario = new JFrame();
		frameReporteInventario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				form.getFrameMenu().setVisible(true);
			}
		});
		frameReporteInventario.getContentPane().setBackground(Color.WHITE);
		frameReporteInventario.getContentPane().setLayout(null);

		JLabel separador = new JLabel("");
		separador.setOpaque(true);
		separador.setBackground(Color.BLACK);
		separador.setBounds(0, 0, 1020, 1);
		frameReporteInventario.getContentPane().add(separador);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 1020, 20);
		frameReporteInventario.getContentPane().add(labelusuarioregistro);

		JLabel lblReporteInventario = new JLabel("REPORTE INVENTARIO");
		lblReporteInventario.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteInventario.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblReporteInventario.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblReporteInventario.setBackground(Color.WHITE);
		lblReporteInventario.setBounds(0, 20, 1020, 30);
		frameReporteInventario.getContentPane().add(lblReporteInventario);

		JLabel lblFechaFinal = new JLabel("PRODUCTO");
		lblFechaFinal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaFinal.setBounds(600, 70, 400, 20);
		frameReporteInventario.getContentPane().add(lblFechaFinal);

		JButton btnlimpiar = new JButton("LIMPIAR");
		btnlimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlimpiar.setForeground(Color.WHITE);
		btnlimpiar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnlimpiar.setBorderPainted(false);
		btnlimpiar.setBackground(Color.BLUE);
		btnlimpiar.setBounds(800, 135, 200, 25);
		frameReporteInventario.getContentPane().add(btnlimpiar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 175, 980, 328);
		frameReporteInventario.getContentPane().add(scrollPane);

		ttotal_1 = new JLabel("0  ");
		ttotal_1.setOpaque(true);
		ttotal_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal_1.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal_1.setBounds(660, 513, 80, 25);
		frameReporteInventario.getContentPane().add(ttotal_1);

		lblCantTotal = new JLabel("CANT. TOTAL ");
		lblCantTotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblCantTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCantTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCantTotal.setBounds(460, 513, 200, 25);
		frameReporteInventario.getContentPane().add(lblCantTotal);

		ttotal = new JLabel("0,00  ");
		ttotal.setOpaque(true);
		ttotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(860, 513, 140, 25);
		frameReporteInventario.getContentPane().add(ttotal);

		lblTotalBs = new JLabel("TOTAL Bs. ");
		lblTotalBs.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTotalBs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBs.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalBs.setBounds(740, 513, 120, 25);
		frameReporteInventario.getContentPane().add(lblTotalBs);

		tablareporte = new JTable();
		tablareporte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablareporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablareporte.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablareporte.setSelectionForeground(new Color(26, 115, 232));
		tablareporte.setSelectionBackground(new Color(232, 240, 254));

		tablareporte.setBorder(null);
		tablareporte.setRowHeight(20);
		JTableHeader th = tablareporte.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		String data[][] = new String[15][7];
		tablareporte.setModel(new DefaultTableModel(data,
				new String[] { "ID COMPRA", "FECHA", "PRODUCTO", "CATEGORIA", "CANTIDAD", "PRECIO U.", "SUBTOTAL" }) {

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
		tablareporte.getColumnModel().getColumn(0).setResizable(false);
		tablareporte.getColumnModel().getColumn(0).setPreferredWidth(100);
		tablareporte.getColumnModel().getColumn(0).setMinWidth(100);
		tablareporte.getColumnModel().getColumn(0).setMaxWidth(100);
		tablareporte.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(1).setResizable(false);
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(140);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(140);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(140);
		tablareporte.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(4).setResizable(false);
		tablareporte.getColumnModel().getColumn(4).setPreferredWidth(80);
		tablareporte.getColumnModel().getColumn(4).setMinWidth(80);
		tablareporte.getColumnModel().getColumn(4).setMaxWidth(80);
		tablareporte.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(5).setResizable(false);
		tablareporte.getColumnModel().getColumn(5).setPreferredWidth(120);
		tablareporte.getColumnModel().getColumn(5).setMinWidth(120);
		tablareporte.getColumnModel().getColumn(5).setMaxWidth(120);
		tablareporte.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(6).setResizable(false);
		tablareporte.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tablareporte.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollPane.setViewportView(tablareporte);

		JComboBox<Producto> tproducto = new JComboBox<Producto>();
		tproducto.setBackground(Color.WHITE);
		tproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tproducto.getSelectedIndex() > 0) {
					CargarTabla(((Producto) tproducto.getSelectedItem()).getIdproducto());
				} else {
					CargarTabla(0);
				}
			}
		});
		ArrayList<Producto> prod = OpReporte.ListarProducto();
		for (int i = 0; i < prod.size(); i++) {
			tproducto.addItem(prod.get(i));
		}
		tproducto.setForeground(Color.BLACK);
		tproducto.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tproducto.setBounds(600, 90, 400, 25);
		frameReporteInventario.getContentPane().add(tproducto);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameReporteInventario.dispose();
			}
		});
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(20, 514, 140, 25);
		frameReporteInventario.getContentPane().add(lblVolver);

		frameReporteInventario.setTitle("REPORTE CONTROL DE INVENTARIO");
		frameReporteInventario.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(FormReporteControlInventario.class.getResource("/iconos/iconReport.png")));
		frameReporteInventario.setResizable(false);
		frameReporteInventario.setBounds(100, 100, 1036, 597);
		frameReporteInventario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
