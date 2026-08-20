package Excepciones;

public class InvalidEntryException extends Exception {
	
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public InvalidEntryException(String msg) {
		super(msg);
	}
}