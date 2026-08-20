package TDALista;

/**
 * Clase DNodo que implementa a la interface Position
 *
 * @param <E> Tipo de dato del elemento del nodo.
 */

public class DNodo<E> implements Position<E>{
	private E elemento;
	private DNodo<E> siguiente;
	private DNodo<E> previo;

	/**
	 * Crea un nuevo nodo doblemente enlazado 
	 * @param elem Elemento del nodo
	 * @param sig Referencia al nodo siguiente
	 * @param prev Referencia al nodo previo
	 */
	public DNodo(E elem, DNodo<E> sig, DNodo<E> prev) {
		elemento = elem;
		siguiente = sig;
		previo = prev;	
	}	
	
	/**
	 * Crea un nuevo nodo doblemente enlazado con referencias al siguiente nodo y al previo nulas
	 * @param elem Elemento del nodo
	 */
	public DNodo(E elem) {
		this(elem, null, null);	
	}

	/**
	 * Establece el elemento pasado por parámetro en el nodo
	 * @param elem Elemento a establecer en el nodo
	 */
	public void setElemento(E elem) {
		elemento = elem;
	}
	
	/**
	 * Devuelve el nodo siguiente referenciado
	 * @return Nodo siguiente
	 */
	public DNodo<E> getSiguiente() {
		return siguiente;
	}

	/**
	 * Establece la referencia al nodo siguiente pasada por parámetro en el nodo
	 * @param sig Referencia al nodo siguiente a establecer en el nodo
	 */
	public void setSiguiente(DNodo<E> sig) {
		siguiente = sig;
	}

	/**
	 * Devuelve el nodo previo referenciado
	 * @return Nodo previo
	 */
	public DNodo<E> getPrevio() {
		return previo;
	}

	/**
	 * Establece la referencia al nodo previo pasada por parámetro en el nodo
	 * @param prev Referencia al nodo previo a establecer en el nodo
	 */
	public void setPrevio(DNodo<E> prev) {
		previo = prev;
	}

	@Override
	public E element() {
		return elemento;
	}

}