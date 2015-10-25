/* Álvaro Baño Fos 2DAM */

package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Principal {

	private Scanner sc;
	private Almacen almacen;
	private String[] args;
	private LibroController libroController;

	
	public String[] getArgs() {
		return args;
	}
	public void setArgs(String[] args) {
		this.args = args;
	}

	/*
	 * Constructor que setea los atributos de la clase
	 */
	public Principal()
	{
		 sc = new Scanner(System.in);
		 almacen = new Almacen();
		 libroController = new LibroController();
	}
	public static void main(String[] args) {
		Principal principal = new Principal();
		principal.escribirMenu();
		int opcion = principal.esperarOpcion();
		principal.ejecutarOpcion(opcion);
		principal.setArgs(args);
	}
	
	
	public void escribirMenu()
	{
		System.out.println("==== Menú ====");
		System.out.println("1.- Guardar libro");
		System.out.println("2.- Recuperar libro");
		System.out.println("3.- Guardar listado de libros");
		System.out.println("4.- Modificar atributos libro");
		System.out.println("5.- Salir");
		System.out.println("==============");
		System.out.println();
	}
	
	public int esperarOpcion()
	{
		int opcion = this.sc.nextInt();
		this.sc.nextLine();
		return opcion;
	}
	
	public void ejecutarOpcion(int opcion)
	{
		switch (opcion) {
        case 1:  guardarLibro();
                 break;
        case 2:  recuperarLibro();
        		 break;
        case 3:  guardarListadoLibros();
        		  break;
        case 4:  modificarLibro();
        		  break;
        default: return;		  
        
		}
	}
	
	
	public void guardarLibro()
	{
		libroController.guardarLibro(sc, almacen);
		this.main(args);
	}
	
	public void recuperarLibro()
	{
		System.out.println("Título del libro:");
		String titulo = sc.nextLine();
		Libro libro = libroController.recuperarLibro(titulo);
		if(libro != null)
		{
			libroController.mostrarInfoLibro(libro);
		}
		this.main(args);

		
	}
	
	public void guardarListadoLibros()
	{
		System.out.println("Número de libros a guardar:");
		int numLibros = sc.nextInt();
		sc.nextLine();
		for(int i=0; i<numLibros; i++)
		{
			libroController.guardarLibro(sc, almacen);
		}
		this.main(args);

	}
	
	public void modificarLibro()
	{
		System.out.println("Título del libro:");
		String titulo = sc.nextLine();
		
		libroController.modificarLibro(titulo, sc, almacen);
		this.main(args);

	}
	
	
	
	
	
	
	

}
