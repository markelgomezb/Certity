import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class VentanaInicioSesion extends JFrame {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	    private JLabel labelUsername;
	    private JLabel labelPassword;
	    private JTextField textUsername;
	    private JPasswordField textPassword;
	    private JButton buttonLogin;
	    private JButton buttonCancel;
	    private JLabel linkLabel;
	    
	    public VentanaInicioSesion() {
	        // Configurar la ventana
	        setTitle("Inicio de sesi�n");
	        setSize(400, 300);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        
	        panel = new JPanel();
	        panel.setLayout(null);


	        labelUsername = new JLabel("Nombre de usuario:");
	        labelUsername.setBounds(30, 50, 150, 25);
	        labelPassword = new JLabel("Contraseña:");
	        labelPassword.setBounds(30, 100, 150, 25);
	        linkLabel = new JLabel("<html><a href=''>Crear nuevo usuario</a></html>");
	        linkLabel.setBounds(30, 200, 350, 25);
	        
	     
	        textUsername = new JTextField();
	        textUsername.setBounds(200, 50, 150, 25);
	        textPassword = new JPasswordField();
	        textPassword.setBounds(200, 100, 150, 25);

	
	     buttonLogin = new JButton("Iniciar sesion");
	     buttonLogin.setBounds(100, 150, 120, 30);
	     buttonCancel = new JButton("Cancelar");
	     buttonCancel.setBounds(240, 150, 120, 30);
	    


	     
	    
	     panel.add(labelUsername);
	     panel.add(labelPassword);
	     panel.add(textUsername);
	     panel.add(textPassword);
	     panel.add(linkLabel);
	     panel.add(buttonLogin);
	     panel.add(buttonCancel);


	     add(panel);


	     setVisible(true);
	     }

	     public static void main(String[] args) {
	         SwingUtilities.invokeLater(new Runnable() {
	             public void run() {
	                 new VentanaInicioSesion();
	             }
	         });
	     }
	     
}

