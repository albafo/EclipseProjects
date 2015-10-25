/*Alvaro Baño Fos 2DAM */

package streambytes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StreamBytes {
	
	public static final int READ_MODE = 0;
	public static final int WRITE_MODE = 1;
	private FileInputStream fileInput = null;
	private FileOutputStream fileOutput = null;
	
	
	/*Método que abre un buffer de entrada y otro de salida para copiar byte a byte sus contenidos*/
	public void copiaBytes() throws IOException {
		BufferedInputStream bufInput = new BufferedInputStream(this.fileInput);
		BufferedOutputStream bufOutput = new BufferedOutputStream(this.fileOutput);

		int dataByte;
		int bytes = 0;
		
		while((dataByte = bufInput.read()) != -1) {
			bufOutput.write(dataByte);
			bytes++;
		}
		
		
		bufInput.close();
		bufOutput.close();
		this.fileInput.close();
		this.fileOutput.close();
	}
	
	/*Fichero de comprobación de los estados de fichero de lectura o escriura para realizar
	 * dichas acciones. Una vez comprobado se setea los Stream de entrada y salida.
	 * */
	public boolean abrirFichero(String path, int mode) throws IOException {
		boolean result = false;
		File file = new File(path);
		if(mode == this.READ_MODE) {
			if(file.exists() && file.canRead()) {
				
				try {
					this.fileInput = new FileInputStream(file);
					result = true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Fichero no encontrado");
					this.fileInput.close();
				}
				
			}
			else System.out.println("Fichero no existente o sin permisos de escritura");

		}
		if(mode == this.WRITE_MODE)
			if(file.getParentFile().isDirectory() && file.getParentFile().canWrite()) {
				try {
					this.fileOutput = new FileOutputStream(file);
					result = true;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					
					this.fileInput.close();
					
				}
			}
		
			else System.out.println("El directorio padre no tiene permisos de escritura");

		
		return result;
				
	}

	/*
	 * Main de la aplicación que abre escaner de consola y lee línea a línea los paths del
	 * archivo a copiar y la copia
	 * 
	 */
	public static void main(String[] args) {
		
		StreamBytes streambytes = new StreamBytes();
		System.out.println("----------------------");
		System.out.println("Copia ficheros");
		System.out.println("----------------------");

		System.out.println("Ruta fichero a copiar:");
		Scanner scanner = new Scanner(System.in);
		String ruta1 = scanner.nextLine();
		System.out.println("Ruta fichero a pegar:");
		scanner = new Scanner(System.in);
		String ruta2 = scanner.nextLine();
		try {
			
			if(!streambytes.abrirFichero(ruta1, StreamBytes.READ_MODE))
				streambytes.main(args);
			
			if(!streambytes.abrirFichero(ruta2, StreamBytes.WRITE_MODE))
				streambytes.main(args);
			
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			streambytes.main(args);
		}
		
		
		
		
		try {
			streambytes.copiaBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo al copiar ficheros.");
			streambytes.main(args);

		}
		
	}
}
