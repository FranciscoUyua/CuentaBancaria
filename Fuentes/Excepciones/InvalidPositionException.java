package Excepciones;

/**
 * Modela la excepción ante posiciones inválidas o lista vacía
 *
 */

public class InvalidPositionException extends Exception {
	
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}