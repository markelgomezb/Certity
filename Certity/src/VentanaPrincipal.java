import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Anuncio> anuncios;
	
	private DefaultTableModel modeloAnuncios;
	private JTable tablaAnuncios;
	private JScrollPane scrollAnuncios;
	private JLabel lblUsuario, lblBusca;
	private JTextField txtBusqueda;
	private static List<Usuario> usuarios = new ArrayList<>();
	private Usuario usuario1;

	
    public VentanaPrincipal(ArrayList<Anuncio> anuncios, Usuario u) {
    	
        super("Anuncios");
        
		
        this.anuncios = anuncios;
        
        this.initTable();
        JPanel panelNorte = new JPanel(new GridLayout(2,1));
        
        JPanel panelArriba = new JPanel();
        lblUsuario = new JLabel("¡Bienvenido " + u.toString() + "!");
        panelArriba.add(lblUsuario);
        
        JPanel panelFiltro = new JPanel();
        lblBusca = new JLabel("Busca productos/ofertas:");
        txtBusqueda = new JTextField(14);
        panelFiltro.add(lblBusca);
        panelFiltro.add(txtBusqueda);
        
        panelNorte.add(panelArriba);
        panelNorte.add(panelFiltro);
        
        this.scrollAnuncios = new JScrollPane(this.tablaAnuncios);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios"));
        
        this.setLayout(new BorderLayout());
        
        this.getContentPane().add(BorderLayout.NORTH,panelNorte);
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
				System.out.println(usuario1);
				new VentanaUsuario(usuario1);
				
				
			}
		});
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); 
        this.setSize(800, 600);
        try {
        this.loadAnuncios();
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println(e);
		}
        
        
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
        
        JPanel panelSur = new JPanel();

        panelSur.add(btnAgregar);



        

        this.getContentPane().add(BorderLayout.SOUTH, panelSur);


        
        
        
        setVisible(true);
    }
    
    public void initTable() {
    	Vector<String> cabeceraAnuncios = new Vector<String>(Arrays.asList("USUARIO", "FOTOS", "DESCRIPCION", "LOCALIDAD", "PRECIO"));
    	
    	this.modeloAnuncios = new DefaultTableModel(new Vector<Vector<Object>>(),cabeceraAnuncios);
    	
    	this.tablaAnuncios = new JTable(this.modeloAnuncios);
    	
    	TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
    		JLabel label = new JLabel(value.toString());
    		
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
    	
    	this.tablaAnuncios.setRowHeight(15);
    	this.tablaAnuncios.setDefaultRenderer(Object.class, tablerenderer);
    }
    
    public void loadAnuncios() {
    	this.modeloAnuncios.setRowCount(0);
    	anuncios.forEach(e ->{
    		this.modeloAnuncios.addRow(new Object[] {e.getUsuario(),e.getFotos(),e.getDescripcion(),e.getUsuario().getLocalidad(),e.getPrecio()});
    	});
    }
    
  
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
    
    public static void cargarUsuarioEnLista(String nomfich) {
		try {
			Scanner sc = new Scanner(new FileReader(nomfich));
			String linea;
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String [] partes = linea.split(",");
				String dni = partes[0];
				String fecha = partes[1];
				String nombre = partes[2];
				String nombre_usuario = partes[3];
				String localidad = partes[4];
				String email = partes[5];
				String foto = partes[6];
				String contrasenia = partes[7];
				
				
				Usuario c = new Usuario(dni, fecha, nombre, nombre_usuario, localidad, email, foto, contrasenia);
				if(buscarCliente(dni)==null)
					usuarios.add(c);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
		}
		
	}
    
    
    
    public static Usuario buscarCliente(String dni) {
		boolean enc = false;
		int pos = 0;
		Usuario c = null;
		while(!enc && pos<usuarios.size()) {
			c = usuarios.get(pos);
			if(c.getDni().equals(dni)) {
				enc = true;
			}else {
				pos++;
			}
		}
		if(enc) {
			return c;
		}else{
			return null;
		}
	}
}