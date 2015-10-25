/* Alvaro Baño Fos 2DAM */

package gestionficherosapp;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el n�mero de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila m�s
		}

		// dimensionar la matriz contenido seg�n los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {

		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}

	}

	
	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		
		//Comprobación de que el argumento pasado no sea nulo, esto es porque
		//no se comprueba en la librería antes de llamar al método.
		if(arg0 != null) {
			
			//Generación de la instancia file
			File file = new File(carpetaDeTrabajo,arg0);
			
			//que se pueda escribir -> lanzar� una excepci�n
			//que no exista -> lanzar� una excepci�n
			//crear la carpeta -> lanzar� una excepci�n
			
			
			//Checkeamos que podamos trabajar sobre la carpeta de trabajo
			this.checkCarpetaDeTrabajo();
		
			//Checkeamos que el directorio a generar no exista ya
			if(file.exists())
				throw new GestionFicherosException("La carpeta "+file.getName()+" ya existe");
			
		

			//Creamos la carpeta y checkeamos que se ha realizado correctamente
			if(!file.mkdir())
				throw new GestionFicherosException("No se ha podido generar la carpeta en " + carpetaDeTrabajo.getName());
	
			//Refrescamos para mostrar los cambios
			actualiza();
		}
	}

	/*Método que checkea la carpeta de trabajo, 
	 * comprueba que es un directorio existente y que se puede escribir sobre él*/
	private void checkCarpetaDeTrabajo() throws GestionFicherosException {
		
		if(!carpetaDeTrabajo.isDirectory() || !carpetaDeTrabajo.exists())
			throw new GestionFicherosException("El directorio padre "+carpetaDeTrabajo.getName()+" no existe");

		if(!carpetaDeTrabajo.canWrite())
			throw new GestionFicherosException("El directorio padre "+carpetaDeTrabajo.getName()+" no tiene permisos de escritura");
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		
		//Comprobación de que el argumento pasado no sea nulo, esto es porque
		//no se comprueba en la librería antes de llamar al método.
		if(arg0 != null) {

			//Checkeamos que podamos trabajar sobre la carpeta de trabajo
			this.checkCarpetaDeTrabajo();
			
			//Generación de la instancia file
			File file = new File(carpetaDeTrabajo, arg0);
			
			//Comprobamos que el fichero no exista ya
			if(file.exists())
				throw new GestionFicherosException("El fichero "+file.getName()+" ya existe");
			
			//Generamos el nuevo archivo
			try {
				if(!file.createNewFile())
					throw new GestionFicherosException("El fichero "+file.getName()+ " no ha podido crearse");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new GestionFicherosException("El fichero "+file.getName()+ " no ha podido crearse");
			}
	
			//Refrescamos para mostrar los cambios
			actualiza();
		}

		
		
	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		
		//Comprobación de que el argumento pasado no sea nulo, esto es porque
		//no se comprueba en la librería antes de llamar al método.
		this.checkCarpetaDeTrabajo();
		
		//Generación de la instancia file
		File file = new File(carpetaDeTrabajo, arg0);
		
		//Comprobamos que el fichero exista
		if(!file.exists())
			throw new GestionFicherosException("El fichero/directorio "+file.getName()+" no existe.");

		//Borramos y comprobamos el borrado
		if(!file.delete())
			throw new GestionFicherosException("El fichero/directorio "+file.getName()+" no ha podido ser eliminado.");
		
		//Refrescamos para mostrar los cambios
		actualiza();

	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignaci�n de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		if(!file.exists())
			throw new GestionFicherosException("El fichero/directorio "+file.getAbsolutePath()+" no se"
					+ " encuentra en el sistema");
		
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		if(!file.canRead())
			throw new GestionFicherosException("El fichero/directorio "+file.getAbsolutePath()+" no tiene"
					+ " permisos de lectura");	
					
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(arg0);
		strBuilder.append("\n");
		
		//Tipo de fichero (Archivo o Directorio)
		String tipo = "Desconocido";
		if(file.isDirectory())
			tipo = "Directorio";
		
		if(file.isFile())
			tipo = "Archivo";
		
		strBuilder.append("Tipo: ");
		strBuilder.append(tipo);
		
		//Ruta completa (canónica) al archivo o directorio
		strBuilder.append("\n");
		strBuilder.append("Ruta completa: ");
		//Manejador de excepción IO en caso de que el sistema no pueda recorrer la ruta completa
		try {
			strBuilder.append(file.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			strBuilder.append("Fallo al generar la ruta completa");
		}
		
		//Fecha de última modificación
		strBuilder.append("\n");
		strBuilder.append("Fecha de última modificación: ");
		//Conversión de long retornado por lastModified a un String de tipo fecha
		Date date = new Date(file.lastModified());
		Format format = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		strBuilder.append(format.format(date));
		
		//Fichero o directorio oculto
		strBuilder.append("\n");
		String oculto = "";
		if(file.isHidden())
			oculto = "Sí";
		else oculto = "No";
		strBuilder.append("Oculto: ");
		strBuilder.append(oculto);
		
		//En caso de archivo muestra el tamaño del mismo
		if(file.isFile()) {
			strBuilder.append("\n");
			strBuilder.append("Tamaño: ");
			strBuilder.append(this.convertLongToDottedNumberString(file.length(), Locale.ITALIAN) + " bytes");
		}
		
		//En caso de directorio muestra los items que contiene, el espacio libre,
		//el disponible y el total.
		if(file.isDirectory()) {
			strBuilder.append("\n");
			strBuilder.append("Items: ");
			strBuilder.append(file.list().length);
			strBuilder.append("\n");
			strBuilder.append("Espacio libre: ");
			strBuilder.append(this.convertLongToDottedNumberString(file.getFreeSpace(), Locale.ITALIAN) + " bytes");
			strBuilder.append("\n");
			strBuilder.append("Espacio disponible: ");
			strBuilder.append(this.convertLongToDottedNumberString(file.getUsableSpace(), Locale.ITALIAN) + " bytes");
			strBuilder.append("\n");
			strBuilder.append("Espacio total: ");
			strBuilder.append(this.convertLongToDottedNumberString(file.getTotalSpace(), Locale.ITALIAN) + " bytes");
		}
			
		//Retorno del método (String)
		return strBuilder.toString();
	}
	
	
	//Conversor de números long a string con comas y puntos para separar los millares y los decimales	
	protected String convertLongToDottedNumberString(Long number, Locale locale)
	{
		DecimalFormat df = new DecimalFormat("#,##0.00");
		df.setDecimalFormatSymbols(new DecimalFormatSymbols(locale));
		return df.format(new BigDecimal(number+"")); 

	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)
			throws GestionFicherosException {
		
		//Comprobación del argumento de entrada, podría ser null si el usuario cancela la entrada de datos
		if(arg1 != null) {
			
			//Comprobación de la carpeta de trabajo para poder escribir.
			this.checkCarpetaDeTrabajo();
			
			//Generamos las instancias de archivo para el original y el renombrado
			File file = new File(carpetaDeTrabajo, arg0);
			File fileDest = new File(carpetaDeTrabajo, arg1);
			
			//Comprobamos que el original existe y el renombrado no exista
			if(!file.exists())
				throw new GestionFicherosException("El fichero "+file.getName()+" no existe");
			
			if(fileDest.exists())
				throw new GestionFicherosException("El fichero "+fileDest.getName()+" ya existe");
			
			//Renombramos y comprobamos que renombra correctamente
			if(!file.renameTo(fileDest))
				throw new GestionFicherosException("El fichero "+file.getName()+" no ha podido ser renombrado");
	
			//Refrescamos para mostrar los cambios
			actualiza();
		}
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la direcci�n exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
