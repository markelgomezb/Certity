 package Main;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.BD;
import Domain.*;
import Gui.VentanaInicioSesion2;
import Gui.VentanaPrincipal;

public class ProgramaPrincipal {
	public static void main(String[] args) throws ParseException {
		
		Connection con = BD.initBD("certity.db");
		BD.crearTablas(con);
		
		
//		InicioSesion v = new InicioSesion();
		
		VentanaPrincipal.cargarUsuarioEnLista("Resources/Ficheros/Usuarios.csv");
		
		
		//IAG DATOS DE PRUEBA
        // Crear usuarios de ejemplo
        Usuario usuario1 = new Usuario("123456789A","10/05/1990" , "Usuario1", "Ciudad1", "user1",  "usuario1@example.com", "Resources/Imagenes/nano.png", "contrasenia123");
        Usuario usuario2 = new Usuario("987654321B","10/05/1990" , "Usuario2", "Ciudad2", "user2",  "usuario2@example.com", "Resources/Imagenes/nano.png", "contrasenia123");
        Usuario usuario3 = new Usuario("567890123C","10/05/1990" , "Usuario3", "Ciudad3", "user3",  "usuario3@example.com", "Resources/Imagenes/nano.png", "contrasenia123");

        // Crear anuncios asociados a los usuarios
        ArrayList<String> fotosAnuncio1 = new ArrayList<>();
        fotosAnuncio1.add("Resources/Imagenes/5.jpg");
        fotosAnuncio1.add("Resources/Imagenes/nano.png");
        Anuncio anuncio1Usuario1 = new Anuncio(1, "Anuncio 1", usuario1, "Montaje mueble ikea", (float) 100.0, fotosAnuncio1);
        Anuncio anuncio2Usuario1 = new Anuncio(2, "Anuncio 2", usuario1, "Chequeo de tuberias", (float)150.0, fotosAnuncio1);
        //BD.insertarAnuncio(con, anuncio2Usuario1);
        //System.out.println(BD.obtenerAnuncios(con));
        //BD.borrarAnuncio(con, 1);
        for (int i = 0; i < BD.obtenerAnuncios(con).size(); i++) {
            System.out.println("Elemento en la posición " + i + ": " + BD.obtenerAnuncios(con).get(i));
        }
        BD.borrarAnuncio(con, 1);
        ArrayList<String> fotosAnuncio2 = new ArrayList<>();
        
        fotosAnuncio2.add("Resources/Imagenes/5.jpg");
        fotosAnuncio2.add("Resources/Imagenes/nano.png");
        Anuncio anuncio1Usuario2 = new Anuncio(3, "Anuncio 1", usuario2, "Descripción del anuncio 1", (float)200.0, fotosAnuncio2);
        Anuncio anuncio2Usuario2 = new Anuncio(4, "Anuncio 2", usuario2, "Descripción del anuncio 2", (float)250.0, fotosAnuncio2);
        System.out.println(anuncio1Usuario1);
        
        ArrayList<String> fotosAnuncio3 = new ArrayList<>();
        fotosAnuncio3.add("Resources/Imagenes/5.jpg");
        fotosAnuncio3.add("Resources/Imagenes/nano.png");
        Anuncio anuncio1Usuario3 = new Anuncio(5, "Anuncio 1", usuario3, "Descripción del anuncio 1", (float)300.0, fotosAnuncio3);
        Anuncio anuncio2Usuario3 = new Anuncio(6, "Anuncio 2", usuario3, "Descripción del anuncio 2", (float)350.0, fotosAnuncio3);
        
        System.out.println(anuncio1Usuario2.getFotos());
        System.out.println("hola");
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        anuncios.add(anuncio1Usuario1);
        anuncios.add(anuncio2Usuario1);
        anuncios.add(anuncio1Usuario2);
        anuncios.add(anuncio2Usuario2);
        anuncios.add(anuncio1Usuario3);
        anuncios.add(anuncio2Usuario3);
        
        
        // Crear acuerdos con los datos proporcionados

        ArrayList<Acuerdo> acuerdos = new ArrayList<>();
        
        String fechaHoraAcuerdo1 = "01/01/2023 10:00";
		Acuerdo acuerdo1 = new Acuerdo(anuncio1Usuario1, usuario2, fechaHoraAcuerdo1);

		String fechaHoraAcuerdo2 = "02/01/2023 15:30";
		Acuerdo acuerdo2 = new Acuerdo(anuncio2Usuario2, usuario1, fechaHoraAcuerdo2);

		String fechaHoraAcuerdo3 = "03/01/2023 14:00";
		Acuerdo acuerdo3 = new Acuerdo(anuncio1Usuario3, usuario2, fechaHoraAcuerdo3);
		
		acuerdos.add(acuerdo1);
		acuerdos.add(acuerdo2);
		acuerdos.add(acuerdo3);
        
        anuncios.forEach(e ->{
        	System.out.println(e);
        });
        
        new VentanaInicioSesion2(anuncios,acuerdos);
//        new VentanaPrincipal(anuncios, usuario1);
	
		BD.closeBD(con);

	}
	
}
