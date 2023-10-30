import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


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
	private JLabel lblEmail = new JLabel("Email:");
	private JTextField txtDni = new JTextField(9);
	private JTextField txtFechaNac = new JTextField("Ej: 21/02/2003", 15);
	private JTextField txtNombre = new JTextField(30);
	private JTextField txtUsuario = new JTextField(20);
	private JTextField txtLocalidad = new JTextField(20);
	private JTextField txtEmail = new JTextField(20);
	private JButton btnRegistro = new JButton("Registrarse");
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnRegistroInicio = new JButton("Registrarse e iniciar sesion");
	
	public VentanaRegistro() {
        setTitle("Inicio de sesion");
        setSize(650,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel pArriba = new JPanel();
        lblLogo.setIcon(logo);
        pArriba.add(lblLogo);
        pArriba.add(lblTitle);
        
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
        
        JPanel pBotones = new JPanel();
        btnRegistro.setBackground(Color.GREEN);
        btnRegistroInicio.setBackground(Color.CYAN);
        btnCancelar.setBackground(Color.ORANGE);
        pBotones.add(btnRegistro);
        pBotones.add(btnRegistroInicio);
        pBotones.add(btnCancelar);
        
        JPanel pTodo = new JPanel();
        pTodo.setLayout(new GridLayout(8,1));
        pTodo.add(pArriba);
        pTodo.add(pUsuario);
        pTodo.add(pNombre);
        pTodo.add(pDni);
        pTodo.add(pEmail);
        pTodo.add(pFecha);
        pTodo.add(pLocalidad);
        pTodo.add(pBotones);
        
        add(pTodo);
        
        btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				VentanaInicioSesion2 vis = new VentanaInicioSesion2();
				vis.setVisible(true);
			}
		});
     

	}
}
