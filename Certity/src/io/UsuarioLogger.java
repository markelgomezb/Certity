package io;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import Domain.Usuario;

public class UsuarioLogger {
	private static final Logger LOGGER = Logger.getLogger(UsuarioLogger.class.getName());
	public static void configurarLogger() {
		try {
			FileHandler fh = new FileHandler("log/LogUsuario.log", true);
			SimpleFormatter sf = new SimpleFormatter();
			fh.setFormatter(sf);
			LOGGER.addHandler(fh);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void inicioUsuario(Usuario u) {
		LOGGER.info("Inicio de sesion del usuario" + u.getNombre_usuario() + " con DNI " + u.getDni());
	}
	
	public static void creacionUsuario(Usuario u) {
		LOGGER.info("El usuario "+ u.getNombre_usuario() + "se ha registrado con el DNI" + u.getDni());
	}
}
