package tiendavideojuegos.tarjetas;

/**
 * Clase de excepci�n que se lanzar� cuando se intente realizar una operaci�n no permitida con tiendavideojuegos.tarjetas
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class ExcepcionTarjeta extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El concepto usado en la operaci�n que ha dado lugar a la excecpci�n */
	private String concepto;
	/** La causa de la excecpci�n */
	private CausaExcepcionTarjeta causa;

	/**
	 * Constructor que permite crear una excepci�n al operar sobre tiendavideojuegos.tarjetas
	 * 
	 * @param causa la causa de la excepci�n
	 * @param concepto el concepto usado en la operaci�n que ha causado la excepci�n
	 */
	public ExcepcionTarjeta(CausaExcepcionTarjeta causa, String concepto) {
		super();
		// Asigna los par�metros
		this.causa = causa;
		this.concepto = concepto;
	}

	/**
	 * M�todo que devuelve el concepto que dio lugar a esta excepci�n
	 * 
	 * @return el concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * M�todo que devuelve la causa que dio lugar a esta excepci�n
	 * 
	 * @return la causa
	 */
	public CausaExcepcionTarjeta getCausa() {
		return causa;
	}

}
