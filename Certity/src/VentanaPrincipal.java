import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal(ArrayList<Anuncio> anuncios) {
        super("Anuncios");
        
        

        // Crear el modelo de la tabla
        AnunciosTableModel tableModel = new AnunciosTableModel(anuncios);

        // Crear la tabla y asignar el modelo
        JTable table = new JTable(tableModel);

        // Asignar el renderizador personalizado a las columnas que desees
        table.getColumnModel().getColumn(0).setCellRenderer(new AnuncioRederer());
        table.getColumnModel().getColumn(3).setCellRenderer(new AnuncioRederer());
        table.getColumnModel().getColumn(4).setCellRenderer(new AnuncioRederer());

        // Crear un JScrollPane y agregar la tabla a él
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar el JScrollPane a la ventana
        getContentPane().add(scrollPane);

        // Configurar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }
}