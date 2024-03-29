package Gui;
import java.awt.Color;



import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Domain.Acuerdo;
import Domain.Anuncio;
import Domain.Usuario;
import io.UsuarioLogger;



public class VentanaRegistro extends JFrame{
	

	private static final long serialVersionUID = 1L;
	
	private static String LOGO;
	private static String IMAGENES;
	
	private JLabel lblTitle =new JLabel("Registro de nuevo usuario");
	private JLabel lblLogo = new JLabel();
	private ImageIcon logo = new ImageIcon(LOGO);
	private JLabel lblDni = new JLabel("DNI/NIE:");
	private JLabel lblFecha = new JLabel("Fecha de nacimiento:");
	private JLabel lblNombre = new JLabel("Nombre y apellidos:");
	private JLabel lblUsuario = new JLabel("Nombre de usuario:");
	private JLabel lblLocalidad = new JLabel("Localidad:");
	private JLabel lblCon = new JLabel("Contraseña:");
	private JLabel lblEmail = new JLabel("Email:");
	private JTextField txtDni = new JTextField(9);
	private JTextField txtFechaNac = new JTextField("Ej: 21-02-2003", 15);
	private JTextField txtNombre = new JTextField(30);
	private JTextField txtUsuario = new JTextField(20);
	private JTextField txtLocalidad = new JTextField(20);
	private JTextField txtCon = new JTextField(20);
	private JTextField txtEmail = new JTextField(20);
	private JButton btnRegistro = new JButton("Registrarse");
	private JButton btnCancelar = new JButton("Cancelar");

	private JButton btnCargarFoto = new JButton("Cargar Foto");
	private JLabel lblFoto = new JLabel("Cargar foto:");
	private ArrayList<Anuncio> anuncios;
	private String destino1;
	private ArrayList<Acuerdo> acuerdos;
	private static final String nomfichUsuarios = "Resources/Ficheros/Usuarios.csv";



	
	public VentanaRegistro(ArrayList<Anuncio> anuncios, ArrayList<Acuerdo> acuerdos) {
		
		try {
			//Se crea el Properties y se actualizan los 3 parámetros
			Properties connectionProperties = new Properties();
			connectionProperties.load(new FileReader("conf/parametros.properties"));
			
			LOGO = connectionProperties.getProperty("LOGO");
			IMAGENES = connectionProperties.getProperty("IMAGENES");
			
		} catch (Exception ex) {
			System.err.format("\n* error", ex.getMessage());
			ex.printStackTrace();
		}
		
		this.anuncios = anuncios;
		this.acuerdos = acuerdos;
        setTitle("Inicio de sesion");
        setSize(650,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pArriba = new JPanel();
        lblLogo.setIcon(logo);
        pArriba.add(lblLogo);
        pArriba.add(lblTitle);
        
        JPanel pFoto = new JPanel();
        pFoto.add(lblFoto);
        pFoto.add(btnCargarFoto);
        
        JPanel pDni = new JPanel();
        pDni.add(lblDni);
        pDni.add(txtDni);
        
        JPanel pFecha = new JPanel();
        pFecha.add(lblFecha);
        pFecha.add(txtFechaNac);
        
        JPanel pEmail = new JPanel();
        pEmail.add(lblEmail);
        pEmail.add(txtEmail);
        
        JPanel pNombre = new JPanel();
        pNombre.add(lblNombre);
        pNombre.add(txtNombre);
        
        JPanel pUsuario = new JPanel();
        pUsuario.add(lblUsuario);
        pUsuario.add(txtUsuario);
        
        JPanel pLocalidad = new JPanel();
        pLocalidad.add(lblLocalidad);
        pLocalidad.add(txtLocalidad);
        
        JPanel pCon = new JPanel();
        pCon.add(lblCon);
        pCon.add(txtCon);
        
        JPanel pBotones = new JPanel();
        btnRegistro.setBackground(Color.GREEN);
        
        btnCancelar.setBackground(Color.ORANGE);
        pBotones.add(btnRegistro);
        
        pBotones.add(btnCancelar);
        
        JPanel pTodo = new JPanel();
        pTodo.setLayout(new GridLayout(10,1));
        pTodo.add(pArriba);
        pTodo.add(pUsuario);
        pTodo.add(pNombre);
        pTodo.add(pDni);
        pTodo.add(pEmail);
        pTodo.add(pFecha);
        pTodo.add(pLocalidad);
        pTodo.add(pFoto);
        pTodo.add(pCon);
        pTodo.add(pBotones);

        
        add(pTodo);
        
        btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
				VentanaInicioSesion2 v = new VentanaInicioSesion2(anuncios, acuerdos);
				v.setVisible(true);
				
			}
		});
        
        btnCargarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        String rutaDestino = IMAGENES;

                        File destino = new File(rutaDestino + selectedFile.getName());

                        if (!destino.exists()) {
                            Files.copy(selectedFile.toPath(), destino.toPath());
                            System.out.println("Foto guardada en: " + destino.getAbsolutePath());
                            destino1 = destino.getPath();
                            
                        } else {
                            System.out.println("Ya existe un archivo con ese nombre en el directorio.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        
        btnRegistro.addActionListener((e)->{
			String dni = txtDni.getText();
			String nom = txtNombre.getText();
			String fNac = txtFechaNac.getText();
			String con = txtCon.getText();
			String loc = txtLocalidad.getText();
			String email = txtEmail.getText();
			String usuario = txtUsuario.getText();
			
			Usuario c = new Usuario(dni, fNac, nom, loc, usuario, email, destino1, con );
			
//				VentanaPrincipal.aniadirUsuario(c);

				UsuarioLogger.creacionUsuario(c);
				JOptionPane.showMessageDialog(null, "Cliente registrado con éxito","REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
				VentanaPrincipal.aniadirUsuario(c);
				VentanaPrincipal.guardarUsuariosEnFichero(nomfichUsuarios);
			
			dispose();
			VentanaInicioSesion2 vis = new VentanaInicioSesion2(this.anuncios, this.acuerdos);
			vis.setVisible(true);
		});
        
  
     

	}
	//metodos
		public boolean validarContrasenia(String contrasenia) throws IllegalArgumentException {
			
			if (!contrasenia.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
				throw new IllegalArgumentException("el formato del correo no es válido.");
			}
			return true;
			
		}
		
		public boolean validarEmail(String email) throws IllegalArgumentException, IllegalAccessException {
			
			if (!email.matches("[a-zA-Z0-9._%+-]+@gmail.com")) {
				throw new IllegalAccessException("La contraseña tiene que tener al menos 8 caracteres, uan letra mayúscula, una minúscula y un número.");
			}
			return true;
		}
		
		public boolean registrarUsuario(String email, String contrasenia) {
			try {
				validarEmail(email);
				validarContrasenia(contrasenia);
				
				Usuario c = new Usuario(
			            txtDni.getText(),
			            txtFechaNac.getText(),
			            txtNombre.getText(),
			            txtLocalidad.getText(),
			            txtUsuario.getText(),
			            email,
			            destino1,
			            contrasenia
			        );
				
				VentanaPrincipal.aniadirUsuario(c);
		        VentanaPrincipal.guardarUsuariosEnFichero(nomfichUsuarios);
		        
		        dispose();
		        VentanaInicioSesion2 vis = new VentanaInicioSesion2(this.anuncios, this.acuerdos);
		        vis.setVisible(true);

				
				return true;
			} catch (IllegalAccessException e) {
				System.out.println("Error: " + e.getMessage());
				return false;
			}
		}
	
}
