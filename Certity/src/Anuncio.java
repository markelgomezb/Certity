public class Anuncio {
    private String id;
    private String nombre;
    private Usuario usuario;

    public Anuncio(String id, String nombre, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
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

    @Override
    public String toString() {
        return "Anuncio{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", usuario=" + usuario +
               '}';
    }
}
