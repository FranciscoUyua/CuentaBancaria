package Fuente;



import java.util.Comparator;


import java.util.Date;
import java.util.Iterator;

import Entrada.Entry;
import Excepciones.BoundaryViolationException;
import Excepciones.EmptyListException;
import Excepciones.EmptyPriorityQueueException;
import Excepciones.EmptyQueueException;
import Excepciones.EmptyStackException;
import Excepciones.InvalidPositionException;
import TDACola.ColaConArregloCircular;
import TDACola.Queue;
import TDAColaCP.ColaCPconHeap;

import TDAColaCP.PriorityQueue;
import TDADiccionario.DiccionarioHashAbierto;
import TDADiccionario.Dictionary;
import TDALista.ListaDoblementeEnlazada;
import TDALista.Position;
import TDALista.PositionList;
import TDAPila.PilaConEnlaces;
import TDAPila.Stack;
/**
	 * Clase CuentaBancaria
	 * 
	 */

public class CuentaBancaria {
	private String nombre;
	private String apellido;
	private String codigo;
	private int  dni;
	private float saldo;
	private ListaDoblementeEnlazada<Transaccion> transacciones; 
	 /**
	  *Constructor de CuentaBancaria con un monto 
	  *@param n String nombre
	  *@param a String apellido
	  *@param c String codigo 
	  *@param d entero dni
	  *@param m real sueldo
	  */
	
	
	public CuentaBancaria(String n, String a, String c, int d, float m) {
		nombre = n;
		apellido = a;
		codigo = c ;
		dni = d;
		saldo = m;
		transacciones= new ListaDoblementeEnlazada<Transaccion>();
	}
	
	/**
	 *Constructor de CuentaBancaria pero sin necesidad del monto
	 *@param n String nombre,
	 *@param a String apellido, 
	 *@param c String codigo,
	 *@param d entero dni
	 */
	public CuentaBancaria(String n, String a, String c, int d) {
		nombre = n;
		apellido = a;
		codigo = c ;
		dni = d;
		saldo = 0;
	}
	 

	/**
	 *Consulta el nombre de la cuenta
	 *@return nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 *Consulta el apellido de la cuenta
	 *@return apellido del usuario
	 */
	public String getApellido() {
		return apellido;
	}
	/**
	 *Consulta el codigo de acceso de la cuenta
	 *@return codigo del usuario
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 *Consulta el dni de la cuenta
	 *@return dni de la cuenta
	 */
	public int  getDni() {
		return dni;
	}
	/**
	 *Consulta el saldo de la cuenta
	 *@return saldo de cuenta
	 */
	public float getSaldo() {
		return saldo;
	}
	/**
	 *Consulta La lista de transacciones
	 *@return Position List de transacciones
	 */
	public PositionList<Transaccion> getTransaferencias(){
		return transacciones;
	}
	/**
	 *Consulta el saldo de la cuenta
	 *@param saldo real establece el sueldo
	 */
	public void setSaldo(float saldo) {
		this.saldo=saldo;
	}
	/**
	 *Verifica el acceso a la cuenta
	 *@return verdadero si tiene acceso a la cuenta falso si no
	 */
	public boolean acceso() {
		 String apelli= getApellido();
		 String clave=getCodigo();
	     Stack<Character> apell = new PilaConEnlaces<Character>();
		 boolean valido = true;
		 int largCodig= clave.length();
	     int largApell= apelli.length();
	     if(largCodig != (largApell*3 +1))
	    	 valido=false;
		 int i = 0;
		 Queue<Character> n1 =  new ColaConArregloCircular<Character>(largApell);
		 char x;
		 while( i < largApell && valido) {
			 x= clave.charAt(i);
			 if(x==apelli.charAt(i)) {
				 apell.push(x);
			     i++;
		 }	 
			 else valido=false;
			 }
		 if (clave.charAt(i)=='x')
			 i++;
		 else valido=false;
		try {
		 while(!apell.isEmpty() && valido) {
			 x=apell.pop();
			 if(x == clave.charAt(i)) {
				 n1.enqueue(x);
				 i++;
			 }
			 else valido=false;
		 }
		 while(!n1.isEmpty() && valido) {
			 x=n1.dequeue();
			 if(x==clave.charAt(i)) 
				i++;
			 else valido=false;
		 }
		 } catch(EmptyQueueException | EmptyStackException e ) {
			 System.out.println(e.getMessage());
			 }
	
		 return valido;
		 	 
	}
	
	/**
	 *Consulta las transacciones de mismo valor a un n ingresado
	 *@param n real valor de parametro
	 *@return iterable de entradas clave real y valor trasacciones
	 */
	public Iterable<Entry<Float,Transaccion>> mismoValor(Float n){
		Dictionary<Float,Transaccion> diccionario = new DiccionarioHashAbierto<Float,Transaccion>();
		Iterable<Position<Transaccion>> p = transacciones.positions();
		try {
		for(Position<Transaccion> j : p) {
			if(j.element().getMonto() == n)
				diccionario.insert(n,j.element());
		}
		}catch(Excepciones.InvalidKeyException e) {
			System.out.println(e.getMessage());
		}
		Iterable<Entry<Float,Transaccion>> Ipos = diccionario.entries();
		
		return Ipos;
	}
	
	/**
	 *Ejecuta una transfereancia de dinero
	 *@param  monto real monto ingresado
	 *@param d dni del beneficiario
	 *@param benef nombre del beneficiario
	 */
	public void transferir(float monto, int d, String benef) {
		saldo = saldo - monto;
		Date hoy= new Date();
		transacciones.addLast(new Transaccion(hoy, monto, nombre, apellido, d,true,saldo,benef));
		
		
	}

	/**
	 *Ejecuta un deposito en la misma cuenta 
	 *@param monto real
	 *@param benef nombre del beneficiario
	 */
	public void depositar(float monto, String benef) {
		saldo=saldo+monto;
		Date hoy= new Date();
		transacciones.addLast(new Transaccion(hoy, monto, nombre, apellido, dni,false,saldo,benef));
		
	}
	/**
	 *Consulta las transacciones con valor mayor a n
	 *@param n real valor de parametro
	 *@param deocre d si filtra por débito, c por crédito y a por ambos
	 *@return iterable de entradas de clave real y valor transaccion
	 */
	public Iterable<Entry<Float,Transaccion>> mayorValor(Float n, String deocre){
		Dictionary<Float,Transaccion> diccionario = new DiccionarioHashAbierto<Float,Transaccion>();
		Iterable<Position<Transaccion>> p = transacciones.positions();
		try {
		for(Position<Transaccion> j : p) {
            if(deocre.equals("c")&& !j.element().getDebocred() && j.element().getMonto() > n)  
            	diccionario.insert(n,j.element());
            	else  if(deocre.equals("d")&& j.element().getDebocred() && j.element().getMonto() > n)  
                	diccionario.insert(n,j.element());
                	else  if(deocre.equals("a") && j.element().getMonto() > n)  
                    	diccionario.insert(n,j.element());
		}
		}catch(Excepciones.InvalidKeyException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {}	
		Iterable<Entry<Float,Transaccion>> Ipos = diccionario.entries();
		
		return Ipos;
	}
	/**
	 * Consulta las n transacciones con mayor valor
	 * @param num cantidad de transacciones a mostrar
	 * @return iterator de transacciones
	 */
	
	public Iterator<Transaccion> nMayorValor(int num){
		PriorityQueue<Float,Transaccion> cola = new ColaCPconHeap<Float,Transaccion>(new TDAColaCP.Comparator<Float>());
		PositionList<Transaccion> retornar = new ListaDoblementeEnlazada<Transaccion>();
		
		Iterable<Position<Transaccion>> p = transacciones.positions();
		try {
		
			for(Position<Transaccion> j : p) 
					cola.insert(j.element().getMonto(),j.element());
			for(int i=0;i<num;i++) {
				Entry<Float,Transaccion> aux = cola.removeMin();
 				retornar.addLast(aux.getValue());				
				}
			}catch(Excepciones.InvalidKeyException | EmptyPriorityQueueException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {}	
		Iterator<Transaccion> retor= retornar.iterator();
		return retor;
		}
	/**
	 * Consulta las ultimas n transacciones
	 * 
	 * @param num cantidad de transacciones a mostrar
	 * @return Iterator de transacciones
	 */
	public Iterator<Transaccion> ultimasN(int num){
		PositionList<Transaccion> retornar = new ListaDoblementeEnlazada<Transaccion>();
		try {
		Position<Transaccion> tran= transacciones.last();
		while(num != 0) {
			retornar.addFirst(tran.element());
			tran = transacciones.prev(tran);
			num--;
			}
		}catch  (InvalidPositionException | BoundaryViolationException | EmptyListException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {}	
		Iterator<Transaccion> retorna = retornar.iterator();
		return retorna;
	}
	/**
	 * Consulta si la cantidad de transacciones solicitadas es mayor a la realizadas 
	 * @param n cantidad de transacciones solicitadas
	 * @return boolean, true si hay menos transacciones que las solicitadas, false si son suficientes
	 */
	public boolean bUltimasN(int n) {
		int tamanio= transacciones.size(); 
	    return tamanio< n;
	}
	/**
	 * Consulta el saldo de la cuenta en una fecha determinada
	 * 
	 * @param d dia ingresado por el usuario
	 * @param m mes ingresado por el usuario
	 * @param a año ingresado por el usuario
	 * @return float ultimo saldo registrado en tal fecha 
	 */
	
	public float saldoFecha(int d, int m, int a){
		Transaccion trans;
		Iterable<Position<Transaccion>> p = transacciones.positions();
		float sal=0;
		for( Position<Transaccion>x:p) {
			trans=x.element();
			if(trans.mismaFecha(d, m, a))
				sal=trans.getSaldo();
		}
		return sal;
			
	}
	/**
	 * Consulta todas las transacciones realizadas en una fecha ingresda 
	 * @param d dia ingresado por el usuario
	 * @param m mes ingresado por el usuario
	 * @param a año ingresado por el usuario 
	 * @return Iterable posiciones de transacciones
	 */
	public Iterable<Position<Transaccion>> mismaFecha(int d, int m, int a){
		PositionList<Transaccion> retornar = new ListaDoblementeEnlazada<Transaccion>();
		Transaccion trans;
		Iterable<Position<Transaccion>> p = transacciones.positions();
		for( Position<Transaccion>x:p) {
			trans=x.element();
			if(trans.mismaFecha(d, m, a))
				retornar.addLast(trans);
		}
		return retornar.positions();
		}
	
}

	