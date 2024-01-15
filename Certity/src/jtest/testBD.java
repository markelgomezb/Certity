package jtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Database.BD;
import Domain.Usuario;
import Domain.Acuerdo;
import Domain.Anuncio;

public class testBD {
	
	private Connection con;
	//necesario por failure
	private static int anuncioID = 1;

	@Before
	public void setUp() throws Exception {
		
		con = BD.initBD("testBD.db");
		BD.crearTablas(con);
		limpiarTablas();

	}
	
	//Aquí nos ayudamos de ia porque había un failure que no entendíamos
	//por lo que creamos id distintos para anuncios en el test como ya 
	//se vera indicado con algún comentario. Y además que las tablas se limpiaran.
	
	private void limpiarTablas() {
		
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate("DELETE FROM Anuncio");
            stmt.executeUpdate("DELETE FROM Acuerdos");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	@After 
	public void tearDown() {
		
		BD.closeBD(con);
	}
	
	@Test
	public void testConnection() {
		
		//test que simplemente verifica conexión
		assertNotNull("la conexión no debe ser nula", con);
	}
	
	@Test
	public void testBorrarAnuncio() {
		
		Usuario usuario = new Usuario("DNI123", "01-01-2000", "NombreUsuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "password");
        Anuncio anuncio = new Anuncio(1, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
		
		BD.insertarAnuncio(con, anuncio);
		BD.borrarAnuncio(con, 1);
		
		List<Anuncio> anuncios = BD.obtenerAnuncios(con);
		
		//ver si se vacía
		assertTrue(anuncios.isEmpty());
	}
	
	//aqui obteneranuncio

		
	
	@Test
	public void testInsertarAnuncio() {
		
		Usuario usuario = new Usuario("DNI123", "01-01-2002", "NombreUsuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "pQAssword3");
        Anuncio anuncio = new Anuncio(anuncioID++, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
		
		BD.insertarAnuncio(con, anuncio);
		
		//haciendo esta obtención de anuncios para luego poder verificar el insertado
		List<Anuncio> anuncios = BD.obtenerAnuncios(con);
		
		//la lista de anuncios no debe estar vacía
		assertFalse(anuncios.isEmpty());
		
		//debe haber un anuncio en la lista
		assertEquals(1, anuncios.size());
		
		//que tenga el nombre correcto
		assertEquals("NombreAnuncio", anuncios.get(0).getNombre());
		
	}
	

	
	@Test
	public void testModificarNombreAnuncio() {
		
		Usuario usuario = new Usuario("DNI123", "01-01-2002", "Nombreusuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "pQAssword3");
        Anuncio anuncio = new Anuncio(1, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
        
		BD.insertarAnuncio(con, anuncio);
		BD.modificarNombreAnuncio(con, 1, "NuevoNombre");
		
		List<Anuncio> anuncios = BD.obtenerAnuncios(con);
		
		// ver si se modifica su nombre
		assertEquals("NuevoNombre", anuncios.get(0).getNombre());
	}
	
	@Test
	public void testModificarPrecioAnuncio() {
		
	    Usuario usuario = new Usuario("DNI799", "08-11-2003", "usuarionose", "localidadnose", "usernamenose", "bye@gmail.com", "foto.jpg", "pAssword789");
	    Anuncio anuncio = new Anuncio(anuncioID++, "PrecioAnuncio", usuario, "Descripción Precio", 150.0f, new ArrayList<>());
	    BD.insertarAnuncio(con, anuncio);

	    BD.modificarPrecioAnuncio(con, anuncio.getId(), 200.0f);
	    List<Anuncio> anuncios = BD.obtenerAnuncios(con);
	    
	    //esto comprueba que el precio del anuncio haya sido modificado
	    assertEquals(200.0f, anuncios.get(0).getPrecio(), 0.01);
	}

    @Test
    public void testAgregarFotoAnuncio() {
        Usuario usuario = new Usuario("DNI799", "08-11-2003", "usuarionose", "localidadnose", "usernamenose", "eoeo@gmail.com", "foto.jpg", "password789");
        ArrayList<String> fotos = new ArrayList<>();
        fotos.add("fotoOriginal.jpg");
        Anuncio anuncio = new Anuncio(anuncioID++, "FotoAnuncio", usuario, "Descripción Foto", 120.0f, fotos);
        BD.insertarAnuncio(con, anuncio);

        BD.agregarFotoAnuncio(con, anuncio.getId(), "nuevaFoto.jpg");
        List<Anuncio> anuncios = BD.obtenerAnuncios(con);
        
        //infalible, ver si esta vacía
        assertTrue(!anuncios.get(0).getFotos().isEmpty());
        //que la foto agregada esté
        assertTrue(anuncios.get(0).getFotos().contains("nuevaFoto.jpg"));
    }


	

}
