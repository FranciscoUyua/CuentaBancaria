package TDADiccionario;

import Entrada.Entrada;
import Entrada.Entry;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;
import Excepciones.InvalidPositionException;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;

/**
 * Clase DiccionarioHashAbierto que implementa a la interface Dictionary
 * 
 * @param <K> Tipo de dato de las claves de las entradas a almacenar en el diccionario
 * @param <V> Tipo de dato de los valores de las entradas a almacenar en el diccionario
 */
public class DiccionarioHashAbierto<K, V> implements Dictionary<K, V> {
	protected PositionList<Entry<K, V>>[] arreglo;
	protected int size;
	protected int tamanioInicial = 13;

	/**
	 * Crea un nuevo diccionario con hash abierto representado por un arreglo de un tamaño inicial predeterminado, preferentemente un valor primo, que luego
	 * puede ser redimensionado en caso de ser necesario. Inicializa todas las componentes del arreglo con una lista de entradas vacía
	 */
	public DiccionarioHashAbierto() {
		arreglo = (PositionList<Entry<K, V>>[]) new PositionList[tamanioInicial];

		for (int i = 0; i < tamanioInicial; i++)
			arreglo[i] = new ListaDoblementeEnlazada<Entry<K, V>>();

		size = 0;
	}

	/**
	 * Crea un nuevo diccionario con hash abierto representado por un arreglo de un tamaño inicial parametrizado, preferentemente un valor primo, que luego
	 * puede ser redimensionado en caso de ser necesario. Inicializa todas las componentes del arreglo con una lista de entradas vacía
	 */
	public DiccionarioHashAbierto(int buckets) {
		arreglo = (PositionList<Entry<K, V>>[]) new PositionList[buckets];

		for (int i = 0; i < buckets; i++)
			arreglo[i] = new ListaDoblementeEnlazada<Entry<K, V>>();
	}

	/**
	 * Calcula el valor hash de la clave pasada por páremtro
	 * 
	 * @param clave Clave de la entrada a calcular su valor hash
	 * @return Valor hash de la clave
	 */
	protected int hash(K clave) {
		return Math.abs(clave.hashCode() % arreglo.length);
	}

	@Override
	public Entry<K, V> insert(K clave, V valor) throws InvalidKeyException {
		if (clave == null)
			throw new InvalidKeyException("La clave es nula");

		PositionList<Entry<K, V>> pl = null;
		Entry<K, V> p = null;

		if ((size() / arreglo.length) >= 0.9f)
			resize();
		
		pl = arreglo[hash(clave)];
		p = new Entrada<K, V>(clave, valor);
		pl.addLast(p);
		size++;

		return p;
	}

	/**
	 * Redimensiona el mapeo en caso de exceder su factor de carga con un nuevo tamaño igual al primo siguiente al doble de su tamaño actual
	 */
	private void resize() {
		PositionList<Entry<K, V>>[] viejo = arreglo;
		int primo = proximoPrimo(arreglo.length * 2);
		arreglo = new PositionList[primo];
		PositionList<Entry<K, V>> p;
		int hash;
		Position<Entry<K, V>> pos;

		for (int i = 0; i < arreglo.length; i++)
			arreglo[i] = new ListaDoblementeEnlazada<Entry<K, V>>();

		try {
			for (int i = 0; i < viejo.length; i++) {
				p = viejo[i];

				if (!p.isEmpty()) {
					pos = p.first();

					while (pos != null) {
						hash = hash(pos.element().getKey());
						arreglo[hash].addLast(pos.element());

						if (pos != p.last())
							pos = p.next(pos);
						else
							pos = null;
					}
				}
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Busca el proximo primo del entero pasado por parámetro
	 * 
	 * @param n Entero del cual se busca su próximo número primo
	 * @return Número primo siguiente al número pasado por parámetro
	 */
	private int proximoPrimo(int n) {
		int proxP = n;
		boolean primo = true;

		for (int i = 2; i < proxP; i++)
			if (proxP % i == 0)
				primo = false;

		if (!primo)
			proxP = proximoPrimo(proxP + 1);

		return proxP;
	}

	@Override
	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		if (e == null)
			throw new InvalidEntryException("La entrada parametrizada es nula");

		if (e.getKey() == null || e.getValue() == null)
			throw new InvalidEntryException("La clave y/o valor de la entrada parametrizada es/son nula/s");

		Entry<K, V> removida = null;
		PositionList<Entry<K, V>> pl = arreglo[hash(e.getKey())];
		Position<Entry<K, V>> pos;
		boolean encontrado = false;

		try {
			if (!pl.isEmpty()) {
				pos = pl.first();

				while (pos != null && !encontrado) {
					if (pos.element().getKey().equals(e.getKey()))
						if (pos.element().getValue().equals(e.getValue())) {
							removida = pl.remove(pos);
							encontrado = true;
							size--;
						}

					if(!encontrado) {
						if (pos != pl.last())
							pos = pl.next(pos);
						else
							pos = null;
					}
				}
			}

			if (removida == null)
				throw new InvalidEntryException("La entrada no se encuentra en el diccionario");
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException exc) {
			exc.printStackTrace();
		}

		return removida;
	}

	@Override
	public Entry<K, V> find(K clave) throws InvalidKeyException {
		if (clave == null)
			throw new InvalidKeyException("La clave es nula");

		Entry<K, V> e = null;
		PositionList<Entry<K, V>> pl = arreglo[hash(clave)];
		Position<Entry<K, V>> p = null;
		boolean encontrado = false;

		try {
			if (!pl.isEmpty())
				p = pl.first();

			while (p != null && !encontrado) {
				if (p.element().getKey().equals(clave)) {
					e = p.element();
					encontrado = true;
				}

				if (p != pl.last())
					p = pl.next(p);
				else
					p = null;
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException t) {
			t.printStackTrace();
		}

		return e;
	}

	@Override
	public Iterable<Entry<K, V>> findAll(K clave) throws InvalidKeyException {
		if (clave == null)
			throw new InvalidKeyException("La clave es nula");

		PositionList<Entry<K, V>> pF = new ListaDoblementeEnlazada();
		PositionList<Entry<K, V>> pl = arreglo[hash(clave)];
		Position<Entry<K, V>> p;

		try {
			if (!pl.isEmpty()) {
				p = pl.first();

				while (p != null) {
					if (p.element().getKey().equals(clave))
						pF.addLast(p.element());

					if (p != pl.last())
						p = pl.next(p);
					else
						p = null;

				}
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}

		return pF;
	}

	@Override
	public Iterable<Entry<K, V>> entries() {
		PositionList<Entry<K, V>> dIT = new ListaDoblementeEnlazada();
		Position<Entry<K, V>> pos;

		try {
			for (int i = 0; i < arreglo.length; i++) {
				PositionList<Entry<K, V>> pl = arreglo[i];

				if (!pl.isEmpty()) {
					pos = pl.first();

					while (pos != null) {
						dIT.addLast(pos.element());

						if (pos != pl.last())
							pos = pl.next(pos);
						else
							pos = null;
					}
				}
			}
		} catch (EmptyListException | InvalidPositionException | BoundaryViolationException e) {
			e.printStackTrace();
		}

		return dIT;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
}