package Domain;
import java.util.ArrayList;

public class Anuncio {
    private String id;
    private String nombre;
    private Usuario usuario;
    private String descripcion;
    private float precio;
    private ArrayList<String> fotos;

    public Anuncio(String id, String nombre, Usuario usuario, String descripcion, float precio, ArrayList<String> fotos) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public ArrayList<String> getFotos() {
		return fotos;
	}

	public void setFotos(ArrayList<String> fotos) {
		this.fotos = fotos;
	}
	
    @Override
    public String toString() {
        return "Anuncio{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", usuario=" + usuario +
               '}';
    }
    //prueba conexion
    

}
