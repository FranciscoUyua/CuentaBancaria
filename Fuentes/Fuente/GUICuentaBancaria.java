package Fuente;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;

import Entrada.Entry;
import Excepciones.EmptyListException;
import TDAColaCP.PriorityQueue;
import TDADiccionario.Dictionary;
import TDALista.Position;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;

public class GUICuentaBancaria {

	private JFrame frmCuenta;
    private  String name;
    private String surname;
    private float monto;
    boolean credit = false;  
    boolean debit = false;
    int dniBenef;
    private JButton btnCredito;
    private int hour;
    private int minutes;
    private String minutos;
    private String hora;
    private char opcion;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUICuentaBancaria window = new GUICuentaBancaria(null);
					window.frmCuenta.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public GUICuentaBancaria(CuentaBancaria persona) {
		initialize(persona);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(CuentaBancaria per) {
		
		name=per.getNombre();
		surname= per.getApellido();
		frmCuenta = new JFrame();
		frmCuenta.setTitle("Cuenta Bancaria");
		frmCuenta.setResizable(false);
		frmCuenta.setBounds(100, 100, 534, 486);
		frmCuenta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCuenta.getContentPane().setLayout(null);
		frmCuenta.setVisible(true);
		

		JLabel lblUsuario = new JLabel("Usuario:" +name +" "+surname);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsuario.setBounds(10, 11, 212, 14);
		frmCuenta.getContentPane().add(lblUsuario);
		
		JButton btnTransaccion = new JButton("Realizar transacción");
		btnTransaccion.setBounds(10, 145, 166, 23);
		frmCuenta.getContentPane().add(btnTransaccion);
		
		JLabel lblSaldo = new JLabel("Saldo: "+per.getSaldo());
		lblSaldo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSaldo.setBounds(10, 26, 193, 14);
		frmCuenta.getContentPane().add(lblSaldo);
		
		JLabel lblOpcion = new JLabel("Seleccione una opción:");
		lblOpcion.setBounds(10, 95, 166, 14);
		frmCuenta.getContentPane().add(lblOpcion);
		
		JButton btnDebito = new JButton("Débito");
		btnDebito.setBounds(10, 111, 78, 23);
		frmCuenta.getContentPane().add(btnDebito);
		btnDebito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				debit=true;
				btnCredito.setEnabled(false);
				// Pido un número al usuario con un diálogo de entrada:
				String sNumero = JOptionPane.showInputDialog("Ingrese DNI del beneficiario ");
				// Convierto el string en número :
				try{
					dniBenef = Integer.parseInt(sNumero); 		
				}  catch(NumberFormatException t ) {
			    	JOptionPane.showInputDialog("Ingrese un DNI valido");
			    	
				} catch(Exception t) {
				}
			}
			});
		
		
		btnCredito = new JButton("Crédito");
		btnCredito.setBounds(98, 111, 78, 23);
		frmCuenta.getContentPane().add(btnCredito);
		btnCredito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dniBenef= per.getDni();
				credit = true; 			
				btnDebito.setEnabled(false);
			
			}
			});
		JTextField tfMonto = new JTextField();
		tfMonto.setBounds(10, 64, 86, 20);
		frmCuenta.getContentPane().add(tfMonto);
		tfMonto.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(10, 50, 46, 14);
		frmCuenta.getContentPane().add(lblMonto);
		
		JPanel panel = new JPanel();
		panel.setBounds(232, 13, 276, 194);
		frmCuenta.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnUltimasN = new JButton("Ultimas n transacciones");
		btnUltimasN.setBounds(0, 25, 276, 23);
		panel.add(btnUltimasN);
		
		JLabel lblNewLabel = new JLabel("Consultar en el historial:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(0, 0, 276, 23);
		panel.add(lblNewLabel);
		
		JButton btnNMayorValor = new JButton("N transacciones de mayor valor");
		btnNMayorValor.setBounds(0, 53, 276, 23);
		panel.add(btnNMayorValor);
		
		JButton btnMismoValor = new JButton("Transacciones de un mismo valor n");
		btnMismoValor.setBounds(0, 81, 276, 23);
		panel.add(btnMismoValor);
		
		JButton btnMismaFecha = new JButton("Transacciones de una misma fecha");
		btnMismaFecha.setBounds(0, 109, 276, 23);
		panel.add(btnMismaFecha);
		
		JButton btnMayoresAN = new JButton("Transacciones mayores a n");
		btnMayoresAN.setBounds(0, 135, 276, 23);
		panel.add(btnMayoresAN);
		
		JButton btnSaldoFecha = new JButton("Saldo en una determinada fecha");
		btnSaldoFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSaldoFecha.setBounds(0, 161, 276, 23);
		panel.add(btnSaldoFecha);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 498, 206);
		frmCuenta.getContentPane().add(scrollPane);
		
		
		JTextArea textArea = new JTextArea();
		frmCuenta.getContentPane().add(textArea);
		
		textArea.setBounds(10, 230, 498, 206);
		textArea.setVisible(true);
		scrollPane.setViewportView(textArea);
		
		
		

		btnTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date hoy= new Date();
				hour= hoy.getHours();
				if(hour <10)
					hora="0"+hour;
				else hora="hour";
		
				minutes= hoy.getMinutes();
				if(minutes <10)
					minutos="0"+minutes;
				else minutos="minutes";
				String sNomyApe = JOptionPane.showInputDialog("Ingrese nombre y apellido del beneficiario ");	
				
				try {
					monto= Float.parseFloat(tfMonto.getText());
				} catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingresar un monto válido");
			    } catch(Exception t) {
				}
				if(debit) {
					if(monto<per.getSaldo()) {
						btnCredito.setEnabled(true);
						per.transferir(monto, dniBenef,sNomyApe);
						lblSaldo.setText("Saldo ="+per.getSaldo());
						tfMonto.setText(null);
						debit = false;
						try{
						textArea.append(per.getTransaferencias().last().element().toString());
						}catch(EmptyListException f) {};
						
					}
					else {JOptionPane.showMessageDialog(frmCuenta,"Monto insufuciente");
						 tfMonto.setText(null);
				    }
				}
				
				else {
					btnDebito.setEnabled(true);
					per.depositar(monto,sNomyApe);
				    lblSaldo.setText("Saldo ="+per.getSaldo());
				    tfMonto.setText(null);
				    credit = false;
				    try{
						textArea.append(per.getTransaferencias().last().element().toString());
						}catch(EmptyListException f) {};
				}}
		});
		
		btnMismoValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sNumero = JOptionPane.showInputDialog("Ingrese N ");
				try{
					textArea.setText(null);
					float n = Float.parseFloat(sNumero); 	
					Iterable<Entry<Float,Transaccion>> p = per.mismoValor(n);
					for(Entry<Float,Transaccion> x:p) {
	
						textArea.append(x.getValue().toString());
					}
						
						
					
				}  catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}
			}
			});
		
		btnMismaFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try{
					textArea.setText(null);
					String sAnio = JOptionPane.showInputDialog("Ingrese el año ");
					int aniox = Integer.parseInt(sAnio); 
					String sMes = JOptionPane.showInputDialog("Ingrese el mes ");
					int mesx = Integer.parseInt(sMes); 
					String sDia = JOptionPane.showInputDialog("Ingrese el dia ");
					int diax = Integer.parseInt(sDia); 
					Transaccion trans;
					Iterable<Position<Transaccion>> p = per.mismaFecha(diax, mesx, aniox);
					for( Position<Transaccion>x:p) {
						trans=x.element();
						textArea.append(trans.toString());
					}
				}  catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}
			}
			});
		
		btnUltimasN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sN = JOptionPane.showInputDialog("Ingrese un numero: ");
				try{
					textArea.setText(null);
					int n =  Integer.parseInt(sN);
					if (per.bUltimasN(n)) {
						JOptionPane.showMessageDialog(frmCuenta,"No hay suficientes transacciones, se mostrarán las existentes");
						n= per.getTransaferencias().size();
					}
					Iterator<Transaccion> p = per.ultimasN(n);
					while(p.hasNext()) {
						Transaccion aux = p.next();
						textArea.append(aux.toString());					
						}
					
					}  catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}	
				
			}
			});
		
		btnSaldoFecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				try{
					textArea.setText(null);
					String sAnio = JOptionPane.showInputDialog("Ingrese el año ");
					int aniox = Integer.parseInt(sAnio); 
					String sMes = JOptionPane.showInputDialog("Ingrese el mes ");
					int mesx = Integer.parseInt(sMes); 
					String sDia = JOptionPane.showInputDialog("Ingrese el dia ");
					int diax = Integer.parseInt(sDia); 
					textArea.setText("Saldo: "+per.saldoFecha(diax, mesx, aniox));
					}
				 catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}
			}
			});
		btnMayoresAN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sNumero = JOptionPane.showInputDialog("Ingrese N ");
				String sEleccion = JOptionPane.showInputDialog("Ingrese 'd' para filtrar débito, 'c' para crédito, o 'a' para ambas ");
				try{	
				 	textArea.setText(null);
					float n = Float.parseFloat(sNumero); 	
	                    if(!sEleccion.equals("c") && !sEleccion.equals("d") && !sEleccion.equals("a") ) 
	                    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un caracter válido");
	                     
	                    	Iterable<Entry<Float,Transaccion>> p= per.mayorValor(n, sEleccion);
	                    	for(Entry<Float,Transaccion> x:p) {
	    						textArea.append(x.getValue().toString());
	    					}
	    		}  catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}
			}
			});
		

		btnNMayorValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sNumero = JOptionPane.showInputDialog("Ingrese N ");
				try{
					textArea.setText(null);
					int n = Integer.parseInt(sNumero); 
					if (per.bUltimasN(n)) {
						JOptionPane.showMessageDialog(frmCuenta,"No hay suficientes transacciones, se mostrarán las existentes");
						n= per.getTransaferencias().size();
					}
					Iterator<Transaccion> p = per.nMayorValor(n);
				
					while(p.hasNext()) {
						Transaccion aux = p.next();
						textArea.append(aux.toString());					
						}
						
				}  catch(NumberFormatException t ) {
			    	JOptionPane.showMessageDialog(frmCuenta,"Ingrese un numero valido");
				}catch(Exception t) {}
			}
			});
	}
	
}

