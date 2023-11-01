import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Anuncio> anuncios;
	
	private DefaultTableModel modeloAnuncios;
	private JTable tablaAnuncios;
	private JScrollPane scrollAnuncios;
	
    public VentanaPrincipal(ArrayList<Anuncio> anuncios) {
        super("Anuncios");
        this.anuncios = anuncios;
        
        this.initTable();
        
        this.scrollAnuncios = new JScrollPane(this.tablaAnuncios);
        scrollAnuncios.setBorder(new TitledBorder("Anuncios"));
        
        this.getContentPane().add(this.scrollAnuncios);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); 
        this.setSize(800, 600);
        this.loadAnuncios();
        setVisible(true);
    }
    
    public void initTable() {
    	Vector<String> cabeceraAnuncios = new Vector<String>(Arrays.asList("USUARIO", "FOTOS", "DESCRIPCION", "LOCALIDAD", "PRECIO"));
    	
    	this.modeloAnuncios = new DefaultTableModel(new Vector<Vector<Object>>(),cabeceraAnuncios);
    	
    	this.tablaAnuncios = new JTable(this.modeloAnuncios);
    	
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
    
    public void loadAnuncios() {
    	this.modeloAnuncios.setRowCount(0);
    	anuncios.forEach(e ->{
    		this.modeloAnuncios.addRow(new Object[] {e.getUsuario(),e.getFotos(),e.getDescripcion(),e.getUsuario().getLocalidad(),e.getPrecio()});
    	});
    }
}