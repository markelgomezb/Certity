package Gui;
import javax.swing.*;
import java.awt.*;
import Domain.*;
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
        
        JLabel lblFotoPerfil = new JLabel();
        ImageIcon img = new ImageIcon("Resources/Imagenes/"+usuario.getFoto());
        lblFotoPerfil.setIcon(img);
        lblFotoPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotoPerfil.setVerticalAlignment(JLabel.CENTER);
        lblFotoPerfil.setIconTextGap(0);
       
        panel.add(lblFotoPerfil); 
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblDNI);
        panel.add(txtDNI);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblLocalidad);
        panel.add(txtLocalidad);

        add(panel); 
        setSize(600, 600); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
}
