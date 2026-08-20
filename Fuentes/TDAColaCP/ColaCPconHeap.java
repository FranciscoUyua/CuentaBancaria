package TDAColaCP;

import java.util.Comparator;

import Entrada.Entrada;
import Entrada.Entry;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.InvalidKeyException;

/**
 * Clase ColaCPconHeap que implementa la interfaz PriorityQueue
 *
 * @param <K> Tipo de dato de las claves de las entradas a almacenar en la cola
 *            con prioridad
 * @param <V> Tipo de dato de los valores de las entradas a almacenar en la cola
 *            con prioridad
 */
public class ColaCPconHeap<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	protected Entrada<K, V>[] elems;
	protected int size;
	protected Comparator<K> comp;

	/**
	 * Crea una cola con prioridad con heap con un comparador y tamaño inicial
	 * parametrizados
	 * 
	 * @param comp     Comparador de claves de las entradas
	 * @param maxElems Tamaño inicial de la cola con prioridad
	 */
	public ColaCPconHeap(Comparator<K> comp, int maxElems) {
		size = 0;
		elems = (Entrada<K, V>[]) new Entrada[maxElems];
		this.comp = comp;
	}

	/**
	 * Crea una cola con prioridad con heap con un comparador parametrizado y un
	 * tamaño inicial predeterminado
	 * 
	 * @param comp Comparador de claves de las entradas
	 */
	public ColaCPconHeap(Comparator<K> comp) {
		size = 0;
		elems = (Entrada<K, V>[]) new Entrada[20];
		this.comp = comp;
	}
	

	/**
	 * Retorna el tamaño de la cola
	 * 
	 * @return int tamaño
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (isEmpty())
			throw new EmptyPriorityQueueException("Está vacía");

		return elems[1];
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		if (key == null)
			throw new InvalidKeyException("La clave es nula");

		Entrada<K, V> entrada = new Entrada<K, V>(key, value);

		if (size == elems.length - 1)
			resize();

		elems[++size] = entrada;
		int i = size;
		boolean seguir = true;

		while (i > 1 && seguir) {
			Entrada<K, V> actual = elems[i];
			Entrada<K, V> padre = elems[i / 2];

			if (comp.compare(actual.getKey(), padre.getKey()) < 0) {
				Entrada<K, V> aux = elems[i];
				elems[i] = elems[i / 2];
				elems[i / 2] = aux;
				i /= 2;
			} else
				seguir = false;

		}

		return entrada;
	}

	/**
	 * Agranda la cola con prioridad con heap actual para poder insertar más
	 * elementos
	 */
	private void resize() {
		Entrada<K, V>[] viejo = elems;
		elems = (Entrada<K, V>[]) new Entrada[viejo.length * 2];

		for (int i = 0; i < viejo.length; i++) {
			elems[i] = viejo[i];
		}
	}

	@Override
	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		Entry<K, V> ent;
		int i, hi, hd, hMin;
		boolean cortar;

		if (size == 0)
			throw new EmptyPriorityQueueException("Cola vacia");

		ent = min();

		if (size == 1) {
			elems[1] = null;
			size = 0;
		} else {
			elems[1] = elems[size];
			elems[size] = null;
			size--;
			i = 1;
			cortar = false;

			while (!cortar) {
				hi = i * 2;
				hd = i * 2 + 1;

				if (!(hi < elems.length) || !(hi <= size))
					cortar = true;
				else {
					if (hd < elems.length && hd <= size)
						if (comp.compare(elems[hi].getKey(), elems[hd].getKey()) < 0)
							hMin = hi;
						else
							hMin = hd;
					else
						hMin = hi;

					if (comp.compare(elems[i].getKey(), elems[hMin].getKey()) > 0) {
						Entrada<K, V> aux = elems[i];
						elems[i] = elems[hMin];
						elems[hMin] = aux;
						i = hMin;
					} else
						cortar = true;
				}
			}
		}

		return ent;
	}
}
