package Excepciones;

/**
 * Modela la excepción ante una cola con prioridad vacía
 *
 */

public class EmptyPriorityQueueException extends Exception {
	
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public EmptyPriorityQueueException(String msg) {
		super(msg);
	}
}