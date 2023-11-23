package Domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Acuerdo {
	private Anuncio anuncio;
	private Usuario contratador;
	private Date fecha_hora_acordada;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public Acuerdo(Anuncio anuncio, Usuario contratador, String fecha_hora_acordada) {
		super();
		this.anuncio = anuncio;
		this.contratador = contratador;
        try {
			this.fecha_hora_acordada = sdf.parse(fecha_hora_acordada);
		} catch (ParseException e) {
			this.fecha_hora_acordada = new Date(0);
		}
	}
	public Anuncio getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	public Usuario getContratador() {
		return contratador;
	}
	public void setContratador(Usuario contratador) {
		this.contratador = contratador;
	}
	public Date getFecha_hora_acordada() {
		return fecha_hora_acordada;
	}
	public void setFecha_hora_acordada(Date fecha_hora_acordada) {
		this.fecha_hora_acordada = fecha_hora_acordada;
	}
	@Override
	public String toString() {
		return "Acuerdo [anuncio=" + anuncio + ", contratador=" + contratador + ", fecha_hora_acordada="
				+ fecha_hora_acordada + "]";
	}
	
	
	
	
	
}
