package TDALista;

import java.util.Iterator;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;

/**
 * Clase ListaDoblementeEnlazada que implementa a la interface PositionList
 *
 * @param <E> Tipo de dato de los elementos a almacenar en la lista.
 */

public class ListaDoblementeEnlazada<E>  implements PositionList<E>{
	protected DNodo<E> header;
	protected DNodo<E> trailer;
	protected int tamanio;
	
	/**
     * Crea una nueva lista doble enlazada
     */
	public ListaDoblementeEnlazada() {
		header = new DNodo<E>(null);
		trailer = new DNodo<E>(null);
		trailer.setPrevio(header);
		header.setSiguiente(trailer);
		tamanio = 0;
	}
	
	@Override
	public int size() {
		return tamanio;
	}

	@Override
	public boolean isEmpty() {
		return tamanio == 0;
	}

	@Override
	public Position<E> first() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("No hay elementos en la lista");
		
		return header.getSiguiente();
	}

	@Override
	public Position<E> last() throws EmptyListException {
		if (isEmpty())
			throw new EmptyListException("No hay elementos en la lista");
		
		return trailer.getPrevio();
	}

	@Override
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {
		DNodo<E> pos= checkPosition(p);
		
		if (pos.getSiguiente() == trailer)
			throw new BoundaryViolationException("Posicion invalida rabo");
		
		return pos.getSiguiente();
	}
		
	@Override
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {		
		DNodo<E> pos= checkPosition(p);
		
		if (pos.getPrevio() == header)
			throw new BoundaryViolationException("Posicion invalida cabeza");
		
		return pos.getPrevio();
	}

	@Override
	public void addFirst(E element) {
		DNodo<E> n = new DNodo<E>(element, header.getSiguiente(), header);
		header.getSiguiente().setPrevio(n);
		header.setSiguiente(n);	
		tamanio++;
	}

	@Override
	public void addLast(E element) {
		DNodo<E> n = new DNodo<E>(element, trailer, trailer.getPrevio());
		trailer.getPrevio().setSiguiente(n);
		trailer.setPrevio(n);
		tamanio++;
	}

	@Override
	public void addAfter(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> nuevo;
		DNodo<E> pos= checkPosition(p);
		
		if(pos == trailer)
			throw new InvalidPositionException("Posicion invalida para añadir");
		
		nuevo = new DNodo<E>(element);
		nuevo.setSiguiente(pos.getSiguiente());
		nuevo.setPrevio(pos);
		pos.getSiguiente().setPrevio(nuevo);
		pos.setSiguiente(nuevo);
		tamanio++;		
	}

	@Override
	public void addBefore(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> nuevo;
		DNodo<E> pos= checkPosition(p);
		
		if(pos == header)
			throw new InvalidPositionException("Posicion invalida para añadir");
		
		nuevo = new DNodo<E>(element);
		nuevo.setPrevio(pos.getPrevio());
		nuevo.setSiguiente(pos);
		pos.getPrevio().setSiguiente(nuevo);
		pos.setPrevio(nuevo);
		tamanio++;
	}
	
	@Override
	public E remove(Position<E> p) throws InvalidPositionException {
		E aux;
		DNodo<E> pos;
		
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		
		pos= checkPosition(p);
		aux=pos.element();
		pos.getPrevio().setSiguiente(pos.getSiguiente());
		pos.getSiguiente().setPrevio(pos.getPrevio());
		pos.setElemento(null);
		pos.setSiguiente(null);
		pos.setPrevio(null);
		tamanio--;
		
		return aux;
	}

	@Override
	public E set(Position<E> p, E element) throws InvalidPositionException {
		DNodo<E> pos;
		E aux;
		
		if(isEmpty())
			throw new InvalidPositionException("Lista vacia");
		
		pos= checkPosition(p);
		aux=pos.element();
		pos.setElemento(element);
		
		return aux;
	}
	
	@Override
	public Iterator<E> iterator() {
		return new ElementIterator<E>(this);
	}

	@Override
	public Iterable<Position<E>> positions(){
		 DNodo<E> aux;
	   	 PositionList<Position<E>> p = new ListaDoblementeEnlazada<Position<E>>();
	   	 
	   	 if(!isEmpty()) {
	   		 aux = header.getSiguiente();
	   		 
	   		 while(aux != trailer) {
	   			 p.addLast(aux);
	   			 aux = aux.getSiguiente();
	   		 }
	   	 }
	   	 
	   	 return p;
	 }	
	
	/**
	 * Revisa que la posición pasada por parámetro no sea inválida
	 * @param p Posición a revisar
	 * @return Posición pasada por parámetro
	 * @throws InvalidPositionException si la posicion es inválida o la posición previa o siguiente es inválida
	 */
	private DNodo<E> checkPosition( Position<E> p ) throws InvalidPositionException {
			try {
				if( p == null ) 
					throw new InvalidPositionException("Posición nula");
				
				if (p == header || p == trailer)
					throw new InvalidPositionException("p No es una posicion valida");	 
				
				return (DNodo<E>) p;
			} catch( ClassCastException e ) {
				throw new InvalidPositionException( "p no es un DNodo de lista" );
			}
	} 
}
