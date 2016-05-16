package tiendavideojuegos.alquileres;

import java.util.HashMap;
import java.util.Map;

import tiendavideojuegos.copias.*;
import tiendavideojuegos.usuarios.*;
import tiendavideojuegos.descripciones.*;

/** Clase que representa a un alquiler en la tienda de videojuegos
 * @author iss018
 *
 */
public class Alquiler {

	private float precioTotal;

	private String loginAlquilador;

	private Copia copiaAlquilada;
	
	private int diasAlquiler;

	/**Contructor que permite crear un alquiler
	 * @param loginAlquilador login del socio que alquila la copia
	 * @param copiaAlquilada copia que se alquila
	 * @param diasAlquiler tiempo que dura el alquiler
	 */
	public Alquiler (String loginAlquilador, Copia copiaAlquilada, int diasAlquiler){
		super();
		precioTotal = 0;
		this.loginAlquilador = loginAlquilador;
		this.copiaAlquilada = copiaAlquilada;
		this.diasAlquiler = diasAlquiler;
		
		copiaAlquilada.cambiarEstadoCopia();
	}

	/**Metodo que permite calcular el importe total
	 * @param diasAlquiler tiempo que dura el alquiler
	 * @return el precio total del alquiler
	 */
	public float calculaPrecioTotal(int diasAlquiler) {
		return precioTotal = diasAlquiler * copiaAlquilada.damePrecioDiario();
		
	}


	

}
