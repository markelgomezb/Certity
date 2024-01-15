package Database;

import java.sql.Connection;





import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Domain.Acuerdo;
import Domain.Anuncio;
import Domain.Usuario;
import Gui.VentanaPrincipal;

public class BD {
	
	public static Connection initBD(String nombreBD) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:"+nombreBD);
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	
	public static void closeBD(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void crearTablas(Connection con) {
		String sql = "CREATE TABLE IF NOT EXISTS Anuncio (id int, nombre String, "
				+ "dniUsuario String, descripcion String, precio float, fotos String)";
		String sql1 = "CREATE TABLE IF NOT EXISTS Acuerdos (idAnuncio String, usuarioContratador String, fechaAcordada String)";

		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.executeUpdate(sql1);

			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertarAnuncio(Connection con, Anuncio p) {
			String sql = String.format("INSERT INTO Anuncio VALUES('%s','%s','%s','%s','%s','%s');"
					, p.getId(),p.getNombre(),p.getUsuario().getDni(),p.getDescripcion(),p.getPrecio(),p.getFotos().toString());
			try {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public static void insertarAcuerdo(Connection con, Acuerdo p) {
		String sql = String.format("INSERT INTO Acuerdos VALUES('%s','%s','%s');"
				, p.getAnuncio().getId(),p.getContratador().getDni(),p.getFecha_hora_acordada());
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}

	public static List<Anuncio> obtenerAnuncios(Connection con){
		String sql = "SELECT * FROM Anuncio";
		List<Anuncio> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nombre");
				String usu = rs.getString("dniUsuario");
				String desc = rs.getString("descripcion");
				Float precio = rs.getFloat("precio");
				
				String[] fotosArray = rs.getString("fotos").split(",");
				//anuncio.setFotos(new ArrayList<>(Arrays.asList(fotosArray)));
				
				Usuario usuario = VentanaPrincipal.buscarUsuario(usu);
				Anuncio p = new Anuncio(id, nom, usuario, desc, precio, new ArrayList<>(Arrays.asList(fotosArray)));
				l.add(p);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	
	public static List<Acuerdo> obtenerListaAcuerdos(Connection con) {
	    List<Acuerdo> acuerdos = new ArrayList<>();
	    String sql = "SELECT * FROM Acuerdos;";
	    try (Statement st = con.createStatement();
	         ResultSet rs = st.executeQuery(sql)) {
	        while (rs.next()) {
	            int idAnuncio = rs.getInt("idAnuncio");
	            String usuarioContratadorDNI = rs.getString("usuarioContratador");
	            String fechaAcordada = rs.getString("fechaAcordada");

	            Usuario usuarioContratador = VentanaPrincipal.buscarUsuario(usuarioContratadorDNI);
	            Anuncio anuncio = VentanaPrincipal.anuncio1;

	            acuerdos.add(new Acuerdo(anuncio, usuarioContratador, fechaAcordada));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return acuerdos;
	}
	
	public static List<Integer> obteneridAnuncios(Connection con){
		String sql = "SELECT id FROM Anuncio";
		List<Integer> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				
				
				//String[] fotosArray = rs.getString("fotos").split(",");
				//anuncio.setFotos(new ArrayList<>(Arrays.asList(fotosArray)));
				
				//Usuario usuario = VentanaPrincipal.buscarUsuario(usu);
				//Anuncio p = new Anuncio(id, nom, usuario, desc, precio, new ArrayList<>(Arrays.asList(fotosArray)));
				l.add(id);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
	
	public static void borrarAnuncio(Connection con, int id) {
		String sql = String.format("DELETE FROM Anuncio WHERE id=%d", id);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void borrarAcuerdo(Connection con, int id) {
		String sql = String.format("DELETE FROM Acuerdo WHERE id=%d", id);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	
		
	public static void modificarNombreAnuncio(Connection con, int id, String nuevoNombre) {
		String sql = String.format("UPDATE Anuncio SET nombre='%s' WHERE id='%s'",nuevoNombre,id);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void modificarPrecioAnuncio(Connection con, int id, float nuevoPrecio) {
		String sql = String.format("UPDATE Anuncio SET precio='%s' WHERE id='%s'",nuevoPrecio,id);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void modificarDescripcioneAnuncio(Connection con, int id, String nuevaDescripcion) {
		String sql = String.format("UPDATE Anuncio SET descripcion='%s' WHERE id='%s'",nuevaDescripcion,id);
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static void agregarFotoAnuncio(Connection con, int id, String nuevaFoto) {
        String sql = String.format("UPDATE Anuncio SET foto='%s' WHERE id='%s'",nuevaFoto, id);
        try  {
        	Statement st = con.createStatement();
            st.executeUpdate(sql);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



