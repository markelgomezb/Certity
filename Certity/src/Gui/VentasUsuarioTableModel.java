package Gui;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import Domain.Acuerdo;
import Domain.Anuncio;
import Domain.Usuario;

public class VentasUsuarioTableModel extends DefaultTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  ArrayList<Acuerdo> acuerdos;
	private Usuario usuario;
	private ArrayList<Acuerdo> acuerdosUsuario;
	
	public VentasUsuarioTableModel(Usuario usuario,  ArrayList<Acuerdo> acuerdos) {

		this.usuario = usuario;
		this.acuerdos =acuerdos;
		acuerdosUsuario = new ArrayList<Acuerdo>();
		System.out.println(acuerdos);
		System.out.println(this.usuario);
		acuerdosUsuario = new ArrayList<>();
		for (Acuerdo acuerdo : this.acuerdos) {
//			System.out.println(u);
//			System.out.println(anuncio);
//			System.out.println(anuncio.getUsuario());
			if(this.usuario.equals(acuerdo.getContratador())) {
//				System.out.println(anuncio.getUsuario());
				acuerdosUsuario.add(acuerdo);
			}
		}
		System.out.println(acuerdosUsuario);
	}

	
	public ArrayList<Acuerdo> getAcuerdo(){
		return this.acuerdos;
	}
	
	public int getRowCount() {
		if(this.acuerdosUsuario != null) {

			return acuerdosUsuario.size();
		}else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	
	public Object getValueAt(int row, int column) {
	    // TODO Auto-generated method stub
	    Acuerdo acuerdo = acuerdosUsuario.get(row);
	        switch(column) {
	            case 0: 
	                return acuerdo.getAnuncio().getId();
	            case 1:
	                return acuerdo.getContratador();
	            case 2:
	                return acuerdo.getFecha_hora_acordada();
	            case 3:
	            	return acuerdo.getAnuncio().getPrecio();
	            case 4:
	            	return acuerdo.getAnuncio().getDescripcion();
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
            return "ID del Anuncio";
        case 1:
        	return "Contratado";
        case 2:
        	return "Fecha acordada";
        case 3:
        	return "Precio del servicio";
        case 4:
        	return "Descipcion del trabajo";
        default:
            return "";
    }
	}
	
}
