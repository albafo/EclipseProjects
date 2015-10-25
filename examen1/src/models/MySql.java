package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
	private Connection con;
	
	/*Configuración MYSQL*/
	private String server="127.0.0.1:3307";
	private String bbdd="delincuentes";
	private String user="root";
	private String password="a16b2f88";
	
	private static MySql instance=null;
	
	MySql(){
		
		/* Genera una nueva instancia del driver de conexión MySQL */
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/* Intenta conectar y guardar esa conexión a la base de datos */
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://"+server+"/"+bbdd+"?"+"user="+user+"&password="+password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/* LLamada a generación o recogida de la instancia de la conexión */
	
	public static MySql getInstance() {
		if(instance==null) {
			instance=new MySql();
		}
		return instance;
	}
		

	//Con este metodo hago los selects
	public ResultSet query(String query){
		
		
		Statement st;
		ResultSet rs = null;

		try {
			st = con.createStatement();
			try{
				rs = st.executeQuery(query);
			}catch (SQLException e){
				e.printStackTrace();
			}
		
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		return rs;	
										
	}
	
	//Con este metodo vamos a hacer los inserts, update y deletes.
	public int modifyQuery(String update){
		Statement stmt;
		int rs = 0;
		try{
		stmt = con.createStatement();		
			try{
				rs = stmt.executeUpdate(update);
			}catch (SQLException e){
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
		

}
