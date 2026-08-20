package Excepciones;

/**
 * Modela la excepción ante una clave inválida
 *
 */

public class InvalidKeyException extends Exception {
	
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public InvalidKeyException(String msg) {
		super(msg);
	}
}