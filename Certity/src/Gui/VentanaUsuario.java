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
	private JTable tablaUsuarioAnuncios;
	private JScrollPane scrollAnuncios;
	private Usuario usuario;

	public VentanaUsuario(Usuario usuario, ArrayList<Anuncio> anuncios) {
        super("Detalles del Usuario");
        
        this.anuncios = anuncios;
        this.usuario = usuario;
        initTable();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 


        JPanel pnlNombre = new JPanel();
        JPanel pnllbltxt = new JPanel();
        pnlNombre.setLayout(new BorderLayout());
        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(usuario.getNombre());
        txtNombre.setEditable(false);
        
        JLabel lblFotoPerfil = new JLabel();
        ImageIcon img = new ImageIcon(usuario.getFoto());
        System.out.println(usuario.getFoto());
        lblFotoPerfil.setIcon(img);
        lblFotoPerfil.setHorizontalAlignment(JLabel.CENTER);
        lblFotoPerfil.setVerticalAlignment(JLabel.CENTER);
        lblFotoPerfil.setIconTextGap(0);
        
        pnlNombre.add(BorderLayout.WEST,lblFotoPerfil);
        pnllbltxt.add(lblNombre);
        pnllbltxt.add(txtNombre);
        pnlNombre.add(BorderLayout.CENTER,pnllbltxt);


        JPanel pnlDNI = new JPanel();
        JLabel lblDNI = new JLabel("DNI:");
        JTextField txtDNI = new JTextField(usuario.getDni());
        txtDNI.setEditable(false);
        pnlDNI.add(lblDNI);
        pnlDNI.add(lblDNI);

        JPanel pnlFecha = new JPanel();
        JLabel lblFecha = new JLabel("Fecha de nacimiento:");
        JTextField txtFecha = new JTextField(usuario.getFecha());
        txtFecha.setEditable(false);
        pnlFecha.add(lblFecha);
        pnlFecha.add(txtFecha);

        JPanel pnlLocalidad = new JPanel();
        JLabel lblLocalidad = new JLabel("Localidad:");
        JTextField txtLocalidad = new JTextField(usuario.getLocalidad());
        txtLocalidad.setEditable(false);
        pnlLocalidad.add(lblLocalidad);
        pnlLocalidad.add(txtLocalidad);
        
        
        
        
        this.scrollAnuncios = new JScrollPane(this.tablaUsuarioAnuncios);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios publicados por ti"));
        
        JPanel pTodo = new JPanel();
        pTodo.setLayout(new GridLayout(6,1));
        pTodo.add(pnlNombre);
        pTodo.add(pnlDNI);
        pTodo.add(pnlFecha);
        pTodo.add(pnlLocalidad);
        pTodo.add(scrollAnuncios);
       
        

        add(pTodo); 
        setSize(600, 600); 
        setLocationRelativeTo(null); 
        setVisible(true);
    }
	

	public void initTable() {
		this.tablaUsuarioAnuncios = new JTable(new AnuncioUsuarioTableModel(this.anuncios, this.usuario));
		
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

    	this.tablaUsuarioAnuncios.setRowHeight(15);
    	this.tablaUsuarioAnuncios.setDefaultRenderer(Object.class, tablerenderer);
	}
}
