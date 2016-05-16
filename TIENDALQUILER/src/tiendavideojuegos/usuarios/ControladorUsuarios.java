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
 * Clase controladora que recibe los métodos provenientes de la UI relacionados con la gestión de usuarios
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class ControladorUsuarios {
	/**
	 * Lista de los jugadores existentes en el sistema, indexada por su login o identificador de usuario (que por lo tanto debe ser único)
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
	 * Método que crea una nueva instancia de <code>Socio</code> y la colecciona, indexada por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de socio
	 * @param clave la clave del socio (en claro)
	 * @param nombre el nombre del socio
	 * @param apellidos los apellidos del socio
	 * @param nacimiento la fecha de nacimiento del socio
	 * @param nif el NIF del socio
	 * @param movil el número de teléfono del socio
	 * @param correo la dirección de correo del socio
	 * @param metodo el método de mensajería preferido por el socio
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
			// Pero si ya existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.YA_EXISTE, login);
		}
	}

	/**
	 * Método que permite mostrar toda la información de una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login login el login, o identificador único de usuario
	 * @return una cadena con toda la información del usuario buscado
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public String mostrarSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
		if (esteSocio != null) {
			// Le pide al socio que muestre su información completa
			return esteSocio.verFichaCompleta();
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que modifica una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador único de socio (no se puede modificar)
	 * @param clave la nueva clave del socio (en claro)
	 * @param nombre el nuevo nombre del socio
	 * @param apellidos los nuevos apellidos del usuario
	 * @param nacimiento la nueva fecha de nacimiento del socio
	 * @param nif el nuevo NIF del socio
	 * @param movil el nuevo número de teléfono del socio
	 * @param correo la nueva dirección de correo del socio
	 * @param metodo el nuevo método de mensajería preferido por el socio
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void modificarSocio(String login, String clave, String nombre, String apellidos, Date nacimiento, String nif, String movil, String correo, MetodoMensajeria metodo)
			throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
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
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que permite borrar una instancia de <code>Socio</code> dada por un determinado <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @throws ExcepcionUsuario si no existe un usuario con este <code>login</code>
	 */
	public void eliminarSocio(String login) throws ExcepcionUsuario {
		// Borra la instancia de la colección
		Socio esteSocio = listaSocios.remove(login);
		// Si este socio es null es que no existía, así que podemos informar de que no lo hemos borrado (porque no existía)
		if (esteSocio == null) {
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que permite obtener una lista de cadenas, cada una con información breve de cada instancia de <code>Socio</code> coleccionada por este
	 * controlador.
	 * 
	 * @return Una lista de cadenas
	 */
	public List<String> listarSocios() {
		// Inicializa la lista
		List<String> listado = new ArrayList<String>();

		// Recorre la colección de socios
		for (Socio j : listaSocios.values()) {
			// A cada socio le pide información breve
			String ficha = j.verFichaBreve();
			// Y la añade al listado
			listado.add(ficha);
		}

		// Al terminar retorna el listado
		return listado;
	}

	/**
	 * Método que permite realizar un ingreso de una <code>cantidad</code> de dinero en la tarjeta del socio identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param cantidad la cantidad a ingresar en la tarjeta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws ExcepcionTarjeta si no se acepta el ingreso porque se supera el límite máximo por tarjeta
	 */
	public void realizarIngresoEnTarjetaSocio(String login, float cantidad) throws ExcepcionUsuario, ExcepcionTarjeta {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
		if (esteSocio != null) {
			// Así que delegamos en él que realice el ingreso
			esteSocio.realizarIngreso("Ingreso en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Método que permite realizar un reintegro de una <code>cantidad</code> de dinero desde la tarjeta del jugador identificado por el <code>login</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param cantidad la cantidad a reintegrar desde la cuenta (positiva)
	 * @throws ExcepcionUsuario si el usuario no existe
	 * @throws ExcepcionTarjeta si la tarjeta no tiene saldo suficiente para reintegrar dicha cantidad
	 */
	public void realizarReintegroDesdeTarjetaSocio(String login, float cantidad) throws ExcepcionUsuario, ExcepcionTarjeta {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
		if (esteSocio != null) {
			// Así que delegamos en él que realice el ingreso (ojo, este método lanza una excepción)
			esteSocio.realizarReintegro("Reintegro en efectivo por el usuario " + login, cantidad);
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * Método que permite ver el saldo en la tarjeta del socio identificado por el <code>login</code>
	 * @param login el login, o identificador único de usuario
	 * @return el saldo en la cuenta del socio
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public float verSaldoTarjetaSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
		if (esteSocio != null) {
			// Así que delegamos en él que nos informe de su saldo
			return esteSocio.verSaldo();
		} else {
			// Pero si no existía lanza una excepción
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}
	
	/**
	 * Método que permite obtener un listado de los movimientos de tarjeta del socio identificador por el  <code>login</code>
	 * @param login el login, o identificador único de usuario
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 * @throws ExcepcionUsuario si el usuario no existe
	 */
	public List<String> listarMovimientosTarjetaSocio(String login) throws ExcepcionUsuario {
		// Recupera la instancia de la colección
		Socio esteSocio = listaSocios.get(login);
		// Si este socio existía, no es null
		if (esteSocio != null) {
			// Así que delegamos en él que nos informe de sus movimientos
			return esteSocio.listarMovimientosTarjeta();
		} else {
			// Pero si no existía lanza una excepción
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
