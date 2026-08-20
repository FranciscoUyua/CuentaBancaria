package Excepciones;

/**
 * Modela la excepción ante recorridos que exceden los límites de la lista
 * @author Juan Cruz Rossi
 *
 */

public class BoundaryViolationException extends Exception {

	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}

