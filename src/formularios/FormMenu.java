package formularios;

import javax.swing.JFrame;
import clases.Usuario;
import operaciones.OpReporte;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMenu {

	private JFrame frameMenu;
	private Usuario usuario;
	private FormMenu formmenu = this;
	private JPanel contenedor;

	public JFrame getFrameMenu() {
		return frameMenu;
	}

	public void setFrameMenu(JFrame frameMenu) {
		this.frameMenu = frameMenu;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public FormMenu(Usuario u) {
		usuario = u;
		initialize(u);
		reporte();
		frameMenu.setLocationRelativeTo(null);
	}

	public void reporte() {
		if (OpReporte.ListarMenosStock() != null) {
			DefaultCategoryDataset datos = new DefaultCategoryDataset();
			String data[][] = OpReporte.ListarMenosStock();
			System.out.println(data.length + " son total");
			String r = "";
			for (int i = 0; i < data.length; i++) {
				if (data[i][1] != null) {
					System.out.println(
							i + " - " + Integer.parseInt(data[i][1]) + " PRODUCTOS CON MENOS STOCK " + data[i][0]);
					datos.setValue(Integer.parseInt(data[i][1]), "PRODUCTOS CON MENOS STOCK", data[i][0]);
				} else {
					datos.setValue(0, "PRODUCTOS CON MENOS STOCK", r);
					r = r + " ";
				}

			}
			JFreeChart grafico = ChartFactory.createBarChart(null, "PRODUTO(S)", "STOCK DISPONIBLE", datos,
					PlotOrientation.HORIZONTAL, true, true, false);
			ChartPanel panel = new ChartPanel(grafico);
			panel.setBorder(null);
			panel.setMouseWheelEnabled(true);
			panel.setPreferredSize(new Dimension(730, 438));
			contenedor.add(panel, BorderLayout.NORTH);

		}

	}

	private void initialize(Usuario u) {
		frameMenu = new JFrame();
		frameMenu.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				String[] options = { "Sí", "No" };
				int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro de cerrar sesión?", "Cerrar sesión",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (seleccion == 0) {
					frameMenu.dispose();
					new FormLogin().getFormLogin().setVisible(true);
				}
			}
		});
		setUsuario(u);
		frameMenu.getContentPane().setBackground(Color.WHITE);
		frameMenu.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frameMenu.setBackground(Color.WHITE);
		frameMenu
				.setIconImage(Toolkit.getDefaultToolkit().getImage(FormMenu.class.getResource("/iconos/iconMenu.png")));
		frameMenu.setResizable(false);
		frameMenu.setTitle("MENÚ");
		frameMenu.setBounds(100, 100, 788, 639);
		frameMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frameMenu.getContentPane().setLayout(null);

		JLabel btncerrarsesion = new JLabel("CERRA SESIÓN");
		btncerrarsesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncerrarsesion.setHorizontalAlignment(SwingConstants.CENTER);
		btncerrarsesion.setForeground(new Color(254, 32, 32));
		btncerrarsesion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btncerrarsesion.setBackground(Color.WHITE);
		btncerrarsesion.setBounds(286, 540, 200, 30);
		frameMenu.getContentPane().add(btncerrarsesion);

		JLabel labelUser = new JLabel(
				"USUARIO: " + (u.getNombre()).toUpperCase() + "         CARGO: " + (u.getTipo()).toUpperCase());
		labelUser.setHorizontalTextPosition(SwingConstants.CENTER);
		labelUser.setHorizontalAlignment(SwingConstants.CENTER);
		labelUser.setForeground(new Color(26, 115, 232));
		labelUser.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelUser.setBounds(0, 510, 772, 30);
		frameMenu.getContentPane().add(labelUser);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuBar.setBorderPainted(false);
		menuBar.setForeground(Color.BLACK);
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		menuBar.setBackground(Color.BLUE);
		menuBar.setBounds(0, 0, 772, 30);
		frameMenu.getContentPane().add(menuBar);

		JMenu mnusuario = new JMenu("USUARIOS");
		mnusuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnusuario.setOpaque(true);
		mnusuario.setForeground(Color.WHITE);
		mnusuario.setBackground(Color.BLUE);
		mnusuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnusuario);

		JMenuItem btnusuario = new JMenuItem("MODULO USUARIOS");
		btnusuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormUsuario_(getUsuario(), formmenu).getFrameUsuario().setVisible(true);
			}
		});
		btnusuario.setForeground(Color.WHITE);
		btnusuario.setBounds(new Rectangle(0, 0, 500, 25));
		btnusuario.setBackground(SystemColor.textHighlight);
		btnusuario.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnusuario.add(btnusuario);

		JMenu mncliente = new JMenu("CLIENTES");
		mncliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mncliente.setOpaque(true);
		mncliente.setForeground(Color.WHITE);
		mncliente.setBackground(Color.BLUE);
		mncliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mncliente);

		JMenuItem btncliente = new JMenuItem("MODULOS CLIENTES");
		btncliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormCliente(getUsuario(), formmenu).getFrameCliente().setVisible(true);
			}
		});
		btncliente.setBackground(SystemColor.textHighlight);
		btncliente.setForeground(Color.WHITE);
		btncliente.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mncliente.add(btncliente);

		JMenu mnproveedor = new JMenu("PROVEEDORES");
		mnproveedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnproveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnproveedor.setBackground(Color.BLUE);
		mnproveedor.setOpaque(true);
		mnproveedor.setForeground(Color.WHITE);
		mnproveedor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnproveedor);

		JMenuItem btnproveedor = new JMenuItem("MODULO PROVEEDORES");
		btnproveedor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnproveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormProveedor(usuario, formmenu).getFrameProveedor().setVisible(true);
			}
		});
		btnproveedor.setBackground(SystemColor.textHighlight);
		btnproveedor.setForeground(Color.WHITE);
		btnproveedor.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnproveedor.add(btnproveedor);

		JMenu mncategoria = new JMenu("CATEGORIAS");
		mncategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mncategoria.setBackground(Color.BLUE);
		mncategoria.setOpaque(true);
		mncategoria.setForeground(Color.WHITE);
		mncategoria.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mncategoria);

		JMenuItem btncategoria = new JMenuItem("MODULO CATEGORIAS");
		btncategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormCategoria(usuario, formmenu).getFrameCategoria().setVisible(true);
			}
		});
		btncategoria.setBackground(SystemColor.textHighlight);
		btncategoria.setForeground(Color.WHITE);
		btncategoria.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mncategoria.add(btncategoria);

		JMenu mnproducto = new JMenu("PRODUCTOS");
		mnproducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnproducto.setBackground(Color.BLUE);
		mnproducto.setOpaque(true);
		mnproducto.setForeground(Color.WHITE);
		mnproducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnproducto);

		JMenuItem btnproducto = new JMenuItem("MODULOS PRODUCTOS");
		btnproducto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnproducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormProducto(usuario, formmenu).getFrameProducto().setVisible(true);
			}
		});
		btnproducto.setBackground(SystemColor.textHighlight);
		btnproducto.setForeground(Color.WHITE);
		btnproducto.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnproducto.add(btnproducto);

		JMenu mncompra = new JMenu("COMPRAS");
		mncompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mncompra.setBackground(Color.BLUE);
		mncompra.setOpaque(true);
		mncompra.setForeground(Color.WHITE);
		mncompra.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mncompra);

		JMenuItem btncompra = new JMenuItem("MODULO COMPRAS");
		btncompra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btncompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormCompra(usuario, formmenu).getFrameCompra().setVisible(true);
			}
		});
		btncompra.setBackground(SystemColor.textHighlight);
		btncompra.setForeground(Color.WHITE);
		btncompra.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mncompra.add(btncompra);

		JMenu mnventa = new JMenu("VENTAS");
		mnventa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnventa.setBackground(Color.BLUE);
		mnventa.setOpaque(true);
		mnventa.setForeground(Color.WHITE);
		mnventa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnventa);

		JMenuItem btnventa = new JMenuItem("MODULO VENTAS");
		btnventa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnventa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormVenta(usuario, formmenu).getFrameventa().setVisible(true);
			}
		});
		btnventa.setBackground(SystemColor.textHighlight);
		btnventa.setForeground(Color.WHITE);
		btnventa.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnventa.add(btnventa);

		JMenu mnreporte = new JMenu("REPORTES");
		mnreporte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mnreporte.setBackground(Color.BLUE);
		mnreporte.setOpaque(true);
		mnreporte.setForeground(Color.WHITE);
		mnreporte.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnreporte);

		JMenuItem mntmNewMenuItem = new JMenuItem("REPORTE COMPRAS");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormReporteCompra(formmenu, usuario).getFrameReporteCompra().setVisible(true);
			}
		});
		mntmNewMenuItem.setForeground(Color.WHITE);
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNewMenuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem.setBackground(SystemColor.textHighlight);
		mnreporte.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("REPORTE VENTAS");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormReporteVenta(formmenu, usuario).getFrameReporteVenta().setVisible(true);
			}
		});
		mntmNewMenuItem_1.setForeground(Color.WHITE);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNewMenuItem_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem_1.setBackground(SystemColor.textHighlight);
		mnreporte.add(mntmNewMenuItem_1);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("REPORTE PRODUCTO MAS Y MENOS VENDIDOS");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormReporteMasMenos(formmenu, usuario).getFrameReporteProductoMasMenos().setVisible(true);
			}
		});
		mntmNewMenuItem_2.setForeground(Color.WHITE);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNewMenuItem_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem_2.setBackground(SystemColor.textHighlight);
		mnreporte.add(mntmNewMenuItem_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("REPORTE INVENTARIO");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameMenu.setVisible(false);
				new FormReporteControlInventario(formmenu, usuario).getFrameReporteInventario().setVisible(true);
			}
		});
		mntmNewMenuItem_3.setForeground(Color.WHITE);
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmNewMenuItem_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mntmNewMenuItem_3.setBackground(SystemColor.textHighlight);
		mnreporte.add(mntmNewMenuItem_3);

		contenedor = new JPanel();
		contenedor.setBorder(new LineBorder(new Color(0, 0, 0)));
		contenedor.setBounds(20, 50, 732, 440);
		frameMenu.getContentPane().add(contenedor);
		contenedor.setLayout(new BorderLayout());

		btncerrarsesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btncerrarsesion.setForeground(new Color(244, 22, 22));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btncerrarsesion.setForeground(new Color(254, 32, 32));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btncerrarsesion.setForeground(new Color(239, 17, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btncerrarsesion.setForeground(new Color(254, 32, 32));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btncerrarsesion.setForeground(new Color(254, 32, 32));
				String[] options = { "Sí", "No" };
				int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro de cerrar sesión?", "Cerrar sesión",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (seleccion == 0) {
					frameMenu.dispose();
					new FormLogin().getFormLogin().setVisible(true);
				}
			}
		});
	}
}
