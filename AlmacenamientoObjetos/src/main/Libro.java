/* Álvaro Baño Fos 2DAM */


package main;

import java.io.Serializable;

/*
 * Objecto Libro con getters y setters
 */
public class Libro implements Serializable{
	private String titulo, autor, editor;
	private int anyoPublicacion, numPaginas;
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int getAnyoPublicacion() {
		return anyoPublicacion;
	}
	public void setAnyoPublicacion(int anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}
	public int getNumPaginas() {
		return numPaginas;
	}
	public void setNumPaginas(int numPaginas) {
		this.numPaginas = numPaginas;
	}
	
	
}
