import java.util.Date;

public class Usuario {
    private String dni;
    private Date fecha;
    private String nombre;
    private String nombre_usuario;
    private String localidad;

    public Usuario(String dni, Date fecha, String nombre, String localidad) {
        this.dni = dni;
        this.fecha = fecha;
        this.nombre = nombre;
        this.localidad = localidad;
        this.nombre_usuario = nombre_usuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "dni='" + dni + '\'' +
               ", fecha=" + fecha +
               ", nombre='" + nombre + '\'' +
               ", localidad='" + localidad + '\'' +
               ", nombre de usuario ='" + nombre_usuario + '\'' +
               '}';
        
    }

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
}
