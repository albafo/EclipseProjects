/* Ã�lvaro BaÃ±o Fos 2DAM */


package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class LibroController {
	
	private static final String USERS_DAI_ALMACEN_DAT = "/Users/Amparo/almacen.dat";

	/*
	 * Recupera un libro a partir de su titulo
	 */
	public Libro recuperarLibro(String tit)
	{
		Libro returnLibro = null;
		Almacen almacen = new Almacen();
		ArrayList <Libro> libros = almacen.recuperar(USERS_DAI_ALMACEN_DAT);
		Iterator<Libro> libroIterator = libros.iterator();
		while(libroIterator.hasNext())
		{
			Libro libro = libroIterator.next();
			if(libro.getTitulo().equals(tit)){
				returnLibro = libro;
			}
		}
		
		return returnLibro;
				
	}
	
	/*
	 * Muestra la info de un libro a partir de un objeto Libro
	 */
	public void mostrarInfoLibro(Libro libro)
	{
		
		System.out.println("TÃ­tulo del libro: "+libro.getTitulo());
		
		System.out.println("Autor del libro: "+libro.getAutor());
		
		System.out.println("AÃ±o de publicaciÃ³n: "+libro.getAnyoPublicacion());
		
		System.out.println("Editor: "+libro.getEditor());

		
		System.out.println("NÃºmero de pÃ¡ginas: "+libro.getNumPaginas());
		
		
	}
	
	/*
	 * Guarda un nuevo Libro en Almacen pidiendo los datos a travÃ©s del Scanner
	 */
	public void guardarLibro(Scanner sc, Almacen almacen)
	{
		
		Libro libro = new Libro();
		System.out.println("TÃ­tulo del libro:");
		String tit = sc.nextLine();
		libro.setTitulo(tit);
		System.out.println("Autor del libro:");
		String autor = sc.nextLine();
		libro.setAutor(autor);
		System.out.println("AÃ±o de publicaciÃ³n:");
		int anyo = sc.nextInt();
		sc.nextLine();
		libro.setAnyoPublicacion(anyo);
		System.out.println("Editor:");
		String editor = sc.nextLine();
		libro.setEditor(editor);
		System.out.println("NÃºmero de pÃ¡ginas:");
		int pags = sc.nextInt();
		sc.nextLine();
		libro.setNumPaginas(pags);
		almacen.guardar(libro, USERS_DAI_ALMACEN_DAT);
		

	}
	
	/*
	 * ModificaciÃ³n de los atributos titulo y autor de un libro a partir de su
	 * tÃ­tulo antiguo
	 */
	public void modificarLibro(String titulo, Scanner sc, Almacen almacen)
	{
		ArrayList<Libro> libros = almacen.recuperar(USERS_DAI_ALMACEN_DAT);
		Iterator<Libro> libroIterator = libros.iterator();
		while(libroIterator.hasNext())
		{
			Libro libro = libroIterator.next();
			if(libro.getTitulo().equals(titulo)){
				System.out.println("Nuevo tÃ­tulo del libro:");
				String tit = sc.nextLine();
				libro.setTitulo(tit);
				System.out.println("Nuevo autor del libro:");
				String autor = sc.nextLine();
				libro.setAutor(autor);
			}
			
		}
		
		almacen.guardar(libros, USERS_DAI_ALMACEN_DAT);
	}
}
