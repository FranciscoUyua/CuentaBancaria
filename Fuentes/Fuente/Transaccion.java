package Fuente;

import java.util.Date;
/**
 * Clase CuentaBancaria
 * 
 */
public class Transaccion {

	private Date fecha;
	private float monto;
	private String nombre;
	private String apellido;
	private int dni;
	private float sueldo;
	private boolean debocred;
	private int hour;
	private int minutes;
	private String hora;
	private String minutos;
	private String beneficiario;
	/**
	 * Clase Transaccion
	 * 
	 * @param f Date fecha
	 * @param m real monto
	 * @param nom String nombre
	 * @param a String apellido
	 * @param d entero dni
	 *  @param doc boolean para saber si es debito o credito, 
	 *  @param s float sueldo 
	 *  @param bene String nombre y apellido del beneficiario
	 */
	public Transaccion(Date f, float m,String nom,String a, int d, boolean doc, float s, String bene) {
		fecha = f;
		monto = m;
		nombre = nom;
		apellido = a;
		dni = d;
		debocred=doc;
		sueldo=s;
		beneficiario = bene;
	}
	
	/**
	 *  Comprueba que la fecha es la misma
	 *  @param dia entero 
	 *  @param mes entero
	 *  @param anio entero
	 */
	
	public boolean mismaFecha(int dia,int mes , int anio) {
		return (fecha.getDate()) == dia && (fecha.getMonth()+1) == mes && (fecha.getYear() +1900) == anio;
		
	}
	
	public void setBeneficiario(String b) {
		beneficiario = b;
	}
	/**
	 * setea el monto 
	 * @param n monto seteado
	 */

	public void setMonto(float n) {
		monto = n;
	}
	/**
	 * Setea la fecha 
	 * @param f fecha seteada
	 */
	 public void setFecha(Date f) {
		 fecha = f;
	 }
	 /**
	 * Setea nombre
	 * @param n nombre seteado
	 */
	 public void setNombre(String n) {
		 nombre = n;
	 }
	 /**
		 * Setea  apellido
		 * @param a apellido seteado
		 */
	 public void setApellido(String a) {
		 apellido = a;
	 }
	 /**
	 * Setea  dni
	 * @param d dni seteado
	 */
	 public void setDni(int d) {
		 dni = d;
	 }
	
	 /**
		 *Consulta la fecha de la transaccion
		 *@return fecha de la transaccion
		 */
	public Date getFecha() {
		return fecha;
	}
	 /**
	 *Consulta el monto de la transaccion
	 *@return monto que tuvo la transaccion
	 */
	public float getMonto() {
		return monto;
	}
	 /**
	 *Consulta el nombre de la transaccion
	 *@return nombre de la transaccion
	 */
	public String getNombre() {
		return nombre;
	}
	 /**
	 *Consulta el apellido de la transaccion
	 *@return apellido de la transaccion
	 */
	public String getApellido() {
		return apellido;
	}
	 /**
	 *Consulta el dni de la transaccion
	 *@return dni de la transaccion
	 */
	public float getDni() {
		return dni;
	}
	 /**
	 *Consulta el sueldo que tuvo esa transaccion
	 *@return sueldo de la trasaccion
	 */
	public float getSaldo() {
		return sueldo;
	}
	 /**
	 *Consulta el tipo de la transaccion
	 *@return debocred true si es debito false si es credito
	 */
	public boolean getDebocred() {
		return debocred;
	}
	
	 /**
	 * 
	 * Escribe la transaccion 	
	 */
	
	public String toString() {
		String retornar;
		hour= fecha.getHours();
		if(hour <10)
			hora="0"+hour;
		else hora=hour+"";

		minutes= fecha.getMinutes();
		if(minutes <10)
			minutos="0"+minutes;
		else minutos=minutes+"";
		if(debocred) 
			retornar = " -$"+monto+" "+fecha.getDate()+"/"+( fecha.getMonth()+1)+"/"+(fecha.getYear()+1900)+" "+hora+":"+minutos +" Beneficiario: "+beneficiario+" "+" DNI: "+dni+" DEBITO"+"\n";
		else retornar = " +$"+monto+" "+fecha.getDate()+"/"+( fecha.getMonth()+1)+"/"+(fecha.getYear()+1900)+" "+hora+":"+minutos  +" Beneficiario: "+beneficiario+" DNI: "+dni+" CREDITO"+"\n";
		return retornar;
		
			
	}
}