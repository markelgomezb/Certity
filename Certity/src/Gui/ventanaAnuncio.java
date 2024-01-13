package Gui;
import javax.swing.JFrame;


import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Database.BD;
import Domain.Anuncio;
import Main.ProgramaPrincipal;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ventanaAnuncio extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnSubirFoto;
    private JTextField txtDescripcion;
    private JTextField txtLocalidad;
    private JTextField txtPrecio;
    private JButton btnTodo;
    private String destino1;
    private List<Integer> ids;
    

    public ventanaAnuncio() {
        super("Crear Anuncio");

        btnSubirFoto = new JButton("Subir Foto");
        btnTodo = new JButton("Guardar cambios");
        txtDescripcion = new JTextField(20);
        txtLocalidad = new JTextField(20);
        txtPrecio = new JTextField(10);

        JPanel panelAnuncio = new JPanel();
        JPanel panelAnuncio1 = new JPanel();
        panelAnuncio.setLayout(new GridLayout(4, 2));
        panelAnuncio1.setLayout(new GridLayout(1, 1));

        panelAnuncio.add(new JLabel("Descripción:"));
        panelAnuncio.add(txtDescripcion);
        panelAnuncio.add(new JLabel("Localidad:"));
        panelAnuncio.add(txtLocalidad);
        panelAnuncio.add(new JLabel("Precio:"));
        panelAnuncio.add(txtPrecio);
        panelAnuncio.add(new JLabel("Foto:"));
        panelAnuncio.add(btnSubirFoto);
        //panelAnuncio.add(new JLabel("Guardar Anuncio:"));
        panelAnuncio1.add(btnTodo);

        this.getContentPane().add(BorderLayout.CENTER,panelAnuncio);
        this.getContentPane().add(BorderLayout.SOUTH,panelAnuncio1);
        
       
        

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
                            destino1 = destino.getPath();
                        } else {
                            System.out.println("Ya existe un archivo con ese nombre en el directorio.");
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnTodo.addActionListener((e)->{
        	String desc =  txtDescripcion.getText();
        	String loc = txtLocalidad.getText();
        	String precio1= txtPrecio.getText();
        	float precio = Float.parseFloat(precio1);
        	Boolean a = false;
        	while(a == false) {
        		 Random rand = new Random();
        	     int numeroAleatorio = rand.nextInt(100);
        	     
        	     ids= BD.obteneridAnuncios(ProgramaPrincipal.con);
        	     
        		
        	}
        	
        	//Anuncio c = new Anuncio
        	
        	
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
