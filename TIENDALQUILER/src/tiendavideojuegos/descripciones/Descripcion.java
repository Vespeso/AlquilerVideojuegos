package tiendavideojuegos.descripciones;

import java.time.Year;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiendavideojuegos.copias.*;
//import tiendavideojuegos.usuarios.Socio;

/** Clase que represnta las descripciones presentes en una tienda de videojuegos
 * @author iss018
 *
 */
public class Descripcion {

	private	String IDvdj;
	
	private String titulo;

	private String creador;

	private String modeloConsola;

	private Year añoLanzamiento;

	private GeneroVideojuego genero;

	private int PEGI;

	private float costeDiario;
	
	private int numeroCopias;
	
	private int numeroCopiasDisponibles;

	/**
	 * Lista de las copias existentes de cada descripcion, indexada por su ID (que por lo tanto debe ser único)
	 */
	private Map<String, Copia> listaCopias;
	
	

	/**
	 * Constructor que crea una instancia de esta clase recibiendo como parámetros todos los atributos
	 * 
	 * @param IDvdj Identificador unico de la descripcion en cuestion
	 * @param titulo titulo del juego
	 * @param creador El que haya creado el juego
	 * @param modeloConsola La consola en la cual se rula el juego
	 * @param añoLanzamiento Año que salio al mercado
	 * @param genero Tipo de juego en el cual se enmarca
	 * @param PEGI limite de edad
	 * @param costeDiario Precio por alquiler diario
	 */
	
	public Descripcion(String IDvdj, String titulo, String creador, String modeloConsola, Year añoLanzamiento,
			GeneroVideojuego genero, int PEGI, float costeDiario) {
		super();
		
		this.IDvdj = IDvdj;
		this.titulo =  titulo;
		this.creador =  creador;
		this.modeloConsola= modeloConsola;
		this.añoLanzamiento = añoLanzamiento;
		this.genero = genero;
		this.PEGI = PEGI;
		this.costeDiario = costeDiario;
		numeroCopias = 0;
		numeroCopiasDisponibles = 0;
		listaCopias = new HashMap<String, Copia>();
	}
	
	/** Metod que compone una cadena con los campos relevantes y la retorna
	 * @return una cadena con los campos relevantes de la descripcion
	 */
	public String verDescripcionBreve(){
		return IDvdj + ":" + titulo + " para " + modeloConsola;
	}
	
	
	/** Metodo que permite ver todos los datos de una descripcion
	 * @return un string con los datos de una descripcion
	 */
	public String verDescripcionCompleta() {
		
		return "Identificador:" + IDvdj +
				"\nTítulo:" + titulo +
				"\nCreador:" + creador +
				"\nConsola:" + modeloConsola +
				"\nGénero:" + genero.toString() +
				"\nEdad mínima:" + PEGI +
				"\nPrecio diario:" + costeDiario +
				"\nCopias totales:" + listaCopias.size() + 
				"\nCopias disponibles:" + numeroCopiasDisponibles ;
	}

	

	/** Metodo que crea una copia nueva con un ID a partir del de la descripcion y del numero de copias existentes, el estado a priori siempre es deisponible
	 * @param IDvdj Identificador unico de la descrpcion del videojuego
	 */
	public void crearCopiaVideojuego(String IDvdj, Descripcion estaDescripcion) {
		Copia nuevaCopia =  new Copia ( (IDvdj + "-" + (listaCopias.size() + 1) ), EstadoCopia.DISPONIBLE, estaDescripcion);
		listaCopias.put((IDvdj + "-" + (listaCopias.size() + 1) ), nuevaCopia);
	}
	
	
	/** Metodo que permite ver los datos de una copia del videojuego
	 * @param IDcopia Identificador unico de la copia
	 * @return Un string los datos de una copia del videojuego
	 */
	public String verCopiaVideojuego(String IDcopia) {
		Copia estaCopia = listaCopias.get(IDcopia);
		return "Copia " + IDcopia + " en estado " + estaCopia.dameEstado(IDcopia);	
	}

	/** Metodo que permite ver un listado con los datos de las copias
	 * @param IDvdj Identificador unico de la descrpcion del videojuego
	 * @return un listado con los datos de las copias
	 */
	public List<String> listarCopiasVideojuegos(String IDvdj) {
		// Inicializa la lista
		List<String> listado = new ArrayList<String>();
	
		// Recorre la colección de copias
		for (Copia c : listaCopias.values()) {
			// A cada copia le pide la informacion
			String ficha = c.verCopiaVideojuego();
			// Y la añade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;
		
	}
	
	/** Metodo que permite conseguir la copia
	 * @param IDcopia Identificador unico de la copia
	 * @return la copia pedida
	 */
	public Copia dameCopia(String IDcopia) {
		Copia estaCopia = listaCopias.get(IDcopia);
		return estaCopia;
	}

	
	public String getIDvdj() {
		return IDvdj;
	}

	public void setIDvdj(String iDvdj) {
		IDvdj = iDvdj;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCreador() {
		return creador;
	}

	public void setCreador(String creador) {
		this.creador = creador;
	}

	public String getModeloConsola() {
		return modeloConsola;
	}

	public void setModeloConsola(String modeloConsola) {
		this.modeloConsola = modeloConsola;
	}

	public Year getAñoLanzamiento() {
		return añoLanzamiento;
	}

	public void setAñoLanzamiento(Year añoLanzamiento) {
		this.añoLanzamiento = añoLanzamiento;
	}

	public GeneroVideojuego getGenero() {
		return genero;
	}

	public void setGenero(GeneroVideojuego genero2) {
		this.genero = genero2;
	}

	public int getPEGI() {
		return PEGI;
	}

	public void setPEGI(int pEGI) {
		PEGI = pEGI;
	}

	public float getCosteDiario() {
		return costeDiario;
	}

	public void setCosteDiario(float costeDiario) {
		this.costeDiario = costeDiario;
	}

}
