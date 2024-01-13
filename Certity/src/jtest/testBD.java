package jtest;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Database.BD;
import Domain.Usuario;
import Domain.Anuncio;

public class testBD {
	
	private static final String bdtest = "testBD.db"; 
	private Connection connection;
	
	@Before
	public void setUp() {
		connection = BD.initBD(bdtest);
		BD.crearTablas(connection);
	}
	
	@After 
	public void tearDown() {
		BD.closeBD(connection);
	}
	
	@Test
	public void testInsertarYObtenerAnuncio() {
		Usuario usuario = new Usuario("DNI123", "01-01-2000", "NombreUsuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "password");
        Anuncio anuncio = new Anuncio(1, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
		
		BD.insertarAnuncio(connection, anuncio);
		
		List<Anuncio> anuncios = BD.obtenerAnuncios(connection);
		
		assertEquals(1, anuncios.size());
		assertEquals(anuncio, anuncios.get(0));
	}
	
	@Test
	public void testBorrarAnuncio() {
		Usuario usuario = new Usuario("DNI123", "01-01-2000", "NombreUsuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "password");
        Anuncio anuncio = new Anuncio(1, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
		
		BD.insertarAnuncio(connection, anuncio);
		BD.borrarAnuncio(connection, 1);
		
		List<Anuncio> anuncios = BD.obtenerAnuncios(connection);
		
		assertTrue(anuncios.isEmpty());
	}
	
	@Test
	public void testModificarNombreAnuncio() {
		Usuario usuario = new Usuario("DNI123", "01-01-2000", "NombreUsuario", "Localidad", "username", "hola@gmail.com", "foto.jpg", "password");
        Anuncio anuncio = new Anuncio(1, "NombreAnuncio", usuario, "Descripción", (float) 100.0, new ArrayList<>());
        
		BD.insertarAnuncio(connection, anuncio);
		BD.modificarNombreAnuncio(connection, 1, "NuevoNombre");
		
		List<Anuncio> anuncios = BD.obtenerAnuncios(connection);
		
		assertEquals("NuevoNombre", anuncios.get(0).getNombre());
	}
	

}
