package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle; 
import businessLogic.BLFacade;
import java.awt.Font;
import domain.Buyer;

public class EditProfileGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

	public EditProfileGUI(Buyer buyer) {
		
		// Cambiar todos los textos por dinámicos 
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register.Title"));
        setBounds(100, 100, 350, 300);
        getContentPane().setLayout(null);
        
        JLabel lblName = new JLabel("Nuevo nombre:");
        lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblName.setBounds(30, 30, 80, 25);
        getContentPane().add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(120, 30, 180, 25);
        getContentPane().add(txtName);
        
        JLabel lblEmail = new JLabel("Nuevo email:");
        lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblEmail.setBounds(30, 70, 80, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 70, 180, 25);
        getContentPane().add(txtEmail);
        
        JLabel lblPass = new JLabel("Nuevo Pass:");
        lblPass.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblPass.setBounds(30, 110, 80, 25);
        getContentPane().add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 110, 180, 25);
        getContentPane().add(txtPassword);
        
        JButton btnConfirmar = new JButton("Aceptar");
        btnConfirmar.setBounds(30, 184, 117, 29);
        getContentPane().add(btnConfirmar);
        
        JButton btnCancelar = new JButton("Cancelar"); 
        btnCancelar.setBounds(183, 184, 117, 29);
        getContentPane().add(btnCancelar);
        
	}
}
