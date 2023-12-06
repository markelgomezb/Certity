package Gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

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
	
	private JLabel lblTitle =new JLabel("Registro de nuevo usuario");
	private JLabel lblLogo = new JLabel();
	private ImageIcon logo = new ImageIcon("Resources/Imagenes/LOGO_CERTITY.jpg");
	private JLabel lblDni = new JLabel("DNI/NIE:");
	private JLabel lblFecha = new JLabel("Fecha de nacimiento:");
	private JLabel lblNombre = new JLabel("Nombre y apellidos:");
	private JLabel lblUsuario = new JLabel("Nombre de usuario:");
	private JLabel lblLocalidad = new JLabel("Localidad:");
	private JLabel lblCon = new JLabel("Contraseña:");
	private JLabel lblEmail = new JLabel("Email:");
	private JTextField txtDni = new JTextField(9);
	private JTextField txtFechaNac = new JTextField("Ej: 21/02/2003", 15);
	private JTextField txtNombre = new JTextField(30);
	private JTextField txtUsuario = new JTextField(20);
	private JTextField txtLocalidad = new JTextField(20);
	private JTextField txtCon = new JTextField(20);
	private JTextField txtEmail = new JTextField(20);
	private JButton btnRegistro = new JButton("Registrarse");
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnRegistroInicio = new JButton("Registrarse e iniciar sesion");
	private JButton btnCargarFoto = new JButton("Cargar Foto");
	private JLabel lblFoto = new JLabel("Cargar foto:");
	private ArrayList<Anuncio> anuncios;
	private String destino1;
	private ArrayList<Acuerdo> acuerdos;
	private static final String nomfichUsuarios = "Resources/Ficheros/Usuarios.csv";



	
	public VentanaRegistro(ArrayList<Anuncio> anuncios, ArrayList<Acuerdo> acuerdos) {
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
        btnRegistroInicio.setBackground(Color.CYAN);
        btnCancelar.setBackground(Color.ORANGE);
        pBotones.add(btnRegistro);
        pBotones.add(btnRegistroInicio);
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
        
        btnCargarFoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    try {
                        String rutaDestino = "Resources/Imagenes/";

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
}
