package Fuente;

import java.awt.EventQueue;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;

public class GUI {
 
	private JFrame frmIngreso;
	private JFrame frmCuenta;
	private JTextField tfMonto;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDNI;
	private JTextField tfCodigo;
	private CuentaBancaria pers;
	private GUICuentaBancaria guiCuentaBancaria;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmIngreso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmIngreso = new JFrame();
		frmIngreso.setResizable(false);
		frmIngreso.setBounds(100, 100, 275, 376);
		frmIngreso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIngreso.getContentPane().setLayout(null);
		
	
		
		
		
		
		
		tfMonto = new JTextField();
		tfMonto.setBounds(10, 274, 130, 20);
		frmIngreso.getContentPane().add(tfMonto);
		tfMonto.setColumns(10);
		
		JLabel lblngrese = new JLabel("Ingrese los siguientes datos:");
		lblngrese.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblngrese.setBounds(10, 11, 191, 20);
		frmIngreso.getContentPane().add(lblngrese);
		
		JLabel lbNombre = new JLabel("Nombre:");
		lbNombre.setBounds(10, 36, 130, 14);
		frmIngreso.getContentPane().add(lbNombre);
		
		JLabel lbApellido = new JLabel("Apellido:");
		lbApellido.setBounds(10, 86, 130, 14);
		frmIngreso.getContentPane().add(lbApellido);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 56, 130, 20);
		frmIngreso.getContentPane().add(tfNombre);
		tfNombre.setColumns(10);
		
		
		JLabel lbDni = new JLabel("DNI:");
		lbDni.setBounds(10, 137, 130, 14);
		frmIngreso.getContentPane().add(lbDni);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(10, 106, 130, 20);
		frmIngreso.getContentPane().add(tfApellido);
		tfApellido.setColumns(10);
		
		
		
		JLabel lbCodigo = new JLabel("Código:");
		lbCodigo.setBounds(10, 193, 130, 14);
		frmIngreso.getContentPane().add(lbCodigo);
		
		
		
		tfDNI = new JTextField();
		tfDNI.setBounds(10, 162, 130, 20);
		frmIngreso.getContentPane().add(tfDNI);
		tfDNI.setColumns(10);
		
		
		JLabel lbSueldo = new JLabel("Monto (Opcional):");
		lbSueldo.setBounds(10, 249, 130, 14);
		frmIngreso.getContentPane().add(lbSueldo);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(10, 218, 130, 20);
		frmIngreso.getContentPane().add(tfCodigo);
		tfCodigo.setColumns(10);
		
		
		JButton btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(88, 305, 89, 23);
		frmIngreso.getContentPane().add(btnAcceder);
	    btnAcceder.setEnabled(true);
	    
	    
	    
	    
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manejarBotonAcceder();
			
				
			}
		});}
	
	    
			
		protected void manejarBotonAcceder() {
		if(permitirAcceso()) {
			 guiCuentaBancaria= new GUICuentaBancaria(pers);
			 frmIngreso.setVisible(false);
			
			
		}
		
		}
		public boolean permitirAcceso() {
			boolean permitir=false;
			boolean acc=false;
			String s = tfMonto.getText();
			String nombr = tfNombre.getText();
			String apell = tfApellido.getText();
			String codig = tfCodigo.getText();
			
			int dni = 0;
            
			if (nombr.length()!=0 && apell.length()!=0  && tfCodigo.getText().length()!=0  ) {
					 float mont=0;
					permitir=true;
				    try {
					     dni =Integer.parseInt(tfDNI.getText());
					    
			        if(s.length()!=0) 
				          mont= Float.parseFloat(tfMonto.getText());
			        
				    } catch(NumberFormatException e ) {
				    	JOptionPane.showMessageDialog(frmIngreso,"Ingresar un numero en DNI y monto");
				    	permitir=false;
						 
						 
					   
					} catch(Exception e) {
					}
			        CuentaBancaria usuario= new CuentaBancaria(nombr, apell, codig, dni, mont);
				    acc=usuario.acceso();
				    if(!acc)
				    	JOptionPane.showMessageDialog(frmIngreso,"Codigo invalido");
				    else pers=usuario;
				    
			}
			        
				
			
		return acc && permitir;
		}
		
}