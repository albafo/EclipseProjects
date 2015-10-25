package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Delincuente {

	private int id;
	private String nombre;
	private static ArrayList<Delincuente> delincuentes=null;
	
	
	public static ArrayList<Delincuente> loadFromDB(boolean forceLoadFromDB) {
		
		
		
		if(forceLoadFromDB || delincuentes==null) {
			delincuentes = new ArrayList<Delincuente>();
		
			MySql mysql = MySql.getInstance();
			ResultSet rs = mysql.query("Select * from delincuentes");
			try {
				while(rs.next()) {
					Delincuente delincuente = new Delincuente();
					delincuente.setId(rs.getInt("id"));
					delincuente.setNombre(rs.getString("nombre"));
					delincuentes.add(delincuente);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return delincuentes;
		
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


	public static ArrayList<Delincuente> getDelincuentes() {
		return delincuentes;
	}

	public String toString() {
		return this.getNombre();
	}
}
