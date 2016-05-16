package tiendavideojuegos.tarjetas;

import java.util.ArrayList;
import java.util.List;



/**
 * Clase que representa una tarjeta de dinero mantenida en la aplicaci�n. Tiene un saldo y una lista de movimientos.
 * 
 * @author Eduardo G�mez S�nchez, ETSIT UVa.
 */
public class Tarjeta {
	/** El l�mite superior al saldo de la tarjeta */
	private static final float SALDO_MAXIMO=100;
	/** El saldo de la tarjeta */
	private float saldo;
	/** La lista de movimientos de la tarjeta */
	private List<Movimiento> listaMovimientos;

	/**
	 * Constructor que permite crear una tarjeta nueva, por lo que su saldo inicial ser� 0
	 */
	public Tarjeta() {
		super();
		// Inicializa el saldo
		saldo = 0;
		// Inicializa la lista de movimientos como un ArrayList vac�o
		listaMovimientos = new ArrayList<Movimiento>();
	}

	
	/**
	 * M�todo que permite recuperar el saldo de una tarjeta
	 * @return el saldo
	 */
	public float getSaldo() {
		return saldo;
	}


	/**
	 * M�todo que permite realizar un ingreso en una tarjeta
	 * 
	 * @param concepto el concepto por el que se ingresa
	 * @param cantidad la cantidad (positiva) que se ingresa
	 * @throws ExcepcionTarjeta si con el nuevo ingreso el saldo queda por encima del m�ximo fijado para una tarjeta
	 */
	public void realizarIngreso(String concepto, float cantidad) throws ExcepcionTarjeta {
		//Comprueba que el saldo no quedase por encima del m�ximo
		if (saldo+cantidad <= SALDO_MAXIMO) {
			// Crea el movimiento
			Movimiento m = new Movimiento(concepto, cantidad);
			// Lo a�ade a la lista de movimientos
			listaMovimientos.add(m);
			// Actualiza el saldo
			saldo += cantidad;
		} else {
			// Si el saldo resultante es excesivo, lanza una excepci�n
			throw new ExcepcionTarjeta(CausaExcepcionTarjeta.SALDO_EXCESIVO, concepto);
		}
		
	}

	/**
	 * M�todo que permite realizar un reintegro desde una tarjeta
	 * 
	 * @param concepto el concepto por el que se reintegra
	 * @param cantidad la cantidad (positiva) que se reintegra
	 * @throws ExcepcionTarjeta si la <code>cantidad</code> es mayor que el saldo de la tarjeta
	 */
	public void realizarReintegro(String concepto, float cantidad) throws ExcepcionTarjeta {
		// Comprueba si hay saldo suficiente
		if (saldo >= cantidad) {
			// Crea el movimiento, cambiando el signo a la cantidad
			Movimiento m = new Movimiento(concepto, -cantidad);
			// Lo a�ade a la lista de movimientos
			listaMovimientos.add(m);
			// Actualiza el saldo
			saldo -= cantidad;
		} else {
			// Si el saldo no era suficiente, lanza una excepci�n
			throw new ExcepcionTarjeta(CausaExcepcionTarjeta.SALDO_INSUFICIENTE, concepto);
		}
	}


	/**
	 * M�todo que permite a una tarjeta entregar un listado de los movimientos
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 */
	public List<String> listarMovimientos() {
		//Inicializa el listado
		List<String> listado = new ArrayList<String>();
		
		//Recorre la lista de movimientos
		for(Movimiento m : listaMovimientos) {
			//A cada movimiento le pide que informe sobre �l, y el resultado lo a�ade al listado
			listado.add(m.verFicha());
		}

		//Al terminar retorna el listado
		return listado;
	}


	/**Metodo que crea el Movimiento registrando el alquiler y que a su vez calcula el saldo nuevo de la tarjeta
	 * @param precioTotal Precio integro del alquiler
	 */
	public void cobraAlquiler(float precioTotal) {
		Movimiento m = new Movimiento("Alquiler", precioTotal);
		listaMovimientos.add(m);
		saldo = saldo - precioTotal;
	}

}
