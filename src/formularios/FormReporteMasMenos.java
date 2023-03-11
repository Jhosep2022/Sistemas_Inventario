package formularios;

import javax.swing.JFrame;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpReporte;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormReporteMasMenos {

	private JFrame frameReporteProductoMasMenos;
	private JTable tablareporte;
	private JFormattedTextField tfechaf, tfechai;
	private Usuario usuario;
	private FormMenu form;
	
	

	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { FormReporteMasMenos window = new
	 * FormReporteMasMenos(); window.frameReporteProductoMasMenos.setVisible(true);
	 * } catch (Exception e) { e.printStackTrace(); } } }); }
	 */

	public JFrame getFrameReporteProductoMasMenos() {
		return frameReporteProductoMasMenos;
	}

	public void setFrameReporteProductoMasMenos(JFrame frameReporteProductoMasMenos) {
		this.frameReporteProductoMasMenos = frameReporteProductoMasMenos;
	}

	public void Limpiar() {
		tfechai.setText("");
		tfechaf.setText("");
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		String data[][] = new String[15][4];
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "NRO", "PRODUCTO", "CATEGORIA", "CANTIDAD" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

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
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

	}

	public void CargarTablaMas(String fechai, String fechaf) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		String data[][] = OpReporte.ListarProductosMas(fechai, fechaf);
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "NRO", "PRODUCTO", "CATEGORIA", "CANTIDAD" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

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
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	public void CargarTablaMenos(String fechai, String fechaf) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		String data[][] = OpReporte.ListarProductosMenos(fechai, fechaf);
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "NRO", "PRODUCTO", "CATEGORIA", "CANTIDAD" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

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
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
	}

	public FormReporteMasMenos(FormMenu f, Usuario u) {
		usuario = u;
		form = f;
		initialize();
		frameReporteProductoMasMenos.setLocationRelativeTo(null);
	}

	private void initialize() {
		frameReporteProductoMasMenos = new JFrame();
		frameReporteProductoMasMenos.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				form.getFrameMenu().setVisible(true);
			}
		});
		frameReporteProductoMasMenos.getContentPane().setBackground(Color.WHITE);
		frameReporteProductoMasMenos.getContentPane().setLayout(null);

		JLabel lblReporteDeProductos = new JLabel("REPORTE DE PRODUCTOS VENDIDOS");
		lblReporteDeProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeProductos.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblReporteDeProductos.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblReporteDeProductos.setBackground(Color.WHITE);
		lblReporteDeProductos.setBounds(0, 20, 600, 30);
		frameReporteProductoMasMenos.getContentPane().add(lblReporteDeProductos);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 600, 20);
		frameReporteProductoMasMenos.getContentPane().add(labelusuarioregistro);

		JLabel separador = new JLabel("");
		separador.setBackground(Color.BLACK);
		separador.setOpaque(true);
		separador.setBounds(0, 0, 600, 1);
		frameReporteProductoMasMenos.getContentPane().add(separador);

		JLabel lblFechaInicio = new JLabel("FECHA INICIO");
		lblFechaInicio.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaInicio.setBounds(200, 70, 180, 20);
		frameReporteProductoMasMenos.getContentPane().add(lblFechaInicio);

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
		try {
			tfechai.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tfechai.setForeground(Color.BLACK);
		tfechai.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tfechai.setBounds(200, 90, 180, 25);
		CajasTexto.fuerafoco(tfechai);
		frameReporteProductoMasMenos.getContentPane().add(tfechai);

		JLabel lblFechaFinal = new JLabel("FECHA FINAL");
		lblFechaFinal.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblFechaFinal.setBounds(400, 70, 180, 20);
		frameReporteProductoMasMenos.getContentPane().add(lblFechaFinal);

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
		tfechaf.setBounds(400, 90, 180, 25);
		CajasTexto.fuerafoco(tfechaf);
		try {
			tfechaf.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-##-##")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frameReporteProductoMasMenos.getContentPane().add(tfechaf);

		JButton btnlimpiar = new JButton("LIMPIAR");
		btnlimpiar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpiar();
			}
		});
		btnlimpiar.setForeground(Color.WHITE);
		btnlimpiar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnlimpiar.setBorderPainted(false);
		btnlimpiar.setBackground(Color.BLUE);
		btnlimpiar.setBounds(200, 135, 180, 25);
		frameReporteProductoMasMenos.getContentPane().add(btnlimpiar);

		JButton btnMas = new JButton("MAS");
		btnMas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechai = "", fechaf = "";
				if (!tfechai.getText().equals("    -  -  ")) {
					fechai = tfechai.getText();
				}
				if (!tfechaf.getText().equals("    -  -  ")) {
					fechaf = tfechaf.getText();
				}
				CargarTablaMas(fechai, fechaf);
			}
		});
		btnMas.setForeground(Color.WHITE);
		btnMas.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnMas.setBorderPainted(false);
		btnMas.setBackground(Color.BLUE);
		btnMas.setBounds(400, 135, 85, 25);
		frameReporteProductoMasMenos.getContentPane().add(btnMas);

		JButton btnMenos = new JButton("MENOS");
		btnMenos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMenos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fechai = "", fechaf = "";
				if (!tfechai.getText().equals("    -  -  ")) {
					fechai = tfechai.getText();
				}
				if (!tfechaf.getText().equals("    -  -  ")) {
					fechaf = tfechaf.getText();
				}
				CargarTablaMenos(fechai, fechaf);
			}
		});
		btnMenos.setForeground(Color.WHITE);
		btnMenos.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnMenos.setBorderPainted(false);
		btnMenos.setBackground(Color.BLUE);
		btnMenos.setBounds(495, 135, 85, 25);
		frameReporteProductoMasMenos.getContentPane().add(btnMenos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 175, 560, 328);
		frameReporteProductoMasMenos.getContentPane().add(scrollPane);

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

		String data[][] = new String[15][4];
		tablareporte.setModel(new DefaultTableModel(data, new String[] { "NRO", "PRODUCTO", "CATEGORIA", "CANTIDAD" }) {

			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, };

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
		tablareporte.getColumnModel().getColumn(1).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(1).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(2).setResizable(false);
		tablareporte.getColumnModel().getColumn(2).setPreferredWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMinWidth(210);
		tablareporte.getColumnModel().getColumn(2).setMaxWidth(210);

		tablareporte.getColumnModel().getColumn(3).setResizable(false);
		tablareporte.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

		tablareporte.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollPane.setViewportView(tablareporte);

		JLabel lblVolver = new JLabel("VOLVER");
		lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameReporteProductoMasMenos.dispose();
			}
		});
		lblVolver.setHorizontalAlignment(SwingConstants.CENTER);
		lblVolver.setForeground(new Color(26, 115, 232));
		lblVolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblVolver.setBounds(20, 513, 140, 25);
		frameReporteProductoMasMenos.getContentPane().add(lblVolver);
		frameReporteProductoMasMenos.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormReporteMasMenos.class.getResource("/iconos/iconReport.png")));
		frameReporteProductoMasMenos.setTitle("REPORTE DE PRODUCTOS");
		frameReporteProductoMasMenos.setResizable(false);
		frameReporteProductoMasMenos.setBounds(100, 100, 616, 597);
		frameReporteProductoMasMenos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
