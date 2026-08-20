package TDALista;

import java.lang.*;

import java.util.*;

import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidPositionException;


/**
 * Modela un iterador de PositionList
 *
 * @param <E> Tipo de dato de las posiciones
 */

public class ElementIterator<E> implements Iterator<E> {
	protected PositionList<E> list;
	protected Position<E> cursor; 

	/**
	 * Inicializa un iterador a partir de una lista parametrizada
	 * @param l lista de elementos genéricos
	 */
	public ElementIterator (PositionList <E> l ) {
		try {
			list = l;

			if (list.isEmpty())
				cursor = null; 
			else 
				cursor = list.first();
		}
		catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}
	}


	/*
	 * Consulta si hay más elementos
	 * @return Si hay más elementos
	 */
	public boolean hasNext() {
		return cursor != null; 
	}
	
	/*
	 * Devuelve el el siguiente elemento
	 * @return El elemento actual del cursor al llamar al método 
	 */
	public E next () throws NoSuchElementException {
		if ( cursor == null )
			throw new NoSuchElementException ("Error: No hay siguiente");
		
		E toReturn = cursor.element();
		
		try {
			cursor = (cursor == list.last()) ? null : list.next(cursor);
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
		
		return toReturn;
	}

}
