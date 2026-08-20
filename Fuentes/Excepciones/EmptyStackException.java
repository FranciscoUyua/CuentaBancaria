package Excepciones;

/**
 * Modela la excepción ante una pila vacía
 * 
 */

public class EmptyStackException extends Exception{
	/**
	 * Inicializa la excepción indicando el origen del error
	 * @param msg Especifica información adicional acerca de la excepción
	 */
	public EmptyStackException(String msg) {
		super(msg);
	}
}



