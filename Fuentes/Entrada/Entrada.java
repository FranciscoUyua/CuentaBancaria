package Entrada;

public class Entrada<K, V> implements Entry<K, V> {
	private K key;
	private V value;
	
	public Entrada(K clave, V valor) {
		key = clave;
		value = valor;
	}
	
	public void setKey(K clave) {
		key = clave;
	}
	
	public void setValue(V valor) {
		value = valor;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}
	
	public String toString() {
		return "("+getKey()+","+getValue()+")";
	}
}
