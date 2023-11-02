import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ventanaAnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnSubirFoto;
    private JTextField txtDescripcion;
    private JTextField txtLocalidad;
    private JTextField txtPrecio;

    public ventanaAnuncio() {
        super("Crear Anuncio");

        btnSubirFoto = new JButton("Subir Foto");
        txtDescripcion = new JTextField(20);
        txtLocalidad = new JTextField(20);
        txtPrecio = new JTextField(10);

        JPanel panelAnuncio = new JPanel();
        panelAnuncio.setLayout(new GridLayout(4, 2));

        panelAnuncio.add(new JLabel("Descripción:"));
        panelAnuncio.add(txtDescripcion);
        panelAnuncio.add(new JLabel("Localidad:"));
        panelAnuncio.add(txtLocalidad);
        panelAnuncio.add(new JLabel("Precio:"));
        panelAnuncio.add(txtPrecio);
        panelAnuncio.add(btnSubirFoto);

        this.getContentPane().add(panelAnuncio);

        btnSubirFoto.addActionListener(new ActionListener() {
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
                        } else {
                            System.out.println("Ya existe un archivo con ese nombre en el directorio.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
