package TDAPila;

/**
 * Clase Nodo
 * @param <E> Tipo de dato del nodo.
 */

public class Nodo<E> {
	private E elemento;
	private Nodo<E> siguiente;
	
	/**
	 * Crea un nuevo nodo
	 * @param item Elemento del nodo
	 * @param sig Referencia al nodo siguiente
	 */
	public Nodo(E item, Nodo<E> sig) {
		elemento = item; 
		siguiente = sig;
	}
	
	/**
	 * Crea un nuevo nodo con referencia al nodo siguiente nula
	 * @param item Elemento del nodo
	 */
	public Nodo(E item) {
		this(item, null);
	}
	
	/**
	 * Establece el elemento del nodo
	 * @param item Elemento a establecer en el nodo
	 */
	public void setElemento(E item) {
		elemento = item;
	}
	
	/**
	 * Establece la referencia al nodo siguiente
	 * @param sig Referencia al nodo siguiente a establecer 
	 */
	public void setSiguiente(Nodo<E> sig) {
		siguiente = sig;
	}
	
	/**
	 * Devuelve el elemento del nodo
	 * @return Elemento del nodo
	 */
	public E getElemento() {
		return elemento;
	}
	
	/**
	 * Devuelve el nodo siguiente referenciado
	 * @return Nodo siguiente
	 */
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
}

