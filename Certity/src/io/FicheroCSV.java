package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

import Domain.Usuario;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FicheroCSV {
	
	//la lista de usuarios se maneja dentro de cada metodo,
	//estos metodos se sacan de la clase ventanaPrincipal
	//donde esta era un atributo de clase
	
	   public static List<Usuario> cargarUsuarioEnLista(String nomfich) {
		   List<Usuario> usuarios = new ArrayList<>();//esto
			try {
				Scanner sc = new Scanner(new FileReader(nomfich));
				String linea;
				while(sc.hasNext()) {
					linea = sc.nextLine();
					String [] partes = linea.split(";");
					String dni = partes[0];
					String fecha = partes[1];
					String nombre = partes[2];
					String nombre_usuario = partes[3];
					String localidad = partes[4];
					String email = partes[5];
					String foto = partes[6];
					String contrasenia = partes[7];
					
					
					Usuario c = new Usuario(dni, fecha, nombre, nombre_usuario, localidad, email, foto, contrasenia);	
					usuarios.add(c);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				
			}
			return usuarios;
			
		}
	    
	    public static void guardarUsuariosEnFichero(List<Usuario> usuarios, String nomfich) {
			try {
				PrintWriter pw = new PrintWriter(new File(nomfich));
				for(Usuario c : usuarios) {
					pw.println(c.getDni()+";"+c.getFecha()+";"+c.getNombre()+";"+c.getNombre_usuario()+
							";"+c.getLocalidad()+";"+c.getEmail()+";"+c.getFoto()+";"+c.getContrasenia());
				}
				pw.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	    	

}
