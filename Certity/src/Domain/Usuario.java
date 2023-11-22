package Domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Usuario {
    private String dni;
    private Date fecha;
    private String nombre;
    private String nombre_usuario;
    private String contrasenia;
    private String localidad;
    private String email;
    private String foto;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    

    public Usuario(String dni, String fecha, String nombre, String localidad, String nombre_usuario, String email, String foto, String contrasenia) {
        this.dni = dni;
        try {
			this.fecha = sdf.parse(fecha);
		} catch (ParseException e) {
			this.fecha = new Date(0);
		}
        this.nombre = nombre;
        this.localidad = localidad;
        this.nombre_usuario = nombre_usuario;
        this.email = email;
        this.foto= foto;
        this.contrasenia=contrasenia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha() {
        return sdf.format(fecha);
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
    
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

    @Override
    public String toString() {
        return  nombre_usuario;
        
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}


}
