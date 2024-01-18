package Gui;
import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Database.BD;
import Domain.Anuncio;
import Domain.Usuario;
import Main.ProgramaPrincipal;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class ventanaAnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private static String IMAGENES;
    
    private JButton btnSubirFoto;
    private JTextField txtDescripcion;
    private JTextField txtLocalidad;
    private JTextField txtPrecio;
    private JTextField txtNombre;

    private JButton btnTodo;
    private String destino1;
    private List<Integer> ids;
    private ArrayList<String> lista;
    int numeroAleatorio = 0;
    Random rand = new Random();
    boolean a = false;
    private Usuario u;

    

    public ventanaAnuncio() {
        super("Crear Anuncio");
        
		try {
			//Se crea el Properties y se actualizan los 3 parámetros
			Properties connectionProperties = new Properties();
			connectionProperties.load(new FileReader("conf/parametros.properties"));
			

			IMAGENES = connectionProperties.getProperty("IMAGENES");
			
		} catch (Exception ex) {
			System.err.format("\n* error", ex.getMessage());
			ex.printStackTrace();
		}

        btnSubirFoto = new JButton("Subir Foto");
        btnTodo = new JButton("Guardar cambios");
        txtDescripcion = new JTextField(20);
        txtLocalidad = new JTextField(20);
        txtPrecio = new JTextField(10);
        txtNombre = new JTextField(20);
        lista = new ArrayList<String>();

        JPanel panelAnuncio = new JPanel();
        JPanel panelAnuncio1 = new JPanel();
        panelAnuncio.setLayout(new GridLayout(5, 2));
        panelAnuncio1.setLayout(new GridLayout(1, 1));
        
        panelAnuncio.add(new JLabel("Nombre:"));
        panelAnuncio.add(txtNombre);
        panelAnuncio.add(new JLabel("Descripción:"));
        panelAnuncio.add(txtDescripcion);
        panelAnuncio.add(new JLabel("Localidad:"));
        panelAnuncio.add(txtLocalidad);
        panelAnuncio.add(new JLabel("Precio:"));
        panelAnuncio.add(txtPrecio);
        panelAnuncio.add(new JLabel("Foto:"));
        panelAnuncio.add(btnSubirFoto);
        //panelAnuncio.add(new JLabel("Guardar Anuncio:"));
        panelAnuncio1.add(btnTodo);

        this.getContentPane().add(BorderLayout.CENTER,panelAnuncio);
        this.getContentPane().add(BorderLayout.SOUTH,panelAnuncio1);
        
       
        

        btnSubirFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        String rutaDestino = IMAGENES;
                        File destino = new File(rutaDestino + selectedFile.getName());
                        destino1= destino.toString();
                        if (!destino.exists()) {
                            Files.copy(selectedFile.toPath(), destino.toPath());
                            System.out.println("Foto guardada en: " + destino.getAbsolutePath());
                        } else {
                            System.out.println("Ya existe un archivo con ese nombre en el directorio.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        btnTodo.addActionListener((e)->{
        	String nom= txtNombre.getText();
        	String desc =  txtDescripcion.getText();
        	String loc = txtLocalidad.getText();
        	String precio1= txtPrecio.getText();
        	float precio = Float.parseFloat(precio1);
        	ids= BD.obteneridAnuncios(ProgramaPrincipal.con);
        	Boolean a = false;
        	while(a == false) {
        	     int numeroAleatorio = rand.nextInt(100);
        	     
        	     ids= BD.obteneridAnuncios(ProgramaPrincipal.con);
        	     //aqui
        	     if (!ids.contains(numeroAleatorio)) {
        	            a = true;
        	        }
        	     
        		
        	}
        	
        	
        	
        	u = VentanaPrincipal.buscarUsuario(VentanaInicioSesion2.dniUsuario);
        	
        	lista.add(destino1);
        	
        	
        	Anuncio c = new Anuncio(numeroAleatorio,nom,u, desc, precio,lista);
        	if (ProgramaPrincipal.con != null) {
                BD.insertarAnuncio(ProgramaPrincipal.con, c);
                this.dispose();
            } else {
                System.out.println("La conexión a la base de datos es nula.");
            }
        	
        	
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
