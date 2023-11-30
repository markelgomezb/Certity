package Gui;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Domain.*;


public class VentanaUsuario extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Anuncio> anuncios;
	private ArrayList<Acuerdo> acuerdos;
	private JTable tablaUsuarioAnuncios, tablaVentasUsuario;
	private JScrollPane scrollAnuncios;
	private Usuario usuario;

	public VentanaUsuario(Usuario usuario, ArrayList<Anuncio> anuncios, ArrayList<Acuerdo> acuerdos) {
        super("Detalles del Usuario");
        
        this.anuncios = anuncios;
        this.usuario = usuario;
        this.acuerdos = acuerdos;
        System.out.println(this.usuario);
        System.out.println(this.anuncios);
        System.out.println(this.acuerdos);
//        initTable();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);


        JMenu menuMostrar = new JMenu("Mostrar");
        menuBar.add(menuMostrar);
        

        JMenuItem itemMostrarAnuncios = new JMenuItem("Mostrar Anuncios");
        menuMostrar.add(itemMostrarAnuncios);
        
        itemMostrarAnuncios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTablaAnuncios();
            }
        });
        
        JMenuItem itemMostrarVentas = new JMenuItem("Mostrar Ventas");
        menuMostrar.add(itemMostrarVentas);
        
        itemMostrarVentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTablaVentas();
            }
        });
        
        

        JPanel pnlNombre = new JPanel();
        JPanel pnllbltxt = new JPanel();
        pnlNombre.setLayout(new BorderLayout());
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setEditable(false);
        
        JLabel lblFotoPerfil = new JLabel();
        ImageIcon img = new ImageIcon(usuario.getFoto());
        System.out.println(usuario.getFoto());
        lblFotoPerfil.setIcon(img);
        lblFotoPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotoPerfil.setVerticalAlignment(JLabel.CENTER);
        lblFotoPerfil.setIconTextGap(0);
        
        pnlNombre.add(BorderLayout.WEST,lblFotoPerfil);
        pnllbltxt.add(lblNombre);
        pnllbltxt.add(txtNombre);
        pnlNombre.add(BorderLayout.CENTER,pnllbltxt);


        JPanel pnlDNI = new JPanel();
        JLabel lblDNI = new JLabel("DNI:");
        JTextField txtDNI = new JTextField(usuario.getDni());
        txtDNI.setEditable(false);
        pnlDNI.add(lblDNI);
        pnlDNI.add(txtDNI);

        JPanel pnlFecha = new JPanel();
        JLabel lblFecha = new JLabel("Fecha de nacimiento:");
        JTextField txtFecha = new JTextField(usuario.getFecha());
        txtFecha.setEditable(false);
        pnlFecha.add(lblFecha);
        pnlFecha.add(txtFecha);

        JPanel pnlLocalidad = new JPanel();
        JLabel lblLocalidad = new JLabel("Localidad:");
        JTextField txtLocalidad = new JTextField(usuario.getLocalidad());
        txtLocalidad.setEditable(false);
        pnlLocalidad.add(lblLocalidad);
        pnlLocalidad.add(txtLocalidad);
        
        LineBorder borde = new LineBorder(Color.CYAN);
        pnlNombre.setBorder(borde);
        pnlDNI.setBorder(borde);
        pnlFecha.setBorder(borde);
        pnlLocalidad.setBorder(borde);
        
        
        JPanel pTodo = new JPanel();
        pTodo.setLayout(new GridLayout(6,1));
        pTodo.add(pnlNombre);
        pTodo.add(pnlDNI);
        pTodo.add(pnlFecha);
        pTodo.add(pnlLocalidad);

       
        

        add(pTodo); 
        pack();
        setSize(600, 600); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
	

//	public void initTable() {
//		this.tablaUsuarioAnuncios = new JTable(new AnuncioUsuarioTableModel(this.anuncios, this.usuario));
//		
//    	TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
//    		JLabel label = new JLabel(value != null ? value.toString() : "");
//    		
//    		if(isSelected) {
//    			label.setBackground(table.getSelectionBackground());
//    			label.setForeground(table.getSelectionForeground());
//    		}
//    		
//    		if(row % 2 != 0) {
//    			label.setBackground(new Color(0,0,139));
//    			label.setForeground(Color.WHITE);
//    		}
//    		label.setOpaque(true);
//    		return label;
//    	};
//
//    	this.tablaUsuarioAnuncios.setRowHeight(15);
//    	this.tablaUsuarioAnuncios.setDefaultRenderer(Object.class, tablerenderer);
//	}
	
    private void mostrarTablaAnuncios() {
        JFrame frame = new JFrame("Anuncios de Usuario");
        ArrayList<Anuncio> anunciosUsuario = new ArrayList<>();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		for (Anuncio anuncio : this.anuncios) {
			System.out.println(this.usuario);
			System.out.println(anuncio);
			System.out.println(anuncio.getUsuario());
			if(anuncio.getUsuario().equals(this.usuario)) {
				System.out.println(anuncio.getFotos());
				anunciosUsuario.add(anuncio);
			}
		}
		System.out.println(anunciosUsuario);

        this.tablaUsuarioAnuncios = new JTable(new AnuncioUsuarioTableModel(this.anuncios, this.usuario));
        this.scrollAnuncios = new JScrollPane(this.tablaUsuarioAnuncios);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios publicados por ti"));
        
    	TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
    		JLabel label = new JLabel(value != null ? value.toString() : "");
    		
    		if(isSelected) {
    			label.setBackground(table.getSelectionBackground());
    			label.setForeground(table.getSelectionForeground());
    		}
    		
    		if(row % 2 != 0) {
    			label.setBackground(new Color(0,0,139));
    			label.setForeground(Color.WHITE);
    		}
    		label.setOpaque(true);
    		return label;
    	};

    	this.tablaUsuarioAnuncios.setRowHeight(15);
    	this.tablaUsuarioAnuncios.setDefaultRenderer(Object.class, tablerenderer);

        frame.getContentPane().add(this.scrollAnuncios, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
    
    private void mostrarTablaVentas() {
        JFrame frame = new JFrame("Ventas de Usuario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        this.tablaVentasUsuario = new JTable(new VentasUsuarioTableModel(this.usuario, this.acuerdos));
        this.scrollAnuncios = new JScrollPane(this.tablaVentasUsuario);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios publicados por ti"));
        
        TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
    		JLabel label = new JLabel(value != null ? value.toString() : "");
    		
    		if(isSelected) {
    			label.setBackground(table.getSelectionBackground());
    			label.setForeground(table.getSelectionForeground());
    		}
    		
    		if(row % 2 != 0) {
    			label.setBackground(new Color(0,0,139));
    			label.setForeground(Color.WHITE);
    		}
//    		for (Anuncio anuncio : this.anuncios) {
//				List<String> ftAnuncio  = anuncio.getFotos();
//				
//				if(ftAnuncio.size() != 0) {
//				for (String foto : ftAnuncio) {
//					ImageIcon img = new ImageIcon(foto);
//					label.setIcon(img);
//					
//				}}
//				else {
////					ImageIcon img = new ImageIcon("Resources/Imagenes/nano.png");
////					label.setIcon(img);
//					
//				}
//				
//			}
    		if(column == 3) {
    			System.out.println(label);
    		}
    		
    		if(column == 0 ) {
    			label.setHorizontalAlignment(JLabel.RIGHT);
    		}
    		if(column == 1 || column == 3) {
    			label.setHorizontalAlignment(JLabel.CENTER);
    		}
    		if(column == 2) {
    			LocalDateTime lcltime = LocalDateTime.now();
    			Date fechaAcuerdo = (Date) value;
    			LocalDateTime fechaAcuerdoLocalDateTime = fechaAcuerdo.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    			
    			System.out.println("Fecha actual: " + lcltime);
    	        System.out.println("Fecha acuerdo: " + fechaAcuerdoLocalDateTime);
    			if(lcltime.isAfter(fechaAcuerdoLocalDateTime)) {
    				label.setForeground(Color.GREEN);
    			}else if(lcltime.isBefore(fechaAcuerdoLocalDateTime)) {
    				label.setForeground(Color.BLUE);
    			}else {
    				label.setForeground(Color.CYAN);
    			}
    		}
    		label.setOpaque(true);
    		return label;
    	};

    	this.tablaVentasUsuario.setRowHeight(15);
    	this.tablaVentasUsuario.setDefaultRenderer(Object.class, tablerenderer);
        
        frame.getContentPane().add(this.scrollAnuncios, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
