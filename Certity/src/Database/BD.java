package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Domain.Anuncio;
import Domain.Usuario;

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
				+ "usuario String, descripcion String, precio float, fotos String)";
		
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertarAnuncio(Connection con, Anuncio p) {
			String sql = String.format("INSERT INTO Anuncio VALUES('%s','%s','%s','%s');"
					, p.getId(),p.getNombre(),p.getUsuario(),p.getDescripcion(),p.getPrecio(),p.getFotos().toString());
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
		String sql = "SELECT * FROM Persona";
		List<Anuncio> l = new ArrayList<>();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String nom = rs.getString("nombre");
				String usu = rs.getString("usuario");
				String desc = rs.getString("descripcion");
				Float precio = rs.getFloat("precio");
				String fotos = rs.getString("fotos");
				
				
				
				//Anuncio p = new Anuncio(id, nom, usu, desc, precio, fotos);
				System.out.println(fotos);
				//l.add(p);
			}
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
		
		
	

}
