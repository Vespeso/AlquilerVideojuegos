package tiendavideojuegos.copias;

//import tiendavideojuegos.descripciones.Descripcion;
import tiendavideojuegos.copias.EstadoCopia;
import tiendavideojuegos.descripciones.*;

/** Clase que represnta los ejemplares presentes en una tienda de videojuegos
 * @author iss018
 *
 */
public class Copia  {

	private String IDcopia;

	private EstadoCopia estado;
	
	private Descripcion miDescripcion;

	/** Constructor de la copia
	 * @param IDcopia Identificador unico de la copia
	 * @param estado Estado en el que se encuentra la copia
	 * @param miDescripcion Descripcion que corresponde a la copia
	 */
	public Copia (String IDcopia, EstadoCopia estado, Descripcion miDescripcion){
		
		this.IDcopia = IDcopia;
		this.estado = estado;
		this.miDescripcion = miDescripcion;
		//Por definicion la copia a priori esta disponible
		estado = EstadoCopia.DISPONIBLE;		
	}

	/** Recupera el estado de la copia
	 * @param IDcopia Identificador unico de la copia
	 * @return devuelve en un String el estado de la copia
	 */
	public String dameEstado(String IDcopia) {
		return estado.toString();// devuelve el nombre del estado en modo string
	}
	
	
	/** Metodo que permite ver los datos de la copia
	 * @return manda los datos de la copia en un string
	 */
	public String verCopiaVideojuego() {
		return "Copia " + IDcopia + " en estado " + estado.toString();	
	}

	/** Metodo que cambia el estado de la copia a alquilado
	 * 
	 */
	public void cambiarEstadoCopia() {
		estado = EstadoCopia.ALQUILADO;		
	}

	/** Metodo que recupera el ID de una copia
	 * @return El id de la copia
	 */
	public String dameID() {
		
		return IDcopia;
	}

	/** Getter del parametro Descripcion
	 * @return la descripcion de la copia
	 */
	public Descripcion getMiDescripcion() {
		return miDescripcion;
	}

	/** Setter de la descripcion de la copia
	 * @param miDescripcion Ajusta la descripcion a la copia
	 */
	public void setMiDescripcion(Descripcion miDescripcion) {
		this.miDescripcion = miDescripcion;
	}

	/** Metodo que permite conseguir el precio diario de la copia a traves de su descripcion
	 * @return El precio diario de la copia
	 */
	public float damePrecioDiario() {
		
		return miDescripcion.getCosteDiario();
	}
}
