package Gui;
import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;

import Database.BD;
import Domain.Acuerdo;
import Domain.Anuncio;
import Domain.Usuario;
import Main.ProgramaPrincipal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ventanaAcuerdo extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JTextField txtHoraAcordada;

    private int elegido;
    private Anuncio anuncio_quitar;
    private JButton btnTodo;
    private ArrayList<Anuncio> anuncioss;
    private Usuario usuario;
    private ArrayList<Anuncio> anunciosDisponibles;
    private ArrayList<Acuerdo> acuerdoss;
	private JTable tablaAnuncios;
	private JScrollPane scrollAnuncios;
	private AnunciosVentanaAcuerdosTableModel tableModel;
	private JCheckBox precioDescendiente, precioAscendiente;

    public ventanaAcuerdo(ArrayList<Anuncio> anuncios, Usuario u, ArrayList<Acuerdo> acuerdos) {
        super("Crear Anuncio");
        this.anuncioss = anuncios;
        this.usuario = u;
        this.acuerdoss = acuerdos;
        this.anunciosDisponibles = new ArrayList<Anuncio>();
        

        for(Anuncio anuncio : this.anuncioss) {
        	if(!(this.usuario.equals(anuncio.getUsuario()))) {
        		anunciosDisponibles.add(anuncio);
        	}
        }
        
		this.initTable(anunciosDisponibles);
        
        
		this.scrollAnuncios = new JScrollPane(this.tablaAnuncios);
		scrollAnuncios.setBorder(new TitledBorder("Anuncios"));
		
		precioDescendiente = new JCheckBox("Ordenar precio descendiente");
		precioAscendiente = new JCheckBox("Ordenar precio ascendiente");
		
        btnTodo = new JButton("Crear acuerdo");
        txtHoraAcordada = new JTextField("dd/MM/yyyy HH:mm");
        JLabel lblIntro = new JLabel(this.usuario + " !Aquí tienes los anuncios disponibles!");

        JPanel panelNorte = new JPanel();
        JPanel panelAnuncio = new JPanel();
        JPanel panelAnuncio1 = new JPanel();
        panelAnuncio.setLayout(new GridLayout(5, 2));
        panelAnuncio1.setLayout(new GridLayout(1, 1));
        
        panelAnuncio1.add(new JLabel("Día y hora de llegada:"));
        panelAnuncio1.add(txtHoraAcordada);
        panelNorte.add(lblIntro);
        panelNorte.add(precioDescendiente);
        panelNorte.add(precioAscendiente);

        //panelAnuncio.add(new JLabel("Guardar Anuncio:"));
        panelAnuncio1.add(btnTodo);
		this.getContentPane().add(BorderLayout.CENTER, this.scrollAnuncios);
        this.getContentPane().add(BorderLayout.SOUTH,panelAnuncio1);
        this.getContentPane().add(BorderLayout.NORTH,panelNorte);
        
        tablaAnuncios.addMouseListener(new MouseAdapter() {
            @SuppressWarnings("unchecked")
			@Override
            public void mouseClicked(MouseEvent e) {

                int filaSeleccionada = tablaAnuncios.getSelectedRow();

                if (filaSeleccionada != -1) {
                    int id = (Integer) tablaAnuncios.getValueAt(filaSeleccionada, 0); 
                    String nombre = (String) tablaAnuncios.getValueAt(filaSeleccionada, 1);
                    
                    String desc = (String) tablaAnuncios.getValueAt(filaSeleccionada, 3);
                    float precio = (float) tablaAnuncios.getValueAt(filaSeleccionada, 4);

					ArrayList<String> fotos = (ArrayList<String>) tablaAnuncios.getValueAt(filaSeleccionada, 5);
                    anuncio_quitar = new Anuncio(id, nombre, VentanaPrincipal.buscarAnuncio(id, anuncioss).getUsuario(), desc, precio, fotos);
                    elegido = 1;


                }
            }
        });
        
        btnTodo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	boolean quitar = false;
                // Obtener el texto del JTextField
            	if(elegido == 1) {
                    String texto = txtHoraAcordada.getText();

                    // Verificar si el texto sigue el formato esperado
                    if (validarFormatoFecha(texto)) {
                        JOptionPane.showMessageDialog(ventanaAcuerdo.this, "El formato de fecha es válido. Acuerdo guardado.");
                        Acuerdo acuerdo = new Acuerdo(anuncio_quitar, usuario, texto);
                        acuerdoss.add(acuerdo);
      
                    	if (ProgramaPrincipal.con != null) {
                    		BD.insertarAcuerdo(ProgramaPrincipal.con, acuerdo);
                        } else {
                            System.out.println("La conexión a la base de datos es nula.");
                        }
                    	
                        System.out.println(acuerdo);
                        System.out.println(anuncio_quitar);
                        
                        for(Anuncio a : anuncioss) {
                        	if(anuncio_quitar.equals(a)) {
                        		quitar = true;
                        	}
                        }
                        if(quitar) {
                        	anuncioss.remove(anuncio_quitar);
                    		
                    		if (ProgramaPrincipal.con != null) {
                    			BD.borrarAnuncio(ProgramaPrincipal.con, anuncio_quitar.getId());
                            } else {
                                System.out.println("La conexión a la base de datos es nula.");
                            }
                        }
                        dispose();
                        new VentanaPrincipal(anuncioss, usuario, acuerdoss);
                        
                    } else {
                        JOptionPane.showMessageDialog(ventanaAcuerdo.this, "Formato de fecha no válido. Vuelva a introducir la fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                        // Limpiar el JTextField o realizar otra acción según tus necesidades
                        txtHoraAcordada.setText("");
                    }
            	} else {
            		JOptionPane.showMessageDialog(ventanaAcuerdo.this, "Elige un anuncio para participar en el.", "Error", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				new VentanaPrincipal(anuncioss, usuario, acuerdoss);
				 
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
	public void initTable(ArrayList<Anuncio> anuncios) {
//    	Vector<String> cabeceraAnuncios = new Vector<String>(Arrays.asList("USUARIO", "FOTOS", "DESCRIPCION", "LOCALIDAD", "PRECIO"));
//    	
//    	this.modeloAnuncios = new DefaultTableModel(new Vector<Vector<Object>>(),cabeceraAnuncios);
//    	
//    	this.tablaAnuncios = new JTable(this.modeloAnuncios);
//    	

		this.tableModel = new AnunciosVentanaAcuerdosTableModel(anuncios);
		this.tablaAnuncios = new JTable(tableModel);

		TableCellRenderer tablerenderer = (table, value, isSelected, hasFocus, row, column) -> {
			JLabel label = new JLabel(value.toString());
			label.setSize(150, 150);
			table.setRowHeight(row, 150);
			if (isSelected) {
				label.setBackground(table.getSelectionBackground());
				label.setForeground(table.getSelectionForeground());
			}

			if (row % 2 != 0) {
				label.setBackground(new Color(0, 0, 139));
				label.setForeground(Color.WHITE);
			}

			if (column == 5) {
				// for (Anuncio anuncio : this.anuncios) {
				// List<String> ftAnuncio = anuncio.getFotos();

				// if(ftAnuncio.size() != 0) {
				// for (String foto : ftAnuncio) {
//    			System.out.println(value.toString());
				ImageIcon img = new ImageIcon(value.toString());
				ImageIcon imdimensiones = new ImageIcon(
						img.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
				label.setIcon(imdimensiones);

				// }}
				// else {
//    					ImageIcon img = new ImageIcon("Resources/Imagenes/nano.png");
//    					label.setIcon(img);

				// }

				// }

			}
			label.setOpaque(true);
			return label;
		};

		this.tablaAnuncios.setRowHeight(15);
		this.tablaAnuncios.setDefaultRenderer(Object.class, tablerenderer);
	}
	
    private static boolean validarFormatoFecha(String fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        formatoFecha.setLenient(false); // No permite fechas inválidas, como febrero 30.

        try {
            // Intentar parsear la fecha
            Date fechaParseada = formatoFecha.parse(fecha);
            return true;
        } catch (ParseException ex) {
            // La excepción indica que la fecha no sigue el formato esperado
            return false;
        }
    }
}
