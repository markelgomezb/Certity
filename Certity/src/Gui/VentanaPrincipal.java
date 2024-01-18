package Gui;

import javax.swing.*;


import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;

import Database.BD;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import Domain.*;
import Main.ProgramaPrincipal;

import java.net.URL;
import java.sql.Connection;

public class VentanaPrincipal extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Anuncio> anuncios;
	private JTable tablaAnuncios;
	private JScrollPane scrollAnuncios;
	private JLabel lblUsuario, lblBusca;
	private JTextField txtBusqueda;
	private static List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario1;
	private JCheckBox precioDesc, precioAsc;
	public static Anuncio anuncio1;
	private JButton btnAcuerdo;

	// El modelo es un atributo de clase para que se pueda modificar fácilmente
	private AnuncioTableModel tableModel;

	public VentanaPrincipal(ArrayList<Anuncio> anuncios, Usuario u, ArrayList<Acuerdo> acuerdos) {
		super("Anuncios");
		this.anuncios = anuncios;
		this.initTable(this.anuncios);
		JPanel panelNorte = new JPanel(new GridLayout(2, 1));
		/*
		 * for (Anuncio anuncio : anuncios) { //System.out.println(anuncio.getFotos());
		 * 
		 * }
		 */

		JPanel panelArriba = new JPanel();
		lblUsuario = new JLabel("¡Bienvenido " + u.getNombre() + "!");
		panelArriba.add(lblUsuario);

		JPanel panelFiltro = new JPanel();
		lblBusca = new JLabel("Busca ofertas:");
		txtBusqueda = new JTextField(14);
		precioDesc = new JCheckBox("Ordenar precio descendiente");
		precioAsc = new JCheckBox("Ordenar precio ascendiente");
		panelFiltro.add(lblBusca);
		panelFiltro.add(txtBusqueda);
		panelFiltro.add(precioDesc);
		panelFiltro.add(precioAsc);

		panelNorte.add(panelArriba);
		panelNorte.add(panelFiltro);

		this.scrollAnuncios = new JScrollPane(this.tablaAnuncios);
		scrollAnuncios.setBorder(new TitledBorder("Anuncios"));

		this.setLayout(new BorderLayout());

		this.getContentPane().add(BorderLayout.NORTH, panelNorte);
		this.getContentPane().add(BorderLayout.CENTER, this.scrollAnuncios);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuUsuario = new JMenu("Usuario");
		menuBar.add(menuUsuario);

		JMenuItem itemUsuario = new JMenuItem("Usuario");
		menuUsuario.add(itemUsuario);

		itemUsuario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usuario1 = guardarUsuarioPorDNI(VentanaInicioSesion2.dniUsuario);
				// System.out.println(usuario1);
				new VentanaUsuario(usuario1, anuncios, acuerdos);
				dispose();

			}
		});

		
		// boton recursividad, para calcular número máximo de servicios

		JButton btnCalcularServicios = new JButton("Calcular servicios por presupuesto");

		btnCalcularServicios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// aqui se hace (PP.con) aprovechando que la conexion en el main era public
				// static)
				List<Anuncio> anunciosActualizados = BD.obtenerAnuncios(ProgramaPrincipal.con);
				// se muestra cuadro de dialogo
				String presupuestoStr = JOptionPane.showInputDialog(VentanaPrincipal.this, "Ingresa tu presupuesto:");
				try {

					float presupuesto = Float.parseFloat(presupuestoStr);

					int maxServicios = ServiciosRecursividad.maximoServiciosConPresupuesto(anunciosActualizados,
							presupuesto);
					JOptionPane.showMessageDialog(VentanaPrincipal.this,
							"Puedes contratar hasta " + maxServicios + " servicios con ese presupuesto.");

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(VentanaPrincipal.this, "Inegresa un número válido.");
				}
			}
		});

		// boton

		JButton btnAgregar = new JButton("Agregar");

		btnAgregar.setBackground(Color.ORANGE);

		btnAgregar.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent e) {

				ventanaAnuncio ventanaAnuncio = new ventanaAnuncio();

				ventanaAnuncio.setVisible(true);

			}

		});
		btnAcuerdo = new JButton("Acuerdo");
		
		btnAcuerdo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new ventanaAcuerdo(anuncios, u, acuerdos);
			}
		});

		JPanel panelSur = new JPanel();

		panelSur.add(btnAgregar);
		panelSur.add(btnAcuerdo);

		panelSur.add(btnCalcularServicios);

		this.getContentPane().add(BorderLayout.SOUTH, panelSur);

		tablaAnuncios.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint();
				int fila = tablaAnuncios.rowAtPoint(p);
				((AnuncioTableModel) tablaAnuncios.getModel()).getAnuncio(fila).siguienteFoto();
				tablaAnuncios.repaint();
			}
		});

		precioDesc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (precioDesc.isSelected()) {
					// SE DESHABILITA EL OTRO CRITERIO DE ORDENACIÓN
					precioAsc.setSelected(false);
					
					Comparator<Anuncio> comparator = (a1, a2) -> {
						return Float.compare(a1.getPrecio(), a2.getPrecio());
					};

					// SE ORDENA LA LISTA DE ANUNCIOS
					Collections.sort(anuncios, comparator.reversed());
					
					// SE APLICA EL FILTRO (POR SI HUBIESE ALGUN VALOR)
					filtrarComics();
				}
			}

		});

		precioAsc.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (precioAsc.isSelected()) {
					// SE DESHABILITA EL OTRO CRITERIO DE ORDENACIÓN
					precioDesc.setSelected(false);
										
					Comparator<Anuncio> comparator = (a1, a2) -> {
						return Float.compare(a1.getPrecio(), a2.getPrecio());
					};
					
					Collections.sort(anuncios, comparator);
					
					// SE APLICA EL FILTRO (POR SI HUBIESE ALGUN VALOR)
					filtrarComics();
				}
			}
		});

		// EL FILTRO AHORA ESTÁ BIEN
		this.txtBusqueda.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarComics();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarComics();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				filtrarComics();
			}
		});

		tablaAnuncios.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if ((e.getKeyCode() == KeyEvent.VK_E) && ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0)) {
					int selectedRow = tablaAnuncios.getSelectedRow();
					if (selectedRow != -1) {
						Anuncio anuncioSeleccionado = ((AnuncioTableModel) tablaAnuncios.getModel())
								.getAnuncio(selectedRow);
						anuncio1 = anuncioSeleccionado;
					}
				}
			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		setLocationRelativeTo(null);
		this.setSize(800, 600);
		setVisible(true);
	}

	public void initTable(ArrayList<Anuncio> anuncios) {
//    	Vector<String> cabeceraAnuncios = new Vector<String>(Arrays.asList("USUARIO", "FOTOS", "DESCRIPCION", "LOCALIDAD", "PRECIO"));
//    	
//    	this.modeloAnuncios = new DefaultTableModel(new Vector<Vector<Object>>(),cabeceraAnuncios);
//    	
//    	this.tablaAnuncios = new JTable(this.modeloAnuncios);
//    	

		this.tableModel = new AnuncioTableModel(anuncios);
		this.tablaAnuncios = new JTable(tableModel);

		TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel label = new JLabel(value.toString());
			label.setSize(150, 150);
			table.setRowHeight(row, 150);
			if (isSelected) {
				label.setBackground(table.getSelectionBackground());
				label.setForeground(table.getSelectionForeground());
			}

			if (row % 2 != 0) {
				label.setBackground(new Color(0, 0, 139));
				label.setForeground(Color.WHITE);
			}

			if (column == 5) {
				// for (Anuncio anuncio : this.anuncios) {
				// List<String> ftAnuncio = anuncio.getFotos();

				// if(ftAnuncio.size() != 0) {
				// for (String foto : ftAnuncio) {
//    			System.out.println(value.toString());
				ImageIcon img = new ImageIcon(value.toString());
				ImageIcon imdimensiones = new ImageIcon(
						img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
				label.setIcon(imdimensiones);

				// }}
				// else {
//    					ImageIcon img = new ImageIcon("Resources/Imagenes/nano.png");
//    					label.setIcon(img);

				// }

				// }

			}
			label.setOpaque(true);
			return label;
		};

		this.tablaAnuncios.setRowHeight(15);
		this.tablaAnuncios.setDefaultRenderer(Object.class, tablerenderer);
	}

//    public void loadAnuncios() {
//    	this.modeloAnuncios.setRowCount(0);
//    	anuncios.forEach(e ->{
//    		this.modeloAnuncios.addRow(new Object[] {e.getUsuario(),e.getFotos(),e.getDescripcion(),e.getUsuario().getLocalidad(),e.getPrecio()});
//    	});
//    }

	public Usuario guardarUsuarioPorDNI(String dni) {
		Usuario usuarioEncontrado = null;
		for (Usuario usuario : usuarios) {
			if (usuario.getDni().equals(dni)) {
				usuarioEncontrado = usuario;
				break;
			}
		}
		return usuarioEncontrado;
	}

	public static void aniadirUsuario(Usuario c) {
		usuarios.add(c);
	}

	public static List<Usuario> cargarUsuarioEnLista(String nomfich) {
		try {
			Scanner sc = new Scanner(new FileReader(nomfich));
			String linea;
			while (sc.hasNext()) {
				linea = sc.nextLine();
				String[] partes = linea.split(";");
				String dni = partes[0];
				String fecha = partes[1];
				String nombre = partes[2];
				String nombre_usuario = partes[3];
				String localidad = partes[4];
				String email = partes[5];
				String foto = partes[6];
				String contrasenia = partes[7];

				Usuario c = new Usuario(dni, fecha, nombre, localidad, nombre_usuario, email, foto, contrasenia);
				if (buscarUsuario1(dni)==null)
			
					usuarios.add(c);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {

		}
		return usuarios;

	}

	public static void guardarUsuariosEnFichero(String nomfich) {
		try {
			PrintWriter pw = new PrintWriter(nomfich);
			for (Usuario c : usuarios) {
				pw.println(c.getDni() + ";" + c.getFecha() + ";" + c.getNombre() + ";" + c.getNombre_usuario() + ";"
						+ c.getLocalidad() + ";" + c.getEmail() + ";" + c.getFoto() + ";" + c.getContrasenia());
			}
			pw.flush();
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Usuario buscarUsuario(String dni, List<Usuario> usuarios ) {
		boolean enc = false;
		int pos = 0;
		Usuario c = null;
		while (!enc && pos < usuarios.size()) {
			c = usuarios.get(pos);
			if (c.getDni().equals(dni)) {
				enc = true;
			} else {
				pos++;
			}
		}
		if (enc) {
			return c;
		} else {
			return null;
		}
	}
	
	public static Usuario buscarUsuario1(String dni ) {
		boolean enc = false;
		int pos = 0;
		Usuario c = null;
		while (!enc && pos < usuarios.size()) {
			c = usuarios.get(pos);
			if (c.getDni().equals(dni)) {
				enc = true;
			} else {
				pos++;
			}
		}
		if (enc) {
			return c;
		} else {
			return null;
		}
	}
	
	public static Anuncio buscarAnuncio(int id, List<Anuncio> anuncios ) {
		boolean enc = false;
		int pos = 0;
		Anuncio c = null;
		while (!enc && pos < anuncios.size()) {
			c = anuncios.get(pos);
			if (c.getId()==id) {
				enc = true;
			} else {
				pos++;
			}
		}
		if (enc) {
			return c;
		} else {
			return null;
		}
	}

	private void filtrarComics() {
		ArrayList<Anuncio> ann = new ArrayList<Anuncio>();

		// SE FILTRAN LOS ANUNCIOS Y SE ACTUALIZA EL MODELO
		this.anuncios.forEach(c -> {
			if (c.getDescripcion().contains(this.txtBusqueda.getText())) {
				ann.add(c);
			}
		});

		// SE MODIFICAN LOS ANUNCIOS DEL TABLEMODEL
		this.tableModel.setAnnouncements(ann);
		tablaAnuncios.repaint();
	}
}