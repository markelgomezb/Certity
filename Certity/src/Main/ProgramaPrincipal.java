 package Main;
import java.sql.Connection;





import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Database.BD;
import Domain.*;
import Gui.VentanaInicioSesion2;
import Gui.VentanaPrincipal;

public class ProgramaPrincipal {
	public static Connection con = null;
	public static void main(String[] args) throws ParseException {
		
		con = BD.initBD("certity.db");
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
        
        //ArrayList<Anuncio> anuncios1 = new ArrayList<>();
//        for(Anuncio a : anuncios) {
//        	BD.insertarAnuncio(con, a);
//        }
        
        
       

        ArrayList<Anuncio> anunciosBD = new ArrayList<>();
        anunciosBD = (ArrayList<Anuncio>) BD.obtenerAnuncios(con);
        //llamada funcion recursiva y presupuesto de ejemplon 
        //esta implementación permite ver como funciona la función recursiva con los datos actuales
        
        float presupuestoEjemplo = 500.0f;
        
        int maxServicios = ServiciosRecursividad.maximoServiciosConPresupuesto(anuncios, presupuestoEjemplo);
        System.out.println("Con un presupuesto de " + presupuestoEjemplo + ", puedes contratar hasta " + maxServicios + "s4rvicios");
        
        System.out.println("hola");
        for(Anuncio a : anunciosBD) {
        System.out.println(a);
        }
        System.out.println("dat is basura");
        
        ArrayList<Anuncio> anunciosBD1 = new ArrayList<>();
        for(Anuncio a : anunciosBD) {
        	Anuncio p = new Anuncio(a.getId(),a.getNombre(),VentanaPrincipal.buscarUsuario(a.getUsuario1()),a.getDescripcion(),a.getPrecio(),a.getFotos());
        	anunciosBD1.add(p);
        }
        
        System.out.println("hola");
        for(Anuncio a : anunciosBD1) {
        	System.out.println(2);
        System.out.println(a);
        
        }
        
        //en cmabio este codigo de prueba que decidimos poner en el main, nos permite probarlo en diferentes ocasiones o escenarios, con diferentes conjuntos de datos
        
       /*
        List<Anuncio> anunciosDePrueba = crearAnunciosDePrueba();

        // Prueba con diferentes presupuestos
        float[] presupuestosDePrueba = {100.0f, 200.0f, 300.0f, 400.0f, 500.0f};
        for (float presupuesto : presupuestosDePrueba) {
            int maxServicios = ServiciosRecursividad.maximoServiciosConPresupuesto(anunciosDePrueba, presupuesto);
            System.out.println("Con un presupuesto de " + presupuesto + ", puedes contratar hasta " + maxServicios + " servicios");
        }
        
        */
        
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
        
        //System.out.println(usuarios);
        
        System.out.println(anunciosBD1);
        System.out.println(anuncios);
        new VentanaInicioSesion2(anuncios,acuerdos);
//        new VentanaPrincipal(anuncios, usuario1);
        
        //FicheroCSV.guardarUsuariosEnFichero(usuarios, "Resources/Ficheros/Usuarios.csv");
	
		//BD.closeBD(con);

	}
	
	
	//metodo para prueba
	/*
	 private static List<Anuncio> crearAnunciosDePrueba() {
	        List<Anuncio> anuncios = new ArrayList<>();
	        // Suponiendo que cada anuncio tiene un precio fijo, añadir algunos anuncios de ejemplo
	        anuncios.add(new Anuncio(1, "Servicio 1", null, "Descripción 1", 50.0f, null)); 
	        anuncios.add(new Anuncio(2, "Servicio 2", null, "Descripción 2", 75.0f, null)); 
	        anuncios.add(new Anuncio(3, "Servicio 3", null, "Descripción 3", 100.0f, null));
	        anuncios.add(new Anuncio(4, "Servicio 4", null, "Descripción 4", 150.0f, null)); 
	        anuncios.add(new Anuncio(5, "Servicio 5", null, "Descripción 5", 200.0f, null)); 
	        
	        return anuncios;
	    }
	*/
}
