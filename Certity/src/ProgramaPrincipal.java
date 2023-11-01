import java.util.ArrayList;
import java.util.Date;

public class ProgramaPrincipal {
	public static void main(String[] args) {
//		InicioSesion v = new InicioSesion();
//		new VentanaInicioSesion2();
		//IAG DATOS DE PRUEBA
        // Crear usuarios de ejemplo
        Usuario usuario1 = new Usuario("123456789A", new Date(), "Usuario1", "Ciudad1", "user1",  "usuario1@example.com");
        Usuario usuario2 = new Usuario("987654321B", new Date(), "Usuario2", "Ciudad2", "user2",  "usuario2@example.com");
        Usuario usuario3 = new Usuario("567890123C", new Date(), "Usuario3", "Ciudad3", "user3",  "usuario3@example.com");

        // Crear anuncios asociados a los usuarios
        ArrayList<String> fotosAnuncio1 = new ArrayList<>();
        fotosAnuncio1.add("foto1.jpg");
        fotosAnuncio1.add("foto2.jpg");
        Anuncio anuncio1Usuario1 = new Anuncio("1", "Anuncio 1", usuario1, "Descripción del anuncio 1", (float) 100.0, fotosAnuncio1);
        Anuncio anuncio2Usuario1 = new Anuncio("2", "Anuncio 2", usuario1, "Descripción del anuncio 2", (float)150.0, fotosAnuncio1);

        ArrayList<String> fotosAnuncio2 = new ArrayList<>();
        fotosAnuncio2.add("foto3.jpg");
        fotosAnuncio2.add("foto4.jpg");
        Anuncio anuncio1Usuario2 = new Anuncio("3", "Anuncio 1", usuario2, "Descripción del anuncio 1", (float)200.0, fotosAnuncio2);
        Anuncio anuncio2Usuario2 = new Anuncio("4", "Anuncio 2", usuario2, "Descripción del anuncio 2", (float)250.0, fotosAnuncio2);

        ArrayList<String> fotosAnuncio3 = new ArrayList<>();
        fotosAnuncio3.add("foto5.jpg");
        fotosAnuncio3.add("foto6.jpg");
        Anuncio anuncio1Usuario3 = new Anuncio("5", "Anuncio 1", usuario3, "Descripción del anuncio 1", (float)300.0, fotosAnuncio3);
        Anuncio anuncio2Usuario3 = new Anuncio("6", "Anuncio 2", usuario3, "Descripción del anuncio 2", (float)350.0, fotosAnuncio3);
        
        ArrayList<Anuncio> anuncios = new ArrayList<>();
        anuncios.add(anuncio1Usuario1);
        anuncios.add(anuncio2Usuario1);
        anuncios.add(anuncio1Usuario2);
        anuncios.add(anuncio2Usuario2);
        anuncios.add(anuncio1Usuario3);
        anuncios.add(anuncio2Usuario3);
        
        new VentanaPrincipal(anuncios, usuario1);
	}

}
