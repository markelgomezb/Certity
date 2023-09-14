import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class InicioSesion {

	public static void main(String[] args) {
		
		JFrame inicioSesion = new JFrame("Inicio Sesion");
		
		inicioSesion.setSize(1280,720);
		inicioSesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		JPanel panel = new JPanel();
		inicioSesion.add(panel);
		panel.setLayout(new GridLayout(2,3));

		
		inicioSesion.setVisible(true);
		
		
	}

}
