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
			"LOCALIDAD"
			);
	
	public AnunciosTableModel(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
}
