import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel pCentro, pNorte, pSur;
	private JLabel lblNombre, lblContra,lblLogo, lblTitle;
	private JTextField txtNombre;
	private JPasswordField JPsswd;
	private JButton btn_inicio, btn_salir, btn_registro;
	
	
	
	
	
	
	public InicioSesion() {
		
		super();
		setBounds(150, 200, 900, 600);
		setTitle("Certity");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		pCentro = new JPanel(new GridLayout(3,3));
		getContentPane().add(pCentro,BorderLayout.CENTER);
		
		pNorte = new JPanel(new BorderLayout());
		getContentPane().add(pNorte,BorderLayout.NORTH);
		
		pSur = new JPanel(new FlowLayout());
		getContentPane().add(pSur,BorderLayout.SOUTH);
		
		
		btn_inicio = new JButton("Inicar Sesion");
		btn_salir = new JButton("Salir de la app");
		btn_registro = new JButton("Registrarse");
		
		btn_inicio.setBackground(Color.GREEN);
		btn_salir.setBackground(Color.ORANGE);
		btn_registro.setBackground(Color.CYAN);
		pSur.add(btn_inicio);
		pSur.add(btn_registro);
		pSur.add(btn_salir);
		
		
		ImageIcon logo = new ImageIcon("Resources/Imagenes/LOGO_CERTITY.jpg");
		lblLogo = new JLabel();
		lblLogo.setIcon(logo);
		pNorte.add(lblLogo, BorderLayout.WEST);
		
		lblTitle = new JLabel("INICIO DE SESION");
		lblTitle.setFont(new Font("Action Man Shaded", Font.BOLD, 19));
		pNorte.add(lblTitle, BorderLayout.CENTER);

		
		lblNombre = new JLabel("Nombre");
		pCentro.add(lblNombre);
		
		lblContra = new JLabel("Contraseña");
		pCentro.add(lblContra);
		
		txtNombre = new JTextField();
		pCentro.add(txtNombre);
		
		JPsswd = new JPasswordField();
		pCentro.add(JPsswd);
		
		
		setVisible(true);
		
		
		
		
	}

}
