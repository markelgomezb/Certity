import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InicioSesion extends JFrame {
	private JPanel pCentro;
	private JLabel lblNombre, lblContra;
	private JTextField txtNombre, txtContra;
	
	
	
	
	
	
	
	public InicioSesion() {
		
		super();
		setBounds(150, 200, 900, 600);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		pCentro = new JPanel(new GridLayout(3,3));
		
		getContentPane().add(pCentro,BorderLayout.CENTER);
		
		

		
		lblNombre = new JLabel("Nombre");
		pCentro.add(lblNombre);
		
		lblContra = new JLabel("Contraseña");
		pCentro.add(lblContra);
		
		txtNombre = new JTextField();
		pCentro.add(txtNombre);
		
		txtContra = new JTextField();
		pCentro.add(txtContra);
		
		
		setVisible(true);
		
		setTitle("Certity");
		
		
	}

}
