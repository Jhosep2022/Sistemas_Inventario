package formularios;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import clases.Usuario;
import decoradores.CajasTexto;
import operaciones.OpLogin;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormLogin {

	private JFrame frameLogin;
	private JTextField tusuario;
	private JPasswordField tclave;
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JFrame getFormLogin() {
		return frameLogin;
	}

	public void setFormLogin(JFrame formLogin) {
		this.frameLogin = formLogin;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Metal".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					new FormLogin().getFormLogin().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormLogin() {
		initialize();
		frameLogin.setLocationRelativeTo(null);
		tusuario.setToolTipText("");
		tclave.setText("");

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 400, 1);
		frameLogin.getContentPane().add(lblNewLabel);
	}

	private void initialize() {
		frameLogin = new JFrame();

		frameLogin.getContentPane().setBackground(Color.WHITE);
		frameLogin.setResizable(false);
		frameLogin.getContentPane().setFont(new Font("Segoe UI", Font.BOLD, 16));
		frameLogin.getContentPane().setLayout(null);

		UIManager.put("Button.disabledText", Color.WHITE);

		JLabel labellogin = new JLabel("INICIAR SESIÓN");
		labellogin.setHorizontalAlignment(SwingConstants.CENTER);
		labellogin.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labellogin.setBounds(20, 20, 360, 30);
		frameLogin.getContentPane().add(labellogin);

		JLabel labelci = new JLabel("USUARIO (CI)");
		labelci.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelci.setBounds(110, 85, 180, 20);
		frameLogin.getContentPane().add(labelci);

		JLabel labelclave = new JLabel("CONTRASEÑA");
		labelclave.setFont(new Font("Segoe UI", Font.BOLD, 16));
		labelclave.setBounds(110, 160, 180, 20);
		frameLogin.getContentPane().add(labelclave);

		tusuario = new JTextField();

		tusuario.setForeground(Color.BLACK);
		tusuario.setAlignmentX(Component.RIGHT_ALIGNMENT);
		tusuario.setHorizontalAlignment(SwingConstants.LEFT);
		tusuario.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		tusuario.setBounds(110, 110, 180, 30);

		frameLogin.getContentPane().add(tusuario);
		tusuario.setColumns(10);
		frameLogin.setLocale(new Locale("es"));
		frameLogin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		frameLogin.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormLogin.class.getResource("/iconos/iconLogin.png")));
		frameLogin.setTitle("INICIAR SESIÓN");
		frameLogin.setForeground(Color.WHITE);
		frameLogin.setBackground(Color.WHITE);
		frameLogin.setBounds(100, 100, 416, 339);
		frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CajasTexto.soloNumeros(tusuario);

		tclave = new JPasswordField();
		tclave.setForeground(Color.BLACK);
		tclave.setHorizontalAlignment(SwingConstants.LEFT);
		tclave.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		tclave.setBounds(110, 185, 180, 30);
		frameLogin.getContentPane().add(tclave);
		CajasTexto.TxtFueraDeFocoL(tusuario);
		CajasTexto.TxtFueraDeFocoL(tclave);

		JLabel btnsalir = new JLabel("SALIR");

		btnsalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnsalir.setForeground(new Color(26, 115, 232));
		btnsalir.setHorizontalAlignment(SwingConstants.CENTER);
		btnsalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnsalir.setBounds(40, 250, 120, 30);
		frameLogin.getContentPane().add(btnsalir);

		JButton btningresar = new JButton("INGRESAR");

		btningresar.setForeground(Color.WHITE);
		btningresar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btningresar.setFocusPainted(false);
		btningresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btningresar.setBorderPainted(false);
		btningresar.setBorder(null);
		btningresar.setBackground(new Color(26, 115, 232));
		btningresar.setBounds(240, 250, 120, 30);

		btnsalir.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btnsalir.setForeground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnsalir.setForeground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnsalir.setForeground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btnsalir.setForeground(new Color(26, 115, 232));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				frameLogin.dispose();
			}
		});
		btningresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btningresar.setEnabled(false);
				if (tusuario.getText().equals("") || String.valueOf(tclave.getPassword()).equals("")) {
					JOptionPane.showMessageDialog(null, "Ingrese su usuario y contraseña.", "Mensaje", 1);
				} else {
					int ci_ = Integer.parseInt(tusuario.getText());
					String clave_ = String.valueOf(tclave.getPassword());
					setUsuario(OpLogin.IniciarSesion(ci_, clave_));
					String resp = usuario.getNombre();
					if (resp != null) {

						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									frameLogin.dispose();
									FormMenu menu = new FormMenu(getUsuario());
									menu.getFrameMenu().setVisible(true);

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					} else {
						JOptionPane.showMessageDialog(null,
								"-Usuario y/o contraseña incorrecto(s).\n-Usuario desactivado o inhabilitado.",
								"Mensaje", 1);
					}
				}

				btningresar.setEnabled(true);
			}
		});

		btningresar.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				btningresar.setBackground(new Color(16, 105, 222));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btningresar.setBackground(new Color(26, 115, 232));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				btningresar.setBackground(new Color(11, 100, 217));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				btningresar.setBackground(new Color(26, 115, 232));
			}
		});
		frameLogin.getContentPane().add(btningresar);

		tusuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFocoL(tusuario);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFocoL(tusuario);
			}
		});

		tclave.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFocoL(tclave);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFocoL(tclave);
			}
		});

		tusuario.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btningresar.requestFocus();
					btningresar.doClick();
				}
			}

		});

		tclave.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btningresar.requestFocus();
					btningresar.doClick();
				}
			}
		});
	}
}
