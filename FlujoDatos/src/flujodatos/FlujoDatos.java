package flujodatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import funciones.Funciones;

public class FlujoDatos {

	
	public static void main(String[] args) {
		obtenerFicheros();
	}
	
	public static void obtenerFicheros() {
		
		Funciones funciones = new Funciones();
		System.out.println("----------------------");
		System.out.println("Comparador de ficheros:");
		System.out.println("----------------------");

		System.out.println("Ruta fichero 1:");
		Scanner scanner = new Scanner(System.in);
		String ruta1 = scanner.nextLine();
		System.out.println("Ruta fichero 2:");
		scanner = new Scanner(System.in);
		String ruta2 = scanner.nextLine();
		File file1 = new File(ruta1);
		File file2 = new File(ruta2);
		
		try {
			if(funciones.compararContenido(file1, file2))
				System.out.println("Ficheros iguales!");
			else System.out.println("Ficheros distintos!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Error al encontrar los ficheros.");
		}


	}
	
	public static void buscarPalabra()
	{
		Funciones funciones = new Funciones();
		System.out.println("----------------------");
		System.out.println("Buscador de palabra:");
		System.out.println("----------------------");
		System.out.println("Ruta fichero:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		System.out.println("Palabra a buscar:");
		scanner = new Scanner(System.in);
		String palabra = scanner.nextLine();
		File file = new File(ruta);
		try {
			int linea;
			linea=funciones.buscarPalabra(file, true, palabra);
			if(linea!=-1)
				System.out.println("Palabra encontrada en línea: "+linea);
			else System.out.println("Palabra no encontrada");


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Error al encontrar los ficheros.");
		}
	}

}
