package jtest;


import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Domain.Anuncio;
import Gui.VentanaRegistro;


public class testVentanaRegistro {
	
	@Test
	public void testValidarContrasenia() {
		
		ArrayList<Anuncio> anuncios = new ArrayList<>();
		
		VentanaRegistro ventanaRegistro = new VentanaRegistro(anuncios, null);
		
	//casos de prueba
		assertTrue(ventanaRegistro.validarContrasenia("Holahola123"));
		assertFalse(ventanaRegistro.validarContrasenia("noxd"));
	}
	
	@Test
	public void testValidarEmail() {
		
		ArrayList<Anuncio> anuncios = new ArrayList<>();
		
		VentanaRegistro ventanaRegistro = new VentanaRegistro(anuncios, null);
		
		try {
			assertTrue(ventanaRegistro.validarEmail("certity@gmail.com"));
			assertFalse(ventanaRegistro.validarEmail("esto_no"));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegistrarUsuario() {
		ArrayList<Anuncio> anuncios = new ArrayList<>();
		VentanaRegistro ventanaRegistro = new VentanaRegistro(anuncios, null);
		
		
		 assertTrue(ventanaRegistro.registrarUsuario("certity@gmail.com", "Holahola123"));
	     assertFalse(ventanaRegistro.registrarUsuario("correo_invalido", "noxd"));
	     assertFalse(ventanaRegistro.registrarUsuario("certity@gmail.com", "clave_invalida"));
	}
	

}

