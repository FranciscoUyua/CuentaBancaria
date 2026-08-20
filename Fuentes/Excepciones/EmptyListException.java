package Excepciones;

/**
 * Modela la excepción ante una lista vacía
 *
 */

public class EmptyListException extends Exception{
	
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public EmptyListException(String msg) {
		super(msg);
	}
}
