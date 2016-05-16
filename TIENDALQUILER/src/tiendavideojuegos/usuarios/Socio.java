package tiendavideojuegos.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiendavideojuegos.copias.Copia;
import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.tarjetas.Tarjeta;
import tiendavideojuegos.alquileres.*;



/**
 * Clase que representa a un Socio en la casa de apuestas. Deriva de <code>Usuario</code>, a�adiendo la posesi�n de una tarjeta y m�todos relacionados con las
 * apuestas.
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 * @param <listaAlquileresActivos>
 */
public class Socio extends Usuario {
	/** Tarjeta de dinero del socio ante la tienda de alquileres */
	private Tarjeta tarjeta;
	
	private ArrayList<Alquiler> listaAlquileresActivos;
	
	private ArrayList<Alquiler> listaAlquileresFinalizados;

	/**
	 * Constructor que recibe como par�metros todos los campos necesarios para crear una instancia de <code>Usuario</code>
	 * 
	 * @param login el login, o identificador �nico de usuario
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nacimiento la fecha de nacimiento del usuario
	 * @param nif el NIF del usuario
	 * @param movil el n�mero de tel�fono del usuario
	 * @param correo la direcci�n de correo del usuario
	 * @param metodo el m�todo de mensajer�a preferido por el usuario
	 */
	public Socio(String login, String clave, String nombre, String apellidos, Date nacimiento, String nif, String movil, String correo, MetodoMensajeria metodo) {
		// Invoca al constructor de la superclase
		super(login, clave, nombre, apellidos, nacimiento, nif, movil, correo, metodo);
		// Y crea una nueva tarjeta
		tarjeta = new Tarjeta();
		listaAlquileresActivos = new ArrayList<Alquiler>();
		listaAlquileresFinalizados = new ArrayList<Alquiler>();
	}

	/**
	 * M�todo que permite que se realice un ingreso en la tarjeta de este usuario
	 * @param concepto el concepto por el que se ingresa
	 * @param cantidad la cantidad que se ingresa
	 * @throws ExcepcionTarjeta si no se acepta el ingreso porque se supera el l�mite de saldo
	 */
	public void realizarIngreso(String concepto, float cantidad) throws ExcepcionTarjeta {
		// Pasa la petici�n a la tarjeta
		tarjeta.realizarIngreso(concepto, cantidad);
	}

	/**
	 * M�todo que permite que se realice un reintegro desde la tarjeta de este usuario
	 * @param concepto el concepto por el que se reintegra
	 * 
	 * @param cantidad la cantidad que se reintegra
	 * @throws ExcepcionTarjeta si el saldo de la tarjeta no es suficiente para reintegrar la <code>cantidad</code>
	 */
	public void realizarReintegro(String concepto, float cantidad) throws ExcepcionTarjeta {
		// Pasa la petici�n a la tarjeta
		tarjeta.realizarReintegro(concepto, cantidad);
	}

	/**
	 * M�todo que permite que un socio informe de su saldo
	 * @return el saldo en la tarjeta del socio
	 */
	public float verSaldo() {
		//Pasa la petici�n a la tarjeta
		return tarjeta.getSaldo();
	}

	/**
	 * M�todo que permite que un socio informe de los movimientos de su tarjeta
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 */
	public List<String> listarMovimientosTarjeta() {
		//Pasa la petici�n a la tarjeta
		return tarjeta.listarMovimientos();
	}
	
	/**M�tod que crea un alquiler
	 * @param login El login del socio al que se alquila
	 * @param c copia que se alquila
	 * @param diasAlquiler duracion en dias del alquiler
	 */
	public void crearAlquiler(String login, Copia c, int diasAlquiler) {
		Alquiler nuevoAlquiler =  new Alquiler(login, c, diasAlquiler);
		//Indexa el alquiler con un didentificado que se basa en el login del socio y el numero de alquiler activo que se corresponda en orden de llegadda
		listaAlquileresActivos.add(nuevoAlquiler);
		tarjeta.cobraAlquiler(nuevoAlquiler.calculaPrecioTotal(diasAlquiler));

	}

}