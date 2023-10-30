import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class VentanaInicioSesion2 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lblNombre = new JLabel("Nombre:");
	private JLabel lblContra = new JLabel("Contraseña:");
	
	private JLabel lblTitle =new JLabel("INICIO DE SESION");
	private JLabel lblLogo = new JLabel();
	private JTextField txtNombre = new JTextField(20);
	private JPasswordField JPsswd = new JPasswordField(20);
	private JButton btn_inicio = new JButton("Inicar Sesion");
	private JButton btn_salir =  new JButton("Salir de la app");
	private JButton btn_registro = new JButton("Registrarse");
	private ImageIcon logo = new ImageIcon("Resources/Imagenes/LOGO_CERTITY.jpg");
	
	public VentanaInicioSesion2() {
		
        setTitle("Inicio de sesion");
        setSize(650,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JPanel pArriba = new JPanel();
		lblLogo.setIcon(logo);
		pArriba.add(lblLogo);
		pArriba.add(lblTitle);
		
		JPanel pNombre = new JPanel();
		pNombre.add(lblNombre);
		pNombre.add(txtNombre);
		
		JPanel pContrasenia = new JPanel();
		pContrasenia.add(lblContra);
		pContrasenia.add(JPsswd);
		
		JPanel pBotones = new JPanel();
		btn_inicio.setBackground(Color.GREEN);
		btn_salir.setBackground(Color.ORANGE);
		btn_registro.setBackground(Color.CYAN);
		pBotones.add(btn_inicio);
		pBotones.add(btn_registro);
		pBotones.add(btn_salir);
		
		JPanel pSearch = new JPanel();
		pSearch.setBorder(new TitledBorder("Búsqueda de vuelos"));
		pSearch.setLayout(new GridLayout(4, 1));
		pSearch.add(pArriba);
		pSearch.add(pNombre);
		pSearch.add(pContrasenia);
		pSearch.add(pBotones);
		
		add(pSearch);
		
	     setVisible(true);
	}

}
