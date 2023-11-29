package Gui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import Domain.Anuncio;
import Domain.Usuario;

public class AnuncioUsuarioTableModel extends DefaultTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Anuncio> anuncios;
	private Usuario u;
//	private Map<Usuario,ArrayList<Anuncio>> mapAnuncios;
	private ArrayList<Anuncio> anunciosUsuario;
	
	public AnuncioUsuarioTableModel(ArrayList<Anuncio> anuncios, Usuario u) {
		this.anuncios = anuncios;
		this.u = u;
		anunciosUsuario = new ArrayList<>();
		for (Anuncio anuncio : anuncios) {
			System.out.println(u);
			System.out.println(anuncio);
			System.out.println(anuncio.getUsuario());
			if(u.equals(anuncio.getUsuario())) {
				System.out.println(anuncio.getUsuario());
				anunciosUsuario.add(anuncio);
			}
		}
		
		System.out.println(anunciosUsuario);
//		mapAnuncios = new HashMap<Usuario, ArrayList<Anuncio>>();
//		anuncios.forEach(e ->{
//			mapAnuncios.putIfAbsent(e.getUsuario(), new ArrayList<Anuncio>());
//			mapAnuncios.get(e.getUsuario()).add(e);
//		});
//		System.out.println(mapAnuncios);
	}
	
	public ArrayList<Anuncio> getAnunciosUsuario(){
		return this.anuncios;
	}
	
	public int getRowCount() {
		if(this.anunciosUsuario != null) {
			
			return anunciosUsuario.size();
		}else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
//	@Override
//	public Object getValueAt(int row, int column) {
//		// TODO Auto-generated method stub
//		Anuncio anuncio = mapAnuncios.get(u).get(row);
//		
//		switch(column) {
//		case 0: 
//			return anuncio.getId();
//		case 1:
//			return anuncio.getDescripcion();
//		case 2:
//			return anuncio.getPrecio();
//		case 3:
//			return anuncio.getFotos();
//		default:
//			return null;
//		}
//		}
	
	public Object getValueAt(int row, int column) {
	    // TODO Auto-generated method stub

	        Anuncio anuncio = anunciosUsuario.get(row);
	        System.out.println(anuncio);
	        switch(column) {
	            case 0: 
	                return anuncio.getId();
	            case 1:
	                return anuncio.getDescripcion();
	            case 2:
	                return anuncio.getPrecio();
	            case 3:
	                return anuncio.getFotos();
	            default:
	                return null;
	        }
	}
	
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public String getColumnName(int column) {
        switch (column) {
        case 0:
            return "ID";
        case 1:
        	return "Descripción";
        case 2:
        	return "Precio";
        case 3:
        	return "Fotos";
        default:
            return "";
    }
	}
}
