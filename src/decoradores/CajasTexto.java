package decoradores;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import clases.Categoria;
import clases.Cliente;
import clases.Producto;
import clases.Proveedor;
import clases.TipoUsuario;

public class CajasTexto {

	public static void soloNumeros(JTextField field) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
			Pattern regEx = Pattern.compile("\\d*");

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				Matcher matcher = regEx.matcher(text);
				if (!matcher.matches()) {
					return;
				}
				super.replace(fb, offset, length, text, attrs);
			}
		});
	}

	public static void TxtFueraDeFoco(JTextField txt) {
		txt.setBorder(new LineBorder(Color.GRAY, 1, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void TxtFoco(JTextField txt) {
		txt.setBorder(new LineBorder(new Color(26, 115, 232), 2, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void TxtFueraDeFocoA(JTextArea txt) {
		txt.setBorder(new LineBorder(Color.GRAY, 1, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
	}

	public static void TxtFocoA(JTextArea txt) {
		txt.setBorder(new LineBorder(new Color(26, 115, 232), 2, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(4, 4, 4, 4)));
	}

	public static void TxtFueraDeFocoL(JTextField txt) {
		txt.setBorder(new LineBorder(Color.GRAY, 1, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(5, 5, 7, 5)));
	}

	public static void TxtFocoL(JTextField txt) {
		txt.setBorder(new LineBorder(new Color(26, 115, 232), 2, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(5, 5, 7, 5)));
	}

	public static void ComboFoco(JComboBox<TipoUsuario> jc) {
		jc.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboFueraDeFoco(JComboBox<TipoUsuario> jc) {
		jc.setBorder(new LineBorder(Color.GRAY, 0, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboFoco_(JComboBox<Categoria> jc) {
		jc.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboFueraDeFoco_(JComboBox<Categoria> jc) {
		jc.setBorder(new LineBorder(Color.GRAY, 0, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}
	
	public static void ComboFoco__(JComboBox<Proveedor> jc) {
		jc.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboFueraDeFoco__(JComboBox<Proveedor> jc) {
		jc.setBorder(new LineBorder(Color.GRAY, 0, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}
	
	public static void ComboFoco__c(JComboBox<Cliente> jc) {
		jc.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboFueraDeFoco__c(JComboBox<Cliente> jc) {
		jc.setBorder(new LineBorder(Color.GRAY, 0, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}
	
	public static void ComboProductoFoco(JComboBox<Producto> jc) {
		jc.setBorder(new LineBorder(new Color(26, 115, 232), 1, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void ComboProductoFueraDeFoco(JComboBox<Producto> jc) {
		jc.setBorder(new LineBorder(Color.GRAY, 0, false));
		// jc.setBorder(BorderFactory.createCompoundBorder(jc.getBorder(),
		// BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}
	
	public static void fuerafoco(JFormattedTextField txt) {
		txt.setBorder(new LineBorder(Color.GRAY, 1, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}

	public static void foco(JFormattedTextField txt) {
		txt.setBorder(new LineBorder(new Color(26, 115, 232), 2, false));
		txt.setBorder(BorderFactory.createCompoundBorder(txt.getBorder(), BorderFactory.createEmptyBorder(0, 4, 0, 4)));
	}
}
