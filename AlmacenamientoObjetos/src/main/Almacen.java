/* Ã�lvaro BaÃ±o Fos 2DAM */


package main;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Almacen {
	
	/*
	 * MÃ©todo para guardar un objeto libro en el fichero de datos
	 */
	public void guardar(Libro l, String f)
	{
		ArrayList<Libro> libros = recuperar(f);
		libros.add(l);
		guardarListaLibros(libros, f);
	}

	
	/*
	 * MÃ©todo para guardar un array de objetos libro en el fichero de datos
	 */
	public void guardar(ArrayList<Libro> listaLibros, String f)
	{
		ArrayList <Libro> libros = this.recuperar(f);
		libros.addAll(listaLibros);
		guardarListaLibros(libros, f);

	}
	

	/*
	 * MÃ©todo genÃ©rico de la clase para guardar el listado de libros
	 */
	private void guardarListaLibros(ArrayList <Libro> l, String f) {
		ObjectOutputStream out = null;
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(l);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: No existe el fichero data");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Fallo al escribir fichero data");
		}
		finally 
		{
			intentarCerrar(out);
		}
	}
	
	/*
	 * MÃ©todo para recuperar el listado de libros desde data
	 */
	public ArrayList <Libro> recuperar(String f)
	{
		ArrayList <Libro> libros = new ArrayList<Libro>();
		ObjectInputStream in = null;
		try {
			File file = new File(f);
			
			if(file.exists() && file.canRead()) {
				in = new ObjectInputStream(new FileInputStream(f));
				libros = (ArrayList<Libro>) in.readObject();
			}
			if(!file.exists())
				file.createNewFile();
			
		} catch (FileNotFoundException e) {
			System.out.println("Error: No existe el fichero data");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Fallo al leer fichero data");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if(in != null)
				intentarCerrar(in);
		}
		return libros;
	}
	
	
	/*
	 * Cierre del stream 
	 */
	public void intentarCerrar(Closeable stream)
	{
		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Fallo al cerrar fichero data");
		}
	}
	
	
	

	
	
	
}
