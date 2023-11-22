package Gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import javax.swing.table.DefaultTableModel;

import Domain.Anuncio;

public class AnuncioTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Anuncio> anuncios;

	 
	 public AnuncioTableModel(ArrayList<Anuncio> anuncios) {
		 this.anuncios = anuncios;
		 
	 }
	 
	public ArrayList<Anuncio> getAnuncios(){
		return this.anuncios;
	}
	 @Override
		public int getRowCount() {
			if (this.anuncios != null) {
				return anuncios.size();
			} else {
				return 0;
			}
		}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 6;
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		Anuncio anuncio = anuncios.get(row);
		
		switch(column) {
		case 0: 
			return anuncio.getId();
		case 1:
			return anuncio.getNombre();
		case 2:
			return anuncio.getUsuario().getNombre_usuario();
		case 3:
			return anuncio.getDescripcion();
		case 4:
			return anuncio.getPrecio();
		case 5:
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
                return "Nombre";
            case 2:
                return "Usuario";
            case 3:
                return "Descripción";
            case 4:
                return "Precio";
            case 5:
                return "Fotos";
            // Agrega más casos según la cantidad de columnas
            default:
                return "";
        }
		}
	}

