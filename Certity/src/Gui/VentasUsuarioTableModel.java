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
	private Map<Usuario,ArrayList<Acuerdo>> mapUsuarioVentas;
	
	public VentasUsuarioTableModel(Usuario usuario,  ArrayList<Acuerdo> acuerdos) {

		this.usuario = usuario;
		this.acuerdos =acuerdos;
		mapUsuarioVentas = new HashMap<Usuario, ArrayList<Acuerdo>>();
		
		acuerdos.forEach(e ->{
			mapUsuarioVentas.putIfAbsent(e.getContratador(), new ArrayList<Acuerdo>());
			mapUsuarioVentas.get(e.getContratador()).add(e);
		});
	}
	
	public ArrayList<Acuerdo> getAcuerdo(){
		return this.acuerdos;
	}
	
	public int getRowCount() {
		if(this.mapUsuarioVentas != null) {

			return mapUsuarioVentas.keySet().size();
		}else {
			return 0;
		}
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	public Object getValueAt(int row, int column) {
	    // TODO Auto-generated method stub
	    ArrayList<Acuerdo> VentaUsuario = mapUsuarioVentas.get(this.usuario);

	    if (VentaUsuario != null && row < VentaUsuario.size()) {
	        Acuerdo venta = VentaUsuario.get(row);

	        switch(column) {
	            case 0: 
	                return venta.getAnuncio().getId();
	            case 1:
	                return venta.getContratador();
	            case 2:
	                return venta.getFecha_hora_acordada();
	            case 3:
	            	return venta.getAnuncio().getPrecio();
	            case 4:
	            	return venta.getAnuncio().getDescripcion();
	            default:
	                return null;
	        }
	    } else {
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
