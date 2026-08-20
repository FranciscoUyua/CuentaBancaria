package TDAColaCP;
/**
 * Clase Comparator que implementa la clase Comparator de java util
 * @param E tipo de dato abstracto
 * 
 */
public class Comparator<E> implements java.util.Comparator<E> {

	/**
	 * Compara o1 con o2 
	 * @param o1 primer elemto a comparar
	 * @param 02 segundo elemento a comparar
	 * @return int el inverso del resultado de comparar o1 con o2
	 * 
	 */
	public int compare(E o1, E o2) {
		
		return  -1 *((Comparable<E>) o1).compareTo(o2);
	}
	
	

}
