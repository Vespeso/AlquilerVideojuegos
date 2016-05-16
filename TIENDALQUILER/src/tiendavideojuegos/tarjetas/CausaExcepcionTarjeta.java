package tiendavideojuegos.tarjetas;

/**
 * Tipo enumerado que se puede usar para especificar las causas de la excepci�n
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public enum CausaExcepcionTarjeta {
	/** No tiene saldo suficiente para una operaci�n de reintegro */
	SALDO_INSUFICIENTE,
	/** Un ingreso dar�a lugar a un saldo de m�s de 100 euros */
	SALDO_EXCESIVO
}