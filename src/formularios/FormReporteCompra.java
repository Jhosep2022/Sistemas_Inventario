package formularios;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpReporte;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormReporteCompra {

	private JFrame frameReporteCompra;
	private Usuario usuario;
	private JTable tablareporte;
	private JLabel ttotal;
	private JFormattedTextField tfechaf, tfechai;
	private FormMenu form;
	
	

	public JFrame getFrameReporteCompra() {
		return frameReporteCompra;
	}

	public void setFrameReporteCompra(JFrame frameReporteCompra) {
		this.frameReporteCompra = frameReporteCompra;
	}

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormReporteCompra window = new
	 * FormReporteCompra(new FormMenu(new Usuario()), new Usuario());
	 * window.frameReporteCompra.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	public void CargarTabla(String fechai, String fechaf) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		String data[][] = OpReporte.ListarCompras(fechai, fechaf);
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "ID", "PROVEEDOR", "FECHA", "PRODUCTO",
				"CATEGORIA", "CANTIDAD", "PRECIO U.", "SUBTOTAL" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablareporte.getColumnModel().getColumn(0).setResizable(false);
		tablareporte.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMinWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMaxWidth(40);
		tablareporte.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(1).setResizable(false);
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(140);
		tablareporte.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(4).setResizable(false);
		tablareporte.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(5).setResizable(false);
		tablareporte.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMinWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMaxWidth(80);
		tablareporte.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(6).setResizable(false);
		tablareporte.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMinWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMaxWidth(100);
		tablareporte.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(7).setResizable(false);
		tablareporte.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		float total = 0;
		System.out.println(data.length);
		for (int i = 0; i < data.length; i++) {
			String d = data[i][7];
			System.out.println(i + " " + d);
			d = d.trim();
			d = d.replace(",", ".");
			System.out.println(i + " " + d);
			float p = Float.parseFloat(d);
			total = p + total;
		}
		ttotal.setText(String.format("%.2f  ", total));
	}

	public void Limpiar() {
		tfechai.setText("");
		tfechaf.setText("");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		String data[][] = new String[15][8];
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "ID", "PROVEEDOR", "FECHA", "PRODUCTO",
				"CATEGORIA", "CANTIDAD", "PRECIO U.", "SUBTOTAL" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablareporte.getColumnModel().getColumn(0).setResizable(false);
		tablareporte.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMinWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMaxWidth(40);
		tablareporte.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(1).setResizable(false);
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(140);
		tablareporte.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(4).setResizable(false);
		tablareporte.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(5).setResizable(false);
		tablareporte.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMinWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMaxWidth(80);
		tablareporte.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(6).setResizable(false);
		tablareporte.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMinWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMaxWidth(100);
		tablareporte.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(7).setResizable(false);
		tablareporte.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		ttotal.setText("0,00  ");
	}

	public FormReporteCompra(FormMenu f, Usuario u) {
		usuario = u;
		form = f;
		initialize();
		frameReporteCompra.setLocationRelativeTo(null);
	}

	private void initialize() {
		// usuario = new Usuario();
		// usuario.setNombre("MIGUEL ANGEL GUTIERREZ COLQUE");
		// usuario.setTipo("ADMINISTRADOR");
		frameReporteCompra = new JFrame();
		frameReporteCompra.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				form.getFrameMenu().setVisible(true);
			}
		});
		frameReporteCompra.getContentPane().setBackground(Color.WHITE);
		frameReporteCompra.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormReporteCompra.class.getResource("/iconos/iconReport.png")));
		frameReporteCompra.setResizable(false);
		frameReporteCompra.setTitle("REPORTE COMPRA");
		frameReporteCompra.setBounds(100, 100, 1136, 597);
		frameReporteCompra.getContentPane().setLayout(null);
		frameReporteCompra.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 1120, 20);
		frameReporteCompra.getContentPane().add(labelusuarioregistro);

		JLabel labelreportecompra = new JLabel("REPORTE DE COMPRAS");
		labelreportecompra.setHorizontalAlignment(SwingConstants.CENTER);
		labelreportecompra.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelreportecompra.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelreportecompra.setBackground(Color.WHITE);
		labelreportecompra.setBounds(0, 20, 1120, 30);
		frameReporteCompra.getContentPane().add(labelreportecompra);

		JLabel separador = new JLabel("");
		separador.setBackground(Color.BLACK);
		separador.setOpaque(true);
		separador.setBounds(0, 0, 1120, 1);
		frameReporteCompra.getContentPane().add(separador);

		JLabel lblFechaInicio = new JLabel("FECHA INICIO");
		lblFechaInicio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaInicio.setBounds(600, 70, 240, 20);
		frameReporteCompra.getContentPane().add(lblFechaInicio);

		JLabel lblFechaFinal = new JLabel("FECHA FINAL");
		lblFechaFinal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaFinal.setBounds(860, 70, 240, 20);
		frameReporteCompra.getContentPane().add(lblFechaFinal);

		tfechai = new JFormattedTextField();
		tfechai.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.foco(tfechai);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.fuerafoco(tfechai);
			}
		});
		tfechai.setForeground(Color.BLACK);
		tfechai.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		CajasTexto.fuerafoco(tfechai);
		tfechai.setBounds(600, 90, 240, 25);
		try {
			tfechai.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frameReporteCompra.getContentPane().add(tfechai);

		tfechaf = new JFormattedTextField();
		tfechaf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.foco(tfechaf);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.fuerafoco(tfechaf);
			}
		});
		tfechaf.setForeground(Color.BLACK);
		tfechaf.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		CajasTexto.fuerafoco(tfechaf);
		tfechaf.setBounds(860, 90, 240, 25);
		try {
			tfechaf.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frameReporteCompra.getContentPane().add(tfechaf);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane.setBounds(20, 175, 1080, 328);
		frameReporteCompra.getContentPane().add(scrollPane);

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
		String data[][] = new String[15][8];
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "ID", "PROVEEDOR", "FECHA", "PRODUCTO",
				"CATEGORIA", "CANTIDAD", "PRECIO U.", "SUBTOTAL" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };

			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		tablareporte.getColumnModel().getColumn(0).setResizable(false);
		tablareporte.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMinWidth(40);
		tablareporte.getColumnModel().getColumn(0).setMaxWidth(40);
		tablareporte.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(1).setResizable(false);
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(140);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(140);
		tablareporte.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(3).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(4).setResizable(false);
		tablareporte.getColumnModel().getColumn(4).setPreferredWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMinWidth(200);
		tablareporte.getColumnModel().getColumn(4).setMaxWidth(200);

		tablareporte.getColumnModel().getColumn(5).setResizable(false);
		tablareporte.getColumnModel().getColumn(5).setPreferredWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMinWidth(80);
		tablareporte.getColumnModel().getColumn(5).setMaxWidth(80);
		tablareporte.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

		tablareporte.getColumnModel().getColumn(6).setResizable(false);
		tablareporte.getColumnModel().getColumn(6).setPreferredWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMinWidth(100);
		tablareporte.getColumnModel().getColumn(6).setMaxWidth(100);
		tablareporte.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);

		tablareporte.getColumnModel().getColumn(7).setResizable(false);
		tablareporte.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);

		tablareporte.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollPane.setViewportView(tablareporte);

		JButton btngenerar = new JButton("GENERAR");
		btngenerar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btngenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechai = "", fechaf = "";
				if (!tfechai.getText().equals("    -  -  ")) {
					fechai = tfechai.getText();
				}
				if (!tfechaf.getText().equals("    -  -  ")) {
					fechaf = tfechaf.getText();
				}
				CargarTabla(fechai, fechaf);
			}
		});
		btngenerar.setBorderPainted(false);
		btngenerar.setBackground(Color.BLUE);
		btngenerar.setForeground(Color.WHITE);
		btngenerar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btngenerar.setBounds(990, 135, 110, 25);
		frameReporteCompra.getContentPane().add(btngenerar);

		JButton btnlimpiar = new JButton("LIMPIAR");
		btnlimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		btnlimpiar.setBorderPainted(false);
		btnlimpiar.setBackground(Color.BLUE);
		btnlimpiar.setForeground(Color.WHITE);
		btnlimpiar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnlimpiar.setBounds(860, 135, 110, 25);
		frameReporteCompra.getContentPane().add(btnlimpiar);

		JLabel lblTotalBs = new JLabel("TOTAL Bs. ");
		lblTotalBs.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblTotalBs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBs.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTotalBs.setBounds(870, 513, 110, 25);
		frameReporteCompra.getContentPane().add(lblTotalBs);

		ttotal = new JLabel("0,00  ");
		ttotal.setOpaque(true);
		ttotal.setHorizontalTextPosition(SwingConstants.RIGHT);
		ttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		ttotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ttotal.setBounds(980, 513, 120, 25);
		frameReporteCompra.getContentPane().add(ttotal);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameReporteCompra.dispose();
			}
		});
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(20, 513, 140, 25);
		frameReporteCompra.getContentPane().add(lblVolver);
	}
}