package tiendavideojuegos.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.descripciones.ControladorDescripciones;
import tiendavideojuegos.descripciones.Descripcion;
import tiendavideojuegos.copias.*;



/**
 * Clase controladora que recibe los m�todos provenientes de la UI relacionados con la gesti�n de usuarios
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class ControladorUsuarios {
	/**
	 * Lista de los jugadores existentes en el sistema, indexada por su login o identificador de usuario (que por lo tanto debe ser �nico)
	 */
	private Map<String, Socio> listaSocios;
	
	private ControladorDescripciones cvj;

	/**
	 * Constructor que inicializa las colecciones
	 */
	public ControladorUsuarios(ControladorDescripciones cvj) {
		super();
		// Inicializa las colecciones
		listaSocios = new HashMap<String, Socio>();
		this.cvj = cvj;
	}


	/**
	 * M�todo que crea una nueva instancia de <code>Socio</code> y la colecciona, indexada por el <code>login</code>
	 * 
	 * @param login el login, o identificador �nico de socio
	 * @param clave la clave del socio (en claro)
	 * @param nombre el nombre del socio
	 * @param apellidos los apellidos del socio
	 * @param nacimiento la fecha de nacimiento del socio
	 * @param nif el NIF del socio
	 * @param movil el n�mero de tel�fono del socio
	 * @param correo la direcci�n de correo del socio
	 * @param metodo el m�todo de mensajer�a preferido por el socio
	 * @throws ExcepcionUsuario si ya existe un usuario con este <code>login</code>
	 */
	public void crearSocio(String login, String clave, String nombre, String apellidos, Date nacimiento, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Comprueba si ya existe un socio con este login
		if (!listaSocios.containsKey(login)) {
			// Si no existe, crea la instancia
			Socio nuevoSocio = new Socio(login, clave, nombre, apellidos, nacimiento, nif, movil, correo, metodo);
			// Y la colecciona
			listaSocios.put(login, nuevoSocio);
		} else {
			// Pero si ya exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.YA_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite mostrar toda la informaci�n de una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login login el login, o identificador �nico de usuario
	 * @return una cadena con toda la informaci�n del usuario buscado
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public String mostrarSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// Le pide al socio que muestre su informaci�n completa
			return esteSocio.verFichaCompleta();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que modifica una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador �nico de socio (no se puede modificar)
	 * @param clave la nueva clave del socio (en claro)
	 * @param nombre el nuevo nombre del socio
	 * @param apellidos los nuevos apellidos del usuario
	 * @param nacimiento la nueva fecha de nacimiento del socio
	 * @param nif el nuevo NIF del socio
	 * @param movil el nuevo n�mero de tel�fono del socio
	 * @param correo la nueva direcci�n de correo del socio
	 * @param metodo el nuevo m�todo de mensajer�a preferido por el socio
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void modificarSocio(String login, String clave, String nombre, String apellidos, Date nacimiento, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// Modifica uno a uno los otros atributos
			esteSocio.setClave(clave);
			esteSocio.setNombre(nombre);
			esteSocio.setApellidos(apellidos);
			esteSocio.setNacimiento(nacimiento);
			esteSocio.setNif(nif);
			esteSocio.setMovil(movil);
			esteSocio.setCorreo(correo);
			esteSocio.setMetodo(metodo);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite borrar una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador �nico de usuario
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void eliminarSocio(String login) throws ExcepcionUsuario {
		// Borra la instancia de la colecci�n
		Socio esteSocio = listaSocios.remove(login);
		// Si este socio es null es que no exist�a, as� que podemos informar de que no lo hemos borrado (porque no exist�a)
		if (esteSocio == null) {
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite obtener una lista de cadenas, cada una con informaci�n breve de cada instancia de <code>Socio</code> coleccionada por este
	 * controlador.
	 * 
	 * @return Una lista de cadenas
	 */
	public List<String> listarSocios() {
		// Inicializa la lista
		List<String> listado = new ArrayList<String>();

		// Recorre la colecci�n de socios
		for (Socio j : listaSocios.values()) {
			// A cada socio le pide informaci�n breve
			String ficha = j.verFichaBreve();
			// Y la a�ade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;
	}

	/**
	 * M�todo que permite realizar un ingreso de una <code>cantidad</code> de dinero en la tarjeta del socio identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador �nico de usuario
	 * @param cantidad la cantidad a ingresar en la tarjeta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws ExcepcionTarjeta si no se acepta el ingreso porque se supera el l�mite m�ximo por tarjeta
	 */
	public void realizarIngresoEnTarjetaSocio(String login, float cantidad) throws ExcepcionUsuario, ExcepcionTarjeta {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// As� que delegamos en �l que realice el ingreso
			esteSocio.realizarIngreso("Ingreso en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * M�todo que permite realizar un reintegro de una <code>cantidad</code> de dinero desde la tarjeta del jugador identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador �nico de usuario
	 * @param cantidad la cantidad a reintegrar desde la cuenta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws ExcepcionTarjeta si la tarjeta no tiene saldo suficiente para reintegrar dicha cantidad
	 */
	public void realizarReintegroDesdeTarjetaSocio(String login, float cantidad) throws ExcepcionUsuario, ExcepcionTarjeta {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// As� que delegamos en �l que realice el ingreso (ojo, este m�todo lanza una excepci�n)
			esteSocio.realizarReintegro("Reintegro en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * M�todo que permite ver el saldo en la tarjeta del socio identificado por el <code>login</code>
	 * @param login el login, o identificador �nico de usuario
	 * @return el saldo en la cuenta del socio
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public float verSaldoTarjetaSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// As� que delegamos en �l que nos informe de su saldo
			return esteSocio.verSaldo();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * M�todo que permite obtener un listado de los movimientos de tarjeta del socio identificador por el  <code>login</code>
	 * @param login el login, o identificador �nico de usuario
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public List<String> listarMovimientosTarjetaSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colecci�n
		Socio esteSocio = listaSocios.get(login);
		// Si este socio exist�a, no es null
		if (esteSocio != null) {
			// As� que delegamos en �l que nos informe de sus movimientos
			return esteSocio.listarMovimientosTarjeta();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/** Metodo que manda al socio crear el alquiler.
	 * @param login Login del socio al cual se le va a alquilar un videojuego
	 * @param IDvdj Identificador unico de la descripcion que se va a alquilar
	 * @param IDcopia Identificador unico de la copia que se va a alquilar
	 * @param diasAlquiler Tiempo del alquiler
	 * @param cvj 
	 */
	public void crearAlquiler(String login, String IDvdj, String IDcopia, int diasAlquiler, ControladorDescripciones cvj) {
		//Conseguimos la copia y el socio
		Copia estaCopia = cvj.dameCopia(IDvdj,IDcopia);
		Socio esteSocio = listaSocios.get(login);
		esteSocio.crearAlquiler(login, estaCopia, diasAlquiler);
	}

}
