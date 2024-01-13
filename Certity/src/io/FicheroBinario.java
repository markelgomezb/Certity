package io;

import java.io.FileInputStream;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import Domain.Acuerdo;

public class FicheroBinario {
	private static final String nomFich = "acuerdos.bin";
	
	public static void escribirAcuerdos(List<Acuerdo> acuerdos) {
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));){
			oos.writeObject(acuerdos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<Acuerdo> leerAcuerdos(){
		List<Acuerdo> acuerdos = new ArrayList<>();
		 
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));){
			acuerdos = (List<Acuerdo>) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acuerdos;
		
	}
}
