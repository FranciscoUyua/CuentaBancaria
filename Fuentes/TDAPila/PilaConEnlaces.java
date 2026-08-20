package TDAPila;

import Excepciones.EmptyStackException;

/**
 * Clase PilaconEnlaces que implementa la interface Stack
 * @param <E> Tipo de dato de los elementos a almacenar en la pila.
 */

	public class PilaConEnlaces<E> implements Stack<E> {
		protected Nodo<E> tope;
		protected int tamanio;
		
		/**
		 * Crea una nueva pila con enlaces la cual inicialmente se encuentra vacía, 
		 * la cantidad de elementos que puede tener es ilimitada
		 */
		public PilaConEnlaces() {
			tope = null;
			tamanio = 0;
		}
		
		@Override
		public boolean isEmpty() {
			return tamanio == 0;
		}
		
		@Override
		public int size() {
			return tamanio;
		}
		
		@Override
		public E top() throws EmptyStackException {
			if(tamanio == 0)
				throw new EmptyStackException("La pila está vacía");
			
			return tope.getElemento();
		}
		
		@Override
		public void push(E elem) {
			tope = new Nodo<E>(elem, tope);
			tamanio++;
		}
		
		@Override
		public E pop() throws EmptyStackException {
			if(tamanio == 0)
				throw new EmptyStackException("La pila está vacía");
			
			E elem = tope.getElemento();
			tope = tope.getSiguiente();
			tamanio--;
			return elem;
		}
	}


