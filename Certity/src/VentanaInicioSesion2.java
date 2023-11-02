import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaInicioSesion2 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel lbldni = new JLabel("Dni:");
	private JLabel lblContra = new JLabel("Contraseña:");
	
	private JLabel lblTitle =new JLabel("INICIO DE SESION");
	private JLabel lblLogo = new JLabel();
	private JTextField txtdni = new JTextField(20);
	private JPasswordField JPsswd = new JPasswordField(20);
	private JButton btn_inicio = new JButton("Inicar Sesion");
	private JButton btn_salir =  new JButton("Salir de la app");
	private JLabel lblregistro = new JLabel("<html><a href =''>Registrarse</a></html>");
	private ImageIcon logo = new ImageIcon("Resources/Imagenes/LOGO_CERTITY.jpg");
	static String dniUsuario = " ";

	
	public VentanaInicioSesion2() {
        setTitle("Inicio de sesion");
        setSize(650,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		JPanel pArriba = new JPanel();
		lblLogo.setIcon(logo);
		pArriba.add(lblLogo);
		pArriba.add(lblTitle);
		
		JPanel pdni = new JPanel();
		pdni.add(lbldni);
		pdni.add(txtdni);
		
		JPanel pContrasenia = new JPanel();
		pContrasenia.add(lblContra);
		pContrasenia.add(JPsswd);
		
		JPanel pBotones = new JPanel();
		btn_inicio.setBackground(Color.GREEN);
		btn_salir.setBackground(Color.ORANGE);
		pBotones.add(btn_inicio);
		pBotones.add(btn_salir);
		pBotones.add(lblregistro);
		
		JPanel pTodo = new JPanel();
		pTodo.setLayout(new GridLayout(4, 1));
		pTodo.add(pArriba);
		pTodo.add(pdni);
		pTodo.add(pContrasenia);
		pTodo.add(pBotones);
		
		add(pTodo);
		
		lblregistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				dispose();
				VentanaRegistro vr = new VentanaRegistro();
				vr.setVisible(true);
			}
		});
		
		btn_salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

		
		btn_inicio.addActionListener((e)->{
			String dni = txtdni.getText();
			char[] psswd = JPsswd.getPassword();
			String psswdString = new String(psswd);
			Usuario c = VentanaPrincipal.buscarCliente(dni);
			if(c == null) {
				JOptionPane.showMessageDialog(null, "Para poder iniciar sesión tienes que estar registrado","ERROR",JOptionPane.ERROR_MESSAGE);
			}else {
				if(c.getContrasenia().equals(psswdString)) {
					JOptionPane.showMessageDialog(null, "Bienvenido!","SESIÓN INICIADA",JOptionPane.INFORMATION_MESSAGE);
					dniUsuario= dni;
					new VentanaPrincipal(null, c);
				}else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta","ERROR",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
	     setVisible(true);
	}

}
