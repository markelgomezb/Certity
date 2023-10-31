import java.util.Arrays;
import java.util.List;

import javax.swing.table.DefaultTableModel;

public class AnunciosTableModel extends DefaultTableModel{


	private static final long serialVersionUID = 1L;
	private List<Anuncio> anuncios;
	private final List<String> headers = Arrays.asList(
			"USUARIO",
			"FOTOS",
			"DESCRIPCION",
			"LOCALIDAD",
			"PRECIO"
			);
	
	public AnunciosTableModel(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
	
	public String getColumnName(int column) {
		return headers.get(column);
	}
	
	public int getRowCount() {
		if(anuncios != null) {
			return anuncios.size();
		} else {
			return 0;
		}
	}
	
	public boolean IsCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public void setValueAt(Object aValue, int row, int column) {
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		Anuncio anuncio = anuncios.get(rowIndex);
		switch (columnIndex) {
//		"USUARIO",
//		"FOTOS",
//		"DESCRIPCION",
//		"LOCALIDAD",
//		"PRECIO"
		
		case 0: return anuncio.getUsuario();
		case 1: return anuncio.getFotos();
		case 2: return anuncio.getDescripcion();
		case 3: return anuncio.getUsuario().getLocalidad();
		case 4: return anuncio.getPrecio();
		default: return null;
		}
	}
}
