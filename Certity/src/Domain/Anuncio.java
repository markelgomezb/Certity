package Domain;
import java.util.ArrayList;

public class Anuncio {
    private int id;
    private String nombre;
    private Usuario usuario;
    private String usuario1;
    private String descripcion;
    private float precio;
    private int cont;
    private ArrayList<String> fotos;

    public Anuncio(int id, String nombre, Usuario usuario, String descripcion, float precio, ArrayList<String> fotos) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotos = fotos;
        this.cont = 0;
    }
    
    public Anuncio(int id, String nombre, String usuario1, String descripcion, float precio, ArrayList<String> fotos) {
        this.id = id;
        this.nombre = nombre;
        this.setUsuario1(usuario1);
        this.descripcion = descripcion;
        this.precio = precio;
        this.fotos = fotos;
        this.cont = 0;
    }
    
    public String getFoto(){
    	//siguienteFoto();
    	return fotos.get(cont);
    }

    public void siguienteFoto() {
    	cont++;
    	if(cont==fotos.size()) {
    		cont=0;
    	}
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
	
 
    //prueba conexion

	public String getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(String usuario1) {
		this.usuario1 = usuario1;
	}

	@Override
	public String toString() {
		return "Anuncio [id=" + id + ", nombre=" + nombre + ", usuario=" + usuario + ", descripcion=" + descripcion
				+ ", precio=" + precio + ", cont=" + cont + ", fotos=" + fotos + "]";
	}
    

}
