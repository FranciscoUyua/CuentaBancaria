package TDACola;

import Excepciones.EmptyQueueException;

/**
 * Clase ColaConArregloCircular que implementa la interface Queue
 * @param <E> Tipo de dato a almacenar en la cola.
 */

public class ColaConArregloCircular<E> implements Queue<E> {
	protected int f, r;
	protected E[] datos;
	
	/**
	 * Crea una nueva cola con arreglo circular vacía con una cantidad de elementos
	 * inicialmente definida por el parámetro, que luego se puede aumentar si es necesario
	 * @param max Tamaño máximo inicial del arreglo
	 */
	public ColaConArregloCircular(int max) {
		f = 0;
		r = 0;
		datos = (E[]) new Object[max];
	}
	
	/**
	 * Crea un cola con arreglo circular vacía de tamaño máximo 20 elementos inicialmente, 
	 * que luego puede aumentarse en caso de ser necesario
	 */
	public ColaConArregloCircular() {
		this(20);
	}
	
	@Override
	public boolean isEmpty() {
		return f == r;
	}
	
	@Override
	public int size() {
		return (datos.length - f + r) % datos.length;
	}
	
	@Override
	public E front() throws EmptyQueueException {
		if(f == r)
			throw new EmptyQueueException("La cola está vacía");
		
		return datos[f];
	}
	
	@Override
	public void enqueue(E element) {
		if(size() == datos.length - 1) 
			resize();
			
		datos[r] = element;
		r = (r + 1) % datos.length;
	}
	
	/**
	 * Agranda la cola con arreglo circular actual para poder insertar más elementos
	 */
	private void resize() {
		E [] aux;
		r = size();
		aux = (E[]) new Object[datos.length * 2];
		
		for (int i = 0; i < datos.length - 1 ; i++) {
			aux[i] = datos[f];
			f = (f + 1) % datos.length;
		}
		
		datos = aux;			
		f = 0;
	}
	
	@Override
	public E dequeue() throws EmptyQueueException {
		if(f == r)
			throw new EmptyQueueException("La cola está vacía");
		
		E temp = datos[f];
		datos[f] = null;
		f = (f + 1) % datos.length;
		return temp;
	}
}


