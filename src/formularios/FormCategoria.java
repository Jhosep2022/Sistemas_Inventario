package formularios;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import decoradores.CajasTexto;
import operaciones.OpCategoria;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import clases.Categoria;
import clases.Usuario;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormCategoria {

	private JFrame frameCategoria;
	private JTextField tcategoria;
	private JTextField tdescripcion;
	private JTextField tsearch;
	private JTable tablacategoria;
	private int idcategoria_ = 0;
	private Usuario usuario;
	private FormMenu formmenu;

	public JFrame getFrameCategoria() {
		return frameCategoria;
	}

	public void setFrameCategoria(JFrame frameCategoria) {
		this.frameCategoria = frameCategoria;
	}

	public int getIdcategoria_() {
		return idcategoria_;
	}

	public void setIdcategoria_(int idcategoria_) {
		this.idcategoria_ = idcategoria_;
	}

	public int buscarCategoriaTabla(int idcategoria) {
		if (idcategoria != 0) {
			for (int i = 0; i < tablacategoria.getRowCount(); i++) {
				if (tablacategoria.getValueAt(i, 0) != null) {
					if (Integer.parseInt((String) tablacategoria.getValueAt(i, 0)) == idcategoria) {
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
		tablacategoria
				.setModel(new DefaultTableModel(OpCategoria.ListarCategoria(val), new String[] { "ID", "CATEGORÍA" }) {

					private static final long serialVersionUID = 1L;
					@SuppressWarnings("rawtypes")
					Class[] columnTypes = new Class[] { String.class, String.class };

					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}

					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
		tablacategoria.getColumnModel().getColumn(0).setResizable(false);
		tablacategoria.getColumnModel().getColumn(0).setPreferredWidth(40);
		tablacategoria.getColumnModel().getColumn(0).setMinWidth(40);
		tablacategoria.getColumnModel().getColumn(0).setMaxWidth(40);
		tablacategoria.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

		tablacategoria.getColumnModel().getColumn(1).setResizable(false);
	}

	public void limpiarFormulario() {
		setIdcategoria_(0);
		tcategoria.setText("");
		tdescripcion.setText("");
	}

	public void categoriaSeleccionada(Categoria p) {
		setIdcategoria_(p.getIdcategoria());
		tcategoria.setText(p.getCategoria());
		tdescripcion.setText(p.getDescripcion());
	}

	public int buscarCategoriaTablaCategoria(String categoria) {
		if (categoria != null) {
			for (int i = 0; i < tablacategoria.getRowCount(); i++) {
				if (tablacategoria.getValueAt(i, 1) != null) {
					if (((String) tablacategoria.getValueAt(i, 1)).equals("  " + categoria)) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public FormCategoria(Usuario u, FormMenu fm) {
		formmenu = fm;
		usuario = u;
		initialize();
		frameCategoria.setLocationRelativeTo(null);
	}

	private void initialize() {
		frameCategoria = new JFrame();
		frameCategoria.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				formmenu.getFrameMenu().setVisible(true);
			}
		});
		frameCategoria.setTitle("MODULO CATEGORIA");
		frameCategoria.setResizable(false);
		frameCategoria.setIconImage(
				Toolkit.getDefaultToolkit().getImage(FormCategoria.class.getResource("/iconos/iconCategory.png")));
		frameCategoria.getContentPane().setBackground(Color.WHITE);
		frameCategoria.getContentPane().setLayout(null);

		JLabel labelcategorias = new JLabel("REGISTRO DE CATEGORÍAS");
		labelcategorias.setHorizontalAlignment(SwingConstants.CENTER);
		labelcategorias.setFont(new Font("Segoe UI", Font.BOLD, 24));
		labelcategorias.setBorder(new EmptyBorder(0, 0, 0, 0));
		labelcategorias.setBackground(Color.WHITE);
		labelcategorias.setBounds(0, 20, 380, 30);
		frameCategoria.getContentPane().add(labelcategorias);

		JLabel labelusuarioregistro = new JLabel("USUARIO: " + usuario.getNombre().toUpperCase() + "  CARGO: "
				+ usuario.getTipo().toUpperCase() + "     ");
		labelusuarioregistro.setHorizontalTextPosition(SwingConstants.CENTER);
		labelusuarioregistro.setHorizontalAlignment(SwingConstants.RIGHT);
		labelusuarioregistro.setForeground(Color.BLACK);
		labelusuarioregistro.setFont(new Font("Segoe UI", Font.BOLD, 10));
		labelusuarioregistro.setBackground(Color.WHITE);
		labelusuarioregistro.setBounds(0, 0, 380, 20);
		frameCategoria.getContentPane().add(labelusuarioregistro);

		JLabel lcategoria = new JLabel("CATEGORÍA *");
		lcategoria.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lcategoria.setBounds(20, 70, 140, 20);
		frameCategoria.getContentPane().add(lcategoria);

		tcategoria = new JTextField();
		tcategoria.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tcategoria);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tcategoria);
			}
		});
		tcategoria.setToolTipText("");
		tcategoria.setHorizontalAlignment(SwingConstants.LEFT);
		tcategoria.setForeground(Color.BLACK);
		CajasTexto.TxtFueraDeFoco(tcategoria);
		tcategoria.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tcategoria.setBounds(20, 90, 200, 25);
		frameCategoria.getContentPane().add(tcategoria);

		JLabel ldescripcion = new JLabel("DESCRIPCIÓN");
		ldescripcion.setFont(new Font("Segoe UI", Font.BOLD, 16));
		ldescripcion.setBounds(20, 130, 200, 20);
		frameCategoria.getContentPane().add(ldescripcion);

		JLabel labeltop = new JLabel("New label");
		labeltop.setOpaque(true);
		labeltop.setBackground(Color.BLACK);
		labeltop.setBounds(0, 0, 380, 1);
		frameCategoria.getContentPane().add(labeltop);

		JLabel lnuevo = new JLabel("NUEVO");
		lnuevo.setHorizontalTextPosition(SwingConstants.RIGHT);
		lnuevo.setHorizontalAlignment(SwingConstants.RIGHT);
		lnuevo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lnuevo.setBounds(285, 70, 75, 45);
		frameCategoria.getContentPane().add(lnuevo);

		JLabel btnactualizar = new JLabel("");
		btnactualizar.setEnabled(false);
		btnactualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
					btnactualizar.setEnabled(false);
					if (getIdcategoria_() != 0) {
						if (tcategoria.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje",
									1);
						} else {

							String categoria = tcategoria.getText().toUpperCase();
							String descripcion = tdescripcion.getText().toUpperCase();
							Categoria ca = new Categoria(getIdcategoria_(), categoria, descripcion);

							if (OpCategoria.ActualizarCategoria(ca)) {

								JOptionPane.showMessageDialog(null,
										"Se ha actualizado los datos de la categoría correctamente.",
										"Actualización exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								int r = buscarCategoriaTablaCategoria(ca.getCategoria());
								if (r != -1) {
									tablacategoria.setRowSelectionInterval(r, r);
									tablacategoria.requestFocus();
									tablacategoria.changeSelection(r, 0, false, false);

								} else {
									btnactualizar.requestFocus();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido actualizar los datos de la categoria.",
										"Error", 0);
							}

						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccion un cliente a actualizar.", "Mensaje", 1);

					}
					btnactualizar.setEnabled(true);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btnactualizar.isEnabled()) {
					btnactualizar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconUpdateFixedHover.png")));
				}
			}
		});
		btnactualizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnactualizar.setIcon(new ImageIcon(FormCategoria.class.getResource("/iconos/iconUpdateFixed.png")));
		btnactualizar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnactualizar.setHorizontalAlignment(SwingConstants.CENTER);
		btnactualizar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnactualizar.setBounds(240, 130, 45, 45);
		frameCategoria.getContentPane().add(btnactualizar);

		JLabel lactualizar = new JLabel("ACTUALIZAR");
		lactualizar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lactualizar.setHorizontalAlignment(SwingConstants.RIGHT);
		lactualizar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lactualizar.setBounds(285, 130, 75, 45);
		frameCategoria.getContentPane().add(lactualizar);

		JLabel btneliminar = new JLabel("");

		JLabel leliminar = new JLabel("ELIMINAR");
		leliminar.setHorizontalTextPosition(SwingConstants.RIGHT);
		leliminar.setHorizontalAlignment(SwingConstants.RIGHT);
		leliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		leliminar.setBounds(285, 190, 75, 45);
		frameCategoria.getContentPane().add(leliminar);

		tdescripcion = new JTextField();
		tdescripcion.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				CajasTexto.TxtFoco(tdescripcion);
			}

			@Override
			public void focusLost(FocusEvent e) {
				CajasTexto.TxtFueraDeFoco(tdescripcion);
			}
		});
		tdescripcion.setToolTipText("");
		tdescripcion.setHorizontalAlignment(SwingConstants.LEFT);
		CajasTexto.TxtFueraDeFoco(tdescripcion);
		tdescripcion.setForeground(Color.BLACK);
		tdescripcion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		tdescripcion.setBounds(20, 150, 200, 25);
		frameCategoria.getContentPane().add(tdescripcion);

		JLabel lregistrar = new JLabel("REGISTRAR      ");
		lregistrar.setHorizontalTextPosition(SwingConstants.RIGHT);
		lregistrar.setHorizontalAlignment(SwingConstants.RIGHT);
		lregistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lregistrar.setBounds(125, 190, 95, 45);
		frameCategoria.getContentPane().add(lregistrar);

		JLabel btnregistrar = new JLabel("");
		btnregistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btnregistrar.isEnabled()) {
					btnregistrar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover_.png")));

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				if (btnregistrar.isEnabled()) {

					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));
					btnregistrar.setEnabled(false);
					if (tcategoria.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Debe ingresar los campos obligatorios (*).", "Mensaje", 1);
					} else {

						String categoria = tcategoria.getText().toUpperCase();
						String descripcion = tdescripcion.getText().toUpperCase();
						Categoria ca = new Categoria(0, categoria, descripcion);
						if (OpCategoria.RegistrarCategoria(ca)) {
							JOptionPane.showMessageDialog(null, "Se ha registrado la categoría correctamente.",
									"Registro exitoso", 1);
							if (tsearch.getText() != null) {
								cargarTabla(tsearch.getText());
							} else {
								cargarTabla("");
							}
							limpiarFormulario();
							int r = buscarCategoriaTablaCategoria(ca.getCategoria());
							if (r != -1) {
								// tablacliente.setRowSelectionInterval(r, r);
								tablacategoria.requestFocus();
								tablacategoria.changeSelection(r, 0, true, true);
							} else {
								btnregistrar.requestFocus();
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Ha ocurrido un error, no se ha podido registrar al proveedor.", "Error", 0);
						}

					}
					btnregistrar.setEnabled(true);

				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				if (btnregistrar.isEnabled()) {
					btnregistrar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconSaveFixedHover.png")));

				}
			}
		});
		btnregistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnregistrar.setIcon(new ImageIcon(FormCategoria.class.getResource("/iconos/iconSaveFixed.png")));
		btnregistrar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnregistrar.setHorizontalAlignment(SwingConstants.CENTER);
		btnregistrar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnregistrar.setBounds(80, 190, 45, 45);
		frameCategoria.getContentPane().add(btnregistrar);

		btneliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixed.png")));
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover_.png")));
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));

					btneliminar.setEnabled(false);

					if (getIdcategoria_() != 0) {

						int categoria = getIdcategoria_();
						String[] options = { "Sí", "No" };
						int seleccion = JOptionPane.showOptionDialog(null, "¿Quiere eliminar esta categoría?",
								"Eliminar categoría", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
								options, null);
						if (seleccion == 0) {
							if (OpCategoria.EliminarCategoria(categoria)) {
								JOptionPane.showMessageDialog(null, "Se ha eliminado la categoría correctamente.",
										"Eliminación exitosa", 1);
								if (tsearch.getText() != null) {
									cargarTabla(tsearch.getText());
								} else {
									cargarTabla("");
								}
								limpiarFormulario();
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								btnregistrar.setEnabled(true);

							} else {
								JOptionPane.showMessageDialog(null,
										"Ha ocurrido un error, no se ha podido eliminar a la categoría.", "Error", 0);
								btneliminar.setEnabled(true);
							}

						} else {

							btneliminar.setEnabled(true);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente a actualizar.", "Mensaje", 1);
						btneliminar.setEnabled(true);
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (btneliminar.isEnabled()) {
					btneliminar
							.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconDeleteFixedHover.png")));
				}
			}
		});
		btneliminar.setIcon(new ImageIcon(FormCategoria.class.getResource("/iconos/iconDeleteFixed.png")));
		btneliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btneliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btneliminar.setHorizontalAlignment(SwingConstants.CENTER);
		btneliminar.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btneliminar.setEnabled(false);
		btneliminar.setBounds(240, 190, 45, 45);
		frameCategoria.getContentPane().add(btneliminar);

		JLabel btnnuevo = new JLabel("");
		btnnuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

			}

			@Override
			public void mouseExited(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixed.png")));

			}

			@Override
			public void mousePressed(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover_.png")));

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

				limpiarFormulario();
				btnregistrar.setEnabled(true);
				btneliminar.setEnabled(false);
				btnactualizar.setEnabled(false);
				if (tsearch.getText() != null) {
					cargarTabla(tsearch.getText());
				} else {
					cargarTabla("");
				}

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				btnnuevo.setIcon(new ImageIcon(FormCliente.class.getResource("/iconos/iconNewFixedHover.png")));

			}
		});
		btnnuevo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnnuevo.setIcon(new ImageIcon(FormCategoria.class.getResource("/iconos/iconNewFixed.png")));
		btnnuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnnuevo.setHorizontalAlignment(SwingConstants.CENTER);
		btnnuevo.setFont(new Font("Segoe UI", Font.BOLD, 10));
		btnnuevo.setBounds(240, 70, 45, 45);
		frameCategoria.getContentPane().add(btnnuevo);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 249, 380, 1);
		frameCategoria.getContentPane().add(lblNewLabel);

		tsearch = new JTextField();
		tsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String val = tsearch.getText();
				if (val != null) {
					cargarTabla(val);
					int sel = buscarCategoriaTabla(getIdcategoria_());
					if (sel != -1) {
						tablacategoria.setRowSelectionInterval(sel, sel);
						// tablacliente.requestFocus();
						tablacategoria.changeSelection(sel, 0, false, false);
						btnregistrar.setEnabled(false);
						btneliminar.setEnabled(true);
						btnactualizar.setEnabled(true);
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						if (getIdcategoria_() != 0) {
							limpiarFormulario();
						}
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
		tsearch.setBounds(160, 265, 200, 25);
		frameCategoria.getContentPane().add(tsearch);

		JLabel lsearch = new JLabel("");
		lsearch.setIcon(new ImageIcon(FormCategoria.class.getResource("/iconos/iconSearch_.png")));
		lsearch.setToolTipText("Buscar usuario");
		lsearch.setIconTextGap(10);
		lsearch.setHorizontalTextPosition(SwingConstants.CENTER);
		lsearch.setHorizontalAlignment(SwingConstants.CENTER);
		lsearch.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lsearch.setBounds(135, 265, 25, 25);
		frameCategoria.getContentPane().add(lsearch);

		JScrollPane scrollpanetabla = new JScrollPane();
		scrollpanetabla.setViewportBorder(null);
		scrollpanetabla.setOpaque(false);
		scrollpanetabla.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 10));
		scrollpanetabla.setBorder(null);
		scrollpanetabla.setBackground(Color.WHITE);
		scrollpanetabla.setBounds(20, 296, 340, 228);
		frameCategoria.getContentPane().add(scrollpanetabla);

		tablacategoria = new JTable();
		tablacategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if ((!tcategoria.getText().equals("") || !tdescripcion.getText().equals(""))
						&& getIdcategoria_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar categoría",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablacategoria.getSelectedRow();
						if (tablacategoria.getValueAt(r, 0) != null) {
							Categoria c = OpCategoria
									.BuscarCategoria(Integer.parseInt((String) tablacategoria.getValueAt(r, 0)));
							String categoria = c.getCategoria();
							if (categoria != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdcategoria_(c.getIdcategoria());
								categoriaSeleccionada(c);
							} else {
								btnregistrar.setEnabled(true);
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								limpiarFormulario();
							}
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						tablacategoria.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablacategoria.getSelectedRow();
					if (tablacategoria.getValueAt(r, 0) != null) {
						Categoria c = OpCategoria
								.BuscarCategoria(Integer.parseInt((String) tablacategoria.getValueAt(r, 0)));
						String categoria = c.getCategoria();
						if (categoria != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdcategoria_(c.getIdcategoria());
							categoriaSeleccionada(c);
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						limpiarFormulario();
					}
				}
			}
		});
		tablacategoria.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!tcategoria.getText().equals("") || !tdescripcion.getText().equals(""))
						&& getIdcategoria_() == 0) {
					String[] options = { "Sí", "No" };
					int seleccion = JOptionPane.showOptionDialog(null,
							"Se borraran los datos del formulario actual.\n¿Está de acuerdo?", "Seleccionar categoría",
							JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
					if (seleccion == 0) {
						int r = tablacategoria.getSelectedRow();
						if (tablacategoria.getValueAt(r, 0) != null) {
							Categoria c = OpCategoria
									.BuscarCategoria(Integer.parseInt((String) tablacategoria.getValueAt(r, 0)));
							String categoria = c.getCategoria();
							if (categoria != null) {
								btnregistrar.setEnabled(false);
								btneliminar.setEnabled(true);
								btnactualizar.setEnabled(true);
								setIdcategoria_(c.getIdcategoria());
								categoriaSeleccionada(c);
							} else {
								btnregistrar.setEnabled(true);
								btneliminar.setEnabled(false);
								btnactualizar.setEnabled(false);
								limpiarFormulario();
							}
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						tablacategoria.getSelectionModel().clearSelection();
					}

				} else {

					int r = tablacategoria.getSelectedRow();
					if (tablacategoria.getValueAt(r, 0) != null) {
						Categoria c = OpCategoria
								.BuscarCategoria(Integer.parseInt((String) tablacategoria.getValueAt(r, 0)));
						String categoria = c.getCategoria();
						if (categoria != null) {
							btnregistrar.setEnabled(false);
							btneliminar.setEnabled(true);
							btnactualizar.setEnabled(true);
							setIdcategoria_(c.getIdcategoria());
							categoriaSeleccionada(c);
						} else {
							btnregistrar.setEnabled(true);
							btneliminar.setEnabled(false);
							btnactualizar.setEnabled(false);
							limpiarFormulario();
						}
					} else {
						btnregistrar.setEnabled(true);
						btneliminar.setEnabled(false);
						btnactualizar.setEnabled(false);
						limpiarFormulario();
					}
				}
			}
		});
		tablacategoria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablacategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tablacategoria.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tablacategoria.setSelectionForeground(new Color(26, 115, 232));
		tablacategoria.setSelectionBackground(new Color(232, 240, 254));

		tablacategoria.setBorder(null);
		tablacategoria.setRowHeight(20);
		JTableHeader th = tablacategoria.getTableHeader();
		th.setBackground(new Color(26, 115, 232));
		th.setForeground(Color.WHITE);
		th.setPreferredSize(new Dimension(100, 25));
		th.setFont(new Font("Segoe UI", Font.BOLD, 12));
		th.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));

		cargarTabla("");

		tablacategoria.setFont(new Font("Segoe UI SemiBold", Font.PLAIN, 12));
		scrollpanetabla.setViewportView(tablacategoria);

		JLabel btnvolver = new JLabel("VOLVER AL MENÚ");
		btnvolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameCategoria.dispose();
			}
		});
		btnvolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnvolver.setHorizontalAlignment(SwingConstants.CENTER);
		btnvolver.setForeground(new Color(26, 115, 232));
		btnvolver.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnvolver.setBounds(220, 534, 140, 25);
		frameCategoria.getContentPane().add(btnvolver);
		frameCategoria.setBackground(Color.WHITE);
		frameCategoria.setBounds(100, 100, 396, 618);
		frameCategoria.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
