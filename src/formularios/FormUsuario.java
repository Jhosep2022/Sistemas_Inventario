package formularios;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Objects;
import javax.swing.*;
import java.awt.ComponentOrientation;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import clases.TipoUsuario;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpUsuario;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormUsuario {

	private JFrame frameUsuario;
	private JTextField tci;
	private JTextField tnombre;
	private JTextField ttelefono;
	private JTextField tcelular;
	private JTextField tdireccion;
	private JTextField tclave;
	private JTextField tci_;
	private JTextField tnombre_;
	private JTextField ttelefono_;
	private JTextField tcelular_;
	private JTextField tdireccion_;
	private JTextField tclave_;
	private JComboBox<TipoUsuario> ttipo;
	private JComboBox<TipoUsuario> ttipo_;
	private JTable tablausuario;
	private Usuario usuario;
	private JTextField tsearch;
	private int idusuario_ = 0;
	private int idusuario__ = 0;
	JLabel testado;
	private JTextField tsearch_;
	private JTable tablausuario_;
	private JLabel lci, lnombre, ltipo, ltelefono, lcelular, lclave, ldireccion, lfecharegistro, lidusuario, testado_;
	private FormMenu formmenu;

	public int getIdusuario__() {
		return idusuario__;
	}

	public void setIdusuario__(int idusuario__) {
		this.idusuario__ = idusuario__;
	}

	public int getIdusuario_() {
		return idusuario_;
	}

	public void setIdusuario_(int idusuario_) {
		this.idusuario_ = idusuario_;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JFrame getFormUsuario() {
		return frameUsuario;
	}

	public void setFormUsuario(JFrame formUsuario) {
		this.frameUsuario = formUsuario;
	}

	public int IndexComboTipo(JComboBox<TipoUsuario> ctu, int idtipo) {

		for (int i = 0; i < ctu.getItemCount(); i++) {
			if (ctu.getItemAt(i).getIdtipo() == idtipo) {
				return i;
			}
		}
		return 0;
	}

	public FormUsuario(Usuario u, FormMenu fm) {
		formmenu = fm;
		setUsuario(u);
		initialize(u);
		frameUsuario.setLocationRelativeTo(null);

	}

	public void ActualizarTabla(JTable tabla, String[] encabezados) {
		if (tsearch != null) {
			tabla.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(tsearch.getText()), encabezados));
		} else {
			tabla.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(""), encabezados));
		}
		TableColumnModel columnModel = tabla.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(45);
		columnModel.getColumn(1).setPreferredWidth(85);
		columnModel.getColumn(2).setPreferredWidth(240);
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(130);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
		tabla.setDefaultRenderer(String.class, centerRenderer);
	}

	public void ActualizarTabla_(JTable tabla, String[] encabezados) {
		if (tsearch_ != null) {
			tabla.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(tsearch_.getText()), encabezados));
		} else {
			tabla.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(""), encabezados));
		}
		TableColumnModel columnModel = tabla.getColumnModel();

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tabla.setDefaultRenderer(String.class, centerRenderer);
		columnModel.getColumn(0).setPreferredWidth(45);
		columnModel.getColumn(1).setPreferredWidth(85);
		columnModel.getColumn(2).setPreferredWidth(240);
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(130);
		tabla.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tabla.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

	}

	public void LimpiarActualizar() {
		tci_.setText("");
		tnombre_.setText("");
		ttelefono_.setText("");
		tcelular_.setText("");
		tdireccion_.setText("");
		tclave_.setText("");
		ttipo_.setSelectedIndex(0);
		setIdusuario_(0);
		tsearch.setText("");
		String encabezados[] = { "ID", "CI", "NOMBRE", "TIPO", "FECHA" };
		ActualizarTabla(tablausuario, encabezados);
		testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
	}

	public void LimpiarEliminar() {
		setIdusuario__(0);
		lci.setText("-----------------------");
		lnombre.setText("--------------------------------------------------");
		ltipo.setText("-----------------------");
		ltelefono.setText("-----------------------");
		lcelular.setText("-----------------------");
		lclave.setText("-----------------------");
		ldireccion.setText("--------------------------------------------------");
		lfecharegistro.setText("----------------------------");
		lidusuario.setText("------------------");
		String encabezados[] = { "ID", "CI", "NOMBRE", "TIPO", "FECHA" };
		ActualizarTabla_(tablausuario_, encabezados);
		testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
		testado_.setToolTipText("Activo");

	}

	public void LimpiarRegistrar() {
		tci.setText("");
		tnombre.setText("");
		ttelefono.setText("");
		tcelular.setText("");
		tdireccion.setText("");
		tclave.setText("");
		ttipo.setSelectedIndex(0);
	}

	private void initialize(Usuario u) {

		frameUsuario = new JFrame();
		frameUsuario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		String encabezados[] = { "ID", "CI", "NOMBRE", "TIPO", "FECHA" };
		frameUsuario.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormUsuario.class.getResource("/iconos/iconUser.png")));
		UIManager.put("Button.disabledText", Color.WHITE);
		frameUsuario.setTitle("MODULO USUARIO");
		frameUsuario.getContentPane().setBackground(Color.WHITE);
		frameUsuario.getContentPane().setForeground(Color.BLACK);
		frameUsuario.getContentPane().setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frameUsuario.setResizable(false);
		frameUsuario.setBackground(Color.WHITE);
		frameUsuario.setBounds(100, 100, 681, 328);
		frameUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ImageIcon iconadduser = new ImageIcon(
				Objects.requireNonNull(this.getClass().getResource("/iconos/iconAddUser.png")));

		ImageIcon iconupdateuser = new ImageIcon(
				Objects.requireNonNull(this.getClass().getResource("/iconos/iconUpdateUser.png")));
		ImageIcon icondelete = new ImageIcon(
				Objects.requireNonNull(this.getClass().getResource("/iconos/iconDelete.png")));

		JTabbedPane cambiopanel = new JTabbedPane(JTabbedPane.TOP);

		cambiopanel.setForeground(Color.BLACK);
		cambiopanel.setOpaque(true);
		cambiopanel.setBorder(null);
		cambiopanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		cambiopanel.setBackground(Color.WHITE);
		cambiopanel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		cambiopanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		frameUsuario.getContentPane().add(cambiopanel);

		JPanel panelregistrar = new JPanel();
		panelregistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panelregistrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelregistrar.setBackground(Color.WHITE);
		cambiopanel.addTab("REGISTRAR", iconadduser, panelregistrar, null);
		panelregistrar.setLayout(null);

		JPanel panelactualizar = new JPanel();
		panelactualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		panelactualizar.setBackground(Color.WHITE);
		cambiopanel.addTab("ACTUALIZAR", iconupdateuser, panelactualizar, null);
		panelactualizar.setLayout(null);

		JLabel lblRegistrarUsuario_ = new JLabel("ACTUALIZAR USUARIO");
		lblRegistrarUsuario_.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuario_.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblRegistrarUsuario_.setBounds(0, 20, 660, 30);
		panelactualizar.add(lblRegistrarUsuario_);

		JLabel labeluserregistro_ = new JLabel(
				"USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: " + usuario.getTipo().toUpperCase() + "     ");
		labeluserregistro_.setHorizontalTextPosition(SwingConstants.CENTER);
		labeluserregistro_.setHorizontalAlignment(SwingConstants.RIGHT);
		labeluserregistro_.setForeground(Color.BLACK);
		labeluserregistro_.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labeluserregistro_.setBounds(0, 0, 660, 20);
		panelactualizar.add(labeluserregistro_);

		JLabel labelci_ = new JLabel("CI *");
		labelci_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelci_.setBounds(20, 70, 140, 20);
		panelactualizar.add(labelci_);

		tci_ = new JTextField();
		tci_.setToolTipText("");
		tci_.setHorizontalAlignment(SwingConstants.LEFT);
		tci_.setForeground(Color.BLACK);
		tci_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tci_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tci_);
		tci_.setAlignmentX(1.0f);
		tci_.setBounds(20, 90, 140, 25);
		panelactualizar.add(tci_);

		JLabel labeltipo_ = new JLabel("TIPO USUARIO *");
		labeltipo_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltipo_.setBounds(20, 130, 140, 20);
		panelactualizar.add(labeltipo_);

		JLabel labelnombre_ = new JLabel("NOMBRE *");
		labelnombre_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelnombre_.setBounds(180, 70, 300, 20);
		panelactualizar.add(labelnombre_);

		tnombre_ = new JTextField();
		tnombre_.setToolTipText("");
		tnombre_.setHorizontalAlignment(SwingConstants.LEFT);
		tnombre_.setForeground(Color.BLACK);
		tnombre_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tnombre_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tnombre_);
		tnombre_.setAlignmentX(1.0f);
		tnombre_.setBounds(180, 90, 300, 25);
		panelactualizar.add(tnombre_);

		JLabel labeltelefono_ = new JLabel("TELÉFONO");
		labeltelefono_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltelefono_.setBounds(500, 70, 140, 15);
		panelactualizar.add(labeltelefono_);

		ttelefono_ = new JTextField();
		ttelefono_.setToolTipText("");
		ttelefono_.setHorizontalAlignment(SwingConstants.LEFT);
		ttelefono_.setForeground(Color.BLACK);
		ttelefono_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		ttelefono_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(ttelefono_);
		ttelefono_.setAlignmentX(1.0f);
		ttelefono_.setBounds(500, 90, 140, 25);
		panelactualizar.add(ttelefono_);

		JLabel labelcelular_ = new JLabel("CELULAR");
		labelcelular_.setForeground(Color.BLACK);
		labelcelular_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelcelular_.setBounds(500, 130, 140, 15);
		panelactualizar.add(labelcelular_);

		tcelular_ = new JTextField();
		tcelular_.setToolTipText("");
		tcelular_.setHorizontalAlignment(SwingConstants.LEFT);
		tcelular_.setForeground(Color.BLACK);
		tcelular_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcelular_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tcelular_);
		tcelular_.setAlignmentX(1.0f);
		tcelular_.setBounds(500, 150, 140, 25);
		panelactualizar.add(tcelular_);

		JLabel labeldireccion_ = new JLabel("DIRECCIÓN");
		labeldireccion_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeldireccion_.setBounds(180, 130, 300, 15);
		panelactualizar.add(labeldireccion_);

		tdireccion_ = new JTextField();
		tdireccion_.setToolTipText("");
		tdireccion_.setHorizontalAlignment(SwingConstants.LEFT);
		tdireccion_.setForeground(Color.BLACK);
		tdireccion_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdireccion_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tdireccion_);
		tdireccion_.setAlignmentX(1.0f);
		tdireccion_.setBounds(180, 150, 300, 25);
		panelactualizar.add(tdireccion_);

		JLabel labelclave_ = new JLabel("CLAVE *");
		labelclave_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave_.setBounds(20, 190, 140, 20);
		panelactualizar.add(labelclave_);

		tclave_ = new JTextField();
		tclave_.setToolTipText("");
		tclave_.setHorizontalAlignment(SwingConstants.LEFT);
		tclave_.setForeground(Color.BLACK);
		tclave_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tclave_.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tclave_);
		tclave_.setAlignmentX(1.0f);
		tclave_.setBounds(20, 210, 140, 25);
		panelactualizar.add(tclave_);

		JButton btnguardar_ = new JButton("ACTUALIZAR");
		btnguardar_.setForeground(Color.WHITE);
		btnguardar_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnguardar_.setFocusPainted(false);
		btnguardar_.setBorderPainted(false);
		btnguardar_.setBorder(null);
		btnguardar_.setBackground(new Color(26, 115, 232));
		btnguardar_.setBounds(500, 210, 140, 25);
		panelactualizar.add(btnguardar_);

		JLabel btnvolver_ = new JLabel("VOLVER AL MENÚ");

		btnvolver_.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver_.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver_.setForeground(new Color(26, 115, 232));
		btnvolver_.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver_.setBounds(500, 529, 140, 25);
		panelactualizar.add(btnvolver_);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setForeground(Color.BLACK);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		scrollpanetabla.setBounds(20, 255, 620, 264);
		panelactualizar.add(scrollpanetabla);
		tablausuario = new JTable();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		tablausuario.setRowMargin(2);
		tablausuario.setOpaque(false);
		tablausuario.setBorder(null);

		tablausuario.setForeground(Color.BLACK);
		tablausuario.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablausuario.setRowHeight(20);

		JTableHeader th = tablausuario.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tablausuario.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(""), encabezados));
		for (int c = 0; c < tablausuario.getColumnCount(); c++) {
			Class<?> col_class = tablausuario.getColumnClass(c);
			tablausuario.setDefaultEditor(col_class, null); // remove editor
		}
		TableColumnModel columnModel = tablausuario.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(45);
		columnModel.getColumn(1).setPreferredWidth(85);
		columnModel.getColumn(2).setPreferredWidth(240);
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(130);
		tablausuario.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		tablausuario.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		tablausuario.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

		scrollpanetabla.setViewportView(tablausuario);

		tsearch = new JTextField();
		tsearch.setText("");
		tsearch.setToolTipText("");
		tsearch.setHorizontalAlignment(SwingConstants.LEFT);
		tsearch.setForeground(Color.BLACK);
		tsearch.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tsearch);
		tsearch.setAlignmentX(1.0f);
		tsearch.setBounds(268, 210, 140, 25);
		panelactualizar.add(tsearch);

		JLabel labelsearch = new JLabel("");
		labelsearch.setToolTipText("Buscar usuario");
		labelsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		labelsearch.setHorizontalAlignment(SwingConstants.CENTER);
		labelsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelsearch.setIconTextGap(10);
		labelsearch.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconSearch_.png")));
		labelsearch.setBounds(410, 210, 25, 25);
		panelactualizar.add(labelsearch);

		JLabel labelbuscar = new JLabel("BUSCAR");
		labelbuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelbuscar.setBounds(268, 190, 148, 20);
		panelactualizar.add(labelbuscar);
		CajasTexto.soloNumeros(tci_);

		JLabel btnlimpiar_ = new JLabel("");
		btnlimpiar_.setToolTipText("Limpiar formulario");
		btnlimpiar_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String[] options = { "Sí", "No" };
				int seleccion = JOptionPane.showOptionDialog(null,
						"¿Quiere limpiar el formulario de actualizar usuario?", "Limpiar formularios",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (seleccion == 0) {
					LimpiarActualizar();
				}
			}
		});
		btnlimpiar_.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlimpiar_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconClear.png")));
		btnlimpiar_.setIconTextGap(10);
		btnlimpiar_.setHorizontalTextPosition(SwingConstants.CENTER);
		btnlimpiar_.setHorizontalAlignment(SwingConstants.CENTER);
		btnlimpiar_.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnlimpiar_.setBounds(455, 210, 25, 25);
		panelactualizar.add(btnlimpiar_);

		tci_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tci_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tci_);
			}
		});

		tnombre_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tnombre_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tnombre_);
			}
		});

		ttelefono_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(ttelefono_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(ttelefono_);
			}
		});

		tcelular_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tcelular_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tcelular_);
			}
		});

		tdireccion_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tdireccion_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tdireccion_);
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

		tclave_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tclave_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tclave_);
			}
		});
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ActualizarTabla(tablausuario, encabezados);
			}
		});

		btnvolver_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frameUsuario.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnvolver_.setForeground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnvolver_.setForeground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnvolver_.setForeground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnvolver_.setForeground(new Color(26, 115, 232));
			}
		});

		tci_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		tnombre_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		ttelefono_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		tcelular_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		tclave_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		tdireccion_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar_.requestFocus();
					btnguardar_.doClick();
				}
			}
		});

		btnguardar_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnguardar_.setBackground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnguardar_.setBackground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnguardar_.setBackground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnguardar_.setBackground(new Color(26, 115, 232));
			}
		});

		JPanel paneleliminar = new JPanel();
		paneleliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		paneleliminar.setBackground(Color.WHITE);
		cambiopanel.addTab("ELIMINAR - BUSCAR", icondelete, paneleliminar, null);
		paneleliminar.setLayout(null);

		JLabel lblRegistrarUsuario__1 = new JLabel("ELIMINAR USUARIO");
		lblRegistrarUsuario__1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuario__1.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblRegistrarUsuario__1.setBounds(0, 20, 660, 30);
		paneleliminar.add(lblRegistrarUsuario__1);

		JLabel labeluserregistro___ = new JLabel(
				"USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: " + usuario.getTipo().toUpperCase() + "     ");
		labeluserregistro___.setHorizontalTextPosition(SwingConstants.CENTER);
		labeluserregistro___.setHorizontalAlignment(SwingConstants.RIGHT);
		labeluserregistro___.setForeground(Color.BLACK);
		labeluserregistro___.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labeluserregistro___.setBounds(0, 0, 660, 20);
		paneleliminar.add(labeluserregistro___);

		JLabel labelci__ = new JLabel("CI");
		labelci__.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelci__.setBounds(20, 70, 140, 15);
		paneleliminar.add(labelci__);

		JLabel labeltipo___ = new JLabel("TIPO USUARIO");
		labeltipo___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltipo___.setBounds(20, 115, 140, 15);
		paneleliminar.add(labeltipo___);

		JLabel labelnombre___ = new JLabel("NOMBRE");
		labelnombre___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelnombre___.setBounds(180, 70, 300, 15);
		paneleliminar.add(labelnombre___);

		JLabel labeltelefono___ = new JLabel("TELÉFONO");
		labeltelefono___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltelefono___.setBounds(500, 70, 140, 15);
		paneleliminar.add(labeltelefono___);

		JLabel labelcelular___ = new JLabel("CELULAR");
		labelcelular___.setForeground(Color.BLACK);
		labelcelular___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelcelular___.setBounds(500, 115, 140, 15);
		paneleliminar.add(labelcelular___);

		JLabel labeldireccion___ = new JLabel("DIRECCIÓN");
		labeldireccion___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeldireccion___.setBounds(180, 115, 300, 15);
		paneleliminar.add(labeldireccion___);

		JLabel labelclave___ = new JLabel("CLAVE");
		labelclave___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave___.setBounds(20, 160, 140, 15);
		paneleliminar.add(labelclave___);

		JButton btneliminar = new JButton("ELIMINAR");
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btneliminar.setEnabled(false);
				int r = tablausuario_.getSelectedRow();
				System.out.println(r);
				if (r < 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario.", "Mensaje", 1);
				} else {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar a este usuario?",
							"Limpiar usuario", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							null);
					if (seleccion == 0) {
						if (OpUsuario.EliminarUsuario(getIdusuario__())) {
							LimpiarEliminar();
							ActualizarTabla(tablausuario, encabezados);
							JOptionPane.showMessageDialog(null, "Se ha eliminado al usuario correctamente.", "Mensaje",
									1);
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido eliminar al usuario.", "Error", 1);
						}
					}
				}
				btneliminar.setEnabled(true);
			}
		});

		btneliminar.setSelectedIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconDeleteW.png")));
		btneliminar.setForeground(Color.WHITE);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btneliminar.setFocusPainted(false);
		btneliminar.setBorderPainted(false);
		btneliminar.setBorder(null);
		btneliminar.setBackground(Color.RED);
		btneliminar.setBounds(500, 215, 140, 25);
		paneleliminar.add(btneliminar);

		JLabel btnvolver__1 = new JLabel("VOLVER AL MENÚ");
		btnvolver__1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver__1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				frameUsuario.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnvolver__1.setForeground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnvolver__1.setForeground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnvolver__1.setForeground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnvolver__1.setForeground(new Color(26, 115, 232));
			}
		});
		btnvolver__1.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver__1.setForeground(new Color(26, 115, 232));
		btnvolver__1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver__1.setBounds(500, 529, 140, 25);
		paneleliminar.add(btnvolver__1);

		JScrollPane scrollpanetabla_ = new JScrollPane();
		scrollpanetabla_.setForeground(Color.BLACK);
		scrollpanetabla_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		scrollpanetabla_.setBorder(null);
		scrollpanetabla_.setBounds(20, 255, 620, 264);
		paneleliminar.add(scrollpanetabla_);

		JLabel labelbuscar___ = new JLabel("BUSCAR");
		labelbuscar___.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelbuscar___.setBounds(180, 215, 70, 25);
		paneleliminar.add(labelbuscar___);

		lci = new JLabel("-----------------------");
		lci.setForeground(Color.GRAY);
		lci.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lci.setBounds(20, 90, 140, 15);
		paneleliminar.add(lci);

		lnombre = new JLabel("--------------------------------------------------");
		lnombre.setForeground(Color.GRAY);
		lnombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lnombre.setBounds(180, 90, 300, 15);
		paneleliminar.add(lnombre);

		ltelefono = new JLabel("-----------------------");
		ltelefono.setForeground(Color.GRAY);
		ltelefono.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		ltelefono.setBounds(500, 90, 140, 15);
		paneleliminar.add(ltelefono);

		ltipo = new JLabel("-----------------------");
		ltipo.setForeground(Color.GRAY);
		ltipo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ltipo.setBounds(20, 135, 140, 15);
		paneleliminar.add(ltipo);

		ldireccion = new JLabel("--------------------------------------------------");
		ldireccion.setForeground(Color.GRAY);
		ldireccion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldireccion.setBounds(180, 135, 300, 15);
		paneleliminar.add(ldireccion);

		lcelular = new JLabel("-----------------------");
		lcelular.setForeground(Color.GRAY);
		lcelular.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		lcelular.setBounds(500, 135, 140, 15);
		paneleliminar.add(lcelular);

		JLabel labelclave____1 = new JLabel("FECHA REGISTRO");
		labelclave____1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave____1.setBounds(180, 160, 170, 15);
		paneleliminar.add(labelclave____1);

		JLabel labelclave____2 = new JLabel("ID USUARIO");
		labelclave____2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave____2.setBounds(370, 160, 110, 15);
		paneleliminar.add(labelclave____2);

		lclave = new JLabel("-----------------------");
		lclave.setForeground(Color.GRAY);
		lclave.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lclave.setBounds(20, 180, 140, 15);
		paneleliminar.add(lclave);

		lfecharegistro = new JLabel("----------------------------");
		lfecharegistro.setForeground(Color.GRAY);
		lfecharegistro.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lfecharegistro.setBounds(180, 180, 170, 15);
		paneleliminar.add(lfecharegistro);

		lidusuario = new JLabel("------------------");
		lidusuario.setForeground(Color.GRAY);
		lidusuario.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lidusuario.setBounds(370, 180, 110, 15);
		paneleliminar.add(lidusuario);

		JLabel lblRegistrarUsuario = new JLabel("REGISTRAR USUARIO");
		lblRegistrarUsuario.setBounds(0, 20, 660, 30);
		lblRegistrarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarUsuario.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelregistrar.add(lblRegistrarUsuario);

		JLabel labeluserregistro = new JLabel(
				"USUARIO: " + usuario.getNombre().toUpperCase() + "         CARGO: " + usuario.getTipo().toUpperCase() + "     ");
		labeluserregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labeluserregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labeluserregistro.setForeground(Color.BLACK);
		labeluserregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labeluserregistro.setBounds(0, 0, 660, 20);
		panelregistrar.add(labeluserregistro);

		JLabel labelci = new JLabel("CI *");
		labelci.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		labelci.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelci.setBounds(20, 70, 140, 20);
		panelregistrar.add(labelci);

		tci = new JTextField();
		tci.setForeground(Color.BLACK);
		tci.setBorder(new LineBorder(Color.GRAY, 1, false));
		tci.setBorder(BorderFactory.createCompoundBorder(tci.getBorder(), BorderFactory.createEmptyBorder(5, 5, 7, 5)));
		tci.setToolTipText("");
		tci.setHorizontalAlignment(SwingConstants.LEFT);
		tci.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tci.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tci);
		tci.setAlignmentX(1.0f);
		tci.setBounds(20, 90, 140, 25);

		panelregistrar.add(tci);

		JLabel labeltipo = new JLabel("TIPO USUARIO *");
		labeltipo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltipo.setBounds(20, 130, 140, 20);
		panelregistrar.add(labeltipo);

		// ttipo = new JComboBox<TipoUsuario>();
		ttipo = OpUsuario.ListarTipoUsuario();
		ttipo.setForeground(Color.BLACK);
		ttipo.setOpaque(false);
		ttipo.setBackground(Color.WHITE);
		CajasTexto.ComboFueraDeFoco(ttipo);
		ttipo.setBounds(20, 150, 140, 25);
		ttipo.setBorder(null);

		panelregistrar.add(ttipo);

		JLabel labelnombre = new JLabel("NOMBRE *");
		labelnombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelnombre.setBounds(180, 70, 300, 20);
		panelregistrar.add(labelnombre);

		tnombre = new JTextField();
		tnombre.setForeground(Color.BLACK);
		tnombre.setToolTipText("");
		tnombre.setHorizontalAlignment(SwingConstants.LEFT);
		tnombre.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tnombre.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tnombre);
		tnombre.setAlignmentX(1.0f);
		tnombre.setBounds(180, 90, 300, 25);
		panelregistrar.add(tnombre);

		JLabel labeltelefono = new JLabel("TELÉFONO");
		labeltelefono.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeltelefono.setBounds(500, 70, 140, 15);
		panelregistrar.add(labeltelefono);

		ttelefono = new JTextField();
		ttelefono.setForeground(Color.BLACK);
		ttelefono.setToolTipText("");
		ttelefono.setHorizontalAlignment(SwingConstants.LEFT);
		ttelefono.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		ttelefono.setColumns(10);
		CajasTexto.TxtFueraDeFoco(ttelefono);
		ttelefono.setAlignmentX(1.0f);
		ttelefono.setBounds(500, 90, 140, 25);
		panelregistrar.add(ttelefono);

		JLabel labelcelular = new JLabel("CELULAR");
		labelcelular.setForeground(Color.BLACK);
		labelcelular.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelcelular.setBounds(500, 130, 140, 15);
		panelregistrar.add(labelcelular);

		// ttipo_ = new JComboBox<TipoUsuario>();
		ttipo_ = OpUsuario.ListarTipoUsuario();
		ttipo_.setForeground(Color.BLACK);
		ttipo_.setOpaque(false);
		ttipo_.setBackground(Color.WHITE);
		CajasTexto.ComboFueraDeFoco(ttipo_);
		ttipo_.setBounds(20, 150, 140, 25);
		ttipo_.setBorder(null);

		tcelular = new JTextField();
		tcelular.setForeground(Color.BLACK);
		tcelular.setToolTipText("");
		tcelular.setHorizontalAlignment(SwingConstants.LEFT);
		tcelular.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcelular.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tcelular);
		tcelular.setAlignmentX(1.0f);
		tcelular.setBounds(500, 150, 140, 25);
		panelregistrar.add(tcelular);

		JLabel labeldireccion = new JLabel("DIRECCIÓN");
		labeldireccion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labeldireccion.setBounds(180, 130, 300, 15);
		panelregistrar.add(labeldireccion);

		tdireccion = new JTextField();
		tdireccion.setForeground(Color.BLACK);
		tdireccion.setToolTipText("");
		tdireccion.setHorizontalAlignment(SwingConstants.LEFT);
		tdireccion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdireccion.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tdireccion);
		tdireccion.setAlignmentX(1.0f);
		tdireccion.setBounds(180, 150, 300, 25);
		panelregistrar.add(tdireccion);

		JLabel labelclave = new JLabel("CLAVE *");
		labelclave.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave.setBounds(20, 190, 140, 20);
		panelregistrar.add(labelclave);

		tclave = new JTextField();
		tclave.setForeground(Color.BLACK);
		tclave.setToolTipText("");
		tclave.setHorizontalAlignment(SwingConstants.LEFT);
		tclave.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tclave.setColumns(10);
		CajasTexto.TxtFueraDeFoco(tclave);
		tclave.setAlignmentX(1.0f);
		tclave.setBounds(20, 210, 140, 25);
		panelregistrar.add(tclave);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");

		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(340, 210, 140, 25);
		panelregistrar.add(btnvolver);

		JButton btnguardar = new JButton("GUARDAR");

		CajasTexto.soloNumeros(tci);
		btnguardar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnguardar.setForeground(Color.WHITE);
		btnguardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnguardar.setFocusPainted(false);
		btnguardar.setBorderPainted(false);
		btnguardar.setBorder(null);
		btnguardar.setBackground(new Color(26, 115, 232));
		btnguardar.setBounds(500, 210, 140, 25);
		panelregistrar.add(btnguardar);

		JLabel btnlimpiar__1 = new JLabel("");
		btnlimpiar__1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String[] options = { "Sí", "No" };
				int seleccion = JOptionPane.showOptionDialog(null,
						"¿Quiere limpiar el formulario de registrar usuario?", "Limpiar formularios",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
				if (seleccion == 0) {
					LimpiarRegistrar();
				}
			}
		});
		btnlimpiar__1.setToolTipText("Limpiar formulario");
		btnlimpiar__1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnlimpiar__1.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconClear.png")));
		btnlimpiar__1.setIconTextGap(10);
		btnlimpiar__1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnlimpiar__1.setHorizontalAlignment(SwingConstants.CENTER);
		btnlimpiar__1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		btnlimpiar__1.setBounds(180, 210, 25, 25);
		panelregistrar.add(btnlimpiar__1);
		panelactualizar.add(ttipo_);

		JLabel lblEstado = new JLabel("ESTADO");
		lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblEstado.setBounds(180, 190, 68, 20);
		panelactualizar.add(lblEstado);

		testado = new JLabel("");
		testado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
		testado.setToolTipText("Activo");
		testado.setIconTextGap(10);
		testado.setHorizontalTextPosition(SwingConstants.CENTER);
		testado.setHorizontalAlignment(SwingConstants.CENTER);
		testado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		testado.setBounds(180, 210, 68, 25);
		panelactualizar.add(testado);

		ttipo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco(ttipo);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco(ttipo);
			}
		});

		tci.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tci);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tci);
			}
		});

		tnombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tnombre);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tnombre);
			}
		});

		ttelefono.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(ttelefono);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(ttelefono);
			}
		});

		tcelular.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tcelular);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tcelular);
			}
		});

		tdireccion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tdireccion);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tdireccion);
			}
		});

		tclave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tclave);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tclave);
			}
		});

		ttipo_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.ComboFoco(ttipo_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.ComboFueraDeFoco(ttipo_);
			}
		});

		btnguardar_.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int r = tablausuario.getSelectedRow();
				if (tablausuario.getSelectedRow() < 0) {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Usuario.", "Mensaje", 1);
				} else {
					btnguardar_.setEnabled(false);
					if (tci_.getText().equals("") || tnombre_.getText().equals("") || tclave_.getText().equals("")
							|| ttipo_.getSelectedIndex() < 1) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios.", "Mensaje", 1);
					} else if (OpUsuario.BuscarCiUsuarioActualizar(Integer.parseInt(tci_.getText()),
							Integer.parseInt((String) tablausuario.getValueAt(r, 1)))) {
						JOptionPane.showMessageDialog(null,
								"Ya existe un usuario registrado con ese número de cédula de identidad.",
								"Cédula de identidad repetida", 0);
					} else {

						TipoUsuario tu = (TipoUsuario) ttipo_.getSelectedItem();
						int estado = 0;
						if (testado.getToolTipText().equals("Activo")) {
							estado = 1;
						}

						Usuario us = new Usuario(tu.getIdtipo(), tu.getTipo(), getIdusuario_(),
								Integer.parseInt(tci_.getText()), estado, tnombre_.getText(), ttelefono_.getText(),
								tcelular_.getText(), "", tclave_.getText(), tdireccion_.getText());
						if (OpUsuario.ActualizarUsuario(us)) {
							JOptionPane.showMessageDialog(null, "El usuario se actualizó correctamente.",
									"Actualización exitosa", 1);
							ActualizarTabla(tablausuario, encabezados);
							LimpiarActualizar();
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido actualizar al usuario.",
									"Actualización fállida", 0);
						}

					}
					btnguardar_.setEnabled(true);
				}
			}
		});

		btnguardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnguardar.setEnabled(false);
				if (tci.getText().equals("") || tnombre.getText().equals("") || tclave.getText().equals("")
						|| ttipo.getSelectedIndex() < 1) {
					JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios.", "Mensaje", 1);
				} else if (OpUsuario.BuscarCiUsuario(Integer.parseInt(tci.getText()))) {
					JOptionPane.showMessageDialog(null,
							"Ya existe un usuario registrado con ese número de cédula de identidad.",
							"Cédula de identidad repetida", 0);
				} else {
					int estado = 0;
					if (testado.getToolTipText().equals("Activo")) {
						estado = 1;
					}
					TipoUsuario tu = (TipoUsuario) ttipo.getSelectedItem();
					Usuario us = new Usuario(tu.getIdtipo(), tu.getTipo(), 0, Integer.parseInt(tci.getText()), estado,
							tnombre.getText(), ttelefono.getText(), tcelular.getText(), "", tclave.getText(),
							tdireccion.getText());
					if (OpUsuario.InsertarUsuario(us)) {
						JOptionPane.showMessageDialog(null, "El usuario se registro correctamente.", "Registro exitoso",
								1);
						ActualizarTabla(tablausuario, encabezados);
						LimpiarRegistrar();
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido registrar al usuario.", "Registro fallido",
								0);
					}

				}
				btnguardar.setEnabled(true);
			}
		});

		btnvolver.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnvolver.setForeground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnvolver.setForeground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnvolver.setForeground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnvolver.setForeground(new Color(26, 115, 232));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				frameUsuario.dispose();
			}
		});

		tci.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		tnombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		ttelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		tcelular.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		tclave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		tdireccion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnguardar.requestFocus();
					btnguardar.doClick();
				}
			}
		});

		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnguardar.setBackground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnguardar.setBackground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnguardar.setBackground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnguardar.setBackground(new Color(26, 115, 232));
			}
		});

		btneliminar.setForeground(Color.WHITE);
		btneliminar.setBackground(new Color(254, 32, 32));

		testado_ = new JLabel("");
		testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
		testado_.setToolTipText("Activo");
		testado_.setIconTextGap(10);
		testado_.setHorizontalTextPosition(SwingConstants.CENTER);
		testado_.setHorizontalAlignment(SwingConstants.CENTER);
		testado_.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		testado_.setBounds(500, 160, 140, 35);
		paneleliminar.add(testado_);

		tsearch_ = new JTextField();
		tsearch_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ActualizarTabla_(tablausuario_, encabezados);
			}
		});
		tsearch_.setToolTipText("");
		tsearch_.setText("");
		tsearch_.setHorizontalAlignment(SwingConstants.LEFT);
		tsearch_.setForeground(Color.BLACK);
		tsearch_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tsearch_.setColumns(10);
		tsearch_.setAlignmentX(1.0f);
		tsearch_.setBounds(250, 215, 203, 25);
		CajasTexto.TxtFueraDeFoco(tsearch_);
		paneleliminar.add(tsearch_);

		JLabel labelsearch_ = new JLabel("");
		labelsearch_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconSearch_.png")));
		labelsearch_.setToolTipText("Buscar usuario");
		labelsearch_.setIconTextGap(10);
		labelsearch_.setHorizontalTextPosition(SwingConstants.CENTER);
		labelsearch_.setHorizontalAlignment(SwingConstants.CENTER);
		labelsearch_.setFont(new Font("Tahoma", Font.PLAIN, 25));
		labelsearch_.setBounds(455, 215, 25, 25);
		paneleliminar.add(labelsearch_);

		btneliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btneliminar.setBackground(new Color(244, 22, 22));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btneliminar.setBackground(new Color(254, 32, 32));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btneliminar.setBackground(new Color(239, 17, 17));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btneliminar.setBackground(new Color(254, 32, 32));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btneliminar.setBackground(new Color(254, 32, 32));
			}
		});

		testado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (testado.getToolTipText().equals("Inactivo")) {
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
					testado.setToolTipText("Activo");
				} else {
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
					testado.setToolTipText("Inactivo");
				}
			}
		});

		tablausuario_ = new JTable();
		tablausuario_.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tablausuario_.getSelectedRow();
				if (r >= 0) {
					setIdusuario__(Integer.parseInt((String) tablausuario_.getValueAt(r, 0)));
					Usuario us = OpUsuario.Buscarusuario(getIdusuario__());
					lci.setText(us.getCi() + "");
					lnombre.setText(us.getNombre());
					ltipo.setText(us.getTipo());
					ltelefono.setText(us.getTelefono());
					lcelular.setText(us.getCelular());
					lclave.setText(us.getClave());
					ldireccion.setText(us.getDireccion());
					lfecharegistro.setText(us.Fecharegistro());
					lidusuario.setText(us.getIdusuario() + "");
					if (us.getEstado() == 1) {
						testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
						testado_.setToolTipText("Activo");
					} else {
						testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate_.png")));
						testado_.setToolTipText("Inactivo");
					}
				} else {
					setIdusuario__(0);
					lci.setText("-----------------------");
					lnombre.setText("--------------------------------------------------");
					ltipo.setText("-----------------------");
					ltelefono.setText("-----------------------");
					lcelular.setText("-----------------------");
					lclave.setText("-----------------------");
					ldireccion.setText("--------------------------------------------------");
					lfecharegistro.setText("----------------------------");
					lidusuario.setText("------------------");
					testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
					testado_.setToolTipText("Activo");
				}
			}
		});
		tablausuario_.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int r = tablausuario_.getSelectedRow();
				if (r >= 0) {
					setIdusuario__(Integer.parseInt((String) tablausuario_.getValueAt(r, 0)));
					Usuario us = OpUsuario.Buscarusuario(getIdusuario__());
					lci.setText(us.getCi() + "");
					lnombre.setText(us.getNombre());
					ltipo.setText(us.getTipo());
					ltelefono.setText(us.getTelefono());
					lcelular.setText(us.getCelular());
					lclave.setText(us.getClave());
					ldireccion.setText(us.getDireccion());
					lfecharegistro.setText(us.Fecharegistro());
					lidusuario.setText(us.getIdusuario() + "");
					if (us.getEstado() == 1) {
						testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
						testado_.setToolTipText("Activo");
					} else {
						testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate_.png")));
						testado_.setToolTipText("Inactivo");
					}
				} else {
					setIdusuario_(0);
					lci.setText("-----------------------");
					lnombre.setText("--------------------------------------------------");
					ltipo.setText("-----------------------");
					ltelefono.setText("-----------------------");
					lcelular.setText("-----------------------");
					lclave.setText("-----------------------");
					ldireccion.setText("--------------------------------------------------");
					lfecharegistro.setText("----------------------------");
					lidusuario.setText("------------------");
					testado_.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate_.png")));
					testado_.setToolTipText("Activo");

				}
			}
		});

		tablausuario_.setRowMargin(2);

		tablausuario_.setOpaque(false);
		tablausuario_.setBorder(null);
		tablausuario_.setModel(new DefaultTableModel(OpUsuario.ListarUsuario(""), encabezados));
		tablausuario_.setForeground(Color.BLACK);
		tablausuario_.setFont(new Font("Segoe UI", Font.BOLD, 12));
		tablausuario_.setRowHeight(20);

		ActualizarTabla(tablausuario_, encabezados);
		JTableHeader th_ = tablausuario_.getTableHeader();

		scrollpanetabla_.setViewportView(tablausuario_);
		for (int c = 0; c < tablausuario_.getColumnCount(); c++) {
			Class<?> col_class = tablausuario_.getColumnClass(c);
			tablausuario_.setDefaultEditor(col_class, null); // remove editor
		}
		th_.setBackground(new Color(26, 115, 232));
		th_.setForeground(Color.WHITE);
		th_.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tablausuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = tablausuario.getSelectedRow();
				if (r >= 0) {
					setIdusuario_(Integer.parseInt((String) tablausuario.getValueAt(r, 0)));
					Usuario us = OpUsuario.Buscarusuario(getIdusuario_());
					tci_.setText(us.getCi() + "");
					tnombre_.setText(us.getNombre());
					ttipo_.setSelectedIndex(IndexComboTipo(ttipo_, us.getIdtipo()));
					ttelefono_.setText(us.getTelefono());
					tcelular_.setText(us.getCelular());
					tclave_.setText(us.getClave());
					tdireccion_.setText(us.getDireccion());
					if (us.getEstado() == 1) {
						testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
						testado.setToolTipText("Activo");
					} else {
						testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
						testado.setToolTipText("Inactivo");
					}
				} else {
					setIdusuario_(0);
					tci_.setText("");
					tnombre_.setText("");
					ttipo_.setSelectedIndex(0);
					ttelefono_.setText("");
					tcelular.setText("");
					tclave_.setText("");
					tdireccion_.setText("");
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
					testado.setToolTipText("Activo");
				}
			}
		});

		tsearch_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tsearch_);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tsearch_);
			}
		});

		tablausuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int r = tablausuario.getSelectedRow();
				if (r >= 0) {
					setIdusuario_(Integer.parseInt((String) tablausuario.getValueAt(r, 0)));
					Usuario us = OpUsuario.Buscarusuario(getIdusuario_());
					tci_.setText(us.getCi() + "");
					tnombre_.setText(us.getNombre());
					ttipo_.setSelectedIndex(IndexComboTipo(ttipo_, us.getIdtipo()));
					ttelefono_.setText(us.getTelefono());
					tcelular_.setText(us.getCelular());
					tclave_.setText(us.getClave());
					tdireccion_.setText(us.getDireccion());
					if (us.getEstado() == 1) {
						testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
						testado.setToolTipText("Activo");
					} else {
						testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconUnActivate.png")));
						testado.setToolTipText("Inactivo");
					}
				} else {
					setIdusuario_(0);
					tci_.setText("");
					tnombre_.setText("");
					ttipo_.setSelectedIndex(0);
					ttelefono_.setText("");
					tcelular.setText("");
					tclave_.setText("");
					tdireccion_.setText("");
					testado.setIcon(new ImageIcon(FormUsuario.class.getResource("/iconos/iconActivate.png")));
					testado.setToolTipText("Activo");
				}
			}
		});
		cambiopanel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (cambiopanel.getSelectedIndex() == 0) {
					if (panelregistrar.getHeight() != 255) {
						frameUsuario.setBounds(100, 100, 681, 328);
						frameUsuario.setLocationRelativeTo(null);
					}
				} else {
					if (panelregistrar.getHeight() != 566) {
						frameUsuario.setBounds(100, 100, 681, 639);
						frameUsuario.setLocationRelativeTo(null);
					}
				}

			}
		});

	}
}
