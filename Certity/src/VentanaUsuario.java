import javax.swing.*;
import java.awt.*;

public class VentanaUsuario extends JFrame {

    public VentanaUsuario(Usuario usuario) {
        super("Detalles del Usuario");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setEditable(false); 

        JLabel lblDNI = new JLabel("DNI:");
        JTextField txtDNI = new JTextField(usuario.getDni());
        txtDNI.setEditable(false);

        JLabel lblFecha = new JLabel("Fecha de nacimiento:");
        JTextField txtFecha = new JTextField(usuario.getFecha());
        txtFecha.setEditable(false);

        JLabel lblLocalidad = new JLabel("Localidad:");
        JTextField txtLocalidad = new JTextField(usuario.getLocalidad());
        txtLocalidad.setEditable(false);

        
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblDNI);
        panel.add(txtDNI);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblLocalidad);
        panel.add(txtLocalidad);

        add(panel); 
        setSize(400, 300); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
