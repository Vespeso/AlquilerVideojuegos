package tiendavideojuegos.descripciones;

import java.time.Year;
import java.util.ArrayList;
//import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiendavideojuegos.copias.Copia;
//import tiendavideojuegos.usuarios.Socio;


/**
 * Clase controladora que recibe los m�todos provenientes de la UI relacionados con la gesti�n de videojuegos
 * 
 * @author iss018
 */
public class ControladorDescripciones {

	/**
	 * Lista de las descripciones existentes en el sistema, indexada por su ID (que por lo tanto debe ser �nico)
	 */
	private Map<String, Descripcion> listaDescripciones;
	
	
	public  ControladorDescripciones() {
		super(); 
		// Inicializa las colecciones
		listaDescripciones = new HashMap<String, Descripcion>();
	}

		
	/** Metodo que permite crear una descripcion
	 * @param IDvdj Identificador unico del videojuego
	 * @param titulo Titulo del videojuego
	 * @param creador Creador del videojuego
	 * @param modeloConsola Modelo de consola en la cual se utiliza el videojuego
	 * @param a�oLanzamiento A�o que salio el videojuego
	 * @param genero Genero del videojuego
	 * @param PEGI Edad minima recomendada
	 * @param costeDiario Costo diario del videojuego
	 */
	public void crearDescripcionVideojuego(String IDvdj,String titulo,String creador,String modeloConsola,Year a�oLanzamiento,GeneroVideojuego genero,int PEGI,float costeDiario) {
		Descripcion nuevaDescripcion = new Descripcion(IDvdj, titulo, creador, modeloConsola, a�oLanzamiento, genero,PEGI,costeDiario);
						
		listaDescripciones.put(IDvdj,nuevaDescripcion);
	}

	/** Metodo que permite listar las descripciones
	 * @return Un listado con la informacion de las descripciones de los videojuegos existentes
	 */
	public List<String> listarDescripcionesVideojuego() {
		//Inicializ el listado
		List<String> listado = new ArrayList<String>();
		// Recorre la colecci�n de Dscpcin
		for (Descripcion j : listaDescripciones.values()) {
			// A cada dspcn le pide informaci�n breve
			String ficha = j.verDescripcionBreve();
			// Y la a�ade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;		
	}
	
	
	/** Metodo que permite obtener informacion sobre un determinado videojuego
	 * @param IDvdj Identificador unico del videojuego
	 * @return la informacion del videojuego pedido
	 */
	public String verDescripcionVideojuego(String IDvdj) {
		Descripcion estaDescripcion = listaDescripciones.get(IDvdj);//Consigue la descripcion en cuestion 
		return estaDescripcion.verDescripcionCompleta(); //y pide a la descripcion todos los datos para devolverselo al controlador
	}
	
	/** Metodo que permite modificar los datos de la descripcion del videojuego
	 * @param IDvdj Identificador unico del videojuego
	 * @param titulo Titulo del videojuego
	 * @param creador Creador del videojuego
	 * @param modeloConsola Modelo de consola en la cual se utiliza el videojuego
	 * @param a�oLanzamiento A�o que salio el videojuego
	 * @param genero Genero del videojuego
	 * @param PEGI Edad minima recomendada
	 * @param costeDiario Costo diario del videojuego
	 */
	public void modificarDescripcionVideojuego (String IDvdj,String titulo,String creador,String modeloConsola,Year a�oLanzamiento,GeneroVideojuego genero,int PEGI,float costeDiario){
		Descripcion estaDescripcion = listaDescripciones.get(IDvdj);
		estaDescripcion.setIDvdj(IDvdj);
		estaDescripcion.setTitulo(titulo);
		estaDescripcion.setCreador(creador);
		estaDescripcion.setModeloConsola(modeloConsola);
		estaDescripcion.setA�oLanzamiento(a�oLanzamiento);
		estaDescripcion.setGenero(genero);
		estaDescripcion.setPEGI(PEGI);
		estaDescripcion.setCosteDiario(costeDiario);
		
	}

	
	/** Metodo que permite eliminar una descripcion
	 * @param IDvdj Identificador unico del videojuego
	 */
	public void eliminarDescripcionVideojuego(String IDvdj) {
		listaDescripciones.remove(IDvdj);
	}

	/** Metodo que manda a la descripcion que se a�ada una copia
	 * @param IDvdj Identificador unico del videojuego
	 */
	public void crearCopiaVideojuego(String IDvdj) {
		Descripcion estaDescripcion = listaDescripciones.get(IDvdj);
		estaDescripcion.crearCopiaVideojuego(IDvdj,estaDescripcion);
	}

	/** Metodo que delega en la descripcion conseguir la informacion sobre una determinada copia
	 * @param IDvdj Identificador unico del videojuego
	 * @param IDcopia Identificador unico de la copia
	 * @return La informacion sobre una determinada copia
	 */
	public String verCopiaVideojuego(String IDvdj,String IDcopia) {
		Descripcion estaDescripcion = listaDescripciones.get(IDvdj);
		return estaDescripcion.verCopiaVideojuego(IDcopia);
	}

	/** Metodo que delega en la descripcion conseguir un listado con la informacion de las copias
	 * @param IDvdj Identificador unico del videojuego
	 * @return un listado con la informacion de las copias
	 */
	public List<String> listarCopiasVideojuego(String IDvdj) {
		Descripcion estaDescripcion = listaDescripciones.get(IDvdj);
		return estaDescripcion.listarCopiasVideojuegos(IDvdj);
	}

	/** Metodo que pide a la descripcion una copia
	 * @param IDvdj Identificador unico del videojuego
	 * @param IDcopia Identificador unico de la copia
	 * @return Devuelve la copia
	 */
	public Copia dameCopia(String IDvdj, String IDcopia) {
		Descripcion esta = listaDescripciones.get(IDvdj);
		//Aqui el debugger nos dice que "estaDescripcion" es null, y no entendemos el porque; dado que en anteriores metodos
		//hemos hecho lo mismo y responde bien el get, dandonos un valor no nulo.
		Copia estaCopia = esta.dameCopia(IDcopia);
		return estaCopia;
	}
	

}
