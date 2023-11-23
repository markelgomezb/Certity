package Gui;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.util.ArrayList;

import Domain.*;
public class VentanaUsuario extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Anuncio> anuncios;
	private JTable tablaAnuncios;
	private JScrollPane scrollAnuncios;
	private Usuario usuario;

	public VentanaUsuario(Usuario usuario, ArrayList<Anuncio> anuncios) {
        super("Detalles del Usuario");
        
        this.anuncios = anuncios;
        this.usuario = usuario;
        
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
        ImageIcon img = new ImageIcon(usuario.getFoto());
        System.out.println(usuario.getFoto());
        lblFotoPerfil.setIcon(img);
        lblFotoPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotoPerfil.setVerticalAlignment(JLabel.CENTER);
        lblFotoPerfil.setIconTextGap(0);
        
        this.scrollAnuncios = new JScrollPane(this.tablaAnuncios);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios publicados por ti"));
       
        panel.add(lblFotoPerfil); 
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblDNI);
        panel.add(txtDNI);
        panel.add(lblFecha);
        panel.add(txtFecha);
        panel.add(lblLocalidad);
        panel.add(txtLocalidad);
        panel.add(scrollAnuncios);
        

        add(panel); 
        setSize(600, 600); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
	

	public void initTable() {
		this.tablaAnuncios = new JTable(new AnuncioUsuarioTableModel(anuncios, usuario));
		
    	TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
    		JLabel label = new JLabel(value.toString());
    		
    		if(isSelected) {
    			label.setBackground(table.getSelectionBackground());
    			label.setForeground(table.getSelectionForeground());
    		}
    		
    		if(row % 2 != 0) {
    			label.setBackground(new Color(0,0,139));
    			label.setForeground(Color.WHITE);
    		}
    		label.setOpaque(true);
    		return label;
    	};

    	this.tablaAnuncios.setRowHeight(15);
    	this.tablaAnuncios.setDefaultRenderer(Object.class, tablerenderer);
	}
}
