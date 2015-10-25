/* Alvaro Baño Fos 2DAM*/
package funciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Funciones {

	
	/*Método para comparar contenido de ficheros */
	
	public boolean compararContenido(File fichero1, File fichero2) throws FileNotFoundException 
	{
		
		/*Apertura de buffers*/
		FileReader f1 = new FileReader(fichero1);
		FileReader f2 = new FileReader(fichero2);
		BufferedReader br1 = new BufferedReader(f1);
		BufferedReader br2 = new BufferedReader(f2);
		boolean iguales = true;
		String lineaF1 = null;
		String lineaF2 = null;


		try {
			
			lineaF1 = br1.readLine();
			lineaF2 = br2.readLine();
			 
			/* Iteramos sobre las líneas de los dos ficheros hasta el final o encontrar algo distinto*/
			while ((lineaF1!=null) && (lineaF2!=null) && iguales) {
				if(!lineaF1.equals(lineaF2))
					iguales = false;
				
				 lineaF1 = br1.readLine();
				 lineaF2 = br2.readLine();
				
			} 
			
		} catch (IOException e) {
			System.out.println("Error fatal: No se pueden abrir los buffers de lectura");
		}
		
		finally {
			/* Cierre de buffers */
			try {
				br1.close();
				br2.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("Error fatal: No se pueden cerrar los buffers de lectura");
			}
			
		}
		
		return iguales && (lineaF1 == null) && (lineaF2 == null);
		
	}
	
	/*Método para buscar la línea donde se encuantra una palabra, la primera o la última encontrada */

	public int buscarPalabra(File fichero1, boolean primera, String str) throws FileNotFoundException
	{
		/*Apertura buffer lectura*/ 
		FileReader f1 = new FileReader(fichero1);
		BufferedReader br1 = new BufferedReader(f1);
		int line = 1;
		int resultado = -1;
		boolean encontrado = false;
		
		String strLine;
		
		/* Iteramos sobre el fichero buscando la palabra en cada línea, si
		 * primera está activado, retornará esa línea directamente.
		 */
		try {
			strLine = br1.readLine();			
			while(strLine != null && (!primera || !encontrado)) 
			{
				if(strLine.equals(str)) {
					encontrado=true;
					resultado = line;
				}
				line++;
				strLine = br1.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error fatal: No se pueden abrir los buffers de lectura");
		}
		finally {
			try {
				br1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error fatal: No se pueden cerrar los buffers de lectura");
			}
		}
		return resultado;
	}
	
	/*public void ordenarFichero (File origen, File destino, int tipo_orden)
	{
	    try {
			destino.createNewFile();
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(destino));
			BufferedReader reader = new BufferedReader(new FileReader(origen));
			
			String line = reader.readLine();
			ArrayList<String> normalOrder = new ArrayList<String>();
			while(line!=null) {
				if(tipo_orden == 1)
					writer.write(line+'\n');
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    finally {
	    	writer.close();
	    	reader.close();
	    }
	}*/
	
	
	
}

